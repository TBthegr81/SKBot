package botMk2.Commands;

import java.util.ArrayList;

import botMk2.Lib;

public class Random implements botMk2.Command {
	String apikey = "1234567890";
	String returntype = "HTML";
	private String[] triggers = {""};
	public ArrayList<String> evaluate(String[] input) {
		//String tag = "";
		ArrayList<String> answer = new ArrayList<String>();
		//If input isnt null, check if it coresponds with the trigger-words
		if(input.length > 0)
		{
			if(input[0].equalsIgnoreCase("random") || input[0].equalsIgnoreCase("bored"))
			{
				//Check if there could be some other inputs to the function
				if(input.length > 1)
				{
					
					//String datatype = "randomLink";
					String[] tags = {"bajs"};
					String[] result = Lib.readWebsite("http://skbot.snekabel.se/api/v0/getRandomLink.php?apikey="+apikey+"&tags="+tags[0]+"&returntype="+returntype);
					for(int i = 0; i < result.length; i++)
					{
						System.out.println(result[i]);
					}
				}
				// Else just go with the default output
				else
				{
					String[] result = Lib.readWebsite("http://skbot.snekabel.se/api/v0/getRandomLink.php?apikey="+apikey+"&returntype="+returntype);
					for(int i = 0; i < result.length; i++)
					{
						System.out.println(result[i]);
					}
				}
			}
		}
		return new ArrayList<String>();
	}

	public String getHelpDescription() {
		
		return "Returns a random link";
	}

	
	public String getShortDescription() {
		
		return "Returns a random link";
	}


	public String[] getTriggers() {
		return triggers;
	}

}
