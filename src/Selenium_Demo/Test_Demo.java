package Selenium_Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test_Demo {
static WebDriver driver = null;
	
	@Test
	public static void main() throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",
				"C:/Eclipse_Oxy/Drivers/chromedriver_win32/chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//label[@for='fromCity']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @placeholder='From']"))).sendKeys("Kolkata");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Kolkata')]"))).click();
		
		
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(driver.findElement(By.id("abc")))));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("abc"))));	//Example
		
		driver.navigate().refresh();		//Example
		
		SoftAssert soft = new SoftAssert();		//Example
		soft.assertEquals("actual", "expected");
		Assert.assertEquals("", "");
		try {
			System.out.println("The platform name is: " + System.getProperty("platformName"));
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Failureeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		}
		
		driver.get("https://www.google.com/");
		Actions acts = new Actions(driver);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
//		driver.findElement(By.);
		acts.keyDown(Keys.SHIFT).sendKeys("anujam").keyUp(Keys.SHIFT).build().perform();
	}
}
