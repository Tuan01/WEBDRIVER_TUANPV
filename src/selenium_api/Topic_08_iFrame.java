package selenium_api;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_iFrame {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	Actions action;
	JavascriptExecutor jsExcutor;
	
	

 @BeforeClass
	public void beforeClass() {
	 
	   driver = new ChromeDriver();
	   explicitWait = new WebDriverWait(driver,30);
	   action = new Actions(driver);
	   jsExcutor = (JavascriptExecutor) driver;
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();	   	 
 	}


  public void TC_01_iFrame() {
	  driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");	
	  // Switch to iframe
	  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Facebook Social Plugin')]")));
	  Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");
//	  Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText(), "2,140 likes");
	  
	  String likeText = driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
	  int likeNumber = Integer.parseInt(likeText.split(" ")[0].replace(",", ""));
	  System.out.println(likeNumber);
	  assertThat(likeNumber, greaterThan(2000)); 	 
	  
	  // Switch to Top Window
	  driver.switchTo().defaultContent();
	  Assert.assertEquals(driver.findElement(By.className("post-title")).getText(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
	  
	  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
	  Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");  
  }

  @Test
  public void TC_02_iFrame() {
	  driver.get("https://kyna.vn/");	
	  // Switch to iframe
	  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'www.facebook.com')]")));
	  Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']")).getText(),"Kyna.vn");
	  
	  String likeText = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
	  sleepInSecond(3);
	  int likeNumber = Integer.parseInt(likeText.split(" ")[0].replace(",", ""));
	  System.out.println(likeNumber);
//	  assertThat(likeNumber, greaterThan(2000)); 	 
}
 
  
  public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }  
  
  public boolean isElementEnabled(By by) {
	  if (driver.findElement(by).isEnabled()){
		  System.out.println("Element is enabled");
		  return true;
	  } else {
		  System.out.println("Element is disabled");
		  return false;
	  }	  
  } 
  
  public boolean isElementSelected(By by) {
	  if(driver.findElement(by).isSelected()) {
		  System.out.println("Element is selected");
		  return true;
	  } else {
		  System.out.println("Element is de-selected");
		  return false;
	  }
  }
  
  public boolean isElementSelected(WebElement element) {
	  if(element.isSelected()) {
		  System.out.println("Element is selected");
		  return true;
	  } else {
		  System.out.println("Element is de-selected");
		  return false;
	  }
  }
  
  public void checkToCheckboxOrRadio(WebElement element) {
	  if(!element.isSelected()) {
		  element.click();
	  }
  }
  
  public void uncheckToCheckbox(WebElement element) {
	  if(element.isSelected()) {
		  element.click();
	  }
  }
  public void removeDisabledAttributeByJS(By by) {
	  WebElement element = driver.findElement(by);
	  jsExcutor.executeScript("arguments[0].removeAttribute('disabled')", element);
  }
  
  public void clickToElementByJS(WebElement element) {
	  jsExcutor.executeScript("arguments[0].click();", element);
  }
  
 @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
