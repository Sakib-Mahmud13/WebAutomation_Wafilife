package com.sakib.it.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.sakib.it.basedrivers.PageDriver;
import com.sakib.it.utilities.GetScreenShot;


public class ViewAnyBookPage {
	ExtentTest test;
	
	public ViewAnyBookPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(), this);
		this.test = test;
		
	}
	@FindBy(xpath = "//span[contains(text(),'জেনারেল বই')]")
	WebElement book;
	@FindBy(xpath = "//body/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/article[1]/div[1]/div[3]/div[1]/div[2]/ul[1]/li[1]/div[1]/div[1]/a[2]")
	WebElement anybook;
	@FindBy(xpath = "//a[contains(text(),'আরো পড়ুন')]")
	WebElement bookdetails;
	public void failCase(String message, String scName) throws IOException {
		test.fail(
				"<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		@SuppressWarnings("unused")
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDriver().quit();
	}
	
	public void passCase(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>"+message+"</b></p>");
	}
	
	public void passCaseWithSC(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>"+message+"</b></p>");
		@SuppressWarnings("unused")
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}
	
	public void view() throws IOException {
		try {
			test.info("Click on Book");
			if(book.isDisplayed()) {
				book.click();
				Thread.sleep(10000);
				passCaseWithSC("Clicked", "Book");
			}
			try {
				test.info("Click on Any Book");
				if(anybook.isDisplayed()) {
					anybook.click();
					Thread.sleep(10000);
					passCaseWithSC("Clicked", "Any Book");
				}
				try {
					test.info("View on Book Details");
					if(bookdetails.isDisplayed()) {
						bookdetails.click();
						Thread.sleep(10000);
						passCaseWithSC("Viewed", "Book Details");
					}
				} catch (Exception e) {
					failCase("Book Details was not locateable.", "bookdetailsfail");
				}
			} catch (Exception e) {
				failCase("Any Book was not locateable.", "anybookfail");
			}
			
			
		} catch (Exception e) {
			failCase("Book was not locateable.", "bookfail");
		}
	}

}
