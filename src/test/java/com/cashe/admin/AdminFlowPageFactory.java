package com.cashe.admin;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminFlowPageFactory extends CommonFunctions{


	@FindBy(xpath="//div[@id = 'spinner']")
	public WebElement loadingSymbol;
	
	@FindBy(xpath = "//a[@class='sidebar-toggle']")
	public WebElement toggleNavigation;

	@FindBy(xpath = "//a/i[@class ='fa fa-user']")
	public WebElement customerIcon;

	@FindBy(xpath = "//a/span[text()='Customers']")
	public WebElement customersTab;

	@FindBy(xpath = "//a/span[text()='Customer Validation']")
	public WebElement custValidationTab;

	@FindBy(xpath = "//strong[text()='My Cases']")
	public WebElement tabMyCases;

	@FindBy(xpath = "//div[@class = 'box-body no-padding']")
	public WebElement tableMyCases;

	@FindBy(xpath = "//input[@id='search']")
	public WebElement MyCasesFilter;  //In My Cases Tab

	@FindBy(xpath = "//strong[text()='Search']")
	public WebElement tabSearch;

	@FindBy(xpath = "//button[@id='searchbutton']")
	public WebElement btnSearch;

	@FindBy(xpath = "//div[@class='box']")
	public WebElement tableSearchDts;

	@FindBy(xpath = "//input[@id='search']")
	public WebElement SearchFilter;   //In Search Tab

	@FindBy(xpath ="//input[@type='checkbox']")
	public WebElement chkCust;

	@FindBy(xpath ="//*[@id='AssignBtn']")
	public WebElement BtnAssign;

	@FindBy(xpath="//*[@id='UnAssignBtn']")
	public WebElement BtnUnassign;

	@FindBy(xpath="//span[text()='Case(s) Assigned successfully']")
	public WebElement successFlag;

	@FindBy(xpath="//input[@placeholder='New Or Repeat']")
	public WebElement autNewRepeat;

	@FindBy(xpath="//input[@placeholder='Current Assignee']")
	public WebElement drpAssignedTo;

	@FindBy(xpath="//input[@placeholder='Select Status']")
	public WebElement Statusdropdown;

	@FindBy(xpath ="//span[contains(text(),'Assign')]")
	public WebElement drpAssignDD;//*[@ng-class='{open: $select.open}']

	@FindBy(xpath ="//span[contains(text(),'sujitha@sujitha.com')]")
	public WebElement AssignDDvalue;

	@FindBy(xpath ="//button[@ng-show='closeable']")
	public WebElement AssigncloseFlag;

	@FindBy(xpath = "//span[@class='dropdown-toggle blueTri']")
	public WebElement VerifyDots;

	@FindBy(xpath = "//a[text() = 'Verify']")
	public WebElement verifyBtn;

	@FindBy(xpath = "//span[contains(text(),'IMEI Matching Customers:')]")
	public WebElement IMEI;

	@FindBy(xpath = ".//*[@id='main_div']")
	public WebElement FraudCheckMtchCsts;

	@FindBy(xpath = "//div[@class='panel-body']")
	public WebElement IMEITable;

	@FindBy(xpath = "//span[contains(text(),'Defaulters Calls:')]")
	public WebElement DefaulterCalls;

	@FindBy(xpath = "//button[@id='continue']")
	public WebElement ContinueBtn;

	@FindBy(xpath ="//span[text()='Personal Details']")
	public WebElement PersonalDetails;

	@FindBy(xpath ="//*[@id='personalDetails']/div/div[1][@class='panel panel-default panel-open']")
	public WebElement PersonalDetailspanel;

	@FindBy(xpath = "//input[@id='personlDetailsGroupID']")
	public WebElement PrsnlDtlsChkbox;

	/*@FindBy(xpath = "//span[text()='PAN Details']")
  public WebElement PanDetails;*/

	@FindBy(xpath = "//span[text()='PAN/Aadhar Details']")
	public WebElement PanDetails;


	@FindBy(xpath = "//input[@id='PanDetailsID']")
	public WebElement PanDtlsChkbox;

	@FindBy(xpath = "//span[text()='Company Details']")
	public WebElement CompanyDetails;

	@FindBy(xpath = "//input[@id='companyDetailsID']")
	public WebElement CmpnyDtlsChkbox;

	@FindBy(xpath = "//span[text()='Address Details']")
	public WebElement AddressDetails;

	@FindBy(xpath = "//input[@id='addressDetailsID']")
	public WebElement AddrDtlsChkbox;

	@FindBy(xpath = "//span[text()='Bank Details']")
	public WebElement BankDetails;

	@FindBy(xpath = "//input[@id='bankDetailsGroupID']")
	public WebElement BnkDtlsChkbox;

	@FindBy(xpath = "//span[text()='Other Details']")
	public WebElement OtherDetails;

	@FindBy(xpath = "//input[@id='otherGroupID']")
	public WebElement OthrDtlsChkbox;

	/*  @FindBy(xpath = "//button[text() = 'Save']")
  public WebElement SaveButton;*/

	/*  @FindBy(xpath= "//span[text()= 'Successfully saved partially verified data.']")
  public WebElement savesucessflag;*/

	@FindBy(xpath = "//select[@name='status']")
	public WebElement Status;

	@FindBy(xpath="//a[@ng-click='expandCustomerInfo(cust)']")
	public WebElement custidcol;

	@FindBy(xpath = "//select[@name='Sub-Category']")
	public WebElement SubCtgry;

	@FindBy(xpath = "//select[@name='reason']")
	public WebElement Reason;

	/* @FindBy(xpath = "//button[text()='Submit']")
  public static  WebElement btnSubmit;*/

	@FindBy(xpath="//button[contains(text(),'Confirm')]")
	public WebElement btnConfirm;

	@FindBy(xpath="//button[2][contains(text(),'No')]")
	public WebElement btnNo;

	@FindBy(xpath = "//button[@ng-click='ok()']")
	public WebElement btnOk;

	/*  @FindBy(xpath = "//input[@id='company_value']")
  public WebElement ChangeCompany;*/

	@FindBy(xpath = "//input[@id='companyId']")
	public WebElement ChngCmpnyChkbox;

	@FindBy(xpath = "//input[@ng-model = 'CustomerCheckbox.profileImage']")
	public WebElement custProfileImgChkbox;

	@FindBy(xpath = "//input[@ng-model = 'CustomerCheckbox.pancardImage']")
	public WebElement custPanImgChkbox;

	@FindBy(xpath = "//input[@ng-model = 'CustomerCheckbox.addressImage']")
	public WebElement custAddressImgChkbox;

	@FindBy(xpath = "//input[@ng-model = 'CustomerCheckbox.payslipImage']")
	public WebElement custPayslipImgChkbox;

	@FindBy(xpath = "//input[@ng-model='CustomerCheckbox.bankStatementImage']")
	public WebElement custBankStmtImgChkbox;

	@FindBy(xpath ="//select[@name='category']")
	public WebElement category;

	/*  @FindBy(xpath = "//button[@ng-click='cancelValidation()']")
  public WebElement Cancelbtn;*/

	@FindBy(xpath = "//button[@ng-click='yes()']")
	public WebElement Yes;

	@FindBy(xpath = "//button[text()='NO']")
	public WebElement No;


	@FindBy(xpath = "//input[@ng-model='customer.customerName']")
	public WebElement FullName;

	/*  @FindBy(xpath = "//*[@id='company_value']")
  public WebElement Verifycompany;*/

	@FindBy(xpath = "//input[@ng-model='customer.personalEmailId']")
	public WebElement PersonalEmailId;

	@FindBy(xpath = "//select[@name='educationTypeId']")
	public WebElement EducationType;

	@FindBy(xpath = "//input[@name = 'mobNumber']")
	public WebElement MobileNumber;

	@FindBy(xpath = "//input[@ng-model='customer.landlineNumber']")
	public WebElement LandlineNumber;

	@FindBy(xpath = "//input[@id='panNumber']")
	public WebElement PAN;

	@FindBy(xpath = "//select[@ng-model='customer.designationId']")
	public WebElement Designation;

	@FindBy(xpath = "//input[@ng-model='customer.doj']")
	public WebElement DOJ;

	@FindBy(xpath = "//input[@ng-model='customer.email']")
	public WebElement OffEmailId;

	@FindBy(xpath = "//input[1][@ng-model='customer.companyLandlineNo']")
	public WebElement CmpnyLLNumber;

	@FindBy(xpath = "//input[2][@ng-model='customer.extensionNumber']")
	public WebElement CmpnyExtNumber;

	@FindBy(xpath = "//textarea[@ng-model='customer.currentAddress']")
	public WebElement FullCurrentAddr;

	@FindBy(xpath = "//input[@ng-model='customer.postcode']")
	public WebElement Postalcode;

	@FindBy(xpath = "//select[@ng-model='customer.houseTypeId']")
	public WebElement Accommodation;

	@FindBy(xpath = "//input[@name='cibilScore']")
	public WebElement custCibilScore;

	/* @FindBy(xpath = "//label[text()='Latest Address by Lat/Long: ']")
  public WebElement Latlong;*/	

	@FindBy(xpath = "//div[@class='box-header with-border']/table/tbody/tr[1]")
	public WebElement Header; 

	@FindBy(xpath ="//a[@ng-click='expandCustomerInfo(cust)']")
	public WebElement pickcusid;

	@FindBy(xpath ="//a[@ng-click='openEditSalaryModal(customer);']")
	public WebElement CustEditSal;

	@FindBy(xpath ="//input[@ng-model='data_to_sent.salary']")
	public WebElement CustSal;

	@FindBy(xpath ="//button[2][@ng-click='no()']")
	public WebElement BtnNo;

	@FindBy(xpath ="//button[1][@ng-click='yes()']")
	public WebElement BtnYes;

	@FindBy(xpath ="//button[1][@ng-click='ConfirmSalaryUpdate()']")
	public WebElement BtnConfirm;

	@FindBy(xpath ="//div[2]/div[2]/button[2][@ng-click='no()']")
	public WebElement BtnCancel;

	@FindBy(xpath ="//span[contains(text(),'Case(s) Assigned successfully')]")
	public WebElement sucflagafterAssignee;

	@FindBy(xpath ="//span[contains(text(),'Case(s) UnAssigned successfully')]")
	public WebElement sucflagafterUnAssignee;

	@FindBy(xpath="//button[1][@ng-click='yes()']")
	public WebElement BtnYessalaryedit;

	@FindBy(xpath="//span[contains(text(),'Minimum salary for the Loan Type')]")
	public WebElement Txtsalary;

	@FindBy(xpath="//button[2][contains(text(),'NO')]")
	public WebElement BtnNosalaryedit;

	@FindBy(xpath="//button[1][@ng-click='ConfirmSalaryUpdate()']")
	public WebElement BtnSalaryconfirm;

	@FindBy(xpath = "//button[2][contains(text(),'Cancel')]")
	public WebElement BtnSalaryCancel;

	@FindBy(xpath = "//font[@ color='green']")
	public WebElement MsgSalaryUpdatedSuccess;

	@FindBy(xpath = "//button[@ng-click='finalClose(1)']")
	public WebElement BtnFinalClose;

	@FindBy(xpath = "//td/label[contains(text(),'RETAIL_15')]")
	public WebElement Producttype;

	@FindBy(xpath = "//label[contains(text(),' RETAIL_30 ')]")
	public WebElement Producttype30;

	@FindBy(xpath = "//label[contains(text(),' RETAIL_90 ')]")
	public WebElement Producttype90;

	@FindBy(xpath = "//td[10]/label[contains(text(),' RETAIL_30_PLUS_30 ')]")
	public WebElement Producttype30pluse30;

	@FindBy(xpath = "//td[10]/label[contains(text(),' RETAIL_90_PLUS_30 ')]")
	public WebElement Producttype90pluse30;

	@FindBy(xpath = "//td[10]/label[contains(text(),' RETAIL_180 ')]")
	public WebElement Producttype180;

	@FindBy(xpath ="//span[contains(text(),'Minimum salary for the Loan Type RETAIL_15 is 15000')]")
	public WebElement Maxeligibility;

	@FindBy(xpath ="//span[contains(text(),'Minimum salary for the Loan Type RETAIL_30 is 15000')]")
	public WebElement Maxeligibility_30day;

	@FindBy(xpath ="//span[contains(text(),'Minimum salary for the Loan Type RETAIL_90 is 25000')]")
	public WebElement Maxeligibility_90day;

	@FindBy(xpath ="//span[contains(text(),'Minimum salary for the Loan Type RETAIL_180 is 35000')]")
	public WebElement Maxeligibility_180day;

	@FindBy(xpath ="//span[contains(text(),'Minimum salary for the Loan Type RETAIL_30_PLUS_30 is 15000')]")
	public WebElement Maxeligibility_30_PLUS_30day;

	@FindBy(xpath ="//span[contains(text(),'Minimum salary for the Loan Type RETAIL_90_PLUS_30 is 25000')]")
	public WebElement Maxeligibility_90_PLUS_30day;


	@FindBy(xpath ="//div[5]/div[1]/div[2][@class='col-sm-10 emt_mar_pad ng-binding']")
	public WebElement UIMonthlyincome;

	//****** PDC related objects ******//

	@FindBy(xpath="//input[@placeholder='Select Status']")
	public WebElement Search_StatusDD;

	@FindBy(xpath="//*[@class='dropdown-toggle blueTri']")
	public WebElement ActionColumnClick;

	@FindBy(xpath="//*[@class='verifyBtn']")
	public WebElement VerifyBtn;

	@FindBy(xpath="//*[@ng-click='expandPDCInfo()']")
	public WebElement MarkAsPDCBtn;

	@FindBy(xpath="//html/body/div[4]")
	public WebElement Alert;

	@FindBy(xpath="//*[@ng-model='pdc.opsNotes']")
	public WebElement PDC_Alert_Text;

	@FindBy(xpath="//*[@ng-click='markCustomerPDCRequired();']")
	public WebElement PDC_ReqConfBtn;

	@FindBy(xpath="//*[@ng-click='No()']")
	public WebElement PDC_ReqNoBtn;

	@FindBy(xpath="//*[@type='submit']")
	public WebElement DisabledSubmitBtn;

	@FindBy(xpath="//*[@ng-click='updateCustomerCheckbox()']")
	public WebElement DisabledSaveBtn;

	@FindBy(xpath="//div[contains(text(),'PDC_REQUIRED tag is successfully marked for CLN-') and @class='ng-binding']")
	public WebElement ActualString;

	@FindBy(xpath="//div[contains(text(),'PDC_REQUIRED tag is Successfully removed for CLN-') and @class='ng-binding']")
	public WebElement ActualRemove;

	@FindBy(xpath="//button[@ng-click='Close(1)']")
	public WebElement ConFCloseBtn;

	@FindBy(xpath="html/body/div[1]/div/div/section[3]/div/div[3]/div/div/table/tbody/tr[1]/td[2]/div[2]/i")
	public WebElement PDC_tag_onCustValidPage;

	@FindBy(xpath="//*[@class='btn btn-default btn-xs bg-blue ng-binding']")
	public WebElement Status_DVP;

	@FindBy(xpath="//*[@ng-click='yes()']")
	public WebElement CloseConfY;

	@FindBy(xpath="//button[contains(text(), 'Remove PDC ?')]")
	public WebElement RemovePDCBtn;

	@FindBy(xpath="//textarea[@ng-model='pdcRemoved.opsNotes']")
	public WebElement NotesRemovePDC;

	@FindBy(xpath="//button[contains(text(),'Confirm')]")
	public WebElement ConfirmBtn;

	@FindBy(xpath="//button[2][contains(text(),'No')]")
	public WebElement NoBtn;

	//****** PDC related objects  END******//

	//****** CUSTOMER MATCHING DOCUMENTS OBJECTS ******//

	@FindBy(xpath="//li[@heading='Customer Matching Documents']")
	public WebElement CustomerMatchingDocsTAB;

	//************** CUSTOMER PHONE DETAILS*****************

	@FindBy(xpath="//a[contains(text(),'Customer Phone Details')]")
	public WebElement CustomerPhoneDetailsTAB;

	@FindBy(xpath="//span[contains(text(),'IMEI Matching Customers:')]")
	public WebElement IMEIMatchingCustomersTAB;

	// *****************************************************************SHALINI


	//Personal Details
	@FindBy(xpath="//span[contains(text(),'Invalid Name!')]")
	public WebElement invalidname;

	@FindBy(xpath = "//img[@src='static/dist/img/logout.png']")
	public WebElement logoutBtn;

	@FindBy(xpath = "//span[contains(text(),'Invalid Email!')]")
	public WebElement invalidemail;

	@FindBy(xpath = "//span[contains(text(),'Invalid Mobile Number!')]")
	public WebElement invalimobileno;

	@FindBy(xpath = "//div[@ng-show='(custformDetails.mobNumber.$error.minlength)||(custformDetails.mobNumber.$error.maxlength)']")
	public WebElement invalimobilenolength;

	@FindBy(xpath = "//div[contains(text(),'Invalid LandLine Number')]")
	public WebElement invalidlandline;

	//cust_type_badge

	@FindBy(xpath = "//form//table//td[1]//span[2]")
	public WebElement customertype;

	@FindBy(xpath = "//table//div/div[1]/span")
	public WebElement customerbadge;


	//buttons and status
	/*	@FindBy(xpath = "//select[@ng-change='approvalChanged();']//option")
	public WebElement approvalby;*/

	@FindBy(xpath = "//section[2]//div[6]//select[@ng-model='customer.verificationApprovalId']")
	public WebElement approvalby;

	@FindBy(xpath = "//button[@ng-click='updateCustomerCheckbox()']")
	public WebElement SaveButton;

	@FindBy(xpath = "//div[@class='modal-body ng-scope']//div/font")
	public WebElement submitbtnsuccessmsg;

	@FindBy(xpath = "//button[@ng-click='ok()']")
	public WebElement submitbtnsuccessmsgok;

	@FindBy(xpath = "//body/div[4]//font")
	public WebElement cancleflag;

	@FindBy(xpath = "//button[@ng-click='yes()']")
	public WebElement cancleflagyes;

	@FindBy(xpath = "//button[@ng-click='no()']")
	public WebElement cancleflagno;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public   WebElement btnSubmit;

	@FindBy(xpath = "//button[@ng-click='cancelValidation()']")
	public WebElement Cancelbtn;

	@FindBy(xpath= "//div/div[3]/div/div/div/div/span")
	public WebElement savesucessflag;

	@FindBy(xpath= "//h3")
	public WebElement oopsvalidation; 

	@FindBy(xpath= "//select[@ng-model='customerValidationMsg.category_id']")
	public WebElement faildcategory;  

	@FindBy(xpath= "//select[@ng-model='customerValidationMsg.subcategoryIds']")
	public WebElement sub_category;  

	@FindBy(xpath= "//select[@ng-model='customer.validationIds']")
	public WebElement reason; 

	//header
	@FindBy(xpath= "//div[@class='box-header with-border']//tr[1]/td[2]/label")
	public WebElement MaxEligibleAmount; 

	@FindBy(xpath= "//div[@class='box-header with-border']//tr[1]/td[4]/label")
	public WebElement LoanRequestedAmount; 

	@FindBy(xpath= "//div[@class='box-header with-border']//tr[1]/td[6]/label")
	public WebElement PromoCode;

	@FindBy(xpath= "//div[@class='box-header with-border']//tr[1]/td[8]/label")
	public WebElement TotalOutstandingPrincipalAmount;

	@FindBy(xpath= "//div[@class='box-header with-border']//td[@class='ng-binding']")
	public WebElement Latlong ;

	@FindBy(xpath= "//div[@class='box-header with-border']")
	public WebElement headerfooter;

	//view
	@FindBy(xpath="//a[text()='View']")
	public WebElement ActionView;

	@FindBy(xpath="//button[@ng-click='closeValidation(customer.customerId)']")
	public WebElement Viewclose;

	@FindBy(xpath="//a[@ng-click='select()' and contains(text(),'Bank Details')]")
	public WebElement viewbankdetails;

	@FindBy(xpath="//section[@class='content-header']")
	public WebElement header;

	//images
	@FindBy(xpath="//img[@alt='User profile picture']")
	public WebElement profileimage;

	@FindBy(xpath="//img[@data-target='#pancard_active_modal_0']")
	public WebElement panimage;

	@FindBy(xpath="//img[@data-target='#address_active_modal_0']")
	public WebElement addressimage;

	@FindBy(xpath="//img[@data-target='#payslip_active_modal_0']")
	public WebElement payslipimage;

	@FindBy(xpath="//img[@data-target='#bank_statement_active_modal_0']")
	public WebElement bankstatementimage;

	@FindBy(xpath="//img[@data-target='#badge_active_modal_0']")
	public WebElement badgeimage;

	@FindBy(xpath="//button[@ng-click='Badge_hide=!Badge_hide']")
	public WebElement Historicalbadgeimage;

	@FindBy(xpath="//div[@ng-show='Badge_hide']")
	public WebElement badgeimage_hide;

	//bank detaile
	@FindBy(xpath=".//*[@id='bankDetails']/div[2]/div/div[1]/div[1]/div[2]")
	public WebElement accountnumber;

	@FindBy(xpath="//input[@id='accountNoID']")
	public WebElement account_no_checkbox;

	@FindBy(xpath=".//*[@id='bankDetails']/div[2]/div/div[1]/div[2]/div[2]")
	public WebElement bankname;

	@FindBy(xpath="//input[@id='bankNameID']")
	public WebElement bankname_checkbox;

	@FindBy(xpath="//input[@id='bankIfscID']")
	public WebElement ifsc_checkbox;

	//company
	@FindBy(xpath= ".//*[@id='allCmpnyRdio']")
	public WebElement allcmpnyrdio; 

	@FindBy(xpath= ".//*[@id='company_value']")
	public WebElement allcmpnytextbox;  

	@FindBy(xpath = "//input[@id='apprvdCmpny_value']")
	public WebElement ChangeCompany;

	//edit sal
	@FindBy(xpath="//input[contains(@ng-model,'searchdata.customerId')]")
	public WebElement customerid;

	@FindBy(xpath="//section[4]//table//tr[1]/td[5]")
	public WebElement loantype;

	@FindBy(xpath="//section[2]//tr[1]/td[10]/label")
	public WebElement producttype;

	@FindBy(xpath="//form//tbody/tr[2]/td[3]/span")
	public WebElement editsaltext;

	@FindBy(xpath="//div[2][@ng-show='showYes==2']//tr[2]/td[3]")
	public WebElement updatedsal;

	@FindBy(xpath="//div[2][@ng-show='showYes==2']//tr[3]/td[3]")
	public WebElement updatedloan;

	//label names in company details

	@FindBy(xpath="//div[@id='companyDetails']//div[1]/label")
	public WebElement lablescompany;

	@FindBy(xpath="//div[@id='companyDetails']//input[@ng-model='customer.employeeCode']")
	public WebElement employeecode;

	@FindBy(xpath=".//*[@id='companyDetails']//input[@ng-model='customer.doj']")
	public WebElement employeeDOJ;

	@FindBy(xpath="//div[@id='companyDetails']//input[@ng-model='customer.companyAlternateNo']")
	public WebElement companyalternateno;

	@FindBy(xpath=".//div[@id='companyDetails']//div[7]")
	public WebElement companyaddress;

	@FindBy(xpath="//div[@id='companyDetails']//p[@ng-if='customer.isCompanyNewForVerification']")
	public WebElement newforverifitag;


	//cibil
	@FindBy(xpath="//div[@class='nav-tabs-custom']//li[@ng-click='getCustomerCibilData()']/a")
	public WebElement cibiltab;

	@FindBy(xpath="//html//tbody/tr[2]//tr[1]/td[5]")
	public WebElement cibilDate;

	@FindBy(xpath="html//tbody/tr[2]//tr[2]/td[5]")
	public WebElement cibilTime;

	@FindBy(xpath="//html/body/table/tbody/tr[2]//table//tr[1]/td[2]")
	public WebElement cibilName;

	@FindBy(xpath="//html/body/table/tbody/tr[5]//tbody/tr[3]/td[2]")
	public WebElement cibilDob;

	@FindBy(xpath="//html/body/table/tbody/tr[5]//tbody/tr[3]/td[4]")
	public WebElement cibilgender;

	@FindBy(xpath="//html//tbody/tr[7]//table//tr[3]/td[2]")
	public WebElement cibilScore;

	@FindBy(xpath="//html//tbody/tr[7]//table//tr[4]/td[2]")
	public WebElement cibilPersonalLoanScore;

	@FindBy(xpath="//html//tbody/tr[9]//table//tr[3]/td[2]")
	public WebElement cibilPan;

	@FindBy(xpath="//html//tbody/tr[11]//table//tr[3]/td[2]")
	public WebElement cibilMobile;

	@FindBy(xpath="//html//tbody/tr[15]//table//tr[2]/td[2]")
	public WebElement cibilAddress;

	@FindBy(xpath = "//a[@ng-click='editSalaryDay()']")
	public WebElement salary_creditDate_edit;

	@FindBy(xpath = "//select[@ng-options='d as d.salaryDay | dayName  for d in view.days']")
	public WebElement salary_dropdown;

	@FindBy(xpath = "//a[@ng-click='update_customer_salary_day()']")
	public WebElement salaryDate_tick;

	@FindBy(xpath="//span[contains(text(),'Successfully Updated Salary Day.')]")
	public WebElement salaryDate_success;

	@FindBy(xpath = "//button[@ng-show='closeable']")
	public WebElement salaryDate_success_cross;

	@FindBy(xpath ="//input[@ng-model='CustomerCheckbox.custSalDay']")
	public WebElement salaryDate_checkbox;

	@FindBy(xpath = "//input[@ng-model='CustomerCheckbox.custSalDay']")
	public WebElement salaryDate;

}
