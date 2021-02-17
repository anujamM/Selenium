package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoUtil {

	static WebDriver driver;
	static DemoUtil dmUtil = new DemoUtil();
	static String arr[] = new String[5];

	public String[] getUsername() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("src/resources/env.properties");
			prop.load(input);

			arr[0] = prop.getProperty("home");
			if ((prop.getProperty("username") != null)) {
				arr[1] = prop.getProperty("username");

				if ((prop.getProperty("password") != null)) {
					arr[2] = prop.getProperty("password");
				}
				else {
					System.out.println("No Password in propertiesfile");
				}
			}
			else {
				System.out.println("No Username in propertiesfile");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return arr;

	}

	@SuppressWarnings("unused")
	public static void killProcess() throws IOException {

		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("taskkill /im firefox.exe /f /t");

	}

	public static int windCount(WebDriver driver) {
		Set<String> windHandles = driver.getWindowHandles();
		int numberOfWindows = windHandles.size();
		//		System.out.println(numberOfWindows);
		return numberOfWindows;

	}

	public static void login() throws InterruptedException, IOException {
		killProcess();
		System.setProperty("webdriver.gecko.driver", "C:\\Eclipse_Oxy\\Drivers\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette",true);
		driver = new FirefoxDriver();

		int a = windCount(driver);

		String credentials[] = dmUtil.getUsername();
		if ((a) != 0) {
			driver.manage().window().maximize();
			driver.get(credentials[0]);
			Thread.sleep(5000);
			driver.findElement(By.id("txtUsername")).clear();
			driver.findElement(By.id("txtUsername")).sendKeys(credentials[1]);
			driver.findElement(By.id("txtPassword")).clear();
			driver.findElement(By.id("txtPassword")).sendKeys(credentials[2]);
			driver.findElement(By.id("btnLogin")).click();
			driver.navigate().to(credentials[0]);
			System.out.println("Login successful!!!");
			Thread.sleep(5000);
		} else {
			System.out.println("Something went wrong in login method in demoUtil.login().");
		}
	}

	public static void close() throws IOException {
		driver.close();
		killProcess();
		System.out.println("All instances closed successfully!!!");
	}

	public static class welcome {
		static WebElement welcomeDD = driver.findElement(By.xpath("//*[@id=\"welcome\"]"));
		static WebElement aboutLink = driver.findElement(By.xpath("//*[@id=\"aboutDisplayLink\"]"));
		static WebElement chngPwdLink = driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[2]/a"));
		static WebElement logoutLink = driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[3]/a"));
		static WebElement aboutClose = driver.findElement(By.xpath("//*[@id=\"displayAbout\"]/div/a"));

		public void clickWelcomeAdmin() {
			welcomeDD.click();
		}

		public void clickAbout() {
			aboutLink.click();
		}

		public void closeAbout() {
			//			aboutClose.click();
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
		}

		public void clickChngPwd() {
			chngPwdLink.click();
		}

		public void clickLogout() {
			logoutLink.click();
		}

	}

	public static class AdminSegment{

		static WebElement adminTab = driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]"));

		public void clickAdminTab() throws InterruptedException {
			adminTab.click();
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu_admin_UserManagement\"]"))).build().perform();
			//			System.out.println("userMgmt button clicked!");
			WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu_admin_viewSystemUsers\"]")));			
			element.click();
			//			System.out.println("Submenu clicked!");
		}

		public List<WebElement> getUserRole() {
			Select userRoleOptions = new Select(driver.findElement(By.id("searchSystemUser_userType")));
			List<WebElement> listofUserRoles = userRoleOptions.getOptions();
			return listofUserRoles;
		}
	}

	public static void clickWelcomeAdmin() {
		welcome admin = new welcome();
		admin.clickWelcomeAdmin();
	}

	public static void clickAbout() throws InterruptedException {
		welcome about = new welcome();
		about.clickAbout();
		Thread.sleep(5000);
		about.closeAbout();
		Thread.sleep(5000);
	}

	public static void chngPwd() {
		welcome chngPwd = new welcome();
		chngPwd.clickChngPwd();
	}

	public static void logout() {
		welcome logout = new welcome();
		logout.clickLogout();
	}

	public static void adminTabClick() throws InterruptedException {
		AdminSegment clickAdminTab = new AdminSegment();
		clickAdminTab.clickAdminTab();
	}

	public static void putUserName() {
		WebElement userNameField = driver.findElement(By.id("searchSystemUser_userName"));
		String uname = arr[1];
		userNameField.sendKeys(uname);
	}

	public static void status_Admin() {
		//		Select status = new Select(driver.findElement(By.id("searchSystemUser_status")));
		//		List<WebElement> stat_Enable = status.getOptions();
		driver.findElement(By.id("searchSystemUser_status")).sendKeys("Enabled");	
	}

	public static ArrayList<String> getListElements() {
		ArrayList<String> actuallist = new ArrayList<String>();
		AdminSegment listElements =new AdminSegment();
		List<WebElement> userRoles= listElements.getUserRole();
		for(WebElement elements : userRoles) {
			String option = elements.getText();
			//			System.out.println(option);
			actuallist.add(option);
		}
		return actuallist;
	}

	private static int getIndex() {
		int a = 0;
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("src/resources/env.properties");
			prop.load(input);			
			arr[4] = prop.getProperty("val");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		String tempList = getListElements().toString();
		//		System.out.println(tempList);
		if(tempList.contains(arr[4])) {
			//			System.out.println("Element Available");
			a = getListElements().indexOf(arr[4]);
			//			System.out.println(a);
		}
		else {
			System.out.println("Element not available.");
		}
		return a;		
	}

	public static void clickIndexVal() {
		int ind = getIndex();
		if(ind != 0) {
			Select userRoleOptions = new Select(driver.findElement(By.id("searchSystemUser_userType")));
			userRoleOptions.selectByIndex(ind);
			//			System.out.println("Value Selected.");
		}
		else {
			System.out.println("There's no such value in the dropdown.");
		}
	}

	public static void searchButton() {
		WebElement searchButtonClick = driver.findElement(By.id("searchBtn"));
		searchButtonClick.click();
	}

	protected static void selectItemfromTable() {
		List<WebElement> table = driver.findElements(By.xpath("//*[@id=\"resultTable\"]"));
		int i = table.size();
		//		System.out.println("Number of rows: " + i);
		List<WebElement> columns = table.get(i-1).findElements(By.tagName("td"));
		int j = columns.size();
		System.out.println("Number of columns: " + j);
		//		ArrayList<String> actuallist = new ArrayList<String>();
		for (WebElement elements : columns) {
			String columnItem = elements.getText();
			System.out.println(columnItem);
		}
	}

	public static void getIndexforGrid(String a) {
		ArrayList<String> actuallist = new ArrayList<String>();
		System.out.println("Initial actual list: " + actuallist);
		actuallist.add(a);
		System.out.println("Actual list: " + a);
	}

	public static void clickOrangeHRM() {
		driver.get(arr[0]);
		driver.findElement(By.cssSelector("a[target=\"_blank\"]")).click();
	}

	public static void actionsClickandHoldRelease() throws InterruptedException {
		Actions actions = new Actions(driver);
		Thread.sleep(10000);
		WebElement element = driver.findElement(By.cssSelector("a[title=\"Go to Facebook home\"]"));
		WebElement element2 = driver.findElement(By.name("websubmit"));
		element.click();
		System.out.println("Home page icon clicked");
		Thread.sleep(5000);
		element2.click();
		System.out.println("Other thing clicked");

		actions.moveToElement(driver.findElement(By.cssSelector("a[title=\"Go to Facebook home\"]")))
		.clickAndHold()
		.moveToElement(driver.findElement(By.name("websubmit")))
		.release().build().perform();
	}

}
