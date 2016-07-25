package bot;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class MailRead {
	private static int mailCount = 0;
	private static int timeChecked = 0;
	private static int newMailCount = 0;
	private static Message message;
	
	public static String checkMail() throws IOException 
	{
		Properties properties = System.getProperties(); 
		properties.put("mail.store.protocol", "imaps"); 
		String uname="snekabel@gmail.com"; 
		String passw= SKBot.passw;
		String host="imap.gmail.com";//host name like for gmail it is imap.gmail.com 
		
		mailCount = newMailCount;
		
		while(true)
		{
			timeChecked++;
			System.out.println("Checking mail");
			System.out.println("Time checked: " + timeChecked);
			
			//Actually checking mail here
			try 
			{ 
			Session session = Session.getDefaultInstance(properties, null); 
			Store store = session.getStore("imaps"); 
			store.connect(host, uname, passw); 
			Folder folderType = store.getFolder("INBOX"); 
			folderType.open(Folder.READ_WRITE); 
			
			Message messages[] = folderType.getMessages();
			message = messages[messages.length-1];
			newMailCount = messages.length;
			
			// Done
			if(newMailCount > mailCount && timeChecked != 1)
			{
				System.out.println("New Mail!");
				System.out.println("Subject: "+message.getSubject());
				mailCount = newMailCount;
				return message.getSubject();
			}
			
			} 
			catch (NoSuchProviderException e) 
			{ 
			e.printStackTrace(); 
			System.exit(1); 
			} 
			catch (MessagingException e) 
			{ 
			e.printStackTrace(); 
			System.exit(2); 
			}
			
			System.out.println("Total Ammount of Mail: " + mailCount);
			return "noNewMail";
		}
	}

	/*@Override
	public void run() {
		try {
			System.out.println(checkMail());
		} catch (IOException e) {
			System.out.println("Could not check mail");
		}
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Cant sleep");
		}
	}*/
}
