package botMk3;
import botMk3.Interfaces.Command;
import botMk3.Services.*;

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
        //Load settings
		setting = new Settings();

        //Starta botten


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

        ConsoleThread consoleThread = new ConsoleThread();
        consoleThread.start();
	}

	public static ArrayList<Command> getCommands() {
		return commands;
	}
}
