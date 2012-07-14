package in.inagaki.legacybbs.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumSample {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
	driver = new FirefoxDriver();
	baseUrl = "http://localhost:8080/legacybbs/";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled() throws Exception {
	driver.get(baseUrl);
	driver.findElement(By.id("name")).clear();
	driver.findElement(By.id("name")).sendKeys("inatus");
	driver.findElement(By.id("email")).clear();
	driver.findElement(By.id("email")).sendKeys("aaaaa");
	driver.findElement(By.id("title")).clear();
	driver.findElement(By.id("title")).sendKeys("title");
	driver.findElement(By.name("submit")).click();

	assertEquals(driver.getPageSource().contains("内容は必ず入力してください"), true);
    }

    @After
    public void tearDown() throws Exception {
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
	    fail(verificationErrorString);
	}
    }
}
