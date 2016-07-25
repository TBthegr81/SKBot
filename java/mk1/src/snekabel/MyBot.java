package snekabel;

import org.jibble.pircbot.PircBot;

public class MyBot extends PircBot {
    String version = "Rev2 V.0.0.1";
    String lasteditor = "TB";
	String lasteditdate = "2015-04-01";
	String commandchar = "!";
    
    public MyBot() {
        this.setName("SKBot");
    }
    
    public void onMessage(String channel, String sender,
            String login, String hostname, String message)
    {
    	String[] splitmessage = message.split("\\s+");
    	
    	if (splitmessage[0].equalsIgnoreCase(commandchar+"help"))
    	{
    		String commandlist ="!help, !time, !randomlanguage";
    		String text = "SKBot "+version+" Last edited by: "+lasteditor+" Date: "+lasteditdate;
    		sendMessage(channel, text);
    		sendMessage(channel, commandlist);
    	}
    	else if (message.equalsIgnoreCase("http://") || message.equalsIgnoreCase("https://"))
    	{
    		sendMessage(channel, "Adding link");
    	}
    	else if (splitmessage[0].equalsIgnoreCase(commandchar+"random"))
    	{
    		String text = "Randomness";
    		if(splitmessage.length > 1) {
    			if(splitmessage[1].equalsIgnoreCase("link"))
        		{
        			text = "http://google.se";
        		}
        		else if(splitmessage[1].equalsIgnoreCase("spotify"))
        		{
        			text = "https://open.spotify.com/track/4rKSuuxW4UxGn6APw9KcUi";
        		}
        		else if(splitmessage[1].equalsIgnoreCase("language"))
        		{
        			String[] languages = {"Java", "PHP", "JavaScript"};
            		int randomint = Lib.randInt(0,languages.length);
            		text = languages[randomint];
        		}
        		else if(splitmessage[1].equalsIgnoreCase("pokemon"))
        		{
            		text = getRandomPokemon();
        		}
    		}
    		sendMessage(channel, text);
    	}
    	else if (splitmessage[0].equalsIgnoreCase(commandchar+"dice"))
    	{
    		int randomint = 0;
    		if(splitmessage.length > 1)
    		{
    			randomint = Lib.randInt(1,Integer.parseInt(splitmessage[1]));
    		}
    		else
    		{
    			randomint = Lib.randInt(1,6);
    		}
    		sendMessage(channel, "Dice rolls " + randomint);
    	}
    	else if (splitmessage[0].equalsIgnoreCase(commandchar+"coin"))
    	{
    		int randomint = Lib.randInt(1,2);
    		String text = "Coin landed ";
    		String coin = "";
    		if(randomint == 1)
    		{
    			coin = "heads";
    		}
    		else if(randomint == 2)
    		{
    			coin = "tails";
    		}
    		
    		sendMessage(channel, text+coin);
    	}
	}
    
    public static String getRandomPokemon() {
    	String[] pokemon = {""};
    	int randomint = Lib.randInt(0,pokemon.length);
    	return "LOL";
    }
}