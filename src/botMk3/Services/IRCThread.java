package botMk3.Services;

import botMk3.Host;
import botMk3.MyBot;

import java.io.IOException;
import java.util.ArrayList;

public class IRCThread extends Thread
{
    Host host;
    MyBot bot;

	public IRCThread(Host irc_host) {
        System.out.println("Setting up IRC Thread");
        host = irc_host;
        bot = new MyBot("SKBot2");

        bot.setVerbose(true);

        try
        {
            bot.connect(host.getHostname());
            bot.joinChannel("#Snekabel");
        }
        catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }

        /*ArrayList<String> channels = host.getChannels();
        for(int i = 0; i < channels.size(); i++)
        {
            bot.joinChannel(channels.get(i));
        }*/


	}
	public void start()
	{
		System.out.println("IRCThread running");

	}
}
