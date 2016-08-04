package botMk3;


import java.sql.*;

/**
 * Created by TB on 2016-08-03.
 */
public class Sqlite {

    public Sqlite() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SKBot.db");
            System.out.println("Opened database successfully");

            try {
                stmt = c.createStatement();
                String sql = "BEGIN; " +
                        "CREATE TABLE Link " +
                        "(id INTEGER PRIMARY KEY," +
                        " url TEXT  NOT NULL UNIQUE, " +
                        " domain TEXT NOT NULL); " +
                        "CREATE INDEX domain_index ON Link (domain); " +
                        "COMMIT;";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();

                System.out.println("Created tables");
            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                //System.exit(0);
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static void insertLink(String url, String domain)
    {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SKBot.db");
            System.out.println("Opened database successfully");

            try {
                String sql = "INSERT INTO Link (rowid, url, domain) " +
                        "VALUES (null, ?,?);";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, url);
                prep.setString(2, domain);
                prep.addBatch();
                c.setAutoCommit(false);
                prep.executeBatch();
                c.setAutoCommit(true);

                c.close();

                System.out.println("Insert Successfull");
            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static String getRandomLink()
    {
        Connection c = null;
        Statement stmt = null;
        String  url = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SKBot.db");
            //System.out.println("Opened database successfully");

            try {
                stmt = c.createStatement();
                String sql = "SELECT * " +
                        "FROM Link " +
                        "ORDER BY RANDOM() " +
                        "LIMIT 1";
                ResultSet rs =  stmt.executeQuery(sql);

                while ( rs.next() ) {
                    int id = rs.getInt("id");
                    url = rs.getString("url");
                    String domain = rs.getString("domain");

                    /*System.out.println( "ID: " + id );
                    System.out.println( "URL: " + url );
                    System.out.println( "DOMAIN: " + domain );
                    System.out.println();*/
                }

                rs.close();
                stmt.close();
                c.close();
            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        //System.out.println("Operation done successfully");
        return url;
    }
}
