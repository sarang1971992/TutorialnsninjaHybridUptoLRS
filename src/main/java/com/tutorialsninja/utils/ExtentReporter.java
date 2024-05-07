package com.tutorialsninja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() throws IOException {
		
		ExtentReports extentReport = new ExtentReports(); 
		
		File extentReporterFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	    ExtentSparkReporter extentsparkreporter = new ExtentSparkReporter(extentReporterFile);
		
	    extentsparkreporter.config().setTheme(Theme.DARK);
	    extentsparkreporter.config().setReportName("TutorialsNinja Test Automation Result");
	    extentsparkreporter.config().setDocumentTitle("TN Automation Repoort");
	    extentsparkreporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
	    
	    extentReport.attachReporter(extentsparkreporter); 
	    
	    Properties prop = new Properties();
	    File configFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
	    
	    try {
	    	  FileInputStream fisfileinputstream = new FileInputStream(configFile);
	  	      prop.load(fisfileinputstream);
	  	    	
	    } catch(Throwable e) {
	    	e.printStackTrace();
	    }
	    
	    
	    extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
	    extentReport.setSystemInfo("Browser", prop.getProperty("browser"));
	    
	    extentReport.setSystemInfo("Email", prop.getProperty("email"));
	    
	    return extentReport;
		
	}
}
