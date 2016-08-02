package botMk3.Commands;

import botMk3.Interfaces.Command;
import botMk3.Lib;
import kira.WebPageTitleService;

import java.util.ArrayList;

public class link implements Command {
    private String name = "link";

    public ArrayList<String> evaluate(String[] input) {
        //String tag = "";
        ArrayList<String> answer = new ArrayList<String>();

	for(int i = 0; i < input.length; i++)
	{
        String title = "Title: ";
		if(input[i].toLowerCase().contains("http://") || input[i].toLowerCase().contains("https://"))
		{
            //Lib.readWebsite(input[i]);
			//title += "google.se - Search things";

            WebPageTitleService webservice = new WebPageTitleService();
            title += webservice.getWebPageTitle(input[i]);
			answer.add(title);
		}
	}
	 return answer;
    }

    public String getHelpDescription() {

        return "Returns title from a posted link";
    }


    public String getShortDescription() {

        return "Returns title from a posted link";
    }

    public String getName() {
        return name;
    }

}
