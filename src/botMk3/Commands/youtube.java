package botMk3.Commands;

import botMk3.Interfaces.Command;
import botMk3.Lib;

import java.util.ArrayList;
import java.util.Random;

public class youtube implements Command {
    private String name = "random";

    public ArrayList<String> evaluate(String[] input) {
        //String tag = "";
        ArrayList<String> answer = new ArrayList<String>();

        return answer;
    }

    public String getHelpDescription() {

        return "Returns information about a posted youtube video";
    }


    public String getShortDescription() {

        return "Returns information about a posted youtube video";
    }

    public String getName() {
        return name;
    }

}
