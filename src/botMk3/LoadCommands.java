package botMk3;

import java.util.ArrayList;
import botMk3.Commands.*;
import botMk3.Interfaces.Command;

public class LoadCommands {


	public static ArrayList<Command> load()
	{
		ArrayList<Command> commands = new ArrayList<Command>();
		
		commands.add(new Random());
		commands.add(new lol());
		
		return commands;
	}

}
