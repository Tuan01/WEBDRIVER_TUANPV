package selenium_api;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_09_Window_Tab {
	
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



  public void TC_01_Only_2_Windows_Tabs() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  // Lay ra ID cua tab truoc khi click
	  String parentWindowID = driver.getWindowHandle();
	  driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	  switchToWindowByID(parentWindowID);
	  Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
	  	  
}
  
  public void TC_02_greater_Than_2_Windows_Tabs() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  // Lay ra ID cua tab truoc khi click
	  String parentID = driver.getWindowHandle();
	  driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	  switchToWindowByTitle("Google");
	  Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
	  switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	  driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	  switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
	  Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());
	  switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	  driver.findElement(By.xpath("//a[text()='TIKI']")).click();
	  switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Giỏ Hàng']")).isDisplayed());
	  closeAllWindowExceptParent(parentID);
  	  
}
  
  @Test
  public void TC_03_Compare_Product() {
	  driver.get("http://live.demoguru99.com/index.php/");
	  driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	  String parentID = driver.getWindowHandle();
	  driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Sony Xperia has been added to comparison list.");
	  sleepInSecond(1);
	  driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
	  sleepInSecond(1);
	  driver.findElement(By.xpath("//span[text()='Compare']")).click();
	  sleepInSecond(2);
	  switchToWindowByTitle("Products Comparison List - Magento Commerce");
	  Assert.assertEquals(driver.findElements(By.xpath("//h2[@class='product-name']/a")).size(), 2);
	  
	  closeAllWindowExceptParent(parentID);
	  sleepInSecond(1);
	  
	  driver.findElement(By.xpath("//a[text()='Clear All']")).click();
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  // Switch to Alert 
	  alert = driver.switchTo().alert();
	  sleepInSecond(3);
	  
	  Assert.assertEquals(alert.getText(), "Are you sure you would like to remove all products from your comparison?");
	  alert.accept();
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The comparison list was cleared.");  
	  
  }
// 2 Window/Tab
  public void switchToWindowByID(String parentID) {
	  // Lay ra all cac ID cua window/tab dang co
	  Set<String> allWindowsID = driver.getWindowHandles();
	  //Duyet qua tung ID
	  for (String windowID : allWindowsID) {
		  //If nhu ID nao ma khac voi parent ID thi nhay vao ham if
		  if (!windowID.equals(parentID)) {
			  driver.switchTo().window(windowID);
			  break;
		  }	
	}
  }
  
  public void switchToWindowByTitle(String expectedWindowTitle) {
	  // Lay ra all cac window/tab dang co
	  Set <String> allWindowIDs = driver.getWindowHandles();
	  System.out.println("So luong cua so/ tab dang co:" + allWindowIDs.size());
	  
	  // Duyet qua cac gia tri trong SEt
	  for (String windowID : allWindowIDs) {
		  // Switch vao tung window / tab
		  driver.switchTo().window(windowID);
		  // Get ra title cua tung windown/tab
		  String actualWindowTitle = driver.getTitle();
		  // Kiem tra neu nhu cai nao bang voi title mong muon thi thoat khoi vong lap
		  if (actualWindowTitle.equals(expectedWindowTitle)) {
			  break;
		  }		
	}	  	
 }
  
  public void closeAllWindowExceptParent(String parentID) {
	  Set<String> allWindowIDs = driver.getWindowHandles();
	  for (String windowID : allWindowIDs) {
		  if (!windowID.equals(parentID)) {
			  driver.switchTo().window(windowID);
			  driver.close();
			  sleepInSecond(1);
		  }
		  if (driver.getWindowHandles().size() == 1) {
			 driver.switchTo().window(parentID);
			 break;
		  }
	}
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
