package Selenium_Demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.DemoUtil;

public class DragandDrop extends DemoUtil {

	static WebDriver driver;
	static DemoUtil dmUtil = new DemoUtil();

	@BeforeTest
	public static void main() throws IOException {

		killProcess();
		System.setProperty("webdriver.gecko.driver", "C:\\Eclipse_Oxy\\Drivers\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver();

		int a = windCount(driver);
		System.out.println("Number of drivers present: " + a);

		String cred[] = dmUtil.getUsername();

		if (a != 0) {
			driver.manage().window().maximize();
			driver.get(cred[0]);
		}

	}
	
	@Test/*(dependsOnMethods = {"main"})*/
	public void actionClickandHoldRelease() throws InterruptedException {
		Actions acts = new Actions(driver);
//		WebElement one = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[6]/a"));
		WebElement one = driver.findElement(By.linkText("Actions"));
		one.click();
		System.out.println("Actions Clicked");
		Thread.sleep(5000);
		acts.moveToElement(driver.findElement(By.name("one"))).clickAndHold().moveToElement(driver.findElement(By.name("twelve"))).release().build().perform();
		Thread.sleep(5000);
		
		acts.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).build().perform();
		
	}
	
	@AfterClass/*(dependsOnMethods = {"actionClickandHoldRelease"})*/
	public void tearDown() throws IOException {
		driver.close();
		killProcess();
	}

}

