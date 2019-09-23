/*package com.extent.tests;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BeforeExtent {
	ExtentReports report;
	ExtentHtmlReporter html;
	ExtentTest test;
	//WebDriver driver;
	DateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy HHmmss");
	Date date = new Date();
	//WebDriverWait wait;
	String newDir = "C://Java//Java_Tutorial//Page_Object_Model//Screenshots//"+dateformat.format(date);
	
	@BeforeClass
//	@Parameters("browser")
	public void initialize()
	{
		if (ExtentManager.getInstance() == null)
			ExtentManager.createInstance("C:/Java/Java_Tutorial/Page_Object_Model/Reports/MyOwnReport"+dateformat.format(date)+".html");

		File dir = new File(newDir);
		dir.mkdir();
		
		
		
	}
	
}
*/