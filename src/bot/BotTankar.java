package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;

public class BotTankar {
	private static boolean topicGeneration = false;
	private static boolean addLink = false;
	private static boolean spotify = false;
	private static char Char = '!';
	private static ArrayList<String> disabledCommands= new ArrayList<String>();
	private static ArrayList<String> ignoredUsers= new ArrayList<String>();
	
	public static void evaluate(IRCProtocol p, String input)
	{
		/*for(int i = 0; i < disabledCommands.size(); i++)
		{
			System.out.println(disabledCommands.get(i));
		}*/
		
		// Start by splitting the input string by whitespaces
		String[] Input = input.split("\\s+");
		
		
		// Commands for user to use
		if(!disabledCommands.contains(Input[0].toUpperCase()))
		{
			if(Input[0].equalsIgnoreCase(Char + "Bored"))
			{
				bored(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Spotify"))
			{
				spotify(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Topicgenerator"))
			{
				topicGen(p,Input);
			}
			
			else if(Input[0].equalsIgnoreCase(Char + "Coin"))
			{
				coin(p);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Dice"))
			{
				dice(p);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Login"))
			{
				login(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Logout"))
			{
				logout(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Help"))
			{
				help(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Rmlink"))
			{
				delLink(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Proxxi"))
			{
				proxxi(p);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Disable"))
			{
				disableLink(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "LinkInfo"))
			{
				linkInfo(p,Input);
			}
		
		//Special
		//Looking thru  all the words in a post
		else
		{	
			addLink = false;
			//System.out.println("Now we are at ELSE");
			ArrayList<String> tags = new ArrayList<String>();
			String url = "";
			for(int i = 0; i < Input.length; i++)
			{
				//And here you can look for specefic words! :D
				if(Input[i].contains("http://") || Input[i].contains("https://"))
				{
					System.out.println("Found link!");
					if(Input[i].contains("open.spotify.com"))
					{
						spotify = true;
					}
					url = Input[i];
					addLink = true;
					if(!disabledCommands.contains("readlink"));
					{
						try {
							p.sendDataToChannel(getHtmlTag("title", getHtmlCode(Input[i])));
						} catch (IOException e) {
							System.out.println("Cant send data" + e.getLocalizedMessage());
						}
					}
				}
				else if(Input[i].contains("#"))
				{
					tags.add(Input[i]+"");
				}
			}
			if(addLink && !disabledCommands.contains("addlink"))
			{
				//System.out.println("Adding link");
				int type = 2;
				if(spotify)
				{
					type = 1;
				}
				try {
					if(SQLQuerries.addLink(url, type, tags))
					{
						try {
							p.sendDataToChannel("Adding Link");
						} catch (IOException e1) {
							System.out.println("Could not send data to server " + e1.getLocalizedMessage());
						}
					}
				} catch (SQLFuckupExeption e) {
					CLib.print("Cant add new link! " + e.getLocalizedMessage());
				}
			}
		}
		}
	}
	
	public static void bored(IRCProtocol p,String[] Input)
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
			p.sendDataToChannel(svar);
			else
			{
				try {
					p.sendDataToChannel("No link found");
				} catch (IOException e) {
					System.out.println("Could not send data to server " + e.getLocalizedMessage());
				}
			}
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant get Random Link!" + e.getLocalizedMessage());
			try {
				p.sendDataToChannel("Cant get Link!");
			} catch (IOException e1) {
				System.out.println("Could not send data to server " + e1.getLocalizedMessage());
			}
		} catch (IOException e) {
			System.out.println("Could not send data to server " + e.getLocalizedMessage());
		}
	}
	
	public static void spotify(IRCProtocol p,String[] Input)
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
				try {
					p.sendDataToChannel(svar);
				} catch (IOException e1) {
					System.out.println("Could not send data to server " + e1.getLocalizedMessage());
				}
			else
			{
				try {
					p.sendDataToChannel("No link found");
				} catch (IOException e1) {
					System.out.println("Could not send data to server " + e1.getLocalizedMessage());
				}
			}
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant get Random Spotify! " + e.getLocalizedMessage());
		}
	}
	
	public static void topicGen(IRCProtocol p,String[] Input)
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
	
	public static void generateTopic(IRCProtocol p)
	{
		//Day changed to 06 May 2013
		if(topicGeneration)
		{
			String topic = "troffelma0 - Snekabel Edition";
			try {
				p.sendDataToChannel("/topic #Snekabel " + topic);
			} catch (IOException e1) {
				System.out.println("Could not send data to server " + e1.getLocalizedMessage());
			}
			try {
				SQLQuerries.addRoomTopicHistory("oftc.net", "#Snekabel", topic);
			} catch (SQLFuckupExeption e) {
				CLib.print("Cant add Room Topic! " + e.getLocalizedMessage());
			}
		}
	}
	
	public static void coin(IRCProtocol p)
	{
		int random = new Random().nextInt(2);
		if(random == 1)
		{
			try {
				p.sendDataToChannel("Coin Toss says Heads");
			} catch (IOException e1) {
				System.out.println("Could not send data to server " + e1.getLocalizedMessage());
			}
		}
		else
		{
			try {
				p.sendDataToChannel("Coin Toss says Tails");
			} catch (IOException e1) {
				System.out.println("Could not send data to server " + e1.getLocalizedMessage());
			}
		}
	}
	
	public static void dice(IRCProtocol p)
	{
		int random = new Random().nextInt(6);
		try {
			p.sendDataToChannel("Dice says " + (random+1));
		} catch (IOException e1) {
			System.out.println("Could not send data to server " + e1.getLocalizedMessage());
		}
	}
	
	public static void login(IRCProtocol p,String[] Input)
	{
		@SuppressWarnings("unused")
		String username = Input[1];
		@SuppressWarnings("unused")
		String password = Lib.md5(Input[2]);
		
		//Lib.login(username, password);
	}
	
	public static void logout(IRCProtocol p,String[] Input)
	{
		//Lib.logout(username, password);
	}
	
	public static void help(IRCProtocol p,String[] Input)
	{
		try {
			if(Input.length > 1)
			{
				if(Input[1].equalsIgnoreCase("bored") || Input[1].equalsIgnoreCase(Char+"bored"))
				{
					
					p.sendDataToChannel(Char+"Bored [tag] gets and posts a random link from the database of previously posted and tagged links.");
					p.sendDataToChannel("If no tag is givven a totaly random link is taken. VARNING, May contain NSFW links.");
				}
				else if(Input[1].equalsIgnoreCase("spotify") || Input[1].equalsIgnoreCase(Char+"spotify"))
				{
					p.sendDataToChannel(Char+"Spotify [tag] gets and posts a random link to a song/album on Spotify from the database of previously posted and tagged links.");
					p.sendDataToChannel("If no tag is givven, a totaly random link is taken.");
				}
				else if(Input[1].equalsIgnoreCase("coin") || Input[1].equalsIgnoreCase(Char+"coin"))
				{
					p.sendDataToChannel(Char+"Coin flips a virtual coin and returns if it was heads or tails");
				}
				else if(Input[1].equalsIgnoreCase("dice") || Input[1].equalsIgnoreCase(Char+"dice"))
				{
					p.sendDataToChannel(Char+"Dice rolls a virtual 6-sided dice and returns the result");
				}
				else if(Input[1].equalsIgnoreCase("rmlink") || Input[1].equalsIgnoreCase(Char+"rmlink"))
				{
					p.sendDataToChannel(Char+"rmlink <linkid>/<link> Removes that link specefied from the database");
				}
				else if(Input[1].equalsIgnoreCase("linkinfo") || Input[1].equalsIgnoreCase(Char+"linkinfo"))
				{
					p.sendDataToChannel(Char+"Linkinfo <linkid>/<link> returns data about the specefied link, like ID, the link itself and tags.");
				}
				else if(Input[1].equalsIgnoreCase("proxxi") || Input[1].equalsIgnoreCase(Char+"proxxi"))
				{
					p.sendDataToChannel(Char+"proxxi returns if Proxxi is opened or closed right now.");
				}
				else if(Input[1].equalsIgnoreCase("disable") || Input[1].equalsIgnoreCase(Char+"disable"))
				{
					p.sendDataToChannel(Char+"disable <!command> can turn of a command that the bot uses. If so, it wont answer any calls for that command.");
				}
				else if(Input[1].equalsIgnoreCase("toptags") || Input[1].equalsIgnoreCase(Char+"toptags"))
				{
					p.sendDataToChannel(Char+"topTags returns the top 5 most used tags");
				}
			}
			else
			{
				p.sendDataToChannel("This is the Snekabel Bot. I can help with a few things in the chat, here is a list of what i can do.");
				p.sendDataToChannel(Char+"Bored [tag], "+Char+"Spotify [tag], "+Char+"Coin, "+Char+"Dice, " +Char+"Disable <"+Char+"command>, "+Char+"Proxxi, "+Char+"Linkinfo <linkid>/<link>, "+Char+"rmlink <linkid>/<link>");
				p.sendDataToChannel("Write "+Char+"Help [command] to get detailed descriptions about the use of the commands");
				p.sendDataToChannel("This IRC Bot is made by and maintained by TB, Kira and others from the #Snekabel channel.");
			}
		} catch (IOException e1) {
			System.out.println("Could not send data to server " + e1.getLocalizedMessage());
		}
	}
	
	public static void delLink(IRCProtocol p,String[] Input)
	{
		try {
			SQLQuerries.delLink(Input);
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant delete Link! " + e.getLocalizedMessage());
		}
	}
	
	public static void proxxi(IRCProtocol p)
	{
		String status = "stängt";
		if(true)
		{
			status = "öppet";
		}
		try {
			p.sendDataToChannel("Proxxi är " + status);
		} catch (IOException e1) {
			System.out.println("Could not send data to server " + e1.getLocalizedMessage());
		}
	}
	
	public static void disableLink(IRCProtocol p,String[] Input)
	{
		if(Input.length > 1)
		{
			disabledCommands.add(Input[1].toUpperCase());
		}
		try {
			p.sendDataToChannel(Input[1] + " is now disabled");
		} catch (IOException e1) {
			System.out.println("Could not send data to server " + e1.getLocalizedMessage());
		}
	}
	
	public static void linkInfo(IRCProtocol p,String[] Input)
	{
		try {
			try {
				p.sendDataToChannel(SQLQuerries.linkInfo(Input));
			} catch (IOException e1) {
				System.out.println("Could not send data to server " + e1.getLocalizedMessage());
			}
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant delete Link! " + e.getLocalizedMessage());
		}
	}
	
	
	/*
	 *  DOSn3rds old TheGuardian Code
	 *  Use with caution
	 */
	private static String getHtmlCode(String link) {
		URL url;
		StringBuilder builder;
		String htmlCode = "";

		try {
			url = new URL(link);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
			BufferedReader reader = new BufferedReader(inputStream);
			builder = new StringBuilder();
			htmlCode = "";
			String line;

			while ((line = reader.readLine()) != null) {
				builder.append(line+" ");
			}
			reader.close();

			htmlCode = builder.toString();
		} catch (MalformedURLException e) {
			System.out.println(link+" is a bad url link");
			return "";
		} catch (IllegalArgumentException e) {
			System.out.println(link+" is a bad url link");
			return "";
		} catch (IOException e) {
			System.out.println("Could not open link: "+link);
			return "";
		}

		return htmlCode;

	}
	private static String getHtmlTag(String tag, String source) {
		tag = tag.toLowerCase().trim();
		String tagContent = "";
		if(source.toLowerCase().contains("<"+tag.toLowerCase()+">")){
			tagContent += source.substring(source.toLowerCase().indexOf(tag+">")+tag.length()+1, source.toLowerCase().indexOf("</"+tag+">"));
		}
		return tagContent.replace("\n", "").replace("\t","").replace("  ", "");
	}
}
