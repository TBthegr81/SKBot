package botMk2;

public class Host {
	private String hostname;
	private int port;
	private String username;
	private String password;
	
	public String getHostname()
	{
		return hostname;
	}
	public int getPort()
	{
		return port;
	}
	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}
	public void setPort(int port)
	{
		this.port = port;
	}
	public Host(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
	}
	public Host(String hostname, int port, String username)
	{
		this.hostname = hostname;
		this.port = port;
		this.username = username;
	}
	public Host(String hostname, int port, String username, String password)
	{
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String toString()
	{
		String newPassword = password.replaceAll("*", "*");
		return hostname + ":" + port + " - username:" + newPassword; 
	}
}
