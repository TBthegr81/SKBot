package botMk2;

import java.util.ArrayList;

public class Settings {
	private Host MySQL_Host;
	
	private ArrayList<Host> IRC_Hosts = new ArrayList<Host>();
	private ArrayList<String> IRC_Channels;
	private boolean IRC_answerPM;
	
	private ArrayList<Host> Mail_Hosts = new ArrayList<Host>();
	
	private ArrayList<String> Commands_blacklist = new ArrayList<String>();
	
	
	public Host getMySQL_Host() {
		return MySQL_Host;
	}
	public void setMySQL_Host(Host mySQL_Host) {
		MySQL_Host = mySQL_Host;
		System.out.println("MySQL_Host = " + mySQL_Host.toString());
	}
	public ArrayList<Host> getIRC_Hosts() {
		return IRC_Hosts;
	}
	public void setIRC_Hosts(ArrayList<Host> iRC_Hosts) {
		IRC_Hosts = iRC_Hosts;
		System.out.println("IRC_Hosts = " + iRC_Hosts.toString());
	}
	public void addIRC_Host(Host host)
	{
		IRC_Hosts.add(host);
		System.out.println("IRC_Hosts added " + host.toString());
	}
	public ArrayList<String> getIRC_Channels() {
		return IRC_Channels;
	}
	public void setIRC_Channels(ArrayList<String> iRC_Channels) {
		IRC_Channels = iRC_Channels;
		System.out.println("IRC_Channels = " + iRC_Channels.toString());
	}
	public void addIRC_Channel(String channel)
	{
		IRC_Channels.add(channel);
		System.out.println("IRC_Channels added " + channel);
	}
	public ArrayList<Host> getMail_Hosts() {
		return Mail_Hosts;
	}
	public void setMail_Hosts(ArrayList<Host> mail_Hosts) {
		Mail_Hosts = mail_Hosts;
		System.out.println("Mail_Hosts = " + mail_Hosts.toString());
	}
	public void addMail_Host(Host host)
	{
		Mail_Hosts.add(host);
		System.out.println("Mail_Hosts added " + host.toString());
	}
	public ArrayList<String> getCommands_blacklist() {
		return Commands_blacklist;
	}
	public void setCommands_blacklist(ArrayList<String> commands_blacklist) {
		Commands_blacklist = commands_blacklist;
		System.out.println("Commands_blacklist = "+ commands_blacklist.toString());
	}
	public void addCommand_blacklist(String command)
	{
		Commands_blacklist.add(command);
		System.out.println("Commands_blacklist added " + command);
	}
	public boolean isIRC_answerPM() {
		return IRC_answerPM;
	}
	public void setIRC_answerPM(boolean iRC_answerPM) {
		IRC_answerPM = iRC_answerPM;
		System.out.println("IRC_answerPM = " + iRC_answerPM);
	}
	public void parseSettings(ArrayList<String> file)
	{
		for(int i = 0; i < file.size(); i++)
		{
			String[] line = file.get(i).split("\\s+");
			if(line.length > 0)
			{
				if(line[0].equalsIgnoreCase("MySQL_Host") && line.length >= 5)
				{
					setMySQL_Host(new Host(line[1], Integer.parseInt(line[2]), line[3], line[4]));
				}
				else if(line[0].equalsIgnoreCase("IRC_Host") && line.length >= 4)
				{
					addIRC_Host(new Host(line[1], Integer.parseInt(line[2]), line[3]));
				}
				else if(line[0].equalsIgnoreCase("IRC_Channel") && line.length >= 2)
				{
					addIRC_Channel(line[1]);
				}
				else if(line[0].equalsIgnoreCase("Mail_Host") && line.length >= 5)
				{
					addMail_Host(new Host(line[1], Integer.parseInt(line[2]), line[3], line[4]));
				}
				else if(line[0].equalsIgnoreCase("Command_blacklist") && line.length >= 2)
				{
					addCommand_blacklist(line[1]);
				}
			}
		}
	}
	
}
