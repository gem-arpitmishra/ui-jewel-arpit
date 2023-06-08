package StepDefination;

import Objects.Locators;
import Objects.ObjForgotPassword;
import Objects.Script_PreConfig;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.utils.GemJarGlobalVar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUp{
    Logger logger = LoggerFactory.getLogger(StepDefination.class);

    @Given("^click on signup$")
    public void signup() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.signup_button, "SignUp");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Click already registered button and validate navigated to login page")
    public void alreadyRegistered_btn() {
        DriverAction.click(Locators.alreadyregistered_btn, "Already registered button");
        if (DriverAction.getCurrentURL().contains("login")) {
            GemTestReporter.addTestStep("Validate already registered button redirect", "Redirected to Login page", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate already registered button redirect", "Failed to redirect to Login page", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Validate if signUp is unsuccessful")
    public void signup_fail(){
        try {
            DriverAction.waitUntilElementAppear(Locators.Alert_admin1,10);
            if (!DriverAction.getCurrentURL().contains("login")) {
                GemTestReporter.addTestStep("Validate signUp", "Failed to signUp", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate signUp", "SignUp successful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED "+e, STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
    @Then("Fill all fields")
    public void signup_form(){
        DriverAction.click(Locators.firstname, "FIRST NAME");
        DriverAction.typeText(Locators.firstname, Script_PreConfig.signup_fname);
        DriverAction.click(Locators.lastname, "LAST NAME");
        DriverAction.typeText(Locators.lastname, Script_PreConfig.signup_lname);
        DriverAction.click(Locators.username1, "USER NAME");
        DriverAction.typeText(Locators.username1, Script_PreConfig.signup_username);
        DriverAction.click(Locators.signup_email, "EMAIL");
        DriverAction.typeText(Locators.signup_email, Script_PreConfig.signup_email);
        DriverAction.click(ObjForgotPassword.password, "PASSWORD");
        DriverAction.typeText(ObjForgotPassword.password, Script_PreConfig.signup_pass);
        DriverAction.getElement(By.xpath("//html")).click();
        DriverAction.click(ObjForgotPassword.c_password, "CONFIRM PASSWORD");
        DriverAction.typeText(ObjForgotPassword.c_password, Script_PreConfig.signup_cpass);
    }
    @Then("Click Register")
    public void register(){
        DriverAction.click(Locators.register_button, "Register button");
    }

    @When("Fill fields {string},{string},{string},{string},{string},{string},{string}")
    public void fill_details_signup(String fname, String lname, String username, String email, String password, String confirmpassword, String random) {
        try {
            DriverAction.click(Locators.firstname, "FIRST NAME");
            DriverAction.typeText(Locators.firstname, fname);
            DriverAction.click(Locators.lastname, "LAST NAME");
            DriverAction.typeText(Locators.lastname, lname);
            DriverAction.click(Locators.username1, "USER NAME");
            if (random.equals("Y")) {
                DriverAction.typeText(Locators.username1, username + Math.random());
            } else {
                DriverAction.typeText(Locators.username1, username);
            }
            DriverAction.click(Locators.signup_email, "EMAIL");
            if (random.equals("Y")) {
                DriverAction.typeText(Locators.signup_email, Math.random() + email);
            } else {
                DriverAction.typeText(Locators.signup_email, email);
            }
            DriverAction.click(ObjForgotPassword.password, "PASSWORD");
            DriverAction.typeText(ObjForgotPassword.password, password);
            DriverAction.getElement(By.xpath("//html")).click();
            DriverAction.click(ObjForgotPassword.c_password, "CONFIRM PASSWORD");
            DriverAction.typeText(ObjForgotPassword.c_password, confirmpassword);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("check username availability for user already registered")
    public void user_alreadyregistered() {
        try {
            DriverAction.click(Locators.username1, "USER NAME");
            DriverAction.typeText(Locators.username1, Script_PreConfig.signup_username);
            if ("Username not Available".equals(DriverAction.getElementText(Locators.username_availability))) {
                GemTestReporter.addTestStep("Validate username availability", "Username not available", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username availability", "Username available", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("check username availability for user not already registered")
    public void username_new() {
        try {
            DriverAction.click(Locators.username1, "USER NAME");
            DriverAction.typeText(Locators.username1, GemJarGlobalVar.environment + "_Reg_" + Math.random());
            DriverAction.waitSec(5);
            if ("Username Available".equals(DriverAction.getElementText(Locators.username_availability))) {
                GemTestReporter.addTestStep("Validate username availability", "Username available", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username availability", "Username not available", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Validate if signUp is successful")
    public void register_success(){
        try {
            DriverAction.waitUntilElementClickable(Locators.Alert_text,10);
            if (DriverAction.getElementText(Locators.Alert_text).equals("User Registered.") && DriverAction.getCurrentURL().contains("login")) {
                GemTestReporter.addTestStep("Validate signUp", "SignUp success", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate signUp", "SignUp fail", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED: "+e, STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Then("Validate username availability sign appears on focusing on username field and disappears if it goes out of focus")
    public void focus() {
        try {
            DriverAction.click(Locators.username1, "USER NAME");
            if (!DriverAction.isExist(Locators.username_availability)) {
                GemTestReporter.addTestStep("Validate username validation text", "Does not Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username validation text", "Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.typeText(Locators.username1, "arpit.mishra");
            if (DriverAction.isExist(Locators.username_availability)) {
                GemTestReporter.addTestStep("Validate username validation text", "Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username validation text", "Does not Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(ObjForgotPassword.password, "PASSWORD");
            if (!DriverAction.isExist(Locators.username_availability)) {
                GemTestReporter.addTestStep("Validate username validation text", "Does not Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username validation text", "Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Password strength dialog appears on focusing on password field and disappears if it goes out of focus")
    public void password_dialog() {
        try {
            if (!DriverAction.isExist(Locators.password_check)) {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Does not Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(ObjForgotPassword.password, "PASSWORD");
            if (DriverAction.isExist(Locators.password_check)) {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Does not Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.hoverOver(Locators.password_suggestion_icon, "Password suggestion icon");
            if (DriverAction.isExist(Locators.password_suggestion_tooltip)) {
                GemTestReporter.addTestStep("Validate password suggestion dialog visibility", "Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate password suggestion dialog visibility", "Does not Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(By.xpath("//html"), "PASSWORD out of focus");
            if (!DriverAction.isExist(Locators.password_check)) {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Does not Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @When("User enters {string} passwords in password and confirmation-password")
    public void pass_confirmpass(String conf_pass) {
        try {
            if(DriverAction.isExist(Locators.Alert_admin1)){
                DriverAction.waitUntilElementDisappear(Locators.Alert_admin1,10);
            }
            DriverAction.click(ObjForgotPassword.c_password, "CONFIRM PASSWORD");
            DriverAction.typeText(ObjForgotPassword.c_password, Keys.CONTROL + "a" + Keys.BACK_SPACE);
            DriverAction.typeText(ObjForgotPassword.c_password, conf_pass);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Change username case and check username availability")
    public void changecase() {
        try {
            DriverAction.click(Locators.username1, "USER NAME");
            DriverAction.getElement(Locators.username1).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
            DriverAction.typeText(Locators.username1, "jewelautomation".toUpperCase());
            if ("Username not Available".equals(DriverAction.getElementText(Locators.username_availability))) {
                GemTestReporter.addTestStep("Validate username availability", "Username not available", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username availability", "Username available", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }
}
