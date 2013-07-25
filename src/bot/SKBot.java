/**
 * @author Erik Welander + TB
 * Stockholms Universitet DSV
 * @VERSION: 2013-05-03
 * 
 * Compiler: JDK 1.7.0_17
 * Target JRE: 7
 * Charset: UTF-8
 * IDE: Netbeans 7.3 + Eclipse
 * 
 * COPYRIGHT(C) Erik Welander + TB
 * Contact: Erik.Welander@hotmail.com
 */
package bot;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SKBot {
	private static ArrayList<Command> Commands = new ArrayList<Command>();
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        System.out.println("SKBot V 2013-06-23 ALPHA");
        System.out.println("Last Maintained by: TB");
        
        IRCProtocol p = new IRCProtocol("irc.oftc.net", 6667, "Testbot", "SnekabelServer", "#snekabelbottest");
        boolean svar = CLib.choiceyn("Dont use port 3306?");
		if(svar == false)
		SQLQuerries.setPort(Integer.parseInt(CLib.input("Port:")));
		try {
			Commands = SQLQuerries.getCommands();
		} catch (SQLFuckupExeption e) {
			CLib.print("Cant get Commands! " + e.getLocalizedMessage());
		}
        p.joinNetwork();
        while(true){
        	//System.out.println(p.getData());
        	//Message msg = new Message(p.getData());
        	//System.out.println("Nickname:" + msg.getNickname() + " Username:" + msg.getUsername() + " Host:" + msg.getHost() + " Command:" + msg.getCommand() + " Channel:" + msg.getChannel() + " Text:" + msg.getText());
           BotTankar.evaluate(p);
        }
    }
    
    public static ArrayList<Command> getCommands()
    {
    	return Commands;
    }
}
