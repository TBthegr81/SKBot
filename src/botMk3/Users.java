package botMk3;

import java.util.ArrayList;

public class Users {
	private ArrayList<User> users = new ArrayList<User>();
	
	public void addUser(User user)
	{
		users.add(user);
	}

	public User getUserByNickname(String nickname) throws Exception
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getNickname().contains(nickname))
			{
				return users.get(i);
			}
		}
		throw new Exception();
	}
	public User getUserByUsername(String username) throws Exception
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getUsername().contains(username))
			{
				return users.get(i);
			}
		}
		throw new Exception();
	}
	public User getUserByGivenName(String givenName) throws Exception
	{
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getGivenName().contains(givenName))
			{
				return users.get(i);
			}
		}
		throw new Exception();
	}
	public User getUserByIRCString(String ircstring) throws Exception
	{
		String[] user = User.parseIRCUser(ircstring);
		for(int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getNickname().contains(user[0]) && users.get(i).getUsername().contains(user[1]) && users.get(i).getHost().contains(user[2]))
			{
				return users.get(i);
			}
		}
		throw new Exception();
	}
}
