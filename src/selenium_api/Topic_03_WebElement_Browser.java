package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebElement_Browser {
	
	WebDriver driver;
		

@BeforeClass
	public void beforeClass() {
	   driver = new ChromeDriver();
//	   driver.get("http://demo.guru99.com/V4/");
//	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
		 
}
	
  @Test
  public void TC_01_LoginWithUserPassEmpty() {
	  driver.get("http://live.demoguru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Home page");
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
	  driver.navigate().back();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@id='login-form']")).isDisplayed());
	  
	  String loginUrl = driver.getCurrentUrl();
	  Assert.assertEquals(loginUrl,"http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  driver.navigate().forward();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//form[@id='form-validate']")).isDisplayed());
	  
	  
	  String registerUrl = driver.getCurrentUrl();
	  Assert.assertEquals(registerUrl,"http://live.demoguru99.com/index.php/customer/account/create/");
  }
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}