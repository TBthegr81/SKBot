package botMk3.Services;

import botMk3.Host;

public class IRCThread extends Thread
{
	public IRCThread(Host irc_host) {

	}
	public void start()
	{
		System.out.println("IRCThread running");
	}
}
