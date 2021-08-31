package Selenium_Demo;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoSuggestion {

	private static WebDriver driver;

	public static void main(String args[]) {
		System.setProperty("webdriver.chrome.driver",
				"C:/Eclipse_Oxy/Drivers/chromedriver_win32/chromedriver_92.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Selenium");

		List<WebElement> allSuggest = driver.findElements(By.xpath("//ul/li[@class='sbct']/div/div[2]/div[1]"));
		System.out.println(allSuggest.size());

//		for(WebElement e : allSuggest) {
//			System.out.println(e.getText());
//		}
		
		Iterator<WebElement> it = allSuggest.iterator();
		while(it.hasNext()) {
			String value = it.next().getText();
			System.out.println(value);
		}

	}

}
