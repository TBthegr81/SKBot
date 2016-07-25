package botMk3;

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
	
	public static String[] parseIRCUser(String string) throws Exception
	{
		//:TBRPI!~tb@c-795be255.04-35-6875761.cust.bredbandsbolaget.se
		String[] user = new String[3];
		
		String[] split = string.split("!");
		if(split.length < 2)
		{
			throw new Exception();
		}
		//Nickname
		String nickname = split[0].substring(1);
		if(nickname.length()<1)
		{
			throw new Exception();
		}
		user[0] = nickname;
		System.out.println(nickname);
		String username = split[1].substring(1).split("@")[0];
		if(username.length()<1)
		{
			throw new Exception();
		}
		//Username
		user[1] = username;
		System.out.println(username);
		split = split[1].split("@");
		if(split.length<2)
		{
			throw new Exception();
		}
		//Hostname
		user[2] = split[1];
		System.out.println(user[2]);
		return user;
	}
}
