package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Custom_Dropdown {
	
	WebDriver driver;
	WebDriverWait explicitwait;
	JavascriptExecutor jsExcutor;
		

 @BeforeClass
	public void beforeClass() {
	 
	   driver = new ChromeDriver();
	   explicitwait = new WebDriverWait(driver,30);
	   jsExcutor = (JavascriptExecutor) driver;
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
	   	 
}
	
  public void TC_01_JQuery() {
	  driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	  
	  // 5
	  selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "5");
	  
	  // 10
	  selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
	  sleepInSecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "10");
  }
  
  public void TC_01_Angular() {
	  driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
	  selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class, 'e-search-icon')]","//div[@id='games_popup']//li", "Hockey");
	  sleepInSecond(3);
	  Assert.assertEquals(AngularSelectedValueByJS(),"Hockey");	  
	 
  }
  
  public void TC_03_React() {
	  driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
	  selectItemInCustomDropdown("//div[@role='listbox']//i","//div[@role='listbox']//span[@class='text']", "Matt");
	  sleepInSecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Matt']")).isDisplayed());
	  
	  selectItemInCustomDropdown("//div[@role='listbox']//i","//div[@role='listbox']//span[@class='text']", "Justen Kitsune");
	  sleepInSecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Justen Kitsune']")).isDisplayed()); 
	 
  }
  
  public void TC_04_VueJS() {
	  driver.get("https://mikerodham.github.io/vue-dropdowns/");
	  selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a", "First Option");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
	  
  }
  
  public void TC_05_Editable() {
	  driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
	  selectItemInEditDropdown("//input[@class='search']","//div[@role='combobox']//span[@class='text']", "Algeria");
	  sleepInSecond(3);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Algeria']")).getText(), "Algeria");
  }
  
  @Test
  public void TC_06_Multiple_Custom_Dropdown() {
	  driver.get("");
	  
  }
 
  public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueITtem ) {
	  // 
	  driver.findElement(By.xpath(parentXpath)).click();
	  explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	  // Get all items in the Drop down into the list Element (List <WebElement>)
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	  int allItemsNumber = allItems.size();
	  for(int i = 0; i < allItemsNumber; i++) {
		String actualValueItem = allItems.get(i).getText();
		// Kiểm tra nó có bằng với text cần tìm hay không
		if(actualValueItem.equals(expectedValueITtem)) {
			allItems.get(i).click();
			break;
		}
	  }
  }
  
  public void selectItemInEditDropdown(String editableXpath, String allItemXpath, String expectedValueITtem ) {
	  // Input vao 1 element (textbox) de shown all item 
	  driver.findElement(By.xpath(editableXpath)).sendKeys(expectedValueITtem);
	  sleepInSecond(1);
	  explicitwait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	  // Get all items in the Drop down into the list Element (List <WebElement>)
	  List <WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
	  int allItemsNumber = allItems.size();
	  for(int i = 0; i < allItemsNumber; i++) {
		String actualValueItem = allItems.get(i).getText();
		// Kiểm tra nó có bằng với text cần tìm hay không
		if(actualValueItem.equals(expectedValueITtem)) {
			allItems.get(i).click();
			break;
		}
	  }
  }
 
  public String AngularSelectedValueByJS() {
	  return (String) jsExcutor.executeScript("return document.querySelector(\"select[name='games'] option\").text");
  }
  
  public void sleepInSecond(long timeInSecond) {
	try {
		Thread.sleep(timeInSecond * 1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  
 @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}