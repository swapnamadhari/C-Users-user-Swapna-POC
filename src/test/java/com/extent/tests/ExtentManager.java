package com.extent.tests;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager{
    
    static ExtentReports extent;
    
    public static ExtentReports getInstance() {	
        return extent;
    }
    
    public static synchronized ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Cashe Mobile Automation Reports");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("          Cashe");
        htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/src/main/resources/XML.xml"));
        //eReports.loadConfig(new File(System.getProperty("user.dir")+"/src/main/resources/XML.xml"));
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }
    
}
