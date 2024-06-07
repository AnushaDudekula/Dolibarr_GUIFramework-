package com.dolibarr.erp.prospectTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dolibarr.erp.generic.basetest.BaseClass;
import com.dolibarr.erp.objectrepositoryutility.ContractForCustomerPage;
import com.dolibarr.erp.objectrepositoryutility.CustomerInfoPage;
import com.dolibarr.erp.objectrepositoryutility.CustomerPage;
import com.dolibarr.erp.objectrepositoryutility.HomePage;
import com.dolibarr.erp.objectrepositoryutility.ListOfCustomerPage;
import com.dolibarr.erp.objectrepositoryutility.NewContractForCustomerPage;
import com.dolibarr.erp.objectrepositoryutility.NewThirdPartyPage;
import com.dolibarr.erp.objectrepositoryutility.Third_PartiesPage;
import com.dolibarr.erp.objectrepositoryutility.salesOrderPage;
@Listeners(com.dolibarr.erp.generic.listenerutility.ListImpClass.class)
public class closingContractForCustomerTest extends BaseClass {
	@Test
	public void closeContract() throws Throwable {
	/**
	 * Fetching data from Excel
	 */
	String CName = eLib.getDataFromExcel("ThirdParty",1, 2) + jLib.getRandomNumber();
	String CityName = eLib.getDataFromExcel("ThirdParty",1, 3);
	String RefCust = eLib.getDataFromExcel("ThirdParty",1, 4) + jLib.getRandomNumber();
	String status = eLib.getDataFromExcel("ThirdParty",1, 11);
	String discription = eLib.getDataFromExcel("ThirdParty",1, 6);
	String netPrice = eLib.getDataFromExcel("ThirdParty",1, 7);
	String statusOfService1 = eLib.getDataFromExcel("ThirdParty",1, 12);
	String statusOfService_2 = eLib.getDataFromExcel("ThirdParty",1, 14);
	String statusOfService = eLib.getDataFromExcel("ThirdParty",1, 13);
	/**
	 * Navigating to Third-Parties Menu
	 */
    HomePage hp= new HomePage(driver);
    hp.clickOnThirdPartiesMenu();
    /**
     * Creating new customer
     */
    Third_PartiesPage t=new Third_PartiesPage(driver);
	t.getNewCustomerLink().click();
    NewThirdPartyPage ntp = new NewThirdPartyPage(driver);
    ntp.getThirdPartyNameTextField().sendKeys(CName);
    ntp.getSelectCustomerProspect().click();
    ntp.getSelectCustomer().click();
    ntp.getCityTextField().sendKeys(CityName);
    ntp.getCreateThirdPartyButton().click();
    /**
     * Navigating to list of customer link
     */
    Third_PartiesPage tpp = new Third_PartiesPage(driver);
    tpp.getListOfCustomersLink().click();
    ListOfCustomerPage lcp= new ListOfCustomerPage(driver);
    lcp.getSearchCust().sendKeys(CName,Keys.ENTER);
    driver.findElement(By.xpath("//a[text()='"+ CName +"']")).click();
    /**
     * Navigate to customer link
     */
    CustomerInfoPage cip= new CustomerInfoPage(driver);
    cip.getCustomerLink().click();
    /**
     * Click on create contract link
     */
    CustomerPage cp= new CustomerPage(driver);
    cp.getCreateContractLink().click();
    /**
     * create contract
     */
    NewContractForCustomerPage nccp= new NewContractForCustomerPage(driver);
    nccp.getRefCust().sendKeys(RefCust);
    nccp.getCreateButton().click();
    /**
     * Verify the status 
     */
    ContractForCustomerPage ccp= new ContractForCustomerPage(driver);
    String draftstatus = ccp.getDraftStatus().getText();
    Assert.assertEquals(draftstatus, status);
    Reporter.log(draftstatus+"status is verified",true);
    /**
     * Add line for contract
     */
    ccp.addline(discription, netPrice);
    /**
     * Validate the contract
     */
	ccp.getValidateLink().click();
	ccp.getYesButton().click();
	/**
	 * verify the status of service after validate
	 */
	String ActStatusOfService = ccp.getStatusofService().getText();
	Assert.assertEquals(ActStatusOfService, statusOfService1);
	Reporter.log(ActStatusOfService+"   status is verified",true);
	Assert.fail();
	/**
	 * Activate Contract
	 */
	ccp.getActivateContractLink().click();
	ccp.getYesButton().click();
	/**
	 * Verify the status after activate the contract
	 */
	String ActivateStatus = ccp.getActivateStatus().getText();
	Assert.assertEquals(ActivateStatus, statusOfService);
	Reporter.log(ActivateStatus+"  status is verified",true);
    
	/**
	 * closing Contract
	 */
	salesOrderPage sop=new salesOrderPage(driver);
	sop.getCloseContract().click();
	sop.getYesButton().click();
	/**
	 * Verify the status after closing the contract
	 */
	String statusclose=sop.getStatusOfCloseContract().getText();
	Assert.assertEquals(statusclose, statusOfService_2);
	Reporter.log(statusclose+"   status is verified",true);
}
}
