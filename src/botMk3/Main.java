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

		// Create thread for mail-reading
		// Ask for password for mail

		// Create a thread for Web? Audio In? Mumble? Skype?
		commands = LoadCommands.load();


		// Create thread for IRC
		// Start connecting bot to the servers/channels in the config-file
		ArrayList<Host> irc_hosts = setting.getIRC_Hosts();
		ArrayList<IRCThread> irc_threads = new ArrayList<IRCThread>();
		for(int i = 0; i < irc_hosts.size(); i++)
		{
			IRCThread ircthread = new IRCThread(irc_hosts.get(i));
			ircthread.start();
			irc_threads.add(ircthread);
		}

		ConsoleThread consoleThread = new ConsoleThread();
		consoleThread.start();
	}

	public static ArrayList<Command> getCommands() {
		return commands;
	}
}
