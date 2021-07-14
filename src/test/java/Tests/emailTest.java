package Tests;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class emailTest {
	public WebDriver driver1;
	
	@Test(priority = 1)
	public void startbroser() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		driver1=new ChromeDriver();
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver1.manage().window().maximize();
		verifyMail("bharathsham7171", "8197831383", "Critical security alert");
	}
	
	

	@Test(priority = 2)
	public static boolean verifyMail(String userName, String password, String message) { 
		Folder folder = null; 
		Store store = null; 
		System.out.println("***READING MAILBOX..."); 
		try { 
			Properties props = new Properties(); 
			props.put("mail.store.protocol", "imaps"); 
			Session session = Session.getInstance(props); 
			store = session.getStore("imaps"); 
			store.connect("imap.gmail.com", userName, password); 
			folder = store.getFolder("INBOX"); 
			folder.open(Folder.READ_ONLY); 
			Message[] messages = folder.getMessages(); 
			System.out.println("No of Messages : " + folder.getMessageCount()); 
			System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount()); 
			for (int i = messages.length; i >0; i--) { 
				System.out.println("Reading MESSAGE # " + (i) + "..."); 
				Message msg = messages[i-1]; 
				
				String strMailSubject = "", strMailBody = ""; 
				// Getting mail subject 
				Object subject = msg.getSubject(); 
				// Getting mail body 
				Object content = msg.getContent(); 
				Object date=msg.getReceivedDate();
				Date dt=(Date) date;
				// Casting objects of mail subject and body into String 
				strMailSubject = (String) subject; 
				System.out.println(strMailSubject);
				System.out.println(dt);
				//---- This is what you want to do------ 
				if (strMailSubject.contains(message)) { 
					System.out.println(strMailSubject); 
					break; 
				} 
			} 
			return true; 
		} catch (MessagingException messagingException) { 
			messagingException.printStackTrace(); 
		} catch (IOException ioException) { 
			ioException.printStackTrace(); 
		} finally { 
			if (folder != null) { 
				try { 
					folder.close(true); 
				} catch (MessagingException e) { 
					// TODO Auto-generated catch block 
					e.printStackTrace(); 
				} 
			} 
			if (store != null) { 
				try { 
					store.close(); 
				} catch (MessagingException e) { 
					// TODO Auto-generated catch block 
					e.printStackTrace(); 
				} 
			} 
		} 
		return false; 
	}
	
	
	@Test(priority = 3)
	public void  exitDriver() {
		driver1.quit();
	}


}
