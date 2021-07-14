package Tests;


import java.awt.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Page_Objects.ShopingHomePage;
import Page_Objects.SignInPage;

public class EveningDressSelection  {
	
	
	
	public static WebDriver driver;
	ShopingHomePage Shopingpage;
	SignInPage signin;
	
	@BeforeSuite
	public void startrecord() throws Exception {
		VideoRecorder_utlity.startRecord("DressSelections");
	}
	
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		
	}
	
@Test
	public void SelectEveningDress() 
	{
	 Shopingpage=new ShopingHomePage();
	 Shopingpage.SelectDress(driver,"fsdfdsferdsfdsdddfdsf@gmail.com");
	 System.out.println("Dress has Been Selected");
	 //SignInPage
	 signin=new SignInPage();
	 signin.SignIn(driver);
	
    }



@AfterMethod
public void quitBrowser()
{
	driver.quit();
}

@AfterSuite
public void stopRecord() throws Exception {
	VideoRecorder_utlity.stopRecord();
}
}