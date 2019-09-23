/*package com.cash.utilities;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ExtentFactory{    
	public  static ExtentReports eReports;
	public   ExtentTest eTest;
	

	//@BeforeSuite
	public  synchronized ExtentReports getInstance() throws UnknownHostException
	{
		DateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		String date = format.format(new Date());
		//Reporter.log(timeStamp, true);
		Random random = new Random();
		int no = random.nextInt(999);
		 // int no = randomNumberGenerator(999);
		//String PathDate = "C:\\ExtentReports\\Reports\\"+date+"_"+no+".html";
		//String PathDate = "C:\\ExtentReports\\Reports\\"+date+".html";
		String PathDate = "../Reports/index.html";
		eReports= new ExtentReports(PathDate, false);
		
		//String css=".report-name { padding-left: 100px; } .report-name > img { float: right;height: 40px;margin-left: 300px;margin-top: 0px;width: 200px, ; }";
		//eReports.config().reportName(System.getProperty("user.dir")+"/CASHe-Logo1.png").insertCustomStyles(css);
		eReports.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName())
		.addSystemInfo("Environment", "QA")
		.addSystemInfo("Platform", "Android");
		eReports.loadConfig(new File(System.getProperty("user.dir")+"/src/main/resources/XML.xml"));
		
		return eReports;
	}
	
	@BeforeClass
	public void startReport()
	{	
		eReports = ExtentFactory.getInstance();
		eReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/MyRpt.html", true);
		eReports.addSystemInfo("HostName", "Vasudha")
				.addSystemInfo("Environment", "QA")
				.addSystemInfo("User Name", "Vasudha Kakunuri");
		eReports.loadConfig(new File(System.getProperty("user.dir")+"/pom2.xml"));
	}
	
	@AfterClass
	public void tearDown()
	{
		eReports.flush();
	}
}
*/