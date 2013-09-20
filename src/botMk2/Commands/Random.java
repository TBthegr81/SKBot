package botMk2.Commands;

import java.util.ArrayList;

public class Random implements botMk2.Command {
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
					
				}
				// Else just go with the default output
				else
				{
					answer.add("Shadap i dont care");
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
