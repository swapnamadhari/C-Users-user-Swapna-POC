package com.cashe.mobile.tests;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cash.utilities.DataBaseConnection;
import com.cash.utilities.MB_Actions;
import com.cash.utilities.Randomizer;
import com.cashe.base.BasePage;
import com.cashe.mobile.pages.PreLogin;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class LoginTest {

AppiumDriver driver=null;
MB_Actions action=null;


String mobile;

public LoginTest(AppiumDriver<WebElement> driver,String platFormNm) {
// TODO Auto-generated constructor stub
this.driver = driver;
action=new MB_Actions(driver,platFormNm);
this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

public void loginMb() throws MalformedURLException, InterruptedException {
try {
/*PreLogin plogin = new PreLogin(driver);*/
//plogin.getSu-bmitButton("Android").click();
// launchApp();
// Thread.sleep(30000);
// driver.findElement(By.xpath("//*[@text='Login']")).click();

//Click Action performed
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Signup","WelcomePage"))).click();
action.swipeBasedOnDirection("UP",5);


//For taking screenshot call the below method with screenshot name,Screenshot will be present inside the Screenshot Folder near Reports
action.captureScreenShot("Pre Login Page");
//Assertion for element present

Assert.assertTrue(driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","TellUsAbout"))).isDisplayed());
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Continue","TellUsAbout"))).click();
Assert.assertTrue(driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","PersonalDetails"))).isDisplayed());

//Radio button selection
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Ms","PersonalDetails"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_UserName","PersonalDetails"))).click();
//action.swipeBasedOnDirection("UP",5);
//Enter text

String username="Swapna";
driver.findElement(By.xpath("//*[@resource-id='com.dbs.in.digibank:id/et_full_name']/TextInputLayout/android.widget.FrameLayout/android.widget.EditText")).sendKeys(username);
//driver.findElement(By.xpath(DataBaseConnection.getObject("txt_UserName","PersonalDetails"))).sendKeys(username);

//Date picker
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_DOB","PersonalDetails"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Done","PersonalDetails"))).click();
Assert.assertTrue(driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","HowToReachYou"))).isDisplayed());
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_MobileNumer","HowToReachYou"))).click();
//Generate Random Numbers
mobile = "7" + Randomizer.getRandomNumber(9);
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_MobileNumer","HowToReachYou"))).sendKeys(mobile);
//driver.findElement(By.xpath(DataBaseConnection.getObject("txt_MobileNumer","HowToReachYou"))).hideKeyboard());
action.hideKeyboard();

String emailOB = "auto4" +Randomizer.getRandomString(5)  + "@dbs.com";
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_EmailAddress","HowToReachYou"))).sendKeys(emailOB);
//driver.findElement(By.xpath(DataBaseConnection.getObject("txt_EmailAddress","HowToReachYou")).hideKeyboard());
action.hideKeyboard();

/*driver.findElement(By.xpath(DataBaseConnection.getObject("txt_ReEnterEmailAddress","HowToReachYou")).sendKeys(emailOB));
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_ReEnterEmailAddress","HowToReachYou")).hideKeyboard());
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Taphere","HowToReachYou")).isElementPresent());
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Promocode","HowToReachYou")).click());
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_Promocode","HowToReachYou")).sendKeys("234321"));
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_Promocode","HowToReachYou")).hideKeyboard());


driver.findElement(By.xpath("//*[@text='Username']")).sendKeys("testnts");
driver.findElement(By.xpath("//*[@text='Password']")).sendKeys("dbs123");
driver.findElement(By.xpath("//*[@text='LOGIN']")).click();
driver.findElement(By.xpath("//*[@xpath='//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).isDisplayed();
driver.findElement(By.xpath("//*[@text='ACCEPT']")).click();
*/
//------------------------------------------


driver.findElement(By.xpath(DataBaseConnection.getObject("txt_Username","LoginPage"))).sendKeys("testnts");
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_Password","LoginPage"))).sendKeys("dbs123");
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Login","LoginPage"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_HamburgerMenu","DashBoardPage"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Accept","DashBoardPage"))).click();
action.swipeBasedOnDirection("UP",5);
action.captureScreenShot("DashBoardPage");
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Spendings","DashBoardPage"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","SpendingsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("list_Budget","SpendingsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("listItem_Bills&Utilities","SpendingsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("listItem_Bills&Utilities","SpendingsScreen"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","BudgetBarGraphScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Edit","BudgetBarGraphScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Edit","BudgetBarGraphScreen"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_BdtgtCategory","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_MaxAmount","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Frequency","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Notify","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_EditMaxAmount","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_EditFrequency","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_EditNotify","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_EditMaxAmount","BudgetDetailsScreen"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","SetBudgeLimitScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_SetBudgetFeild","SetBudgeLimitScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_SetBudgetFeild","SetBudgeLimitScreen"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_SetBudgetFeild","SetBudgeLimitScreen"))).getText == null;//Need to check this
driver.findElement(By.xpath(DataBaseConnection.getObject("txt_SetBudgetFeild","SetBudgeLimitScreen"))).sendKeys("100000");
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_Save","SetBudgeLimitScreen"))).click();
driver.findElement(By.xpath(DataBaseConnection.getObject("lbl_Header","BudgetDetailsScreen"))).isDisplayed();
driver.findElement(By.xpath(DataBaseConnection.getObject("btn_EditMaxAmount","BudgetDetailsScreen"))).isDisplayed();
//Need  to insert screenshot method after each displayed
}

catch (Exception e) {
e.printStackTrace();
}
driver.quit();

}

}
