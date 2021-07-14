package Page_Objects;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopingHomePage {
	
	
	
	static By DressButton=By.xpath("(//*[@title='Dresses'])[2]");
	static By EveningDress=By.xpath("(//*[@title='Evening Dresses'])[2]");
    static By ClickonDress=By.xpath("//*[@title='Printed Dress']");
    static By QTY=By.xpath("//*[@id='quantity_wanted']");
    static By DressSize=By.xpath("//*[@id='group_1']");
    static By AddtoCart=By.xpath("//*[@id='add_to_cart']");
    static By ProceddToCart=By.xpath("//*[contains(text(),'Proceed to checkout')]");
    static By ProceedToCheckout2=By.xpath("(//*[@title='Proceed to checkout'])[2]");
    static By EmailField= By.xpath("//*[@id='email_create']");
    static By CreateAccountButton=By.xpath("//*[@id='SubmitCreate']");
    
    
   public void SelectDress(WebDriver driver,String mailid)
   
   {
	   Actions act=new Actions(driver);
	   act.moveToElement(driver.findElement(DressButton)).click(driver.findElement(EveningDress)).perform();
	   
	   Wait<WebDriver> wait2=new WebDriverWait(driver,20);
	   WebElement clickdress=wait2.until(ExpectedConditions.elementToBeClickable(ClickonDress));
	   clickdress.click();
	   
	   driver.switchTo().frame(0);
	   Wait <WebDriver> wait=new FluentWait<WebDriver>(driver)
			.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(2, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class);
	   WebElement Qty=wait.until(new Function<WebDriver,WebElement>(){
		public WebElement apply(WebDriver driver) {
			// TODO Auto-generated method stub
			return driver.findElement(QTY);
			}
	  });
	
	 Qty.sendKeys("10");
	 

	 Select Size=new Select(driver.findElement(DressSize));
	        Size.selectByVisibleText("L");
	 driver.findElement(AddtoCart).click();
	 driver.switchTo().defaultContent();
	 driver.findElement(ProceddToCart).click();
	 driver.findElement(ProceedToCheckout2).click();
	 driver.findElement(EmailField).sendKeys(mailid);
	 driver.findElement(CreateAccountButton).click();
	 	   
   }
   
  
    
		
	
	
}
