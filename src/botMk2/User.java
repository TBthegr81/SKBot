package botMk2;

public class User {
	private String username;
	private String nickname;
	private String givenName;
	private String surName;
	private String email;
	private String host;
	private String ip;
	@SuppressWarnings("unused")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email.contains("@") && email.contains(".")) 
		{
			this.email = email;
		}
		else
		{
			System.out.println("Cant set Email, not valid!");
		}
	}
	public String getPassword() {
		return "lol";
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public User()
	{
		
	}
	
	public boolean parseIRCUser(String string)
	{
		//:TBRPI!~tb@c-795be255.04-35-6875761.cust.bredbandsbolaget.se
		String[] split = string.split("!");
		if(split.length < 2)
		{
			return false;
		}
		String nickname = split[0].substring(1);
		if(nickname.length()<1)
		{
			return false;
		}
		setNickname(nickname);
		System.out.println(nickname);
		String username = split[1].substring(1).split("@")[0];
		if(username.length()<1)
		{
			return false;
		}
		setUsername(username);
		System.out.println(username);
		split = split[1].split("@");
		if(split.length<2)
		{
			return false;
		}
		setHost(split[1]);
		System.out.println(host);
		return true;
	}
}
