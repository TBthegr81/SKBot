package bot;

public class User {
	private String nickname;
	private String hostname;
	private int User_ID;
	private boolean loggedin;
	
	public User(String nickname,String hostname)
	{
		this.nickname = nickname;
		this.hostname = hostname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}
	
	public void setUser_ID(int User_ID)
	{
		this.User_ID = User_ID;
	}
	
	public void setLoggedin(boolean loggedin)
	{
		this.loggedin = loggedin;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public String getHostname()
	{
		return hostname;
	}
	
	public int getUser_ID()
	{
		return User_ID;
	}
	
	public boolean getLoggedin()
	{
		return loggedin;
	}
}
