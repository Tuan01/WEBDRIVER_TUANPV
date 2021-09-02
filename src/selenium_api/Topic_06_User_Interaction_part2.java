package selenium_api;

import java.util.concurrent.TimeUnit;

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

public class Topic_06_User_Interaction_part2 {
	
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
   
  public void TC_01_Right_Click() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();  
	  action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	  sleepInSecond(3);
	  // Verify Quit Contain (Visible/ hover status)
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-visible')) and (contains(@class,'context-menu-hover'))]")).isDisplayed());
	  
	  // Click to Quit
	  driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-visible')) and (contains(@class,'context-menu-hover'))]")).click();
	  sleepInSecond(3);
	  // Verify alert displayed 
	  Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
	  driver.switchTo().alert().accept();
	   	  
  }
  
  @Test
  public void TC_02_Drag_And_Drop() {
	  driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
	  
	  	  
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
