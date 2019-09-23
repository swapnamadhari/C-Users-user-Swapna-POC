package com.cashe.mobile.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.cash.utilities.DataBaseConnection;
import com.cash.utilities.EC2Batch;
import com.cashe.admin.AdminFlow;
import com.cashe.admin.CommonFunctions;
import com.cashe.base.Abstract;
import com.cashe.base.ThreadLocalWebDriver;
import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.jcraft.jsch.JSchException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import com.cash.utilities.APIClient;
import com.cash.utilities.APIException;

public class test extends Abstract {
	static WebDriver driver;



	public static void main(String[] args) throws InterruptedException, JSchException, IOException, APIException {
		//AfterScenario();
		//ttt();

		EC2Batch c=new EC2Batch();
		c.ec2Batch("Reconciliation");
		/*Random rn = new Random();
		int range = 8;
		System.out.println("range "+range);
		int randomNum =  rn.nextInt(range) + 1;
		System.out.println("randomNum"+randomNum);
		String aadhaarNum="";
		System.out.println("aadhaarNum"+aadhaarNum);
		for(int i=0;i<11;i++){
			aadhaarNum=aadhaarNum+Integer.toString(rn.nextInt(9));
		}
		aadhaarNum = Integer.toString(randomNum)+aadhaarNum;
		System.out.println(aadhaarNum);
		 */


		/*Random random = new Random();
		System.out.println(Math.round(random.nextFloat() * Math.pow(10,12)));*/
		//long x11= ((long)Math.random()*1000000000000L);
		/*
		System.out.println(x11);


		 AdminFlow admin=new AdminFlow();
		  admin.loanStatusInAdmin("1732352","Verified")*/;



		  /*	
		EC2Batch c=new EC2Batch();
		c.ec2Batch("Reconciliation");

		String x="578.4884";
		//int x=Integer.parseInt(x1);
		//String [] s=x.split(".");
		//System.out.println(s.toString());

		if(x.contains("578")) {
			System.out.println("tt");
		}*/
		  /*	
		int c=-1;
		c=2/3;
		System.err.println("gg");
		   */

		  /*String x="0.65";
		if(Float.parseFloat(x)>=0.65) {
		System.out.println(Float.parseFloat(x));
		}else {
			System.out.println("done");
		}
		   */
		  /*int roundedNumber = (152150 + 2500) / 5000 * 5000;
		System.out.println(roundedNumber);*/
		  /*	String planType="Cashe 18";
		System.out.println(planType.split(" ")[1].trim().substring(0, 3));*/

		  /*
		 DataBaseConnection dbCon=new DataBaseConnection();
		  dbCon.dataBaseUpdate("UPDATE loan_request SET loan_status_id = 4 ,loan_transferred_date = NOW() WHERE customer_id = 5573748 ORDER BY loan_request_id DESC LIMIT 1");
		  System.out.println("Loan status updated successfully");*/

		  /* Random rn = new Random();
		  int range = 9 - 6 + 1;
		  int randomNum =  rn.nextInt(range) + 6;
		   DataBaseConnection dbCon=new DataBaseConnection();
		  dbCon.dataBaseUpdate("UPDATE loan_request SET loan_status_id = 4 ,loan_transferred_date = NOW() WHERE customer_id = "+customerId+" ORDER BY loan_request_id DESC LIMIT 1");
		  System.out.println("Loan status updated successfully");
		  System.out.println("Random Number= "+randomNum);*/
		  /*System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		ChromeOptions co=new ChromeOptions();
		co.addArguments("headless");
		WebDriver driver= new ChromeDriver(co);
		driver.get("https://test.cashe.co.in");

		System.out.println("ttt");
		driver.quit();*/
		  /*String osName=System.getProperty("os.name");
		if(osName.contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver");

		}else if(osName.contains("Win")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		}*/
		  /*ChromeOptions co=new ChromeOptions();
		co.addArguments("--headless");*/
		  //driver= new ChromeDriver(/*co*/);

		  /*	 AdminFlow admin=new AdminFlow();
		  //admin.loanStatusInAdmin(customerId,dataArray.get("LoanStatus"));
		  admin.loanStatusInAdmin("5573268","Verified");*/
		  //	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		  /*ChromeOptions co=new ChromeOptions();
		co.addArguments("--headless");*/
		  //driver= new ChromeDriver(/*co*/);
		  //ChromeOptions options = new ChromeOptions();
		  //options.addArguments("--start-fullscreen");
		  /*driver=new ChromeDriver();
		driver.get("https://test.cashe.co.in");
		driver.manage().window().fullscreen();
		System.out.println("Thread is is :"+Thread.currentThread().getName());

		System.out.println("Driver is :"+driver);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("Supervisor@cashe.co.in");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("cashe@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();*/

		  //Runtime.getRuntime().exec("ssh -i /Users/vijaymattaparthi/Documents/vijayMumQA.pem vijaykumarmattaparthi98096@172.31.3.44 \"/batch/CasheBatch/PaymentReminderJobTest.sh\"");
		  /*File dir = new File(DateFormatObj.format(DateObj));
		 dir.mkdir();
		 System.setProperty("tempFolder",dir.getAbsolutePath());*/

		  /*EC2Batch c=new EC2Batch();
		c.ec2Batch("Reconciliation");*/

		  /*DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", "192.168.128.101:5555");
			cap.setCapability("platformVersion", "6.0");
			cap.setCapability("platformName", "Android");
			//cap.setCapability("unicodeKeyboard", true);
			//cap.setCapability("resetKeyboard", true);
			//cap.setCapability("automationName", "uiautomator2");

			cap.setCapability("appPackage", "co.tslc.cashe.android");
			cap.setCapability("appActivity", "co.tslc.cashe.android.ui.activity.SplashActivity");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);

			cap.setCapability("newCommandTimeout", 30000);

		//	driver = new ThreadLocal<RemoteWebDriver>();
			 driver=new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);

			Thread.sleep(60000);
			seekBar();*/

		  //co.tslc.cashe.android:id/loan_amount_start
		  //co.tslc.cashe.android:id/loan_amount_end
		  //co.tslc.cashe.android:id/txt_loan

		  //	ThreadLocalWebDriver.setWebDriver(driver.get());
		  // implicitWait();
		  // Thread.sleep(8000);

	}
	///@Test
	public static boolean ttt() {
		System.out.println("yyy");
		boolean s=false;
		List<Integer> val=new ArrayList<Integer>();
		int[] a= {7,1,1,7,1,1,7};

		for(int i=0; i<a.length-1;i++) {
			if(a[i]==7) {

				for(int j=i+1;j<a.length;j++) {
					if(a[j]==7 && ((j-i)==1 || (j-i)==2)) {
						s=true;
						break;
					}

				}
				if(s) { 
					break;
				}
			}
		}
		System.out.println("rrrr :"+s);
		return s;
	}

