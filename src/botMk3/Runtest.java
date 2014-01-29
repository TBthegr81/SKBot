package botMk3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Runtest {

	public static void main(String[] args) {
		// Run a java app in a separate system process
		Process proc;
		InputStream in;
		try {
			proc = Runtime.getRuntime().exec("java -jar C:\\Users\\Vionlabs\\workspace\\SKBot\\skbot.v.3.0_test.jar rofl");
			in = proc.getInputStream();
			InputStream err = proc.getErrorStream();
			BufferedReader Sin;
			Sin = new BufferedReader(new InputStreamReader(in));
			String inputLine;
			while ((inputLine = Sin.readLine()) != null)
			{
				//System.out.println(inputLine);
				System.out.println(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
