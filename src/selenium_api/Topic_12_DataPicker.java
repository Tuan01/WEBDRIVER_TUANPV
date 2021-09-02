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


public class Topic_12_DataPicker {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectLocation = System.getProperty("user.dir");
	
	String danangFileName = "Da Nang.jpg";
	String hanoiFileName = "Ha Noi.jpg";
	String saigonFileName = "Ho Chi Minh.jpg";
	
	String danangFileNamePath = projectLocation + "/Uploadfiles/" + danangFileName ;
	String hanoiFileNamePath =	projectLocation + "/Uploadfiles/" + hanoiFileName ; 
	String saigonFileNamePath = projectLocation + "/Uploadfiles/" + saigonFileName ;
	


 @BeforeClass
	public void beforeClass() {
	 
//	   driver = new ChromeDriver();
	   driver = new FirefoxDriver();
	   explicitWait = new WebDriverWait(driver,30);
	   action = new Actions(driver);
	   jsExecutor = (JavascriptExecutor) driver;
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();	   	 
 	}
 
  public void TC_01_Sendeys_One_Per_File() {
	  driver.get("http://blueimp.github.io/jQuery-File-Upload/");

	  driver.findElement(By.xpath("//input[@type='file']")).sendKeys(danangFileNamePath);
	  driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hanoiFileNamePath);
	  driver.findElement(By.xpath("//input[@type='file']")).sendKeys(saigonFileNamePath);
	  
	  // Verify file loaded success
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + danangFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + hanoiFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + saigonFileName + "']")).isDisplayed());
	  
	  // click start upload each file
	  List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
	  for (WebElement startButton : startUpload) {
		  startButton.click();
		  sleepInSecond(1);	  	
	  }
	  	  
	  // Verify file uploaded success
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + danangFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hanoiFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + saigonFileName + "']")).isDisplayed());
	    
  }
  

  public void TC_02_Sendeys_Multile_Files() {
	  driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	  
	  driver.findElement(By.xpath("//input[@type='file']")).sendKeys(danangFileNamePath + "\n" + hanoiFileNamePath + "\n" + saigonFileNamePath);
	 
	  
	  // Verify file loaded success
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + danangFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + hanoiFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + saigonFileName + "']")).isDisplayed());
	  
	  // click start upload each file
	  List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
	  for (WebElement startButton : startUpload) {
		  startButton.click();
		  sleepInSecond(1);	  	
	  }
	  	  
	  // Verify file uploaded success
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + danangFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hanoiFileName + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + saigonFileName + "']")).isDisplayed());
	  
	  
	  sleepInSecond(2);
	  
	  // Verify image not broken 
	  
	 
	  Assert.assertTrue(isImageLoadedSuccess("//a[@title='" + danangFileName +"']/img"));
	  Assert.assertTrue(isImageLoadedSuccess("//a[@title='" + hanoiFileName +"']/img"));
	  Assert.assertTrue(isImageLoadedSuccess("//a[@title='" + saigonFileName +"']/img"));
	  
  }
  
  @Test
  public void TC_04_Upload_File_Sendkey() {
	  driver.get("https://gofile.io/uploadFiles");
	  driver.findElement(By.xpath("//input[@type='file']")).sendKeys(danangFileNamePath + "\n" + hanoiFileNamePath + "\n" + saigonFileNamePath);
	  
	 
	  
	  // Verify file loaded success
	  
	  
	  // click start upload each file
	 
	  	  
	  // Verify file uploaded success
	
  }
  
    public boolean isImageLoadedSuccess(String locator) {
    	JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
    	WebElement element = driver.findElement(By.xpath(locator));
    	return (boolean) jExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
    }
  
  	public Object executeForBrowser(String javaScript) {
	  JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public boolean areJQueryAndJSLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	public String getElementValidationMessage(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
  public WebElement getElement(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
  }
  
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
	  jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
  }
  
  public void clickToElementByJS(WebElement element) {
	  jsExecutor.executeScript("arguments[0].click();", element);
  }
  
 @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}