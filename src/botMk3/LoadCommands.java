package botMk3;

import java.util.ArrayList;
import botMk3.Commands.*;
import botMk3.Interfaces.Command;

public class LoadCommands {


	public static ArrayList<Command> load()
	{
		ArrayList<Command> commands = new ArrayList<Command>();
		

		commands.add(new random());
		//commands.add(new lol());
		commands.add(new link());

		/*System.out.println("Commands loaded");
		for(int i=0; i < commands.size(); i++)
		{
			System.out.println("Command loade: "+commands.get(i).getName());
		}*/

		/*try {
			Command obj = (Command) Class.forName("botMk3.Commands.random").newInstance();
			commands.add(obj);
		} catch (Exception e) {
			System.out.println("SUEPR FAIL!");
			System.out.println(e.getLocalizedMessage());
		}*/

		/*ClassLoader classLoader = Main.class.getClassLoader();

		try {
			Class aClass = classLoader.loadClass("botMk3.Commands.*");
			System.out.println("aClass.getName() = " + aClass.getName());


			try {
				Command obj = (Command) aClass.newInstance();
				commands.add(obj);
			} catch (Exception e) {
				System.out.println("SUEPR FAIL!");
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("SUEPR FAIL!");
			e.printStackTrace();
		}*/

		//System.out.println("COMAMNDS: ");
		//System.out.println(commands);
		return commands;
	}

}
