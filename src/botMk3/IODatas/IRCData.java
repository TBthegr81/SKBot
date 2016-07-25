package botMk3.IODatas;

import botMk3.IOData;
import java.util.Observable;

public class IRCData extends Observable implements IOData{

	public void sendData(String data) {
		System.out.println("data");
	}

	public String getData() {
		
		return "lol";
	}

}
