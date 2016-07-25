package bot;

import java.util.Date;

public class Reminder {
	private String reminder;
	private String toNickname;
	private String fromNickname;
	private Date time;
	
	public Reminder(String reminder, String toNickname, String fromNickname, Date time)
	{
		this.reminder = reminder;
		this.toNickname = toNickname;
		this.fromNickname = fromNickname;
		this.time = time;
	}
	
	public String getReminder()
	{
		return reminder;
	}
	
	public String getToNickname()
	{
		return toNickname;
	}
	
	public String getFromNickname()
	{
		return fromNickname;
	}
	
	public Date getTime()
	{
		return time;
	}
	
	public void setReminder(String reminder)
	{
		this.reminder = reminder;
	}
	
	public void setToNickname(String toNickname)
	{
		this.toNickname = toNickname;
	}
	
	public void setFromNickname(String fromNickname)
	{
		this.fromNickname = fromNickname;
	}
	
	public void setTime(Date time)
	{
		this.time = time;
	}
}
