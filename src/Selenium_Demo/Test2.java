package Selenium_Demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test2 {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\am051163\\OneDrive - Cerner Corporation\\Documents\\eclipse-jee-oxygen-3a-win32-x86_64\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://demo.guru99.com/test/newtours/register.php");
		Select options = new Select(driver.findElement(By.name("country")));

		//		List<WebElement> list = country.getOptions();

		ArrayList<String> actuallist = new ArrayList<String>();
		System.out.println("Initial actual list: " + actuallist);
		

		List<WebElement> myOptions = options.getOptions();
		System.out.println(myOptions);

		for (WebElement elements : myOptions) {
			String country = elements.getText();
			//			System.out.println("Countries: " + country);
			actuallist.add(country);
			//			System.out.println("Now Actual List: " + actuallist);

			String india = "INDIA";
			if(country.equalsIgnoreCase(india)) {
				System.out.println("India is there!!");
			}
			else
			{
				System.out.println("India is not there!");
			}

		}

	}

}
