package in.inagaki.legacybbs.arquillian.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.MavenImporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
public class ArquillianDroneSample {
    @Drone
    WebDriver driver;

    @ArquillianResource
    URL contextPath;

    @Deployment(testable = false)
    public static WebArchive createTestArchive() {
	return ShrinkWrap.create(MavenImporter.class)
		.loadEffectivePom("pom.xml").importBuildOutput()
		.as(WebArchive.class);
    }

    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitled() throws Exception {
	driver.get(contextPath.toString());
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
