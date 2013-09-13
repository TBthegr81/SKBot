package botMk2;
import java.io.IOException;

public class Main {
	private static Settings setting;
	public static void main(String[] args) {
		setting = new Settings();
		
		//Starta botten
		
		//Read configfile
		
		//Skapa en tråd för IRC
		//Detta börjar connecta botten till den IRC-server som står med i configfilen
		//IRCThread ircthread = new IRCThread();
		//ircthread.start();
		
		//Skapa en tråd för MailO
		//Be om password till mailen
		
		//Skapa en tråd för Web? Audio In? Mumble? Skype?
		
		try {
			setting.parseSettings(Lib.readFile("config.conf"));
		} catch (IOException e) {
			System.err.println("Cant read file and/or line! " + e.getLocalizedMessage());
		}
	}

}
