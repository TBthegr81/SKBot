package bot;

public class Message {
	private String nickname;
	private String username;
	private String host;
	private String command;
	private String channel;
	private String text;
	
	public Message(String nickname, String username, String host, String command, String channel, String text)
	{
		this.nickname = nickname;
		this.username = username;
		this.host = host;
		this.command = command;
		this.channel = channel;
		this.text = text;
	}
	
	public Message(String raw)
	{
		try{
			//ALL THIS FAILES, FIX IT
			System.err.println("Cuttardata");
			int n = raw.indexOf("!");
			String newRaw = raw.substring(0, n);
			System.err.print("newRaw: " + newRaw);
			this.nickname = newRaw;
			raw = raw.substring(n);
			System.err.print("Nickname: " + nickname);
			
			n = newRaw.indexOf("@");
			this.username = raw.substring(0, n);
			raw = raw.substring(n);
			System.err.print("Username: " + username);
			
			n = newRaw.indexOf("\\s+");
			this.host = raw.substring(0, n);
			raw = raw.substring(n);
			System.err.print("Host: " + host);
			
			n = newRaw.indexOf("\\s+");
			this.command = raw.substring(0, n);
			raw = raw.substring(n);
			System.err.print("Command: " + command);
			
			n = newRaw.indexOf("\\s+");
			this.channel = raw.substring(0, n);
			raw = raw.substring(n);
			System.err.print("Channel: " + channel);
			
			n = newRaw.indexOf("\\s+");
			this.text = raw.substring(0, n);
			raw = raw.substring(n);
			System.err.print("Text: " + text);
		} catch (IndexOutOfBoundsException e) {
			this.nickname = "null";
			this.username = "null";
			this.command = "Unknown";
			this.host = "null";
			this.channel = "null";
			this.text = raw;
		}
		
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setHost(String host)
	{
		this.host = host;
	}
	
	public void setCommand(String command)
	{
		this.command = command;
	}
	
	public void setChannel(String channel)
	{
		this.channel = channel;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getHost()
	{
		return host;
	}
	
	public String getCommand()
	{
		return command;
	}
	
	public String getChannel()
	{
		return channel;
	}
	
	public String getText()
	{
		return text;
	}
}
