package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_iFrame_Frame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_iFrame_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		
		//verify iframe facebook co hien thi
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
		
		//Switch frame facebook qua element
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		String xpathFacebook = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		
		System.out.println(xpathFacebook);
		
		Assert.assertEquals(xpathFacebook, "165K followers");
		
		//switch về main page
		
		driver.switchTo().defaultContent();
		
		//Switch qua chat
		
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Phuong Nguyen");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0990909090");
		
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		
		driver.findElement(By.name("message")).sendKeys("Tu van khoa hoc");
		
		sleepInSecond(2);
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.cssSelector("nav#navDesktop input.live-search-bar")).sendKeys("Excel");
		
		
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List <WebElement> listCourses = driver.findElements(By.cssSelector("div.content>h4")); 
		
		for (WebElement course : listCourses) {
			
			Assert.assertTrue(course.getText().contains("Excel"));
		}

	}

	@Test
	public void TC_02_Frame_DHFCBanking() {
		
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		
	}

	//@Test
	public void TC_03_() {
		
	}

	public void sleepInSecond(long inSecond) {
		try {
			Thread.sleep(inSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}

