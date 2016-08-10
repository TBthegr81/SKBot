package botMk3.Commands;

import botMk3.Interfaces.Command;

import java.util.ArrayList;

public class proxxi implements Command {
	private String[] triggers = {""};

	public ArrayList<String> evaluate(String[] input) {
		//String tag = "";
		ArrayList<String> answer = new ArrayList<String>();
		//If input isnt null, check if it coresponds with the trigger-words
		if(input.length > 0)
		{
			if(input[0].equalsIgnoreCase("proxxi"))
			{
				answer.add("Proxxi är stängt");
			}
		}
		return answer;
	}

	public String getHelpDescription() {
		
		return "Returns if Proxxi is open.";
	}

	public String getShortDescription() {
		
		return "Returns if Proxxi is open.";
	}

	public String getName() {
        String[] nameparts = this.getClass().getName().split("\\.");
		return nameparts[nameparts.length-1];
	}

}
