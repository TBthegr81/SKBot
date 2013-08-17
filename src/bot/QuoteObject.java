package bot;

import java.util.Random;

public class QuoteObject {
	private String UnitName;
	private String[] Quotes;
	
	public QuoteObject(String UnitName, String[] Quotes)
	{
		this.UnitName = UnitName;
		this.Quotes = Quotes;
	}
	
	public String getUnitName()
	{
		return UnitName;
	}
	
	public void setUnitName(String UnitName)
	{
		this.UnitName = UnitName;
	}
	
	public String[] getQuotes()
	{
		return Quotes;
	}
	
	public String getRandomQuote()
	{
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(Quotes.length);
		return Quotes[index];
	}
}
