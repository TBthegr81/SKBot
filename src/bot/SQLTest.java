package bot;

import java.util.ArrayList;

public class SQLTest {

	public static void main(String[] args) {
		CLib.print("Starting tests");
		boolean svar = CLib.choiceyn("Dont use port 3306?");
		if(svar == false)
		SQLQuerries.setPort(Integer.parseInt(CLib.input("Port:")));
		/*
		//Try to add links with tags
		try {
			ArrayList<String> tags = new ArrayList<String>();
			tags.add("Fun");
			tags.add("Cool");
			tags.add("Poop");
			tags.add("Möök");
			SQLQuerries.addLink("http://hotmail.com", 2, tags);
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		try {
			ArrayList<String> tags = new ArrayList<String>();
			tags.add("poop");
			SQLQuerries.addLink("http://bajs.com", 2, tags);
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		//Get a random link with the tag Poop
		try {
			CLib.print(SQLQuerries.getRandomLink("Poop", 2));
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		//Try to get all the tags for a link
		ArrayList<String> Svar = null;
		try {
			Svar = SQLQuerries.getTags("http://hotmail.com");
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		for(int i = 0; i  < Svar.size(); i++)
		{
			System.out.println("Tagg #"+i+ " " + Svar.get(i));
		}
		
		//Try to create a user
		try {
			SQLQuerries.addUser("TBthegr81", "lol123");
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		//Try to login
		try {
			System.out.println("User_ID:" + SQLQuerries.checkUserLogin("TBthegr81", "lol123"));
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		// Try to add a nick to the Nick-History
		try {
			SQLQuerries.addUserNickHistory(4, "TBtheAwesomeOne");
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		// Try to add a room
		try {
			SQLQuerries.addRoom("irc.oftc.net", "Snekabel");
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		// Try to add a Topic to the Topic-History
		try {
			SQLQuerries.addRoomTopicHistory("irc.oftc.net","Snekabel", "Trollolol - Snekabel edition");
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		try {
			SQLQuerries.addLog("TBthegr81", "!bored");
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}
		
		//Try to delete user
		try {
			SQLQuerries.deleteUser(1);
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}*/
		
		//Try go get TopTags
		ArrayList<String> tagCount = null;
		try {
			tagCount = SQLQuerries.tagCount();
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant get TopTags! " + e.getLocalizedMessage());
		}
		for(int i = 0; i < tagCount.size(); i++)
		{
				System.out.println("Tag #"+i+" "+tagCount.get(i));
		}
		
		//Try to delete latest link
		/*try {
			SQLQuerries.delLink(new String[0]);
		} catch (SQLFuckupExeption e) {
			CLib.print("SQLFuckup! " + e);
		}*/
		
		CLib.print("Test ended");
	}

}
