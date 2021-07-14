package Tests;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class pages {
	
	public WebDriver driver;
    FluentWait<WebDriver> wait;
    WebDriverWait wait2;
	
	
    
    public pages() {
    	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait2=new WebDriverWait(driver, 20);
		wait=new FluentWait<WebDriver>(driver);
    }
	
	public WebElement waitForelement(final String xpath) {
		//System.out.println("Wait till the element is visible");
		wait.withTimeout(Duration.ofSeconds(30))
		.pollingEvery(Duration.ofSeconds(2))
		.ignoring(NoSuchElementException.class);
	WebElement ele=wait.until(new Function<WebDriver, WebElement>() {
		public WebElement apply(WebDriver driver) {
		// TODO Auto-generated method stub
			return driver.findElement(By.xpath(xpath));		 
	       }
	});
		return ele;
	}
	
	public void waittillElement(WebElement ele) {
		wait2.until(ExpectedConditions.visibilityOf(ele));
		wait2.until(ExpectedConditions.elementToBeClickable(ele));
		wait2.until(ExpectedConditions.refreshed(
		        ExpectedConditions.elementToBeClickable(ele)));

	}
	
	public void initiateBrowser() {		
		driver.get("http://sts.karnataka.gov.in/STS");
	}
	
	  public void selectDate(String expDate) throws Exception{
			  List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		       //click on calendar button
		        driver.findElement(By.xpath("(//span[@class='k-icon k-i-calendar'])[2]")).click();
		       Thread.sleep(2000);
		       //click on center button
		        driver.findElement(By.xpath("//div[@class='k-header']//a[contains(@class,'k-nav-fast')]")).click();
		        //splitting date
		        String Date[]=expDate.split("/");
		        String expDay = null;
		        if(Date[0].contains("0")){
		         expDay=Date[0].replaceAll("0", "");
		        }
		        else{
		         expDay=Date[0];
		        }
		        String expMonth=Date[1];
		        String expYear = Date[2];
		        
		        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
		        
		       
		        if(Integer.parseInt(expYear)<Calendar.getInstance().get(Calendar.YEAR)){
		            int count=Calendar.getInstance().get(Calendar.YEAR)-(Integer.parseInt(expYear));
		         for(int i=0;i<count;i++){
		             driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-w")).click(); 
		         }
		        }
		        
		         else if(Integer.parseInt(expYear)>Calendar.getInstance().get(Calendar.YEAR)){
		             int count1=Integer.parseInt(expYear)-Calendar.getInstance().get(Calendar.YEAR);
		          for(int i=0;i<count1;i++){
		          driver.findElement(By.cssSelector("span.k-icon.k-i-arrow-e")).click(); 
		         }
		         
		        }else {
		        	
		        }
		           Thread.sleep(1000);
		           driver.findElement(By.linkText(months.get(Integer.parseInt(expMonth)-1))).click();
		           Thread.sleep(1000);  
		           driver.findElement(By.linkText(expDay)).click();
			  
		  
		     

		        }

}
