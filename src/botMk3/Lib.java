package botMk3;

import botMk3.Interfaces.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;
//import javax.xml.bind.DatatypeConverter;

public class Lib {
	public static int[] convertIntegers(List<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    for (int i=0; i < ret.length; i++)
	    {
	        ret[i] = integers.get(i).intValue();
	    }
	    return ret;
	}
	
	// Method for MD5-hashing
	public static String md5(String input) {
	    
	    String md5 = null;
	     
	    if(null == input) return null;
	     
	    try {
	         
	    //Create MessageDigest object for MD5
	    MessageDigest digest = MessageDigest.getInstance("MD5");
	     
	    //Update input string in message digest
	    digest.update(input.getBytes(), 0, input.length());

	    //Converts message digest value in base 16 (hex) 
	    md5 = new BigInteger(1, digest.digest()).toString(16);

	    } catch (NoSuchAlgorithmException e) {

	        e.printStackTrace();
	    }
	    return md5;
	}
		
	// Method to code string in Base64
	public static String base64Code(String input)
	{
		return DatatypeConverter.parseBase64Binary(input).toString();
	}
	
	// Method to de-code Base64 to string
	public static String base64DeCode(byte[] input)
	{
		return DatatypeConverter.printBase64Binary(input).toString();
	}
	
	// Method to test if String is number
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	// Method to read file and return array with whats inside
	public static ArrayList<String> readFile(String path) throws IOException
	{
		ArrayList<String> sb;
		BufferedReader br = new BufferedReader(new FileReader(path));
	    try {
	        sb = new ArrayList<String>();
	        String line = br.readLine();

	        while (line != null) {
	            sb.add(line);
	            line = br.readLine();
	        }
	    } finally {
	        br.close();
	    }
	    return sb;
	}
	
	// Method for getting content of webpage as Array
	public static String[] readWebsite(String url)
	{
		ArrayList<String> result = new ArrayList<String>();
		URL RealUrl = null;
		try {
			RealUrl = new URL( url );
		} catch (MalformedURLException e) {
			System.err.println("Cant load URL. URL missformed! " + e.getLocalizedMessage());
		}
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(RealUrl.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				//System.out.println(inputLine);
				result.add(inputLine);
			}
			in.close();
		} catch (IOException e) {
			System.err.println("Cant open reader to URL. Check URL and connectivity. " + e.getLocalizedMessage());
		}
		
		return result.toArray(new String[result.size()]);
	}
	

	// Method for reading JSON-files to multi-dimensional arrays
	/*public static String[][] readJSONFile(String path)
	{
		 URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			System.err.println("Cant load URL. URL missformed! " + e.getLocalizedMessage());
		}
		 try (InputStream is = url.openStream();
			 JsonReader rdr = Json.createReader(is)) {
			 
			 JsonObject obj = rdr.readObject();
			 JsonArray results = obj.getJsonArray("data");
		     for (JsonObject result : results.getValuesAs(JsonObject.class)) {
		    	  System.out.print(result.getString("link"));
		          System.out.println("-----------");
		        }
		 } catch (IOException e) {
			 System.err.println("Cant open reader to URL. Check URL and connectivity. " + e.getLocalizedMessage());
			}
		return new String[1][1];
	}*/

	private static ArrayList<Command> commands = Main.getCommands();
	public static void evaluateInput(String[] input)
	{
		if(input[0].equalsIgnoreCase("quit") || input[0].equalsIgnoreCase("exit"))
		{

			System.out.println("Quitting!");
			System.exit(1);
		}
		else if(input[0].equalsIgnoreCase("reload"))
		{
			System.out.println("Reloading Commands");
			commands = LoadCommands.load();
		}
		else if(input[0].equalsIgnoreCase("help"))
		{
			System.out.println("Help module for SKBot");
			if(input.length > 1)
			{
				System.out.println("Help for "+input[1]);
				for(int i = 0; i < commands.size(); i++)
				{
					if(commands.get(i).getName().equalsIgnoreCase(input[1]))
					{
						System.out.println(commands.get(i).getHelpDescription());
					}
				}
			}
			else
			{
				System.out.println("Commands:");
				for(int i = 0; i < commands.size(); i++)
				{
					System.out.println(commands.get(i).getName() + " - " + commands.get(i).getShortDescription());
				}
			}
		}
		else
		{
			ArrayList<String> answers = new ArrayList<String>();
			for(int i = 0; i < commands.size(); i++)
			{
				//System.out.println("Command:  !" + input[0]);
				ArrayList<String> result = commands.get(i).evaluate(input);
				answers.addAll(result);
				//System.out.println(commands.get(i).getShortDescription());
			}

			for(int i = 0; i < answers.size(); i++)
			{
				System.out.println(answers.get(i));
			}
		}
	}
}
