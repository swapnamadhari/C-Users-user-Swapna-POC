package com.cashe.mobile.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.cash.utilities.DataBaseConnection;
import com.cash.utilities.Xls_Reader;
import com.cashe.admin.AdminFlow;
import com.cashe.base.Abstract;

import io.appium.java_client.AppiumDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;


public class AdminTestFlow {
	
	  Hashtable<String, String> dataArray;
	  Xls_Reader xls = new Xls_Reader("src/main/resources/TestData/LoginTestdata.xls");
	 @Factory(dataProviderClass = Abstract.class,dataProvider="dp")
	  public AdminTestFlow(Hashtable<String, String> table) throws UnknownHostException {
		  // TODO Auto-generated constructor stub
		  this.dataArray=table;	
	  }
	 @Test
	public void tt() throws InterruptedException, IOException {
		
		String osName=System.getProperty("os.name");
		if(osName.contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver");

		}else if(osName.contains("Win")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		}
		
	WebDriver driver=new ChromeDriver();
		driver.get("https://prod.cashe.co.in");
		driver.manage().window().fullscreen();
		  
		System.out.println("Driver is :"+driver);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("qateam_readonly@cashe.co.in");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Qa@1234!");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@class='sidebar-toggle']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a/span[text()='Customers']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a/span[text()='Customer Support']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='sidebar-toggle']")).click();
		String query_01 = dataArray.get("PAN");

		DataBaseConnection  dbCon=new DataBaseConnection();
		List<String> CLN= dbCon.dataBaseSelect("SELECT `customer_id` FROM `customer_details` WHERE pan='"+query_01+"'");
		
		driver.findElement(By.xpath("//input[@placeholder='Search by CustomerId']")).sendKeys(CLN.get(0));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='searchbutton']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@ng-click,'viewCustomerDetails')]")).click();
		
		String pan=dataArray.get("PAN");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		js.executeScript("scroll(0, 900);");
		
		/* final Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver);
		 Thread.sleep(2000);
				  final BufferedImage image = screenshot.getImage();
				  ImageIO.write(image, "PNG", new File("/Users/vijaymattaparthi/Documents/ScreenShots/cln_"+cln+".png"));*/
				  
		TakesScreenshot ts = ((TakesScreenshot) driver);
        File source = ts.getScreenshotAs(OutputType.FILE);
      //  String dest = System.getProperty("user.dir") +"\\Reports\\"+screenShotName+".png";
        String dest ="/Users/vijaymattaparthi/Documents/ScreenShots/pan_"+pan+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
		
				  Thread.sleep(2000);
			  
			//xls.setCellData("AdminTest", "Status", dataArray., "done"); 
				  driver.quit();
		
		
	/*	Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	     ImageIO.write(fpScreenshot.getImage(),"PNG",new File("/Users/vijaymattaparthi/Documents/ScreenShots/cln_"+cln+".png"));*/
		//driver.findElement(By.xpath("//a/span[text()='Customers']")).click();
		
	}

}
