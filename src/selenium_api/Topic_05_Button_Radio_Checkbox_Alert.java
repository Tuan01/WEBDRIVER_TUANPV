package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Button_Radio_Checkbox_Alert {
	
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	By loginButton = By.cssSelector(".fhs-btn-login");
	By loginUsername = By.cssSelector("#login_username");
	By loginPassword = By.cssSelector("#login_password");
	By summerRadio = By.xpath("//input[@value='Summer']");
	By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input");
	By indeterminate = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::span/input");

 @BeforeClass
	public void beforeClass() {
	 
	   driver = new ChromeDriver();
	   jsExcutor = (JavascriptExecutor) driver;
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();	   	 
 	}
 

  public void TC01_Button() {
	  driver.get("https://www.fahasa.com/customer/account/create");
	  driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
	  Assert.assertFalse(isElementEnabled(loginButton));
	  driver.findElement(loginUsername).sendKeys("tuan@gmail.com");
	  driver.findElement(loginPassword).sendKeys("123456");
	  sleepInSecond(2);
	  Assert.assertTrue(isElementEnabled(loginButton)); 
	  driver.navigate().refresh();
	  driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
	  removeDisabledAttributeByJS(loginButton);
	  sleepInSecond(5);
	  Assert.assertTrue(isElementEnabled(loginButton));
	  driver.findElement(loginButton).click();
	  sleepInSecond(2); 
	  Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Phone number/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "This infomation can't empty");
	  Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Password']/following-sibling::div[@class='fhs-input-alert']")).getText(), "This infomation can't empty");
	    
  }
  
  
  public void TC_02_Checkbox_SelectAll() {
	  driver.get("https://automationfc.github.io/multiple-fields/");
	  
	  List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
	  // Select All
	  for (WebElement checkbox : checkboxes) {
		checkToCheckboxOrRadio(checkbox);
		sleepInSecond(1);
	  }	  
	  //Verify 	
	  for (WebElement checkbox : checkboxes) {
		  Assert.assertTrue(isElementSelected(checkbox));
	  }	 
	  
	  sleepInSecond(5);
	  
	  //De-selected 
	  for (WebElement checkbox : checkboxes) {
		  uncheckToCheckbox(checkbox);
		  sleepInSecond(1);
	  }	 
	  
	  // Verfiy de-selected 
	  for (WebElement checkbox : checkboxes) {
		  Assert.assertFalse(isElementSelected(checkbox));
	  }	 
	  
  }
  
  public void TC_03_Checkbox_Default() {
	  driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	  checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
	  Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
	  
	  uncheckToCheckbox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
	  Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
	  
	  driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
	  checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
	  Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))));
	  uncheckToCheckbox(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
	  Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))));

  }
  
  @Test
  public void TC_03_Checkbox_Radio_Custom() {
	  driver.get("https://material.angular.io/components/radio/examples");
	  clickToElementByJS(driver.findElement(summerRadio));
	  Assert.assertTrue(isElementSelected(driver.findElement(summerRadio)));
	  
	  // Checkbox
	  
	  driver.get("https://material.angular.io/components/checkbox/examples");
	  clickToElementByJS(driver.findElement(checkedCheckbox));
	  clickToElementByJS(driver.findElement(indeterminate));
	  
	  Assert.assertTrue(isElementSelected(checkedCheckbox));
	  Assert.assertTrue(isElementSelected(indeterminate));
	  
	  sleepInSecond(2);

	  
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
