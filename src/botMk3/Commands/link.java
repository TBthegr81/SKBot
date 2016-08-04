package botMk3.Commands;

import botMk3.Interfaces.Command;
import botMk3.Lib;
import botMk3.Sqlite;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static botMk3.Lib.getDomainName;

public class link implements Command {
    private String name = "link";

    public ArrayList<String> evaluate(String[] input) {
        //String tag = "";
        ArrayList<String> answer = new ArrayList<String>();
        String answertext;
        String domain;
        Document doc;
        String title;
        Elements price;

	for(int i = 0; i < input.length; i++)
	{
        answertext = "";
        domain = "";
        doc = null;
        price = null;
        title = "";

		if(input[i].toLowerCase().contains("http://") || input[i].toLowerCase().contains("https://"))
		{
            try {
                domain = getDomainName(input[i]);
                System.out.println("Domain: " + domain);
            } catch (URISyntaxException e) {
                System.err.println("ERROR: " + e.getLocalizedMessage());
            }

            try {
                doc = Jsoup.connect(input[i]).get();

                if(domain.equalsIgnoreCase("webhallen.com"))
                {
                    title = getTitle(doc);
                    price = doc.select("#product_price");
                    title += " Price: " + price.text() + "kr";
                }
                else if(domain.equalsIgnoreCase("amazon.co.uk") || domain.equalsIgnoreCase("amazon.com"))
                {
                    title = getTitle(doc);
                    price = doc.select("#priceblock_ourprice");
                    title += " Price: " + price.text();
                }
                else if(domain.equalsIgnoreCase("mathem.se"))
                {
                    title = getTitle(doc);
                    price = doc.select("#spnPrice");
                    title += " Price: " + price.first().text() + "kr";
                }
                else if(domain.equalsIgnoreCase("inet.se"))
                {
                    title = getTitle(doc);
                    price = doc.select(".active-price");
                    title += " Price: " + price.get(1).text();
                }
                else if(domain.equalsIgnoreCase("shop.lego.com"))
                {
                    title = getTitle(doc);
                    price = doc.select(".product-price em");
                    if(price.size() > 0)  title += " Price: " + price.get(0).text();
                }
                else if(domain.equalsIgnoreCase("ikea.com"))
                {
                    title = getTitle(doc);
                    price = doc.select("#price1");
                    title += " Price: " + price.get(0).text();
                }
                else if(domain.equalsIgnoreCase("prisjakt.nu"))
                {
                    title = getTitle(doc);
                    price = doc.select(".pris");
                    title += " Price from: " + price.get(0).text();
                }
                else if(domain.equalsIgnoreCase("sfbok.se"))
                {
                    title = getTitle(doc);
                    price = doc.select(".field-item.even");
                    title += " Price: " + price.get(0).text();
                }
                else if(domain.equalsIgnoreCase("blocket.se"))
                {
                    title = getTitle(doc);
                    price = doc.select("#vi_price");
                    title += " Price: " + price.get(0).text();
                }
                else if(domain.equalsIgnoreCase("kjell.com"))
                {
                    title = getTitle(doc);
                    price = doc.select(".bestPrice");
                    title += " Price: " + price.get(0).text();
                }
                else if(domain.equalsIgnoreCase("vimeo.com"))
                {
                    title = "Nobody use vimeo";
                }
                else
                {
                    title = getTitle(doc);
                }

                answer.add(title);
            } catch (Exception e) {
                 System.err.println("ERROR: " + e.getLocalizedMessage());
            }

            Sqlite.insertLink(input[i], domain);
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

    public String getTitle(Document doc) {
        String value = "";
        String title = "";
        Elements titles = doc.select("title");

        title = titles.text();
        if(title.length() > 100)
        {
            title = title.substring(0,99);
        }
        value = "Title: " + title.replaceAll("[\n\r\t]", "").trim();

        //System.out.println(title);
        if(!title.isEmpty())
        {
            return value;
        }
        else
        {
            return "";
        }
    }
}
