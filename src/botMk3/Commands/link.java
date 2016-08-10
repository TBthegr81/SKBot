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
                domain = getDomainName(input[i]).toLowerCase();
                System.out.println("Domain: " + domain);
            } catch (URISyntaxException e) {
                System.err.println("ERROR: " + e.getLocalizedMessage());
            }

            try {
                doc = Jsoup.connect(input[i]).get();

                if(domain.contains("webhallen.com"))
                {
                    title = getTitle(doc);
                    price = doc.select("#product_price");
                    title += " Price: " + price.text() + "kr";
                }
                else if(domain.contains("amazon.co.uk") || domain.contains("amazon.com")) {
                    title = getTitle(doc);
                    price = doc.select("#priceblock_ourprice");
                    if(price != null)
                    {
                        title += " Price: " + price.text();
                    }
                }
                else if(domain.contains("mathem.se")) {
                    title = getTitle(doc);
                    price = doc.select("#spnPrice");
                    if(price != null)
                    {
                        title += " Price: " + price.first().text() + "kr";
                    }
                }
                else if(domain.contains("inet.se")) {
                    title = getTitle(doc);
                    price = doc.select(".active-price");
                    if(price != null)
                    {
                        title += " Price: " + price.get(1).text();
                    }
                }
                else if(domain.contains("shop.lego.com")) {
                    title = getTitle(doc);
                    price = doc.select(".product-price em");
                    if(price != null)
                    {
                        if(price.size() > 0)  title += " Price: " + price.get(0).text();
                    }
                }
                else if(domain.contains("ikea.com")) {
                    title = getTitle(doc);
                    price = doc.select("#price1");
                    if(price != null)
                    {
                        title += " Price: " + price.get(0).text();
                    }
                }
                else if(domain.contains("prisjakt.nu")) {
                    title = getTitle(doc);
                    price = doc.select(".pris");
                    if(price != null)
                    {
                        title += " Price from: " + price.get(0).text();
                    }
                }
                else if(domain.contains("sfbok.se")) {
                    title = getTitle(doc);
                    price = doc.select(".field-item.even");
                    if(price != null)
                    {
                        title += " Price: " + price.get(0).text();
                    }
                }
                else if(domain.contains("blocket.se")) {
                    title = getTitle(doc);
                    price = doc.select("#vi_price");
                    if(price != null)
                    {
                        title += " Price: " + price.get(0).text();
                    }

                    String area_label = doc.select(".area_label").text();
                    if(area_label != null)
                    {
                        title += " Area: " + area_label;
                    }

                    String published = doc.select("#seller-info time").text();
                    if(published != null)
                    {
                        title += " Pub: " + published;
                    }
                }
                else if(domain.contains("kjell.com")) {
                    title = getTitle(doc);
                    price = doc.select(".bestPrice");
                    if(price != null)
                    {
                        title += " Price: " + price.get(0).text();
                    }
                }
                else if(domain.contains("vimeo.com")) {
                    title = "Nobody uses vimeo... Don't kid yourself";
                }
                else if(domain.contains("spotify.com")) {
                    title = doc.select(".primary-title") + " - " + doc.select(".secondary-title");
                }
                else if(domain.contains("youtube.com"))
                {
                    String videoname = doc.select("[property=\"og:title\"]").attr("content");
                    if(videoname.length() > 50)
                    {
                        videoname = videoname.substring(0,49);
                    }

                    String description = doc.select("[property=\"og:description\"]").attr("content");
                    if(description.length() > 70)
                    {
                        description = description.substring(0,69);
                    }

                    String channel = doc.select(".yt-user-info a").text();

                    title = "Title: " + videoname + " Desc: " + description;
                }
                else if(domain.contains("imgur.com")) {
                    title = doc.select("title").text();
                    if(title.equalsIgnoreCase("Imgur"))
                    {
                        title = "";
                    }
                }
                else
                {
                    title = getTitle(doc);
                }

                answer.add(title);
            } catch (Exception e) {
                 System.err.println("ERROR: " + e.getLocalizedMessage());
            }

            //Sqlite.insertLink(input[i], domain);
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
        String[] nameparts = this.getClass().getName().split("\\.");
        return nameparts[nameparts.length-1];
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
