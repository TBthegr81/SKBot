package botMk3.Commands;

import botMk3.Interfaces.Command;
import botMk3.Lib;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class link implements Command {
    private String name = "link";

    public ArrayList<String> evaluate(String[] input) {
        //String tag = "";
        ArrayList<String> answer = new ArrayList<String>();

	for(int i = 0; i < input.length; i++)
	{
        String answertext = "Title: ";
		if(input[i].toLowerCase().contains("http://") || input[i].toLowerCase().contains("https://"))
		{
            //Lib.readWebsite(input[i]);
			//title += "google.se - Search things";

            Document doc = null;
            try {
                doc = Jsoup.connect(input[i]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String title = "";
            Elements titles = doc.select("title");
            for (Element titletext : titles)
            {
                title += titletext.text();
            }
            System.out.println(title);
            answertext += title;
            if(title != null && !title.isEmpty())
            {
                answer.add(answertext);
            }
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
