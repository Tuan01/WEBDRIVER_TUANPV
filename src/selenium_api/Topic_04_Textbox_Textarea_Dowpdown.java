package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Textbox_Textarea_Dowpdown {
	
	WebDriver driver;
	String name,dob,address,city,state,pin,phone,email,password,customerID,newAddress,newCity;
	
	// BY ELEMENT VARIABLE 
	By nameTextbox = By.xpath("//input[@name='name']");
	By genderTextbox = By.xpath("//input[@name='gender']");
	By dobTextbox = By.xpath("//input[@name='dob']");
	By addressTextArea = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordTextbox = By.xpath("//input[@name='password']");

@BeforeClass
	public void beforeClass() {
	   driver = new ChromeDriver();
//	   driver.get("http://demo.guru99.com/v4/");
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   // DATA TEST
	   name="Automation Test";
	   dob="01/03/1992";
	   address ="So 80348 Hoan Kiem Ha Noi";
	   city="Ha Noi";
	   state="Hoan Kiem";
	   pin="393233";
	   phone="09844334332";
	   email="auto" + randomUniqueNumber() + "@gmail.com";
	   password="123123"; 
	   newAddress="320 Hang Bong";
	   newCity="Bac Giang";
}
	
@Test
public void TC_02_Textbox_Textare() {
	 driver.get("http://demo.guru99.com/v4/");
	 
	 driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr302811");
	 driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mejerEj");
	 driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	 driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	 
	 driver.findElement(nameTextbox).sendKeys(name);
	 driver.findElement(dobTextbox).sendKeys(dob);
	 driver.findElement(addressTextArea).sendKeys(address);
	 driver.findElement(cityTextbox).sendKeys(city);
	 driver.findElement(stateTextbox).sendKeys(state);
	 driver.findElement(pinTextbox).sendKeys(pin);
	 driver.findElement(phoneTextbox).sendKeys(phone);
	 driver.findElement(emailTextbox).sendKeys(email);
	 driver.findElement(passwordTextbox).sendKeys(password);
	 driver.findElement(By.xpath("//input[@name='sub']")).click();

	 // VERIFY NEW CUSTOMER SUCCESSFULL
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phone);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

	// GET DYNAMIC CUSTOMER ID 
	 customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		 
	 // EDIT CUSTOMER
	 driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Edit Customer Form']")).isDisplayed());
	 driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	 driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	 
	 // CHECK 3 FIELS ARE DISABLED
	 Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
	 Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
	 Assert.assertFalse(driver.findElement(dobTextbox).isEnabled());

	 // VERIFY CUSTOMER NAME AND ADDRESS EQUAL INPUT DATA
	 Assert.assertEquals(driver.findElement(nameTextbox).getAttribute("value"),name);
	 Assert.assertEquals(driver.findElement(addressTextArea).getText(),address);
	 
	 // EDIT DATA FOR ADDRESS AND CITY 
	 driver.findElement(addressTextArea).clear();
	 driver.findElement(addressTextArea).sendKeys(newAddress);
	 driver.findElement(cityTextbox).clear();
	 driver.findElement(cityTextbox).sendKeys(newCity);
	 driver.findElement(By.xpath("//input[@name='sub']")).click();
	 
	 // VERIFY NEW DATA AFTER EDIT SUCCESSFUL
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
	 Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
	
}

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  
  public int randomUniqueNumber() {
	  Random rand = new Random();
	  int number = rand.nextInt(999999) + 1;
	  return number;
}

}

