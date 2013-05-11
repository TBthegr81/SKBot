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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPSocket{
    private String host = "", data = "";
    private int port = 0;
    private Socket socket;
    DataOutputStream outPutStream = null;
    BufferedReader inputStream = null;
    public TCPSocket(String host, int port) throws UnknownHostException, IOException{
        connectToHost(host, port);
        
    }
    public void connectToHost(String host, int port) throws UnknownHostException, IOException{
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
            outPutStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            throw new UnknownHostException("I could not found any host with that hostname/address");
        } catch (IOException e) {
            throw new IOException("I could not establish an I/O channel with "+host);
        }
    }
    public void disconnectFromHost() throws IOException{
        host = "";
        port = 0;
        try {
            socket.close();
            outPutStream.close();
            inputStream.close();
        } catch (IOException ex) {
            throw new IOException("Could not remove connection to: "+host+"\n"+ex);
        }
    }
    
    public String getData() throws IOException{
       return inputStream.readLine();
    }
    public void sendData(String data) throws IOException{
        outPutStream.writeBytes(data);
    }
    @Override
    public String toString(){
        return socket.toString();
    }
}