	public static void seekBar(){

		try{
			/*((AndroidDriver<?>)driver).pressKeyCode(27);
			((AndroidDriver<?>)driver).getPageSource();*/

			WebElement seek_bar=driver.findElement(By.id("seek_bar"));
			// get start co-ordinate of seekbar
			int start=seek_bar.getLocation().getX();
			//Get width of seekbar
			int end=seek_bar.getSize().getWidth();
			//get location of seekbar vertically
			int y=seek_bar.getLocation().getY();

			// Select till which position you want to move the seekbar
			TouchAction action=new TouchAction((MobileDriver) driver);


			//Move it 40%
			int moveTo=(int)(954);
			action.longPress(start,y).moveTo(moveTo,y).release().perform();

		}catch(Exception e){

		}

	}

	public static void AfterScenario() throws MalformedURLException, IOException, APIException{

		APIClient client = new APIClient("https://casheqa.testrail.io");
		client.setUser("vijaykumar.m@cashe.co.in");
		client.setPassword("Password1!");
		List<String> d=(List<String>) client.sendGet("get_projects");
		System.out.println("Projects"+d);
		List<JSONObject> rr=(List<JSONObject>) client.sendGet("get_cases/1");
		System.out.println("runs"+rr);
		/*JSONObject c = (JSONObject) client.sendGet("get_case/1");
		System.out.println(c.get("title"));*/
		JSONObject t=rr.get(1);
		//JSONObject id = (JSONObject)client.sendGet("get_cases/1");
		System.out.println("title is :"+t.get("id"));
		if(t.get("title").equals("App login through Facebook")) {
			String id=t.get("id").toString();


			HashMap data= new HashMap();
			data.put("status_id", new Integer(1));
			data.put("comment", "Vijay Test working fine 28");
			JSONObject r= (JSONObject) client.sendPost("add_result_for_case/1/"+id+"", data);
		}
		/*Gurock.TestRail.APIClient client = new Gurock.TestRail.APIClient(“https://yoursite.testrail.com/”);

		client.User = “user@domain.com”; //user e-mail

		client.Password = “SpecFlow”; //user password

		Dictionary<string, object> testResult = new Dictionary<string, object>();

		if(null != ScenarioContext.Current.TestError){

		testResult[“status_id”] = “5”; //failed;

		testResult[“comment”] = ScenarioContext.Current.TestError.ToString();
		}

		else

		{
			testResult[“status_id”] = “1”; //passed
		}


		client.SendPost(“add_result/1”, testResult); //hardcoded test id.
		}
		 */
	}
}