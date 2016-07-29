package botMk3;

import org.jibble.pircbot.*;
import java.util.ArrayList;

public class MyBot extends PircBot {

    public MyBot(String name)
    {
        this.setName(name);
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        /*if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }*/
        ArrayList<String> answers = Lib.evaluateInput(message.split("\\s+"));
        for(String answer : answers)
        {
            sendMessage(channel, sender + ": " + answer);
        }
    }
}
