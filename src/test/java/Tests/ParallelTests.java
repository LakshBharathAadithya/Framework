package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import Page_Objects.ShopingHomePage;
import Page_Objects.SignInPage;

public class ParallelTests {
	
	

	
	@Parameters("Email")
	@Test
	public void test1(String Email)
	
	{
		WebDriver driver1;
		ShopingHomePage Shopingpage;
		SignInPage signin;
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		driver1=new ChromeDriver();
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver1.manage().window().maximize();
		driver1.get("http://automationpractice.com/index.php");
		Shopingpage=new ShopingHomePage();
		 Shopingpage.SelectDress(driver1,Email);
		 System.out.println("Dress has Been Selected");
		 //SignInPage
		 signin=new SignInPage();
		 signin.SignIn(driver1);
		 
		 driver1.quit();
	}
	
	
	@Parameters("Email2")
	@Test
	public void test2(String Email2)
	
	{
		WebDriver driver;
		ShopingHomePage Shopingpage;
		SignInPage signin;
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		Shopingpage=new ShopingHomePage();
		 Shopingpage.SelectDress(driver,Email2);
		 System.out.println("Dress has Been Selected");
		 //SignInPage
		 signin=new SignInPage();
		 signin.SignIn(driver);
		 
		 driver.quit();
	}

}
