package botMk3.Services;
import botMk3.CLib;

/**
 * Created by TB on 2016-07-26.
 */
public class ConsoleThread extends Thread
{
    public void start() {
        while(true) {
            String[] input = CLib.input("").split("\\s+");
            CLib.evaluateInput(input);
        }
    }
}
