package Page_Objects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

    static By EmailField=By.xpath("//*[@id='email_create']");
    static By  CreateAccountButton=By.xpath("//*[@id='SubmitCreate']");
    static By  GenderMaleRadioButton=By.xpath("//*[@id='id_gender1']");
    static By  FirstName=By.xpath("//*[@id='customer_firstname']");
    static By  LastName= By.xpath("//*[@id='customer_lastname']");
    static By  Email=By.xpath("//*[@id='email']");
    static By Password=By.xpath("//*[@id='passwd']");
    static By Day_DOB=By.xpath("//*[@id='days']");
    static By Month_DOB=By.xpath("//*[@id='months']");
    static By YEAR_DOB=By.xpath("//*[@id='years']");
    static By Company=By.xpath("//*[@id='company']");
    static By Address1=By.xpath("//*[@id='address1']");
    static By Address2=By.xpath("//*[@id='address2']");
    static By City=By.xpath("//*[@id='city']");
    static By State=By.xpath("//*[@id='id_state']");
    static By PostalCode=By.xpath("//*[@id='postcode']");
    static By Country=By.xpath("//*[@id='id_country']");
    static By Additionalinfo=By.xpath("//*[@id='other']");
    static By Phone=By.xpath("//*[@id='other']");
    static By MobilePhone=By.xpath("//*[@id='phone_mobile']");
    static By AliasAddress =By.xpath("//*[@id='alias']");
    static By Register =By.xpath("//*[contains(text(),'Register')]");
    static By TermsOfServices=By.xpath("//*[@id='cgv']");
    static By ProceedToCheckoutAddressPage=By.xpath("//*[@class='button btn btn-default button-medium']");
    static By ProceedToCheckOutShippingPage=By.xpath("//*[@class='button btn btn-default standard-checkout button-medium']");
    static By PayByCheck=By.xpath("//*[@title='Pay by bank wire']");
    static By ConfirmOrder=By.xpath("//*[contains(text(),'I confirm my order')]");
    
    
    public static void SignIn(WebDriver driver)
    {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	WebDriverWait wait3=new WebDriverWait(driver, 20);
    	wait3.until(ExpectedConditions.elementToBeClickable(driver.findElement(GenderMaleRadioButton)));
    	
    	
       	driver.findElement(GenderMaleRadioButton).click();
    	driver.findElement(FirstName).sendKeys("Hi");
    	driver.findElement(LastName).sendKeys("Hello"); 	
    	driver.findElement(Password).sendKeys("uco@178jqB");
    	Select day=new Select(driver.findElement(Day_DOB));
    	day.selectByValue("20");
    	Select month=new Select(driver.findElement(Month_DOB));
    	month.selectByValue("10");
    	Select year=new Select(driver.findElement(YEAR_DOB));
    	year.selectByValue("1995");
    	driver.findElement(Company).sendKeys("TCS");
    	driver.findElement(Address1).sendKeys("No71 Kammavari nagar");
    	driver.findElement(Address2).sendKeys("Hoskote");
    	driver.findElement(City).sendKeys("Benagaluru");
    	Select state= new Select(driver.findElement(State));
    	state.selectByIndex(2);
    	driver.findElement(PostalCode).sendKeys("00000");
    	Select country =new Select (driver.findElement(Country));
    	country.selectByVisibleText("United States");
    	driver.findElement(Additionalinfo).sendKeys("Hi Hello Good Morning");
    	driver.findElement(Phone).sendKeys("4567891234");
    	driver.findElement(MobilePhone).sendKeys("2583697412");
    	driver.findElement(AliasAddress).sendKeys("Very Good Morning");
    	driver.findElement(Register).click();
    	driver.findElement(ProceedToCheckoutAddressPage).click();
    	driver.findElement(TermsOfServices).click();
    	driver.findElement(ProceedToCheckOutShippingPage).click();
    	driver.findElement(PayByCheck).click();
    	driver.findElement(ConfirmOrder).click();
   
    	
    	
    }
    
    
}
