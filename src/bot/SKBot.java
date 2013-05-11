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
        System.out.println("SKBot V 2013-05-11 ALPHA");
        System.out.println("Last Maintained by: Erik Welander");
        IRCProtocol p = new IRCProtocol("irc.oftc.net", 6667, "SKBot", "SnekabelServer", "#snekabel");
        p.joinNetwork();         
        BotTankar b = new BotTankar(p);
        while(true){
            p.getData();
            p.sendDataToChannel("Hi there!");
        }
    }
}
