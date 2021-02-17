package testNG_Listener_Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class TestCases {

	// Test to pass as to verify listeners .
	@Test
	public void Login() {
		System.out.println("*******************");
		System.out.println("launching Chrome browser");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\am051163\\OneDrive - Cerner Corporation\\Documents\\eclipse-jee-oxygen-3a-win32-x86_64\\Drivers\\chromedriver_win32\\chromedriver (3).exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		
//		  driver.get("http://demo.guru99.com/V4/");
//		  driver.findElement(By.name("uid")).sendKeys("mngr34926");
//		  driver.findElement(By.name("password")).sendKeys("amUpenu");
//		  driver.findElement(By.name("btnLogin")).click();
		  
		  driver.get("http://www.google.com");
		 

	}

	// Forcefully failed this test as to verify listener.

	@Test
	public void TestToFail() {
		System.out.println("This method to test fail");
		Assert.assertTrue(false);
	}
}