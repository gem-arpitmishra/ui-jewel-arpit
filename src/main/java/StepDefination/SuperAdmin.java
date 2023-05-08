package StepDefination;

import Functions.Functions_ForgotPassword_SuperAdmin;
import Objects.ObjSuperAdmin;
import Objects.Locators;
import com.gemini.generic.reporting.GemEcoUpload;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class SuperAdmin extends GemEcoUpload {
    Actions action = new Actions(DriverManager.getWebDriver());

    @When("Edit company details")
    public void editCompany() {
        DriverAction.waitSec(1);
        action.moveToElement(DriverAction.getElement(ObjSuperAdmin.edit_company)).build().perform();
        action.click(DriverAction.getElement(ObjSuperAdmin.edit_company)).build().perform();
        GemTestReporter.addTestStep("Click on Edit company details", "Successfully : Clicked on edit company details icon", STATUS.PASS, DriverAction.takeSnapShot());
    }

    @And("Remove all domains")
    public void removeDomains() {
        DriverAction.click(ObjSuperAdmin.edit_domains, "Edit domains");
        DriverAction.typeText(ObjSuperAdmin.domains_input, Keys.BACK_SPACE + "", "Domains");
        DriverAction.click(ObjSuperAdmin.update_domains, "Update domains");
    }

    @Then("^Verify updated '(.+)'$")
    public void noDomainVerify(String domain) {
        DriverAction.waitUntilElementAppear(Locators.Alert_admin1, 5);
        String alert=DriverAction.getElementText(Locators.Alert_admin1);
        action.sendKeys(Keys.ESCAPE).build().perform();
        Functions_ForgotPassword_SuperAdmin.refreshSuperAdminScreen();
        if (alert.equals("No Domains entered !") && !DriverAction.getElementText(ObjSuperAdmin.company_domain).contains(domain)) {
            GemTestReporter.addTestStep("Validate Domain update status", "Domain updation failed (no domain entered)", STATUS.PASS, DriverAction.takeSnapShot());
        }else if(alert.equals(domain+" already exists")){
            GemTestReporter.addTestStep("Validate Domain update status", "Domain updation failed (Domain already taken by another company)", STATUS.PASS, DriverAction.takeSnapShot());
        }
        else {
            GemTestReporter.addTestStep("Validate Domain update status", "Domain updation success unexpectedly", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
    @And("^Add a '(.+)' already taken by another company$")
    public void domainsAlreadyTaken(String domain){
        DriverAction.click(ObjSuperAdmin.edit_domains, "Edit domains");
        DriverAction.typeText(ObjSuperAdmin.domains_input, domain+""+Keys.ENTER, "Domains");
        DriverAction.waitUntilElementClickable(ObjSuperAdmin.update_domains,5);
        DriverAction.click(ObjSuperAdmin.update_domains, "Update domains");
    }

    @Then("Unverify the company")
    public void unverify(){
        DriverAction.waitSec(3);
        action.moveToElement(DriverAction.getElement(Locators.unlink_option)).build().perform();
        action.click(DriverAction.getElement(Locators.unlink_option)).build().perform();
        DriverAction.waitUntilElementAppear(Locators.Alert_admin1,5);
        if(DriverAction.getElementText(Locators.Alert_admin1).contains("is now unverified!"))
        {
            System.out.println("Unverified");
        }else{
            System.out.println("Failed");
        }
    }

    @Then("^Verify '(.*)' update$")
    public void verifycompany(String domain){
        DriverAction.waitUntilElementAppear(Locators.Alert_admin1, 5);
        String message=DriverAction.getElementText(Locators.Alert_admin1);
        action.sendKeys(Keys.ESCAPE).build().perform();
        Functions_ForgotPassword_SuperAdmin.refreshSuperAdminScreen();
//        DriverAction.waitSec(10);
        DriverAction.waitUntilElementAppear(ObjSuperAdmin.company_domain,5);
        if (message.equals("Domains added Successfully") && DriverAction.getElementText(ObjSuperAdmin.company_domain).contains(domain)) {
            GemTestReporter.addTestStep("Validate Domain update status", "Domain updation successful", STATUS.PASS, DriverAction.takeSnapShot());
        }else {
            GemTestReporter.addTestStep("Validate Domain update status", "Domain updation unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Add a new domain and verify domain added to the company")
    public void newDomainAndVerify(){
        String new_domain=String.valueOf(Math.random());
        DriverAction.click(ObjSuperAdmin.edit_domains, "Edit domains");
        DriverAction.typeText(ObjSuperAdmin.domains_input, new_domain + ""+Keys.ENTER, "Domains");
        DriverAction.click(ObjSuperAdmin.update_domains, "Update domains");
        DriverAction.waitUntilElementAppear(Locators.Alert_admin1, 5);
        String alert=DriverAction.getElementText(Locators.Alert_admin1);
        action.sendKeys(Keys.ESCAPE).build().perform();
        Functions_ForgotPassword_SuperAdmin.refreshSuperAdminScreen();
        if (alert.equals("Domains added Successfully") && DriverAction.getElementText(ObjSuperAdmin.company_domain).contains(new_domain)) {
            GemTestReporter.addTestStep("Validate Domain update status", "Domain updation successful", STATUS.PASS, DriverAction.takeSnapShot());
        }else {
            GemTestReporter.addTestStep("Validate Domain update status", "Domain updation unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
}
