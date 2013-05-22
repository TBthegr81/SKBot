package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class BotTankar {
	private static boolean topicGeneration = false;
	private static boolean addLink = false;
	private static char Char = '!';
	private static ArrayList<String> disabledCommands= new ArrayList<String>();
	private static ArrayList<String> ignoredUsers= new ArrayList<String>();
	private static String input = "";
	private static String[] data = null;
	private static GregorianCalendar gc = new GregorianCalendar();
	private static Date d;
	
	public static void evaluate(IRCProtocol p)
	{
		d = new Date();
		gc.setTime(d);
		/*for(int i = 0; i < disabledCommands.size(); i++)
		{
			System.out.println(disabledCommands.get(i));
		}*/
		
		// Start by splitting the input string by whitespaces
        try{
        	data = p.getChannelDataArray();
        	input = data[3];
        	input = input.substring(1);
        }catch(Exception ex){}
        System.out.println("Channel data: "+input);
		String[] Input = input.split("\\s+");
		
		
		// Commands for user to use
		if(data[1].equalsIgnoreCase("PRIVMSG") && !disabledCommands.contains(Input[0].toUpperCase()))
		{
			if(Input[0].equalsIgnoreCase(Char + "Bored") || Input[0].equalsIgnoreCase(Char + "Random"))
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
			else if(Input[0].equalsIgnoreCase(Char + "TopTags"))
			{
				topTags(p);
			}
			else if(Input[0].equalsIgnoreCase(Char + "Youtube"))
			{
				youtube(p,Input);
			}
			else if(Input[0].equalsIgnoreCase(Char + "!linkStats"))
			{
				linkStats(p);
			}
		
		//Special
		//Looking thru  all the words in a post
		else
		{	
			addLink = false;
			//System.out.println("Now we are at ELSE");
			ArrayList<String> tags = new ArrayList<String>();
			String url = "";
			int type = 2;
			for(int i = 0; i < Input.length; i++)
			{
				//And here you can look for specefic words! :D
				if(Input[i].contains("http://") || Input[i].contains("https://"))
				{
					System.out.println("Found link!");
					if(Input[i].contains("open.spotify.com"))
					{
						type = 1;
					}
					else if(Input[i].contains("youtube.com"))
					{
						type = 3;
					}
					url = Input[i];
					addLink = true;
					if(!disabledCommands.contains("readlink"));
					{
						try {
							p.sendDataToChannel("Title: " + getHtmlTag("title", getHtmlCode(Input[i])));
						} catch (IOException e) {
							System.out.println("Cant send data" + e.getLocalizedMessage());
						}
					}
				}
				else if(Input[i].contains("#"))
				{
					tags.add(Input[i]+"");
				}
				else if(Input[i].contains("<tm>"))
				{
					tm(p, Input, i);
				}
			}
			if(addLink && !disabledCommands.contains("addlink"))
			{
				//System.out.println("Adding link");
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
			dinMamma(p);
			morn(p, Input);
			hal9k(p);
			/*
			String logging = null;
			for(int i = 0; i < Input.length; i++)
			{
				logging = logging + " " + Input[i];
			}
			log(logging);*/
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
	
	public static void youtube(IRCProtocol p,String[] Input)
	{
		String tag = "";
		if(Input.length > 1)
		{
			tag = Input[1];
			System.out.println("TAG:" + tag);
		}
		try {
			String svar = SQLQuerries.getRandomLink(tag, 3);
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
			CLib.print("Cant get Random Youtube! " + e.getLocalizedMessage());
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
					
					p.sendDataToChannel(" " + Char+"Bored [tag] gets and posts a random link from the database of previously posted and tagged links.");
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
		String bruse = getHtmlCode("https://bruse.proxxi.org/is_proxxi_open_eng.php");
		String status = "stängt";
		System.out.println(bruse);
		if(bruse.contains("1"))
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
	
	public static void topTags(IRCProtocol p)
	{ 
		ArrayList<String> tagCount = null;
		try {
			tagCount = SQLQuerries.tagCount();
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant get TopTags! " + e.getLocalizedMessage());
		}
		for(int i = 0; i < 4; i++)
		{
			try {
				p.sendDataToChannel("Tag #"+(i+1)+" "+tagCount.get(i));
			} catch (IOException e) {
				System.out.println("Could not send data to server " + e.getLocalizedMessage());
			}
		}
		try {
			p.sendDataToChannel(tagCount.get(5));
		} catch (IOException e) {
			System.out.println("Could not send data to server " + e.getLocalizedMessage());
		}
	}
	
	private static void dinMamma(IRCProtocol p)
	{
		if(new Random().nextInt(50) == 1)
		{
			try {
				p.sendDataToChannel("Din mamma " + input + " OOOOOH");
			} catch (IOException e) {
				System.out.println("Cant get input data" + e.getLocalizedMessage());
			}
		}
	}
	
	private static void hal9k(IRCProtocol p)
	{
		if(new Random().nextInt(40) == 1)
		{
			try {
				p.sendDataToChannel("I'm Sorry Dave, I'm afraid i cant do that.");
			} catch (IOException e) {
				System.out.println("Cant get input data" + e.getLocalizedMessage());
			}
		}
	}
	
	private static void morn(IRCProtocol p, String[] Input)
	{
		if(Input[0].equalsIgnoreCase("morn") || Input[0].equalsIgnoreCase("morrn") || Input[0].equalsIgnoreCase("morgon"))
		{
			try {
				p.sendDataToChannel("Morn!");
			} catch (IOException e) {
				System.out.println("Cant get input data" + e.getLocalizedMessage());
			}
		}
	}
	
	public static void log(String logging)
	{
		//SQLQuerries.log(d,logging);
		CLib.print(gc.toString() + " " + logging);
	}
	
	public static void tm(IRCProtocol p, String[] Input, int j)
	{
		String trademarked = "";
		for(int i = 0; i < j; i++)
		{
			trademarked = trademarked + " " + Input[i];
		}
		String tmtext = "'" + trademarked +"' Trademarked " + gc.get(Calendar.YEAR) + " - " + (gc.get(Calendar.YEAR)+1) + " by TB";
		CLib.print(tmtext);
		try {
			p.sendDataToChannel(tmtext);
		} catch (IOException e) {
			System.out.println("Cant send data to Channel" + e.getLocalizedMessage());
		}
	}
	
	public static void linkStats(IRCProtocol p)
	{
		//
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
