package botMk2;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	// Metod för att generera MD5 hash av password
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
		
		//Metod för att codea Base64
		public static String base64Code(String input)
		{
			return DatatypeConverter.parseBase64Binary(input).toString();
		}
		
		//Metod för att decodea Base64
		public static String base64DeCode(byte[] input)
		{
			return DatatypeConverter.printBase64Binary(input).toString();
		}
		
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
}
