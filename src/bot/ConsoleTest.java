package bot;

public class ConsoleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean svar = CLib.choiceyn("Dont use port 3306?");
		if(svar == false)
		SQLQuerries.setPort(Integer.parseInt(CLib.input("Port:")));
		BotTankar.evaluate("!HELP");
		while(true)
		{
			BotTankar.evaluate(CLib.input("Command"));
		}
	}

}