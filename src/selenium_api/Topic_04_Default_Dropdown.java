package selenium_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_04_Default_Dropdown {
	
	WebDriver driver;
	String firstname, lastname, email, password, confirmpassword; 

@BeforeClass
	public void beforeClass() {
	   driver = new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	   // DATA
	   firstname = "Tuan";
	   lastname = "Pham";
	   email = "auto" + randomUniqueNumber() + "@gmail.com";
	   password = "123456";
	   confirmpassword = "123456";
	   
}
	
public void TC_02_DropdownList() throws Exception {
	 driver.get("https://automationfc.github.io/basic-form/index.html");
	 
	 Select jobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	 Assert.assertFalse(jobRole.isMultiple());
	 jobRole.selectByVisibleText("Mobile Testing");
	 Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Testing");
	 Thread.sleep(3000);
	 jobRole.selectByValue("manual");
	 Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Manual Testing");
	 jobRole.selectByIndex(9);
	 Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Functional UI Testing");
	 int jobItems = jobRole.getOptions().size();
	 Assert.assertEquals(jobItems, 10);	 
	 
	 
}

public void TC_02_Select_Multiple_Dropdown() throws InterruptedException{
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	String testing[] = {"Manual", "Mobile", "Security", "Perfomance"};

	Select select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
	Assert.assertTrue(select.isMultiple());
	
	for (String value : testing) {
		select.selectByVisibleText(value);
		Thread.sleep(300);
//		select.deselectByVisibleText(value);
//		Thread.sleep(500);
	}
	List<WebElement> selectedOption = select.getAllSelectedOptions();
	Assert.assertEquals(selectedOption.size(), 4);
	
	List<String> actualValues = new ArrayList<String>();

	for (WebElement option : selectedOption) {
		actualValues.add(option.getText());
	}
	
	List<String> expectedValues = Arrays.asList(testing);
	
	Assert.assertEquals(actualValues, expectedValues);
}

@Test
public void TC_03_Dropdown_List() throws InterruptedException{
	driver.get("https://demo.nopcommerce.com/");
	Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Welcome to our store']")).isDisplayed());
	driver.findElement(By.xpath("//a[text()='Register']")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Register']")).isDisplayed());
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//span[@class='male']//input")).click();
	Thread.sleep(3000);
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='male']//input")).isSelected());
	
	driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstname);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastname);	
	Thread.sleep(3000);
	
	Select dateofbirthDay = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
	dateofbirthDay.selectByVisibleText("1");
//	Assert.assertEquals(dateofbirthDay.getFirstSelectedOption().getText(), "1");
	int dobItems = dateofbirthDay.getOptions().size();
	Assert.assertEquals(dobItems, 32);
	
	Thread.sleep(3000);
	
	Select dateofbirthMonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
	dateofbirthMonth.selectByVisibleText("May");
//	Assert.assertEquals(dateofbirthMonth.getFirstSelectedOption().getText(), "May");
	int dobmItems = dateofbirthMonth.getOptions().size();
	Assert.assertEquals(dobmItems,13);
	
	Thread.sleep(3000);
	
	Select dateofbirthYear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
	dateofbirthYear.selectByVisibleText("1980");
//	Assert.assertEquals(dateofbirthYear.getFirstSelectedOption().getText(), "1980");
	int dobyItems = dateofbirthYear.getOptions().size();
	Assert.assertEquals(dobyItems,112);
	Thread.sleep(3000);
	
	
	driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(confirmpassword);
	Thread.sleep(3000);

	driver.findElement(By.xpath("//input[@id='register-button']")).click();
	Thread.sleep(3000);
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//div[@class='header-links']//a[text()='My account']")).click();
	Thread.sleep(3000);
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My account - Customer info']")).isDisplayed());
	Thread.sleep(3000);
	
	// Check DOB is displayed correctly 
	
//	Assert.assertEquals("1", dateofbirthDay.getFirstSelectedOption().getText());
	System.out.println(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"));
	System.out.println(driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value"));
	System.out.println(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"));
	
	Thread.sleep(3000);
	
	Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
	Assert.assertEquals(day.getFirstSelectedOption().getText(), "1");
	
	Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
	Assert.assertEquals(month.getFirstSelectedOption().getText(), "May");
	
	Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
	Assert.assertEquals(year.getFirstSelectedOption().getText(), "1980");
	
	
	
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

