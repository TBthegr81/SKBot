package bot;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
		
		public static boolean isNumeric(String str)  
		{  
		  try  
		  {  
		    double d = Double.parseDouble(str);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;  
		}
}
