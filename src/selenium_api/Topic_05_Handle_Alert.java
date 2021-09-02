package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Handle_Alert {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	JavascriptExecutor jsExcutor;
	By resultText = By.xpath("//p[@id='result']");
	

 @BeforeClass
	public void beforeClass() {
	 
	   driver = new ChromeDriver();
	   explicitWait = new WebDriverWait(driver,30);
	   jsExcutor = (JavascriptExecutor) driver;
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();	   	 
 	}
 
  public void TC_01_Accept_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  // Cho cho alert xuat hien
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  // Switch to Alert 
	  alert = driver.switchTo().alert();
	  sleepInSecond(3);
	  
	  Assert.assertEquals(alert.getText(), "I am a JS Alert");
	  alert.accept();
	  
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
	  
	 
  }
  
  public void TC_02_Confirm_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  // Cho cho alert xuat hien
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  // Switch to Alert 
	  alert = driver.switchTo().alert();
	  sleepInSecond(3);
	  
	  Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	  alert.dismiss();
	  
	  Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
	 
  }
  
  @Test
  public void TC_03_Prompt_Alert() {
	  
	  String loginValue = "Automation Testing";
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  alert = driver.switchTo().alert();
	  sleepInSecond(4);
	  alert.sendKeys(loginValue);
	  Assert.assertEquals(alert.getText(),"I am a JS prompt"); 
	  alert.accept();
	  sleepInSecond(3);
//	  Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + loginValue);
	 
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
