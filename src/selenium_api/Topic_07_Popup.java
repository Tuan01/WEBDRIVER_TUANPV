package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Popup {
	
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
   

  public void TC_01_Popup() {
	  driver.get("https://www.zingpoll.com/");
	  driver.findElement(By.id("Loginform")).click();
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
	  driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
	  Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
  	  
  }
  
  public void TC_02_Fixed_Popup() {
	  driver.get("https://bni.vn/");
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	  driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();
	  
	  explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
	  driver.findElement(By.xpath("//img[@alt='Close']")).click();
	  
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
	  Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
 
  }
  
  public void TC_03_Random_Popup_Not_DOM() {
	  driver.get("https://shopee.vn/");
	  List<WebElement> popup = driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
	  if(popup.size()>0 && popup.get(0).isDisplayed()) {
		  System.out.println("Popup close");
		  explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='shopee-popup__close-btn']")));
		  driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		  
	  } else {
		  System.out.println("Popup không xuất hiện");
	  }
	  
	  sleepInSecond(5);	  
  }
  
  @Test
  public void TC_04_Random_Popup_IN_DOM() {
	  driver.get("https://shopee.vn/");
	  
	  
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
