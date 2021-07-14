package Tests;

import java.awt.Desktop.Action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SATS extends pages {

	WebElement ele;
	boolean val;
	public String satsWin=null;
	public String statsAdmit=null;
	public String admitThrough=null;
	
	
	

	@BeforeTest
	public void firststep() throws Exception {
		
		initiateBrowser();
		satsWin = driver.getWindowHandle();
		driver.findElement(By.xpath("//i[@class='fa fa-lg fa-language']")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'SATS')])[1]")).click();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		// System.out.println(driver.getTitle());
		driver.switchTo().window(tabs.get(1));
		// System.out.println(driver.getTitle());
		// System.out.println(tabs);

		WebElement login_but = driver.findElement(By.xpath("//*[text()='login' and @href='#']"));
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.elementToBeClickable(login_but)).click();
		wait2.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='userName']"))))
				.click();
		driver.findElement(By.xpath("//*[@id='userName']")).sendKeys("29210418502");
		driver.findElement(By.xpath("//*[@id='xxZTT9p2wQ']")).sendKeys("Hosakote@123");
		String Captcha = driver.findElement(By.xpath("//*[@id='randomfield']")).getText();
		driver.findElement(By.xpath("//*[@id='txtcode']")).sendKeys(Captcha);
		driver.findElement(By.xpath("//*[@id='xmlLogin']")).click();
		// driver.findElement(By.xpath("(//*[@class='close'])[2]")).click();
		WebElement Class10 = driver
				.findElement(By.xpath("(//*[contains(text(),'Student Management- Class 1 to 10')])[2]"));
		WebElement cceResult = driver.findElement(By.xpath("(//*[contains(text(),'CCE Results')])[2]"));
		WebElement progressReport = driver.findElement(By.xpath("(//*[contains(text(),'Generate Result Form')])[2]"));
		WebElement admissionDetails = driver.findElement(By.xpath("(//*[contains(text(),'Admission Details')])[2]"));
		WebElement admissionThrough = driver
				.findElement(By.xpath("(//*[contains(text(),'Admission through Promotion')])[2]"));
		Actions act = new Actions(driver);
		// act.moveToElement(Class10).moveToElement(cceResult).click(progressReport).build().perform();
		act.moveToElement(Class10).moveToElement(admissionDetails).click(admissionThrough).build().perform();
		// act.moveToElement(Class10).click(Digital_Communication)
	}

	@DataProvider(name = "classes")
	public Object[][] Data() {
		return new Object[][]{{1},{2},{3},{4},{5},{6},{7},{8},{9}};
	}
	//{1},{2},{3},{4},,

	// @Test(dataProvider = "classes")
	public void Digital_details(int index) throws InterruptedException {

		System.out.println("************Standard_" + index + "************");
		for (int k = 1; k <= 6; k++) {
			System.out.println("*****" + "Semister_" + k + "*****");
			WebElement Web = waitForelement("//*[@id='std']");
			Select standard = new Select(Web);
			standard.selectByIndex(index);
			Select medium = new Select(driver.findElement(By.xpath("//select[@id='stu_schoolmedium']")));
			medium.selectByVisibleText("English");
			Thread.sleep(2000);
			Select semister = new Select(driver.findElement(By.xpath("//select[@id='semester_id']")));
			Select group = new Select(driver.findElement(By.xpath("//select[@id='group']")));
			List<WebElement> subs = group.getOptions();

			for (int m = 1; m < subs.size(); m++) {
				// driver.navigate().refresh();
				try {

					System.out.println("*****" + subs.get(m).getText() + "*****");
					group.selectByIndex(m);
					semister.selectByIndex(k);

				} catch (StaleElementReferenceException ex) {
					waitForelement("//select[@id='group']");
					Select group1 = new Select(driver.findElement(By.xpath("//select[@id='group']")));
					group1.selectByIndex(m);
					List<WebElement> subs1 = group1.getOptions();
					System.out.println("*****" + subs1.get(m).getText() + "*****");
					Select semister1 = new Select(driver.findElement(By.xpath("//select[@id='semester_id']")));
					semister1.selectByIndex(k);
				}
				driver.findElement(By.xpath("//*[@type='button' and @value='Search']")).click();
				WebElement Table = driver.findElement(By.xpath("//table[@id='example']/tbody"));
				List<WebElement> No_Rows = Table.findElements(By.tagName("tr"));
				if (No_Rows.isEmpty() == false) {
					System.out.println("The Number of rows in a students of class " + index + " is " + No_Rows.size());
					int p = 1;
					for (int i = 1; i <= No_Rows.size(); i++) {
						if (i > 80 && p < 2) {
							driver.findElement(By.xpath("//*[@type='button' and @value='Submit']")).click();
							Thread.sleep(3000);
							Alert al = driver.switchTo().alert();
							System.out.println(al.getText());
							al.accept();
							System.out.println("Completed the exam for the semister " + k);
							ele = waitForelement("//*[@id='std']");
							Select standard80 = new Select(ele);
							standard80.selectByIndex(index);
							Select medium80 = new Select(
									driver.findElement(By.xpath("//select[@id='stu_schoolmedium']")));
							medium80.selectByVisibleText("English");
							Thread.sleep(2000);
							Select semister80 = new Select(driver.findElement(By.xpath("//select[@id='semester_id']")));
							Select group80 = new Select(driver.findElement(By.xpath("//select[@id='group']")));
							group80.selectByIndex(m);
							semister80.selectByIndex(k);
							driver.findElement(By.xpath("//*[@type='button' and @value='Search']")).click();
							p++;
						}

						int cols = driver.findElements(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td"))
								.size();
						// System.out.println(cols);
						if (cols > 4) {
							val = true;
							int size = driver
									.findElements(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[5]/select"))
									.size();
							// System.out.println(size);
							if (size == 1) {
								for (int j = 5; j <= cols; j++) {
									Select grade = new Select(driver.findElement(By
											.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[" + j + "]/select")));
									grade.selectByIndex(0);
									grade.selectByVisibleText("A");
								}
							} else {
								System.out.println("Row is empty ");
								break;
							}
						} else {
							val = false;
						}

					}
					if (val == true) {
						driver.findElement(By.xpath("//*[@type='button' and @value='Submit']")).click();
						Thread.sleep(3000);
						Alert al = driver.switchTo().alert();
						System.out.println(al.getText());
						al.accept();
						ele = waitForelement("//*[@id='std']");
						break;
					} else {
						System.out.println("No Update is done");
					}
				}
			}

		}

	}

	@Test(dataProvider = "classes")
	public void admissionthrough(int index) throws InterruptedException {
		try {		
		WebElement Web = waitForelement("//*[@id='standard']");
		Select standard = new Select(Web);
		standard.selectByValue(String.valueOf(index));
		driver.findElement(By.xpath("//*[@type='button' and @value='Search']")).click();
		String xpath1="//table[@id='studentDetailTablee']/tbody/tr";
		waitForelement(xpath1);
		WebElement Table = driver.findElement(By.xpath("//table[@id='studentDetailTablee']/tbody"));
					
		List<WebElement> No_Rows = Table.findElements(By.tagName("tr"));
		if (driver.findElements(By.xpath("//table[@id='studentDetailTablee']/tbody/tr/td[5]")).size()!=0) {
			for (int i = 1; i <= No_Rows.size(); i++) {
				statsAdmit = driver.getWindowHandle();
				//System.out.println(statsAdmit);
				driver.findElement(By.xpath("//table[@id='studentDetailTablee']/tbody/tr[" + i + "]/td[5]")).click();
				Thread.sleep(3000);
				//System.out.println(driver.getWindowHandle());
				Set<String> windows = driver.getWindowHandles();
				for(String win:windows) {
					if (win.contentEquals(statsAdmit) || win.contentEquals(satsWin)) {
						continue;
					}
					driver.switchTo().window(win);
					WebElement element = waitForelement("//select[@id='sectionId']");
				if (driver.findElements(By.xpath("//select[@id='sectionId']")).size() != 0) {
					Select sec = new Select(driver.findElement(By.xpath("//select[@id='sectionId']")));
					Select langGroup = new Select(driver.findElement(By.xpath("//select[@id='langGroup']")));
					sec.selectByIndex(1);
					langGroup.selectByIndex(1);
					WebElement mothertounge = driver.findElement(By.xpath("//select[@id='mother_tonuge']"));
					if(mothertounge.isSelected()!=true) {
						Select motherTounge = new Select(mothertounge);
						motherTounge.selectByVisibleText("Kannada");
					}
					WebElement village= driver.findElement(By.xpath("//select[@id='addressvillage_id']"));
					
					if(!village.isSelected()) {
						Select selectvillage = new Select(village);
						selectvillage.selectByIndex(1);						
					}
					/*
					 * WebElement cal =
					 * driver.findElement(By.xpath("(//span[@class='k-icon k-i-calendar'])[2]"));
					 * Actions act =new Actions(driver); act.moveToElement(cal).build().perform();
					 */
					
					//
					String date= driver.findElement(By.xpath("//input[@id='st_adm_date']")).getAttribute("value");
					System.out.println(date);
					
					if(date.isEmpty()) {
						try {
							driver.findElement(By.xpath("(//span[@class='k-icon k-i-calendar'])[2]")).click();
							selectDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));;							
						} catch (ElementNotInteractableException ex) {
							selectDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
						}
						
					}
					System.out.println();
				
					Select  caste=new Select(driver.findElement(By.xpath("//select[@id='student_category']")));
					String val = caste.getFirstSelectedOption().getText();
					if (val.contentEquals("ST") || val.contentEquals("SC")) {
						driver.findElement(By.xpath("//input[@id='stdu_card_no']")).sendKeys("RD");
						
					}					
					driver.findElement(By.xpath("//input[@id='updateButton1']")).click();
					try {
						Alert al = driver.switchTo().alert();
						System.out.println(al.getText());
						if(al.getText().contains("Minimum Age Criteria")) {
							al.accept();
							Alert al2 = driver.switchTo().alert();
							if(al2.getText().contains("Please Specify Age Reaason")) {
								al2.accept();
								Thread.sleep(2000);
								driver.findElement(By.xpath("//input[@id='age_reason']")).sendKeys("Parents Mistake");
								driver.findElement(By.xpath("//input[@id='updateButton1']")).click();
								Alert al3 = driver.switchTo().alert();
								al3.accept();
								Alert al4 = driver.switchTo().alert();
								al4.accept();
							}else {
								al2.accept();
							}
												
							//Assert.fail("Failed in Age criteria");
						}else {
							al.accept();							
						}
						
						String xpath = "//img[@alt='Success']";
						waitForelement(xpath);						
					}catch (Exception ex) {
						System.out.println("class "+ index+"and student ID is "+i+" Student's is alreday submitted");
						ex.getMessage();
						System.out.println("Alert not present");
					}					
					Thread.sleep(3000);
					driver.close();
					// System.out.println(driver.getWindowHandle());
					driver.switchTo().window(statsAdmit);

				}
			  }
			}

		} else {
			System.out.println("No rows are present for the class "+index);
		}
		

	}catch (Exception e) {
		e.getMessage();
		e.printStackTrace();
		
	}
	}

	@AfterMethod
	public void Refresh() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(5000);

	}

	@AfterTest
	public void close_Browser() {
		driver.quit();
	}

}
