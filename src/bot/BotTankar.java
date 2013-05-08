package bot;

import java.util.ArrayList;
import java.util.Random;

public class BotTankar {
	private static boolean topicGeneration = false;
	private static boolean addLink = false;
	private static boolean spotify = false;
	private static char Char = '!';
	public static void evaluate(String input)
	{
		// Start by splitting the input string by whitespaces
		String[] Input = input.split("\\s+");
		
		// Basic stuff
		if(Input[0].equalsIgnoreCase("PING"))
		{
			SKICKATILLBAKA("PONG");
		}
		
		// Commands for user to use
		else if(Input[0].equalsIgnoreCase(Char + "Bored"))
		{
			bored(Input);
		}
		else if(Input[0].equalsIgnoreCase(Char + "Spotify"))
		{
			spotify(Input);
		}
		else if(Input[0].equalsIgnoreCase(Char + "Topicgenerator"))
		{
			topicGen(Input);
		}
		else if(Input[0].contains("Day") && Input[1].contains("changed"))
		{
			generateTopic();
		}
		
		else if(Input[0].equalsIgnoreCase(Char + "Coin"))
		{
			coin();
		}
		else if(Input[0].equalsIgnoreCase(Char + "Dice"))
		{
			dice();
		}
		else if(Input[0].equalsIgnoreCase(Char + "Login"))
		{
			login(Input);
		}
		else if(Input[0].equalsIgnoreCase(Char + "Logout"))
		{
			logout(Input);
		}
		else if(Input[0].equalsIgnoreCase(Char + "Help"))
		{
			help(Input);
		}
		
		//Special
		//Looking thru  all the words in a post
		else
		{	
			ArrayList<String> tags = new ArrayList<String>();
			String url = "";
			for(int i = 0; i < Input.length; i++)
			{
				//And here you can look for specefic words! :D
				if(Input[i].contains("http://") || Input[i].contains("https://"))
				{
					if(Input[i].contains("open.spotify.com"))
					{
						spotify = true;
					}
					url = Input[i];
					addLink = true;
				}
				else if(Input[i].contains("#"))
				{
					tags.add(Input[i]+"");
				}
			}
			if(addLink)
			{
				int type = 2;
				if(spotify)
				{
					type = 1;
				}
				try {
					SQLQuerries.addLink(url, type, tags);
				} catch (SQLFuckupExeption e) {
					CLib.print("Cant add new link! " + e.getLocalizedMessage());
				}
			}
		}
	}
	
	public static void SKICKATILLBAKA(String text)
	{
		System.out.println(text);
	}
	
	public static void bored(String[] Input)
	{
		String tag = "";
		if(Input.length > 1)
		{
			tag = Input[1];
			System.out.println("TAG:" + tag);
		}
		try {
			String svar = SQLQuerries.getRandomLink(tag, 2);
			if(svar != null)
			SKICKATILLBAKA(svar);
			else
			{
				SKICKATILLBAKA("No link found");
			}
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant get Random Link!" + e.getLocalizedMessage());
			SKICKATILLBAKA("Cant get Link!");
		}
	}
	
	public static void spotify(String[] Input)
	{
		String tag = "";
		if(Input.length > 1)
		{
			tag = Input[1];
			System.out.println("TAG:" + tag);
		}
		try {
			String svar = SQLQuerries.getRandomLink(tag, 1);
			if(svar != null)
			SKICKATILLBAKA(svar);
			else
			{
				SKICKATILLBAKA("No link found");
			}
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant get Random Spotify! " + e.getLocalizedMessage());
		}
	}
	
	public static void topicGen(String[] Input)
	{
		if(Input[1].equalsIgnoreCase("on"))
		{
			//Sätt på skiten
			topicGeneration = true;
		}
		else if(Input[1].equalsIgnoreCase("off"))
		{
			topicGeneration = false;
		}
	}
	
	public static void generateTopic()
	{
		//Day changed to 06 May 2013
		if(topicGeneration)
		{
			String topic = "troffelma0 - Snekabel Edition";
			SKICKATILLBAKA("/topic #Snekabel " + topic);
			try {
				SQLQuerries.addRoomTopicHistory("oftc.net", "#Snekabel", topic);
			} catch (SQLFuckupExeption e) {
				CLib.print("Cant add Room Topic! " + e.getLocalizedMessage());
			}
		}
	}
	
	public static void coin()
	{
		int random = new Random().nextInt(2);
		if(random == 1)
		{
			SKICKATILLBAKA("Coin Toss says Heads");
		}
		else
		{
			SKICKATILLBAKA("Coin Toss says Tails");
		}
	}
	
	public static void dice()
	{
		int random = new Random().nextInt(6);
		SKICKATILLBAKA("Dice says " + (random+1));
	}
	
	public static void login(String[] Input)
	{
		@SuppressWarnings("unused")
		String username = Input[1];
		@SuppressWarnings("unused")
		String password = Lib.md5(Input[2]);
		
		//Lib.login(username, password);
	}
	
	public static void logout(String[] Input)
	{
		//Lib.logout(username, password);
	}
	
	public static void help(String[] Input)
	{
		if(Input.length > 1)
		{
			if(Input[1].equalsIgnoreCase("bored") || Input[1].equalsIgnoreCase(Char+"bored"))
			{
				SKICKATILLBAKA(Char+"Bored [tag] gets and posts a random link from the database of previously posted and tagged links."); 
				SKICKATILLBAKA("If no tag is givven a totaly random link is taken. VARNING, May contain NSFW links.");
			}
			else if(Input[1].equalsIgnoreCase("spotify") || Input[1].equalsIgnoreCase(Char+"spotify"))
			{
				SKICKATILLBAKA(Char+"Spotify [tag] gets and posts a random link to a song/album on Spotify from the database of previously posted and tagged links.");
				SKICKATILLBAKA("If no tag is givven, a totaly random link is taken.");
			}
			else if(Input[1].equalsIgnoreCase("coin") || Input[1].equalsIgnoreCase(Char+"coin"))
			{
				SKICKATILLBAKA(Char+"Coin flips a virtual coin and returns if it was heads or tails");
			}
			else if(Input[1].equalsIgnoreCase("dice") || Input[1].equalsIgnoreCase(Char+"dice"))
			{
				SKICKATILLBAKA(Char+"Dice rolls a virtual 6-sided dice and returns the result");
			}
		}
		else
		{
			SKICKATILLBAKA("This is the Snekabel Bot. I can help with a few things in the chat, here is a list of what i can do.");
			SKICKATILLBAKA(Char+"Bored [tag], "+Char+"Spotify [tag], "+Char+"Coin, "+Char+"Dice");
			SKICKATILLBAKA("Write "+Char+"Help [command] to get detailed descriptions about the use of the commands");
		}
	}
}
