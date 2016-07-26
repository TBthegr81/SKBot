package botMk3;
import botMk3.Interfaces.Command;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

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
			// If no configfile can be found, create one
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

		// Create thread for IRC
		// Start connecting bot to the servers/channels in the config-file
		//IRCThread ircthread = new IRCThread();
		//ircthread.start();

		// Create thread for mail-reading
		// Ask for password for mail

		// Create a thread for Web? Audio In? Mumble? Skype?
		commands = LoadCommands.load();

		/*input[0] = "RANDOM";
		System.out.println("Start eval!");
		for(int i = 0; i < commands.size(); i++)
		{
			System.out.println("Command:  !" + input[i]);
			commands.get(i).evaluate(input);
			System.out.println(commands.get(i).getShortDescription());
		}*/

		//@SuppressWarnings("unused")
		User user = new User();
		//@SuppressWarnings("unused")
		String[] userA = null;
		try {
			userA = User.parseIRCUser(":TBRPI!~tb@c-795be255.04-35-6875761.cust.bredbandsbolaget.se");
		} catch (Exception e) {
			System.out.println("Cant parse IRC string! " + e.getLocalizedMessage());
		}
		user.setNickname(userA[0]);
		user.setUsername(userA[1]);
		user.setHost(userA[2]);

		//Main Loop
		while(true)
		{
			String[] input = CLib.input("").split("\\s+");;
			//System.out.println(input[0]);
            evaluateInput(input);
		}
	}

    public static void evaluateInput(String[] input)
    {
        if(input[0].equalsIgnoreCase("quit") || input[0].equalsIgnoreCase("exit"))
        {

            System.out.println("Quitting!");
            System.exit(1);
        }
        else if(input[0].equalsIgnoreCase("reload"))
        {
            System.out.println("Reloading Commands");
            commands = LoadCommands.load();
        }
        else if(input[0].equalsIgnoreCase("help"))
        {
            System.out.println("Help module for SKBot");
            if(input.length > 1)
            {
                System.out.println("Help for "+input[1]);
                for(int i = 0; i < commands.size(); i++)
                {
                    if(commands.get(i).getName().equalsIgnoreCase(input[1]))
                    {
                        System.out.println(commands.get(i).getHelpDescription());
                    }
                }
            }
            else
            {
                System.out.println("Commands:");
                for(int i = 0; i < commands.size(); i++)
                {
                    System.out.println(commands.get(i).getName() + " - " + commands.get(i).getShortDescription());
                }
            }
        }
        else
        {
            ArrayList<String> answers = new ArrayList<String>();
            for(int i = 0; i < commands.size(); i++)
            {
                //System.out.println("Command:  !" + input[0]);
                ArrayList<String> result = commands.get(i).evaluate(input);
                answers.addAll(result);
                //System.out.println(commands.get(i).getShortDescription());
            }

            for(int i = 0; i < answers.size(); i++)
            {
                System.out.println(answers.get(i));
            }
        }
    }
}
