package Selenium_Demo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {

	//	public static void beforeClass(String[]  args) {
	//
	//	}
	static WebDriver driver = null;
	
	@Test
	public static void main() throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.gecko.driver",
				"C:\\Eclipse_Oxy\\Drivers\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//input[contains(@name, 'firstname')]")).sendKeys("Anujam");
		driver.findElement(By.xpath("//input[contains(@name, 'lastname')]")).sendKeys("Mondal");
		driver.findElement(By.xpath("//input[contains(@name, 'reg_email__')]")).sendKeys("1234567890");
		driver.findElement(By.xpath("//input[contains(@name, 'reg_passwd__')]")).sendKeys("Password");

		Select date = new Select(driver.findElement(By.xpath("//*[@id=\"day\"]")));
		date.selectByValue("1");
		Select month = new Select(driver.findElement(By.xpath("//*[@id=\"month\"]")));
		month.selectByIndex(04);
		Select year = new Select(driver.findElement(By.xpath("//*[@id=\"year\"]")));
		year.selectByValue("1995");


		driver.findElement(By.xpath("(//input[contains(@name, 'sex')])[2]")).click();
		driver.findElement(By.xpath("//button[contains(@name, 'websubmit')]")).click();

		Thread.sleep(5000);

		String errorMsg = driver.findElement(By.xpath("//*[@id=\"reg_error_inner\"]")).getText();
		System.out.println(errorMsg);
		Assert.assertEquals("Too many users have this phone number listed as pending.", errorMsg, "Error text is there!");

		Thread.sleep(5000);

		//		driver.findElement(By.xpath("//*[@id=\"js_0\"]/ul/li[6]/a"));
		driver.findElement(By.linkText("People")).click();
		driver.navigate().refresh();
		driver.navigate().back();
		driver.navigate().refresh();
	}
}
