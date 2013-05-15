/**
 * @authur Erik Welander
 * Stockholms Universitet DSV
 * @VERSION: 2013-05-03
 * 
 * Compiler: JDK 1.7.0_17
 * Target JRE: 7
 * Charset: UTF-8
 * IDE: Netbeans 7.3
 * 
 * COPYRIGHT(C) Erik Welander
 * Kontakt: Erik.Welander@hotmail.com
 */
package bot;

import java.io.IOException;
import java.net.UnknownHostException;

public class SKBot {
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        System.out.println("SKBot V 2013-05-15 ALPHA");
        System.out.println("Last Maintained by: TB");
        IRCProtocol p = new IRCProtocol("irc.oftc.net", 6667, "TEST", "TestServer", "#snekabel");
        boolean svar = CLib.choiceyn("Dont use port 3306?");
		if(svar == false)
		SQLQuerries.setPort(Integer.parseInt(CLib.input("Port:")));
        p.joinNetwork();         
        while(true){
            String channelData = "";
           try{
            channelData = p.getChannelDataArray()[3];
            channelData = channelData.substring(1);
           }catch(Exception ex){}
           System.out.println("Channel data: "+channelData);
           BotTankar.evaluate(p, channelData);
        }
    }
}
