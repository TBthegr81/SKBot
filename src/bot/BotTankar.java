package bot;

import java.util.ArrayList;
import java.util.Random;

public class BotTankar {
	private static boolean topicGeneration = false;
	private static boolean addLink = false;
	private static boolean spotify = false;
	public static void evaluate(String input)
	{
		char Char = '!';
		// Start by splitting the input string by whitespaces
		String[] Input = input.split("\\s+");
		
		// Basic stuff
		if(Input[0].equals("PING"))
		{
			SKICKATILLBAKA("PONG");
		}
		
		// Commands for user to use
		else if(Input[0].equals(Char + "Bored"))
		{
			Bored(Input);
		}
		else if(Input[0].equals(Char + "Spotify"))
		{
			Spotify(Input);
		}
		else if(Input[0].equals(Char + "Topicgenerator"))
		{
			TopicGen(Input);
		}
		else if(Input[0].contains("Day") && Input[1].contains("changed"))
		{
			GenerateTopic();
		}
		
		else if(Input[0].equals(Char + "Coin"))
		{
			Coin();
		}
		else if(Input[0].equals(Char + "Dice"))
		{
			Dice();
		}
		else if(Input[0].equals(Char + "Login"))
		{
			Login(Input);
		}
		else if(Input[0].equals(Char + "Logout"))
		{
			Logout(Input);
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
	
	public static void Bored(String[] Input)
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
	
	public static void Spotify(String[] Input)
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
	
	public static void TopicGen(String[] Input)
	{
		if(Input[1].equals("on"))
		{
			//Sätt på skiten
			topicGeneration = true;
		}
		else if(Input[1].equals("off"))
		{
			topicGeneration = false;
		}
	}
	
	public static void GenerateTopic()
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
	
	public static void Coin()
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
	
	public static void Dice()
	{
		int random = new Random().nextInt(6);
		SKICKATILLBAKA("Dice says " + (random+1));
	}
	
	public static void Login(String[] Input)
	{
		@SuppressWarnings("unused")
		String username = Input[1];
		@SuppressWarnings("unused")
		String password = Lib.md5(Input[2]);
		
		//Lib.login(username, password);
	}
	
	public static void Logout(String[] Input)
	{
		//Lib.logout(username, password);
	}
}
