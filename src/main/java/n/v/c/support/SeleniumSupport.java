package n.v.c.support;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class SeleniumSupport {
	public String checkLogin(String username) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\cuong\\Desktop\\New folder\\workspaces\\easy_mooc\\src\\main\\resources\\driver\\chromedriver_win32\\chromedriver.exe");

//		 ChromeOptions opts = new ChromeOptions();
//	        opts.addArguments("");
//		WebDriver driver = new ChromeDriver(opts);

//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--incognito");
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.coursera.org/?authMode=login");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("c");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("h");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("e");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("t");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("7");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("h");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("@");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("g");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("m");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("a");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("i");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("l");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys(".");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("c");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("o");
		sleep(100);
		driver.findElement(By.id("email")).sendKeys("m");
		sleep(100);

		driver.findElement(By.id("password")).sendKeys("yk@_QESH2Lp#@/u");
		sleep(2000);
		driver.findElement(By.className("_6dgzsvq")).click();
		sleep(2000);
		String typeUserPasswordCheck = "can't check";
		// Thoát hẳn Browser
		try {
			if ("You can try again or reset your password"
					.indexOf(driver.findElement(By.className("css-q1vc80")).getText()) > 0) {
				typeUserPasswordCheck = "incorrect";
			}

		} catch (Exception e) {
			if ("Welcome back!".indexOf(driver.findElement(By.className("header-text")).getText()) > 0) {
				typeUserPasswordCheck = "correct";
			}
		}
		driver.quit();
		return typeUserPasswordCheck;
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\cuong\\Desktop\\New folder\\workspaces\\easy_mooc\\src\\main\\resources\\driver\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.navigate().to("https://www.coursera.org");
		driver.findElement(By.xpath("/html/body/div[3]/div/header/div/div/div/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("chet7h@gmail.com");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("yk@_QESH2Lp#@/u");
		driver.findElement(By.className("_6dgzsvq")).click();
		sleep(10000);
		/// start leaning
//		step 1
		driver.navigate().to("https://www.coursera.org/learn/iot-architecture/exam/43XCK/processors/attempt");
		sleep(10000);
		List<WebElement> allTagP = driver.findElements(By.tagName("p"));
		String[] ans = { "The use of cache enhances the average performance.",
				"You can program a hardware system with a ARM processor in a FPGA.", "Renesas Electronics Corporation",
				"Freescale Semiconductors (now NXP)", "Intel", "Altair",
				"Interrupt handling is done differently in different families of processors. ",
				"Each processor family needs an adapted compiler. ", "MIPS Classic Processor Core", "ARM Cortex A9",
				"MIPS M-class Warrior Processor Core" };
		for (WebElement webElement : allTagP) {
			String c = webElement.getText();
			System.out.println(c);
			if (StringUtils.equalsAny(c, ans))
				webElement.click();

		}
		WebElement chec = driver.findElement(By.xpath(
				"/html/body/div[4]/div/div/div/div[2]/div[2]/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div/div[2]/div[1]/div/div/label/span/span/input"));
		chec.click();

		WebElement legalName = driver.findElement(By.xpath(
				"/html/body/div[4]/div/div/div/div[2]/div[2]/div/div[2]/div/div/div/div/div/div/div[2]/div/div[1]/div/div[2]/div[2]/div[2]/div/input"));
		legalName.sendKeys("Cuong");

	}
}
