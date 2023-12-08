package com.sakib.it.tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sakib.it.basedrivers.BaseDrivers;
import com.sakib.it.basedrivers.PageDriver;
import com.sakib.it.pages.ClickOderPage;
import com.sakib.it.utilities.ExtentFactory;

public class ClickOderTest extends BaseDrivers {
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;

	@BeforeClass
	public void start () throws InterruptedException {
		PageDriver.getCurrentDriver().get(url);
		Thread.sleep(10000);
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>WAFILIFE</b></p>").assignAuthor("QA TEAM").assignDevice("Windows");
		
	}
	@Test
	public void viewTest() throws IOException {
		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>Click অর্ডার করুন</b></p>");
		ClickOderPage clickoderPage = new ClickOderPage(childTest);
		clickoderPage.oder();
	}
	@AfterClass
	public void report() {
		report.flush();
	}

}
