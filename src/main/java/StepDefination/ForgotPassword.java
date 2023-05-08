package StepDefination;

import Functions.Functions_ForgotPassword_SuperAdmin;
import Objects.Locators;
import com.gemini.generic.reporting.GemEcoUpload;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Objects.*;


public class ForgotPassword extends GemEcoUpload {
    Logger logger = LoggerFactory.getLogger(StepDefination.class);
    WebDriverWait wait=new WebDriverWait(DriverManager.getWebDriver(),10);

    @Given("You are on the login screen and move to forgot password")
    public void forgotScreen() {
        try {
            DriverAction.click(Locators.login_screen, "Login screen");
            DriverAction.click(ObjForgotPassword.forgot_btn, "Forgot Password button");
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED: " + e, STATUS.FAIL);
        }
    }

    @Then("Click Back to Login button and validate navigation to login screen")
    public void back_to_login_btn() {
        try {
            DriverAction.click(ObjForgotPassword.backtoLogin_btn, "Back to login button");
            if (!DriverAction.getCurrentURL().contains("forgot")) {
                GemTestReporter.addTestStep("Validate Back to login button redirect", "Redirected to Login page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate Back to login registered button redirect", "Failed to redirect to Login page", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED: " + e, STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @When("User fills incorrect username")
    public void incorrectUsername() {
        try {
            DriverAction.click(ObjForgotPassword.username, "Username");
            DriverAction.typeText(ObjForgotPassword.username, Math.random() + "");
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @And("^Enter a \"(.*)\" and \"(.+)\"$")
    public void password(String pass, String conf_pass) {
        try {
            DriverAction.click(ObjForgotPassword.password, "Password");
            DriverAction.typeText(ObjForgotPassword.password, pass, "Password");
            DriverAction.getElement(By.xpath("//html")).click();
            DriverAction.click(ObjForgotPassword.c_password, "Confirm password");
            DriverAction.typeText(ObjForgotPassword.c_password, conf_pass, "Confirm password");
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @When("^User fills a \"(.*)\"$")
    public void username(String username) {
        try {
            DriverAction.click(ObjForgotPassword.username, "Username");
            DriverAction.typeText(ObjForgotPassword.username, username, "Username");
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Verify reset password fails")
    public void resetFail() {
        try {
            DriverAction.click(ObjForgotPassword.submit_btn, "Submit button");
            DriverAction.waitUntilElementAppear(Locators.Alert_admin1, 5);
            if (DriverAction.getElementText(Locators.Alert_admin1).equals("User is not found") && !DriverAction.isExist(ObjForgotPassword.confirm_btn)) {
                GemTestReporter.addTestStep("Validate forgot password status", "Failed to forget (User not found)", STATUS.PASS, DriverAction.takeSnapShot());
            } else if (DriverAction.getElementText(Locators.Alert_admin1).equals("Password and Confirm Password should be a Perfect match") && !DriverAction.isExist(ObjForgotPassword.confirm_btn)) {
                GemTestReporter.addTestStep("Validate forgot password status", "Failed to forget (Password and confirm password does not match)", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate forgot password status", "Forget successful unexpectedly", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Verify reset password selection success")
    public void resetPass() {
        try {
            if(DriverAction.isExist(Locators.Alert_admin1)) {
                wait.until(ExpectedConditions.invisibilityOf(DriverAction.getElement(Locators.Alert_admin1)));
            }
            DriverAction.click(ObjForgotPassword.submit_btn, "Submit button");
            DriverAction.waitUntilElementAppear(Locators.Alert_admin1, 10);
            if (DriverAction.getElementText(Locators.Alert_admin1).equals("Otp emailed, please check your e-mail.") && DriverAction.isExist(ObjForgotPassword.confirm_btn)) {
                GemTestReporter.addTestStep("Validate forgot password status", "Forgot password success", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate forgot password status", "Forget unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED "+e, STATUS.FAIL);
        }
    }
    @Then("Validate if OTP recognition is successful")
    public void otpVerify(){
        if(DriverAction.isExist(Locators.Alert_admin2)) {
            DriverAction.waitUntilElementDisappear(Locators.Alert_admin2,20);
    //        wait.until(ExpectedConditions.invisibilityOf(DriverAction.getElement(Locators.Alert_admin2)));
        }
        String otp= Functions_ForgotPassword_SuperAdmin.retrieve_otp_from_outlook();
        if(otp.equals("not found")){
            GemTestReporter.addTestStep("Validate forgot password OTP status", "OTP not received in email", STATUS.FAIL, DriverAction.takeSnapShot());
            return;
        }
        DriverAction.typeText(ObjForgotPassword.otp_input,otp,"One time password");
        DriverAction.click(ObjForgotPassword.confirm_btn,"Confirm button");
        DriverAction.waitUntilElementClickable(Locators.Alert_admin1,5);
        if (DriverAction.getElementText(Locators.Alert_admin1).equals("Reset password successfully") && !DriverAction.getCurrentURL().contains("forgot")){
            GemTestReporter.addTestStep("Validate forgot password OTP status", "Forgot password success", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate forgot password OTP status", "Forget password failed", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Validate OTP recognition")
    public void otpVerifyFail(){
        if(DriverAction.isExist(Locators.Alert_admin1)) {
            DriverAction.waitUntilElementDisappear(Locators.Alert_admin1, 10);
        }
        DriverAction.typeText(ObjForgotPassword.otp_input,"123456","One time password");
        DriverAction.click(ObjForgotPassword.confirm_btn,"Confirm button");
        DriverAction.waitUntilElementClickable(Locators.Alert_admin1,10);
        if (DriverAction.getElementText(Locators.Alert_admin1).equals("otp is not valid") && DriverAction.getCurrentURL().contains("forgot")){
            GemTestReporter.addTestStep("Validate forgot password OTP status", "Forgot password Failed", STATUS.PASS, DriverAction.takeSnapShot());
        }else if (DriverAction.getElementText(Locators.Alert_admin1).equals("Reset password successfully") || !DriverAction.getCurrentURL().contains("forgot")){
            GemTestReporter.addTestStep("Validate forgot password OTP status", "Forgot password successful unexpectedly", STATUS.FAIL, DriverAction.takeSnapShot());
        }else {
            GemTestReporter.addTestStep("Validate forgot password OTP status", "Something went wrong", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
}