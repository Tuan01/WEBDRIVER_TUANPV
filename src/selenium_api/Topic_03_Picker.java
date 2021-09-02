package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Picker {
	
	WebDriver driver;
		

@BeforeClass
	public void beforeClass() {
	   driver = new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.get("https://www.path2usa.com/travel-companions");
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 
}
	
  @Test
  public void TC_01_isDisplayed() {
		 
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