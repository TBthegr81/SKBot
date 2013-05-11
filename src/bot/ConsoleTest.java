package bot;

public class ConsoleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = Integer.parseInt(CLib.input("Port:"));
		if(port != 0)
		SQLQuerries.setPort(port);
		BotTankar.evaluate("!HELP");
		while(true)
		{
			BotTankar.evaluate(CLib.input("Command"));
		}
	}

}