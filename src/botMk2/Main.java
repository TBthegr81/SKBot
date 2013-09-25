package botMk2;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
	private static Settings setting;
	private static ArrayList<Command> commands = new ArrayList<Command>();
	private static String[] input = new String[1];
	
	public static void main(String[] args) {
		setting = new Settings();
		
		//Starta botten
		
		//Read configfile
		Path path = Paths.get("config.conf");
		try {
            Files.createFile(path);
            System.out.println("Creating file\nDont forget to set up your bot or else it wont do anything!");
        } catch (FileAlreadyExistsException e) {
            System.err.println("already exists: " + e.getMessage());
        } catch (IOException e) {
			System.out.println("Fuckup! " + e.getLocalizedMessage());
		}
		try {
			System.out.println("Loading file");
			setting.parseSettings(Lib.readFile("config.conf"));
		} catch (IOException e) {
			System.err.println("Cant read file and/or line! " + e.getLocalizedMessage());
		}
		
		//Skapa en tråd för IRC
		//Detta börjar connecta botten till den IRC-server som står med i configfilen
		//IRCThread ircthread = new IRCThread();
		//ircthread.start();
		
		//Skapa en tråd för MailO
		//Be om password till mailen
		
		//Skapa en tråd för Web? Audio In? Mumble? Skype?
		commands = LoadCommands.load();
		
		input[0] = "RANDOM";
		System.out.println("Start eval!");
		for(int i = 0; i < commands.size(); i++)
		{
			System.out.println("Command:  " + i);
			commands.get(i).evaluate(input);
			System.out.println(commands.get(i).getShortDescription());
		}
		
		User user = new User();
		if(!user.parseIRCUser(":TBRPI!~tb@c-795be255.04-35-6875761.cust.bredbandsbolaget.se"))
			{
			System.out.println("Somethin fucked up!");
			}
		else
		{
			System.out.println("All is good");
		}
	}

}
