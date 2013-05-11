package bot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class SQLQuerries {
	private static ResultSet rs = null;
	private static PreparedStatement pst = null;
	private static MysqlDataSource ds = new MysqlDataSource();
	private static Connection con = null;
	private static Statement statement;
	private static int sqlport = 3306;

	
	public static boolean connectToDB()
	{
		
		ds.setServerName("127.0.0.1");
		ds.setPort(sqlport);
		ds.setUser("ircBot");
		ds.setPassword("morn");
		ds.setDatabaseName("Bot");
		
		
		try {
			con = ds.getConnection();
			//System.out.println("Connected to MySQL Server");
		} catch (SQLException e) {
			System.out.println("Cant connect to DB! " + e.getLocalizedMessage());
			return false;
		}
		return true;
	}
	
	public static boolean closeDB()
	{
		try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException e)
        {
        	System.out.println("Error closing connections! " + e.getLocalizedMessage());
        	return false;
        }
		return true;
	}
	
	public static void setPort(int port)
	{
		if(port != 0)
		sqlport = port;
	}
	
	public static void addLink(String link, int type, ArrayList<String> Tags) throws SQLFuckupExeption
	{
		connectToDB();
		/*
		 * Add the person to the person table
		 */
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			statement.execute("INSERT INTO Link(Link,Type,LinkHash) VALUES('" + link + "'," + type + ", '" + Lib.md5(link)+ "')");
			statement.execute("SELECT @A:=Link_ID FROM Link ORDER BY Link_ID DESC LIMIT 1;");
			for(int i = 0; i < Tags.size(); i++)
			{
				CLib.print("Tag:" + Tags.get(i));
				statement.execute("INSERT IGNORE INTO Tag(Name) VALUES('"+ Tags.get(i)+"')");
				statement.execute("SELECT @B:=Tag_ID FROM Tag WHERE Name = '"+ Tags.get(i)+"'");
				statement.execute("INSERT INTO LinkTag(Link_ID,Tag_ID) VALUES(@A,@B);");
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	
	public static String getRandomLink(String Tag, int type)  throws SQLFuckupExeption
	{
		String Link = null;
		connectToDB();
		try {
			if(Tag != "")
			{
				pst = con.prepareStatement("SELECT Link " +
						"FROM Link " +
						"JOIN LinkTag on Link.Link_ID=LinkTag.Link_ID " +
						"JOIN Tag ON LinkTag.Tag_ID=Tag.Tag_ID " +
						"WHERE UPPER(Tag.Name) = UPPER('"+ Tag +"') "+
						"AND Type =" + type + " " +
						"ORDER BY RAND() " +
						"LIMIT 1");
			}
			else
			{
				pst = con.prepareStatement("SELECT Link " +
						"FROM Link " +
						"WHERE Type =" + type + " " +
						"ORDER BY RAND() " +
						"LIMIT 1");
			}
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		int i = 1;
		try {
			rs = pst.executeQuery();
			while (rs.next())
			{
				Link = rs.getString(1);
	        }
        	
		} catch (SQLException e) {
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		}
		
		finally {
			closeDB();
		}
		return Link;
	}
	
	public static ArrayList<String> getTags(String link) throws SQLFuckupExeption
	{
		ArrayList<String> Tags = new ArrayList<String>();
		connectToDB();
		try {
			pst = con.prepareStatement("SELECT " +
					"Link.Link, " +
					"Tag.name " +
					"FROM Link " +
					"JOIN LinkTag ON Link.Link_ID=LinkTag.Link_ID " +
					"JOIN Tag ON LinkTag.Tag_ID=Tag.Tag_ID " +
					"WHERE Link.Link = '" + link+ "'");
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		int i = 1;
		try {
			rs = pst.executeQuery();
			while (rs.next())
			{
				Tags.add(rs.getString(1) + " | "+ rs.getString(2));
				i++;
	        }
        	
		} catch (SQLException e) {
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		}
		
		finally {
			closeDB();
		}
		return Tags;
	}
	
	public static void addUser(String username, String password) throws SQLFuckupExeption
	{
		String MD5password = Lib.md5(password);
		
		connectToDB();
		/*
		 * Add the person to the person table
		 */
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			statement.execute("INSERT INTO User(Nick, Password) VALUES('"+ username +"','"+ MD5password +"')");
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	
	public static int checkUserLogin(String username, String password) throws SQLFuckupExeption
	{
		String MD5password = Lib.md5(password);
		int svar = 0;
		connectToDB();
		try {
			pst = con.prepareStatement("SELECT User_ID FROM User WHERE Nick = '"+ username +"' AND Password = '"+ MD5password +"'");
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		try {
			rs = pst.executeQuery();
			while (rs.next())
			{
				svar = rs.getInt(1);
	        }
        	
		} catch (SQLException e) {
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		}
		
		finally {
			closeDB();
		}
		return svar;
	}
	
	public static void deleteUser(int UserID) throws SQLFuckupExeption
	{
		connectToDB();
		/*
		 * Add the person to the person table
		 */
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			statement.execute("DELETE FROM User WHERE User_ID = "+UserID);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	
	public static void addUserNickHistory(int UserID, String newNick) throws SQLFuckupExeption
	{
		connectToDB();
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			statement.execute("INSERT INTO UserNickHistory(Nick, User) VALUES ('"+ newNick +"',"+UserID+")");
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	
	public static void addRoom(String servername, String roomName)  throws SQLFuckupExeption
	{
		connectToDB();
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			statement.execute("INSERT INTO Room(ServerName,RoomName,Hash) VALUES('"+servername+"','"+roomName+"','"+Lib.md5(servername+roomName)+"')");
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	
	public static void addRoomTopicHistory(String server, String roomName, String newRoomTopic) throws SQLFuckupExeption
	{
		connectToDB();
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			statement.execute("SELECT @A:=Room_ID FROM Room WHERE RoomName = '"+roomName+"' AND ServerName = '"+server+"'");
			statement.execute("INSERT INTO RoomTopicHistory(Topic, Room_ID) VALUES('"+newRoomTopic+"',@A)");
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	
	public static void addLog(String nickname, String command) throws SQLFuckupExeption
	{
		connectToDB();
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			statement.execute("SELECT @A:=User_ID FROM User WHERE Nick = '"+nickname+"'");
			statement.execute("INSERT INTO Log(Command,User) VALUES('"+command+"',@A)");
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	public static void delLink(String[] Input) throws SQLFuckupExeption
	{
		connectToDB();
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not Querry! " + e.getLocalizedMessage());
		}
		
		try {
			con.setAutoCommit(false);
			int linkid = 0;
			if(Lib.isNumeric(Input[1]))		
			{
				linkid = Integer.parseInt(Input[1]);
				statement.execute("DELETE FROM Link WHERE Link_ID = "+linkid);
			}
			else
			{
				statement.execute("SELECT @A:=Link_ID FROM Link WHERE Link = \'" + Input[1] + "'");
				statement.execute("DELETE FROM Link WHERE Link_ID = @A");
			}
			
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Could not Rollback" + e1.getLocalizedMessage());
			}
			System.out.println("Coult not Execute Query! " + e.getLocalizedMessage());
		} finally {
			closeDB();
		}
	}
	
	/* Inte speciellt viktiga nu
	public static void addEmail(String Email) throws SQLFuckupExeption
	{
		
	}
	
	public static String getRandomEmail() throws SQLFuckupExeption
	{
		return new String();
	}
	
	public static void addTelephoneNumber(String number) throws SQLFuckupExeption
	{
		
	}
	
	public static String getRandomNumber() throws SQLFuckupExeption
	{
		return new String();
	}
	*/
}
