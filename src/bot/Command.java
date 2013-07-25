package bot;

public class Command {
	private int Command_ID;
	private String Command;
	private String Short_Description;
	private String Long_Description;
	
	public Command(String Command, String Short_Description, String Long_Description)
	{
		this.Command = Command;
		this.Short_Description = Short_Description;
		this.Long_Description = Long_Description;
	}
	
	public void setCommand(String Command)
	{
		this.Command = Command;
	}
	
	public void setShortDescription(String Short_Description)
	{
		this.Short_Description = Short_Description;
	}
	
	public void setLongDescription(String Long_Description)
	{
		this.Long_Description = Long_Description;
	}
	
	public int getCommandID()
	{
		return Command_ID;
	}
	
	public String getCommand()
	{
		return Command;
	}
	
	public String getShortDescription()
	{
		return Short_Description;
	}
	
	public String getLongDescription()
	{
		return Long_Description;
	}
}
