package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class Topic_06_User_Interaction {
	
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
   
  public void TC_01_Hover() {
	  driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
	  action.moveToElement(driver.findElement(By.id("age"))).perform();
	  sleepInSecond(1);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");  
  }
  

  public void TC_03_Hover() {
	  driver.get("https://hn.telio.vn/");
	  action.moveToElement(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='Đồ uống']"))).perform();
	  action.click(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bia']"))).perform();
	   // DOM
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[@id='page-title-heading']//span[text()='Bia']")).isDisplayed());
	  	
	  // UI
	  Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='page-title-heading']//span")).getText(), "BIA");
  }
  
 
  public void TC_03_Click_And_Hold() {
	  driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
	  // Tạo ra 1 list chứa hết tất cả 12 number 
	  
	  List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
	  System.out.println("All number = " + allNumber.size());
	  action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(10)).release().perform();
	  sleepInSecond(5);	  
	  List<WebElement> allNumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
	  System.out.print("Number selected = " + allNumberSelected.size());  
	  Assert.assertEquals(allNumberSelected.size(), 9);
  
  }
  

  public void TC_04_Click_And_Hold_Random() {
	  driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
	  // Tạo ra 1 list chứa hết tất cả 12 number 
	  
	  List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
	  System.out.println("All number = " + allNumber.size());
	  // Nhan phim Ctrl xuong 
	  action.keyDown(Keys.CONTROL).perform();
	  // Click vao cac so 1 3 6 12
	  action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(5)).click(allNumber.get(11)).perform();  
	  sleepInSecond(2);
      // Nha phim ctrl
	  action.keyUp(Keys.CONTROL).perform();  
	  // Verify 
	  List<WebElement> allNumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
	  System.out.print("Number selected = " + allNumberSelected.size());  
	  Assert.assertEquals(allNumberSelected.size(), 4);
  }
  
  @Test
  public void TC_05_Double_Click() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	  sleepInSecond(1);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
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
