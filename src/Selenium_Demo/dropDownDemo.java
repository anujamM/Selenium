package Selenium_Demo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class dropDownDemo {

	@Test
	public void testcase1 () {
		System.out.println("***************************");
		System.out.println("Launching Chrome Driver");
		System.setProperty("webdriver.chrome.driver", "C:\\1. Anujam\\Drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/newtours/register.php");

		Select options = new Select(driver.findElement(By.name("country")));

		ArrayList<String> actuallist = new ArrayList<String>();
		System.out.println("Initial actual list: " + actuallist);

		List<WebElement> myOptions = options.getOptions();

		for (WebElement elements : myOptions) { 
			String country = elements.getText();
			//			System.out.println("List of countries: " + country);
			actuallist.add(country);
			//			System.out.println("New list of Actual list: " + actuallist);

		}

		List<String> temp = new ArrayList<String>();
		temp.addAll(actuallist);
		System.out.println("Temp list is: " + temp);

		Collections.sort(temp);
		System.out.println("Sorted List: " + temp);

		Assert.assertTrue(actuallist.equals(temp));

		Collections.shuffle(temp);
		System.out.println("Shuffled list: " + temp);

		Assert.assertTrue(actuallist.equals(temp));

	}

}
