package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebElement {
	
	WebDriver driver;
		

@BeforeClass
	public void beforeClass() {
	   driver = new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.get("https://automationfc.github.io/basic-form/");
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 
}
	
//  @Test
  public void TC_01_isDisplayed() {
	  WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
	  WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
	  WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
	  
	  
	  Assert.assertTrue(isControlDisplayed(emailTextbox));
	  Assert.assertTrue(isControlDisplayed(ageRadioButton));
	  Assert.assertTrue(isControlDisplayed(educationTextArea));
	  
	  if(isControlDisplayed(emailTextbox) && isControlDisplayed(educationTextArea)) {
		  emailTextbox.sendKeys("Automation Testing");
		  educationTextArea.sendKeys("Automation Testing");
	  }
  }
  
  @Test
  public void TC_02_isEnabled() {
	  WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
	  WebElement ageRadioButton = driver.findElement(By.xpath("//input[@id='under_18']"));
	  WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
	  WebElement educationTextArea = driver.findElement(By.xpath("//textarea[@id='edu']"));
	  isControlEnabled(emailTextbox);
	  isControlEnabled(ageRadioButton);
	  isControlEnabled(passwordTextbox);
	  isControlEnabled(educationTextArea);
  
	  
  }
  
  
  public boolean isControlDisplayed(WebElement element) {
		 return	element.isDisplayed();
	 }
  
  
  public void isControlEnabled(WebElement element) {
	  if(element.isEnabled()) {
		  System.out.print("Element is enabled");
	  } else {
		  System.out.print("Element is disabled");
	  }
  }
  
  public boolean isControlSelected(String locator) {
	  WebElement element = driver.findElement(By.xpath(locator));
	  return element.isSelected();
  }
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}