package com.cashe.base;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import com.cash.utilities.*;
import com.ssts.pcloudy.Connector;
import com.ssts.pcloudy.Version;
import com.ssts.pcloudy.appium.PCloudyAppiumSession;
import com.ssts.pcloudy.dto.appium.booking.BookingDtoDevice;
import com.ssts.pcloudy.dto.device.MobileDevice;
import com.ssts.pcloudy.dto.file.PDriveFileDTO;


public class Abstract extends BasePage {



	public PCloudyAppiumSession pCloudySession;
	public ThreadLocal<RemoteWebDriver> driver;
	WebDriver drive;
	String gridUrl="http://127.0.0.1:4724/wd/hub";
	//@Test
	@SuppressWarnings("rawtypes")
	public void openAndroidApp() throws MalformedURLException, InterruptedException{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "7a6741c");
		cap.setCapability("platformVersion", "5.0.2");
		cap.setCapability("platformName", "Android");
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);
		//cap.setCapability("automationName", "uiautomator2");

		cap.setCapability("appPackage", "co.tslc.cashe.android");
		cap.setCapability("appActivity", "co.tslc.cashe.android.ui.activity.SplashActivity");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);

		cap.setCapability("newCommandTimeout", 30000);

		driver = new ThreadLocal<RemoteWebDriver>();
		driver.set(new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap));

		ThreadLocalWebDriver.setWebDriver(driver.get());
		// implicitWait();
		// Thread.sleep(8000);
	}

	public void openSauceLabs(String name) throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("testobjectApiKey", "26864AC477CD4440ABD84362CFE289AD");

		// Dynamic device allocation of an iPhone 7, running iOS 10.3 device
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "8.0.0");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);

		//Motorola_Moto_E2_real_us  //LG_Nexus_5X_free  //Motorola_Moto_E_2nd_gen_free
		capabilities.setCapability("deviceName", "Motorola_Moto_E_2nd_gen_free");
		capabilities.setCapability("appPackage", "co.tslc.cashe.android");
		capabilities.setCapability("appActivity", "co.tslc.cashe.android.ui.activity.SplashActivity");
		// Set allocation from private device pool only
		capabilities.setCapability("privateDevicesOnly", "true");

		// Set Application under test
		capabilities.setCapability("testobject_app_id", "1");
		capabilities.setCapability("name",  name);

		// Set Appium version
		//capabilities.setCapability("appiumVersion", "1.6.4");

		// Set Appium end point
		driver = new ThreadLocal<RemoteWebDriver>();
		//https://us1.appium.testobject.com/wd/hub
		driver.set(new AndroidDriver(new URL("https://eu1.appium.testobject.com/wd/hub"), capabilities));
		ThreadLocalWebDriver.setWebDriver(driver.get());

		/* driver.get().getCapabilities().getCapability("testobject_test_report_url");
		    driver.get().getCapabilities().getCapability("testobject_test_live_view_url");
		 */
	} 
	public void openiOSAppSauce() throws MalformedURLException, InterruptedException{
		DesiredCapabilities	capabilities = new DesiredCapabilities();
		capabilities.setCapability("newCommandTimeout", 600);
		capabilities.setCapability("launchTimeout", 90000);
		capabilities.setCapability("deviceName", "iPhone");
		capabilities.setCapability("deviceVersion", "11.2.5");

		//capabilities.setCapability("udid", "340006ab94da578c39eb8eb1fcc221cab1fc1a20");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("bundleId", "co.tslc.casheapp.ios");

		//capabilities.setCapability("usePrebuiltWDA", false);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		//capabilities.setCapability("noReset", true);
		//capabilities.setCapability("app", "/Users/vijaymattaparthi/Downloads/Cashe-ios-devJun8.ipa");
		capabilities.setCapability("automationName", "XCUITest");

		capabilities.setCapability("useJSONSource",true);

		driver = new ThreadLocal<RemoteWebDriver>();
		driver.set(new IOSDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), capabilities));
		ThreadLocalWebDriver.setWebDriver(driver.get());

		Thread.sleep(5000);
		try {
			driver.get().switchTo().alert().accept();
		}catch(Exception e) {

		}
	}


	public void openiOSApp() throws MalformedURLException, InterruptedException{
		DesiredCapabilities	capabilities = new DesiredCapabilities();
		capabilities.setCapability("newCommandTimeout", 600);
		capabilities.setCapability("launchTimeout", 90000);
		capabilities.setCapability("deviceName", "iPhone");
		capabilities.setCapability("deviceVersion", "11.2.5");

		capabilities.setCapability("udid", "f69dc4e31f37ed9ee43b0a5cda93bdc43b9717a9");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("bundleId", "co.tslc.casheapp.ios");

		//capabilities.setCapability("usePrebuiltWDA", false);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		//capabilities.setCapability("noReset", true);
		capabilities.setCapability("app", "/Users/vijaymattaparthi/Downloads/Cashe-ios-devJun8.ipa");
		capabilities.setCapability("automationName", "XCUITest");

		capabilities.setCapability("useJSONSource",true);

		driver = new ThreadLocal<RemoteWebDriver>();
		driver.set(new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities));
		ThreadLocalWebDriver.setWebDriver(driver.get());

		Thread.sleep(5000);
		try {
			driver.get().switchTo().alert().accept();
		}catch(Exception e) {

		}
	}

	public void openiOSSimulator() throws MalformedURLException, InterruptedException{

		Random d=new Random();
		int x=d.nextInt((9999 - 1111) + 1) + 1111;
		System.out.println(Thread.currentThread().getId());
		DesiredCapabilities	capabilities = new DesiredCapabilities();
		capabilities.setCapability("newCommandTimeout", 600);
		capabilities.setCapability("launchTimeout", 90000);
		capabilities.setCapability("deviceName", "iPhone 7");
		capabilities.setCapability("deviceVersion", "11.3");

		//capabilities.setCapability("udid", "f69dc4e31f37ed9ee43b0a5cda93bdc43b9717a9");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("bundleId", "co.tslc.casheapp.ios");
		capabilities.setCapability("noResetValue",false);
		//capabilities.setCapability("useNewWDA", false);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", true);
		/*capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);*/
		//capabilities.setCapability("noReset", true);
		capabilities.setCapability("app", "/Users/vijaymattaparthi/Documents/Cashe-ios-dev_Jul_30.app");
		capabilities.setCapability("automationName", "XCUITest");


		/*
		capabilities.setCapability("useJSONSource",true);
		capabilities.setCapability("wdaLocalPort", x);*/
		driver = new ThreadLocal<RemoteWebDriver>();
		//if(Thread.currentThread().getId()==12) {
		driver.set(new IOSDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
		/*}else {
			driver.set(new IOSDriver(new URL("http://localhost:4725/wd/hub"), capabilities));

		}*/
		ThreadLocalWebDriver.setWebDriver(driver.get());

		Thread.sleep(5000);
		try {
			driver.get().switchTo().alert().accept();
		}catch(Exception e) {

		}
	}

	public RemoteWebDriver getDriver() {
		if (driver != null) {
			return driver.get();
		} else {
			return (RemoteWebDriver) ThreadLocalWebDriver.getDriver();
		}
	}



	/*@BeforeClass
	public void startReport()
	{	
		eReports = ExtentFactory.getInstance();

	}*/

	/*@Test
	public void Test() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException
	{
		eTest = eReports.startTest("Test");


	   		Assert.assertTrue(true);
	   		eTest.log(LogStatus.PASS, "landed on customer support page");

	}*/

	/*@Test
	public void FailedTest()
	{
		eTest = eReports.startTest("FailedTest");
		eTest.log(LogStatus.INFO, "test starting for failed");
		Assert.assertTrue(false);
		eTest.log(LogStatus.FAIL, "OOPSSSS- didnt land on customer support page");
	}*/

	//@AfterClass
	/*public void getResult(ITestResult result)
	{
		System.out.println("after test");
		if(result.getStatus()==ITestResult.FAILURE)
			{
				eTest.log(LogStatus.FAIL, result.getThrowable());
			}
		eReports.endTest(eTest);
		eReports.flush();

	}*/

	//@AfterSuite
	/*public void endReport()
	{
		eReports.flush();
		eReports.close();
	}*/



	@DataProvider(name="dp",parallel=true)
	public static Object[][] readTestData(ITestContext context){

		Xls_Reader xls = new Xls_Reader("src/main/resources/TestData/LoginTestdata.xls");
		//String SheetName="NewUser";
		String SheetName=context.getName();
		System.out.println("SheetName is +++"+SheetName);
		ArrayList<Integer> NoofIterations = new ArrayList<Integer>();
		for(int rNum = 2;rNum<=xls.getRowCount(SheetName);rNum++){
			if("Yes".equalsIgnoreCase(xls.getCellData(SheetName, "Runmode", rNum))){
				NoofIterations.add(rNum);
			}                            
		} 
		Object[][] data = new Object[NoofIterations.size()][1];                
		for(int i = 0; i <NoofIterations.size();i++){
			Hashtable<String, String> table = new Hashtable<String, String>();
			for(int cols=0;cols<180;cols++){
				table.put(xls.getCellData(SheetName, cols, 1), xls.getCellData(SheetName, cols, NoofIterations.get(i)));
			}
			data[i][0]=table;
		}
		return data;
	}

	//@Test
	public synchronized MobileDevice runExecutionOnPCloudy(String platform,String tcName) throws Exception {
		System.out.println("Thread is :"+Thread.currentThread().getId());
		ArrayList<MobileDevice> selectedDevices = null;
		Connector pCloudyCONNECTOR;
		String authToken = null;
		String sessionName;
		boolean tabPad=false;
		try{
			do{
				tabPad=false;
				pCloudyCONNECTOR = new Connector("https://device.pcloudy.com/api/");

				// User Authentication over pCloudy
				System.out.println("Env is :::"+System.getProperty("environmemt"));
				if(System.getProperty("environmemt")==null) {
					authToken = pCloudyCONNECTOR.authenticateUser(pro.getProperty("pCloudy_UN"), pro.getProperty("pCloudy_ACCESSKey"));
				}else {
					authToken = pCloudyCONNECTOR.authenticateUser(System.getProperty("pCloudy_UN"), System.getProperty("pCloudy_ACCESSKey"));

				}
				selectedDevices = new ArrayList<>();
				//String[] device={"24f0175a6673467fdd3e390cb6dbdf3190375d06"};
				// Populate the selected Devices here1
				//selectedDevices.add(pCloudyCONNECTOR.chooseSingleDevice(authToken, "android"));
				//selectedDevices.addAll(pCloudyCONNECTOR.chooseDevicesFromArrayOfFullNames(authToken, "android", device ));
				if("android".toLowerCase().equalsIgnoreCase(platform)){
					selectedDevices.addAll(pCloudyCONNECTOR.chooseDevices(authToken, "android", new Version("6.*.*"),new Version("8.*.*"), 1));
				}else{
					//selectedDevices.addAll(pCloudyCONNECTOR.chooseDevices(authToken, "iOS", new Version("9.3.*"),new Version("11.*.*"), 1));
					selectedDevices.addAll(pCloudyCONNECTOR.chooseDevices(authToken, "iOS", new Version("8.*.*"),new Version("11.*.*"), 1));
					//selectedDevices.addAll(pCloudyCONNECTOR.chooseDevicesFromArrayOfFullNames(authToken, "iOS", device ));
				}
				System.out.println("selected device is :"+selectedDevices);
				sessionName = selectedDevices.get(0).display_name + tcName;

				if(sessionName.contains("Tab") || sessionName.contains("Pad") || sessionName.contains("Yulong") || sessionName.contains("Blackberry")){
					tabPad=true;
					selectedDevices = null;
					pCloudyCONNECTOR=null;
					authToken=null;
					sessionName=null;
				}
			}while(tabPad);
			/*String device=Runtime.getRuntime().exec("adb devices").toString().split("@")[1].trim();

			Runtime.getRuntime().exec("adb -s "+device+" uninstall com.facebook.katana");
			System.out.println("Devices are :"+device+",,,,,,"+Runtime.getRuntime().exec("adb devices"));*/
			// Book the selected devices in pCloudy
			Thread.sleep(8000);
			BookingDtoDevice[] bookedDevicesIDs = pCloudyCONNECTOR.AppiumApis().bookDevicesForAppium(authToken, selectedDevices, 30, sessionName);
			System.out.println("Devices booked successfully");

			// Select apk in pCloudy Cloud Drive
			PDriveFileDTO pDriveFile;
			if("android".toLowerCase().equalsIgnoreCase(platform))
				pDriveFile = PDriveFileDTO.getNew("CASHe_STAGE_v7.8.401.apk");
			
			else
				pDriveFile = PDriveFileDTO.getNew("Cashe-ios-devJun8.ipa");

			System.out.println("apk file selected from CloudyDrive");

			pCloudyCONNECTOR.AppiumApis().initAppiumHubForApp(authToken, pDriveFile);

			// Get the endpoint from pCloudy
			URL endpoint = pCloudyCONNECTOR.AppiumApis().getAppiumEndpoint(authToken);
			System.out.println("Appium Endpoint:" + endpoint);

			URL reportFolderOnPCloudy = pCloudyCONNECTOR.AppiumApis().getAppiumReportFolder(authToken);
			System.out.println("Report Folder: " + reportFolderOnPCloudy);

			//pCloudyCONNECTOR.revokeTokenPrivileges(authToken);
			Thread.sleep(5000);

			pCloudySession = new PCloudyAppiumSession(pCloudyCONNECTOR, authToken, bookedDevicesIDs[0]);
			setPSession(pCloudySession);
			//pCloudyCONNECTOR.executeAdbCommand(authToken, deviceBookingDto, adbCommand)
			// Create multiple driver objects in multiple threads
			/*for (int i = 0; i < bookedDevicesIDs.length; i++) {
			Thread.sleep(5000);
			new Thread(getTestCaseClass(endpoint, bookedDevicesIDs[i])).start();

		}*/
			Thread.sleep(5000);
			getTestCaseClass(endpoint, bookedDevicesIDs[0],platform);
		}catch(Exception e){
			e.printStackTrace();
		}
		return selectedDevices.get(0);
	}
	public PCloudyAppiumSession getPSession(){
		return pCloudySession;
	}
	public PCloudyAppiumSession setPSession(PCloudyAppiumSession pSession){
		return	this.pCloudySession=pSession;
	}

	public   void getTestCaseClass( final URL endpoint, final BookingDtoDevice dto,String platform) throws Exception {
		// this will give a Thread Safe TestScript class.
		// You may also like to have this as a named class in a separate file

		/*return new Runnable() {

			@Override
			public void run() {*/
		DesiredCapabilities capabilities;
		try{

			if("android".toLowerCase().equalsIgnoreCase(platform)){
				capabilities = new DesiredCapabilities();
				capabilities.setCapability("newCommandTimeout", 80000);
				capabilities.setCapability("launchTimeout", 90000);
				capabilities.setCapability("unicodeKeyboard", true);
				capabilities.setCapability("resetKeyboard", true);
				capabilities.setCapability("deviceName", dto.capabilities.deviceName);
				capabilities.setCapability("browserName", dto.capabilities.deviceName);
				capabilities.setCapability("platformVersion", dto.getVersion());
				capabilities.setCapability("platformName", "Android");
				//capabilities.setCapability("sendKeyStrategy", "setValue");  //for speedUp of execution 
				//capabilities.setCapability("automationName", "uiautomator2");
				//capabilities.setCapability("appiumVersion", "1.7.1");
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("autoGrantPermissions", true);
				if (dto.getVersion().compareTo(new Version("7.*.*")) >= 0){

					capabilities.setCapability("automationName", "uiautomator2");
					//capabilities.setCapability("systemPort", new Random().nextInt(2000) + 1024);
					//System.out.println("version is:::: "+dto.getVersion());
				}


				capabilities.setCapability("appPackage", "co.tslc.cashe.android");
				capabilities.setCapability("appActivity", "co.tslc.cashe.android.ui.activity.SplashActivity");
				capabilities.setCapability("rotatable", true);
				//	AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(endpoint, capabilities);

				//System.out.println("entered");
				driver = new ThreadLocal<RemoteWebDriver>();
				driver.set(new AndroidDriver(endpoint, capabilities));

				ThreadLocalWebDriver.setWebDriver(driver.get());

			}else{

				capabilities = new DesiredCapabilities();
				capabilities.setCapability("newCommandTimeout", 60000);
				capabilities.setCapability("launchTimeout", 90000);
				capabilities.setCapability("deviceName", dto.capabilities.deviceName);
				//capabilities.setCapability("browserName", dto.capabilities.browserName);
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("bundleId", "co.tslc.casheapp.ios");

				//capabilities.setCapability("usePrebuiltWDA", false);
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("autoGrantPermissions", true);
				capabilities.setCapability("platformVersion", dto.getVersion());


				if (dto.getVersion().compareTo(new Version("9.3")) >= 0)
					capabilities.setCapability("automationName", "XCUITest");
				else
					capabilities.setCapability("automationName", "Appium");


				driver = new ThreadLocal<RemoteWebDriver>();
				driver.set(new IOSDriver(endpoint, capabilities));
				ThreadLocalWebDriver.setWebDriver(driver.get());

				Thread.sleep(5000);
				driver.get().switchTo().alert().accept();


			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//Thread.sleep(5000);
		//driver.get().quit();
		//pCloudySession.releaseSessionNow();
		// Your Test Script Goes Here�
		/*	CasheAppTest casheApp = new CasheAppTest();
				casheApp.driver = driver;
				casheApp.casheLogin();*/
		/*	}

		};*/
	}
	/*public  static void main(String[] arg) throws Exception{

		ArrayList<MobileDevice> selectedDevices = null;
		Connector pCloudyCONNECTOR;
		String authToken;
		String sessionName;
		boolean tabPad=false;
		//try{
		do{
			pCloudyCONNECTOR = new Connector("https://device.pcloudy.com/api/");

			// User Authentication over pCloudy
			authToken = pCloudyCONNECTOR.authenticateUser("monica.natha@cashe.co.in", "b9fzzqchxtcgz54p4mmz77cp");

			selectedDevices = new ArrayList<>();
			String[] device={"Oppo_Neo5_Android_5.1.0"};
			// Populate the selected Devices here1
			//selectedDevices.add(pCloudyCONNECTOR.chooseSingleDevice(authToken, "android"));
			selectedDevices.addAll(pCloudyCONNECTOR.chooseDevicesFromArrayOfFullNames(authToken, "android", device ));

			//selectedDevices.addAll(pCloudyCONNECTOR.chooseDevices(authToken, "android", new Version("5.*.*"),new Version("6.*.*"), 1));


			sessionName = selectedDevices.get(0).display_name + " Appium Session";

			if(sessionName.contains("Tab") || sessionName.contains("Pad")){
				tabPad=true;
				selectedDevices = null;
				pCloudyCONNECTOR=null;
				authToken=null;
				sessionName=null;
			}
		}while(tabPad);

		// Book the selected devices in pCloudy
		BookingDtoDevice[] bookedDevicesIDs = pCloudyCONNECTOR.AppiumApis().bookDevicesForAppium(authToken, selectedDevices, 30, sessionName);
		System.out.println("Devices booked successfully");

		// Select apk in pCloudy Cloud Drive
		PDriveFileDTO pDriveFile = PDriveFileDTO.getNew("CASHe_QA_v7.5.7_debug.apk");
		System.out.println("apk file selected from CloudyDrive");

		pCloudyCONNECTOR.AppiumApis().initAppiumHubForApp(authToken, pDriveFile);

		// Get the endpoint from pCloudy
		URL endpoint = pCloudyCONNECTOR.AppiumApis().getAppiumEndpoint(authToken);
		System.out.println("Appium Endpoint:" + endpoint);

		URL reportFolderOnPCloudy = pCloudyCONNECTOR.AppiumApis().getAppiumReportFolder(authToken);
		System.out.println("Report Folder: " + reportFolderOnPCloudy);

		//pCloudyCONNECTOR.revokeTokenPrivileges(authToken);

		//pCloudySession = new PCloudyAppiumSession(pCloudyCONNECTOR, authToken, bookedDevicesIDs[0]);
	}


	 */

}
