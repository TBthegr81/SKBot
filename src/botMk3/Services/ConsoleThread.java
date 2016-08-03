package botMk3.Services;
import botMk3.CLib;
import botMk3.Lib;
import botMk3.Test;

import java.util.ArrayList;

/**
 * Created by TB on 2016-07-26.
 */
public class ConsoleThread extends Thread
{
    ArrayList<String> answers;
    public ConsoleThread()
    {
        answers = new ArrayList<String>();
    }
    public void start() {
        while(true) {
            String[] input = CLib.input("").split("\\s+");
            if(input[0].equalsIgnoreCase("test"))
            {
                Test test = new Test();
            }
            else
            {
                answers = Lib.evaluateInput(input);

                for(String answer : answers)
                {
                    System.out.println(answer);
                }
            }
        }
    }
}
