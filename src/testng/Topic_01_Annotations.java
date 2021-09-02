package testng;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotations  {
	WebDriver driver;
	
  @Test()
  public void TC_01() {
	  System.out.println("TC01");
  }
  
  @Test()
  public void TC_02() {
	  System.out.println("TC02");
  }
  
  @Test()
  public void TC_03() {
	  System.out.println("TC03");
  }
  
  @Test()
  public void TC_04() {
	  System.out.println("TC04");
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  driver = new ChromeDriver();
	   driver.get("http://demo.guru99.com/V4/");
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

//  @BeforeClass
//  public void beforeClass() {
//	  System.out.println("Before Class");
//  }
//
//  @AfterClass
//  public void afterClass() {
//	  System.out.println("After Class");
//  }
//
//  @BeforeTest
//  public void beforeTest() {
//	  System.out.println("Before test");
//  }
//
//  @AfterTest
//  public void afterTest() {
//	  System.out.println("After Test");
//  }
//
//  @BeforeSuite
//  public void beforeSuite() {
//	  System.out.println("Before Suite");
//  }
//
//  @AfterSuite
//  public void afterSuite() {
//	  System.out.println("After Suite");
//  }

}
