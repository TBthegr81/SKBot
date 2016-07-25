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
 
public class IRCProtocol extends TCPSocket{
    public String nick = "", user = "", channel = "";
    public IRCProtocol(String host, int port, String nick, String user, String channel) throws UnknownHostException, IOException{
        super(host, port);
        this.nick = nick;
        this.user = user;
        this.channel = channel;
    }
    public void disconnect() throws IOException{
        super.disconnectFromHost();
    }
    public String[] getChannelDataArray() throws IOException{
       return getData().split("\\s+",4);
    }
    @Override
    public String getData() throws IOException{
        String data = super.getData();
        if(data.startsWith("PING")){
            data = data.replace("PING", "PONG");
            sendDataRaw(data);
            System.out.println("IRCProtocol: I recieved a PING message, but handled it automatically for you");
        }
        System.out.println("IRCProtocol: Recieved data: "+data);
        return data;
    }
    public void sendDataRaw(String data) throws IOException{
        super.sendData(data);
        System.out.println("IRCProtocol: Sent raw data: "+data);
    }
    public void sendDataToChannel(String data) throws IOException{
        String toSend = "PRIVMSG "+channel+" :"+data+"\r\n";
        super.sendData(toSend);
        System.out.println("IRCProtocol: Sent message to channel: "+toSend);
    }
   
   
    public void joinNetwork() throws IOException, InterruptedException{
        System.out.println("Attempting to send user info to IRC network...");
        sendDataRaw("NICK "+nick+"\r\n");
        getData();
        getData();
        getData();
        getData();
        sendDataRaw("USER "+user+" 0 * :"+nick+"\r\n");
        getData();
        getData();
        sendDataRaw("JOIN "+channel+"\r\n");
        getData();
    }
}
