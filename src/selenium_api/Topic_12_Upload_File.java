package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_Upload_File {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	Actions action;
	JavascriptExecutor jsExecutor;
	
	


 @BeforeClass
	public void beforeClass() {
	 
	   driver = new ChromeDriver();
	   explicitWait = new WebDriverWait(driver,30);
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();	   	 
 	}
 
  public void TC_01_Handle_DataPicker() {
	  driver.get("https://www.path2usa.com/travel-companions");
	 
	  driver.findElement(By.xpath("//input[@id='travel_date']")).click();
	  
	  List<WebElement> dates = driver.findElements(By.className("day"));
	  
	  int count = driver.findElements(By.className("day")).size();
	  
	  for (int i=0; i < count; i++ ) {
		  String text = driver.findElements(By.className("day")).get(i).getText();
		  if(text.equalsIgnoreCase("23")) {
			  driver.findElements(By.className("day")).get(i).click();
			  break;
		  }
	  }
	  
	  
	
  }
  

	 
  
 @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
