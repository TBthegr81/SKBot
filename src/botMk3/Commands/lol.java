package botMk3.Commands;

import botMk3.Interfaces.Command;

import java.util.ArrayList;

public class lol implements Command {
	private String[] triggers = {""};
	private String name = "lol";

	public ArrayList<String> evaluate(String[] input) {
		//String tag = "";
		ArrayList<String> answer = new ArrayList<String>();
		//If input isnt null, check if it coresponds with the trigger-words
		if(input.length > 0)
		{
			if(input[0].equalsIgnoreCase("lol"))
			{
				answer.add("lol");
			}
		}
		return answer;
	}

	public String getHelpDescription() {
		
		return "Returns lol";
	}

	public String getShortDescription() {
		
		return "Returns lol";
	}

	public String getName() {
		return this.name;
	}

}
