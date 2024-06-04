package com.dolibarr.erp.prospectTest;

/**
 * @author anusha
 */

import static org.testng.Assert.assertEquals;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.dolibarr.erp.generic.basetest.BaseClass;
import com.dolibarr.erp.objectrepositoryutility.CommercialInfoPage;

import com.dolibarr.erp.objectrepositoryutility.HomePage;
import com.dolibarr.erp.objectrepositoryutility.NewCommercialProposalPage;
import com.dolibarr.erp.objectrepositoryutility.NewThirdPartyPage;
import com.dolibarr.erp.objectrepositoryutility.Third_PartiesPage;

import com.dolibarr.erp.objectrepositoryutility.prosepectInfoTest;

import com.dolibarr.erp.objectrepositoryutility.prosepectInfoTestPage;

import com.dolibarr.erp.objectrepositoryutility.prospectPage;

/**
 * This method used to create prospect
 */
public class createProspectTest extends BaseClass {
	@Test
	public void createProspect() throws Throwable{
		/**
		 * Fetching the data from excel sheet
		 */
		String PName=eLib.getDataFromExcel("ThirdParty", 4, 2)+jLib.getRandomNumber();
		String city=eLib.getDataFromExcel("ThirdParty", 4, 3);
		String refcus=eLib.getDataFromExcel("ThirdParty",4,4)+jLib.getRandomNumber();
		String date=jLib.getSystemDateYYYYDDMM();	
		String statusmsg=eLib.getDataFromExcel("ThirdParty",4,5);
		String statusmsg1=eLib.getDataFromExcel("ThirdParty",4,10);
		String discription=eLib.getDataFromExcel("ThirdParty",4,6);
		String Nprice=eLib.getDataFromExcel("ThirdParty",4,7);
		String qty=eLib.getDataFromExcel("ThirdParty",4,8);
		String dis=eLib.getDataFromExcel("ThirdParty",4,9);
		/**
		 * navigating HomePage
		 */
		HomePage h=new HomePage(driver);

		h.getThirdPartiesMenu().click();


		/**
		 * navigating thirdparties page
		 */
		Third_PartiesPage t=new Third_PartiesPage(driver);

		t.getNewProspectLink().click();

		NewThirdPartyPage ntp = new NewThirdPartyPage(driver);

		/**
		 * creating new thirdparty
		 */
		ntp.getThirdPartyNameTextField().sendKeys(PName);
		ntp.getSelectCustomerProspect().click();
		ntp.getProspect().click();
		ntp.getCityTextField().sendKeys(city);
		ntp.getCreateThirdPartyButton().click();

		prosepectInfoTest pi = new prosepectInfoTest(driver);

		/**
		 * navigating prospect info page
		 */
		prosepectInfoTestPage pi=new prosepectInfoTestPage(driver);
		pi.getProspectInfo().click();
		prospectPage pp = new prospectPage(driver);

		/**
		 * creating proposal
		 */
		prospectPage pp=new prospectPage(driver);
		/**
		 * navigating new proposal page
		 */
		NewCommercialProposalPage ncpp=new NewCommercialProposalPage(driver);
		ncpp.getRefCustomer().sendKeys(refcus);
		ncpp.getSavedraft().click();
		
		/**
		 * validating status before Validate
		 */
		String actmsg=cpip.getStatus().getText();
		Assert.assertEquals(actmsg,statusmsg);
		Reporter.log(actmsg+"status is verified",true);
		/**
		 * passing the datails of the product
		 */
		cpip.addLine(discription, Nprice, qty, dis);
		cpip.getValidate().click();
		cpip.getYesButton().click();
		/**
		 * validating status  after validate
		 */
		String actMsg1=cpip.getValidateafter().getText();
		Assert.assertEquals(actMsg1,statusmsg1);
		Reporter.log(actMsg1+"status is verified",true);

	}

}
