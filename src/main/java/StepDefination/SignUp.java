package StepDefination;

import Objects.Locators;
import com.gemini.generic.reporting.GemEcoUpload;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.GemJarGlobalVar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Objects.Locators.*;

public class SignUp{
    Logger logger = LoggerFactory.getLogger(StepDefination.class);

    @Given("^click on signup$")
    public void signup() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.signup, "SignUp");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("You are on the Sign up screen")
    public void you_are_on_the_sign_up_screen() {
        try {
            if (GemJarGlobalVar.environment.equals("beta")) {
                DriverAction.navigateToUrl("https://jewel-beta.gemecosystem.com/#/");
            } else {
                DriverAction.navigateToUrl("https://jewel.gemecosystem.com/#/");
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Click on the Sign up Button")
    public void click_on_the_sign_up_button() {
        try {
            DriverAction.waitSec(3);
            DriverAction.click(Locators.signup_button, "Sign up button", "Successfully clicked");
            DriverAction.waitSec(3);
            GemTestReporter.addTestStep("Sign up screen", "Loaded", STATUS.INFO, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Click already registered button and validate navigated to login page")
    public void alreadyRegistered_btn() {
        DriverAction.click(alreadyregistered_btn, "Already registered button");
        if (DriverAction.getCurrentURL().contains("login")) {
            GemTestReporter.addTestStep("Validate already registered button redirect", "Redirected to Login page", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate already registered button redirect", "Failed to redirect to Login page", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Click register and validate if signUp is unsuccessful")
    public void empty_signup() throws InterruptedException {
        try {
            DriverAction.click(Locators.register_button, "Register button");
            DriverAction.waitSec(3);
            if (!DriverAction.getCurrentURL().contains("login")) {
                GemTestReporter.addTestStep("Validate signUp", "Failed to signUp", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate signUp", "SignUp successful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("enter {string} {string} {string} {string} {string} {string}")
    public void signupPage(String name, String last, String user, String email, String pass, String cpass) {
        try {
            DriverAction.click(Locators.firstname, "FIRST NAME");
            DriverAction.typeText(Locators.firstname, name);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.lastname, "LAST NAME");
            DriverAction.typeText(Locators.lastname, last);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.username1, "USER NAME");
            DriverAction.typeText(Locators.username1, user);
            DriverAction.waitSec(3);
            DriverAction.click(Locators.emailm, "EMAIL");
            DriverAction.typeText(Locators.emailm, email);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.password1, "PASSWORD");
            DriverAction.typeText(Locators.password1, pass);
            DriverAction.waitSec(2);
            DriverAction.click(register_newww);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.confirmpass, "CONFIRM PASSWORD");
            DriverAction.typeText(Locators.confirmpass, cpass);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.register, "REGISTER BUTTON");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(email_already_existss));
            String alert_email = DriverAction.getElement(email_already_existss).getAttribute("innerHTML");
            System.out.println("STRING: " + alert_email);
            String s2 = "Email already exists";
            if (alert_email.equals(s2)) {
                GemTestReporter.addTestStep("Email already exists Alert Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + alert_email, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Email already exists Alert Validation", "Unsuccessful<br>Expected Text: " + s2 + "<br>Actual Text: " + alert_email, STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
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
            DriverAction.click(Locators.emailm, "EMAIL");
            if (random.equals("Y")) {
                DriverAction.typeText(Locators.emailm, Math.random() + email);
            } else {
                DriverAction.typeText(Locators.emailm, email);
            }
            DriverAction.click(Locators.password1, "PASSWORD");
            DriverAction.typeText(Locators.password1, password);
            DriverAction.getElement(By.xpath("//html")).click();
            DriverAction.click(Locators.confirmpass, "CONFIRM PASSWORD");
            DriverAction.typeText(Locators.confirmpass, confirmpassword);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("check username availability for user already registered")
    public void user_alreadyregistered() {
        try {
            DriverAction.click(Locators.username1, "USER NAME");
            DriverAction.typeText(Locators.username1, "jewelautomation");
            if ("Username not Available".equals(DriverAction.getElementText(username_availability))) {
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
            if ("Username Available".equals(DriverAction.getElementText(username_availability))) {
                GemTestReporter.addTestStep("Validate username availability", "Username available", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username availability", "Username not available", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Click register and validate if signUp is successful")
    public void register_success() throws InterruptedException {
        try {
            DriverAction.click(Locators.register_button, "Register button");
            DriverAction.waitUntilElementAppear(Alert_admin1, 10);
            if (DriverAction.getElementText(Alert_admin1).equals("User Registered.") && DriverAction.getCurrentURL().contains("login")) {
                GemTestReporter.addTestStep("Validate signUp", "SignUp success", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate signUp", "SignUp fail", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Validate username availability sign appears on focusing on username field and disappears if it goes out of focus")
    public void focus() {
        try {
            DriverAction.click(Locators.username1, "USER NAME");
            if (!DriverAction.isExist(username_availability)) {
                GemTestReporter.addTestStep("Validate username validation text", "Does not Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username validation text", "Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.typeText(Locators.username1, "arpit.mishra");
            if (DriverAction.isExist(username_availability)) {
                GemTestReporter.addTestStep("Validate username validation text", "Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate username validation text", "Does not Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(Locators.password1, "PASSWORD");
            if (!DriverAction.isExist(username_availability)) {
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
            if (!DriverAction.isExist(password_check)) {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Does not Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(Locators.password1, "PASSWORD");
            if (DriverAction.isExist(password_check)) {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate password strength dialog visibility", "Does not Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.hoverOver(password_suggestion_icon, "Password suggestion icon");
            if (DriverAction.isExist(password_suggestion_tooltip)) {
                GemTestReporter.addTestStep("Validate password suggestion dialog visibility", "Exists", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate password suggestion dialog visibility", "Does not Exists", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(By.xpath("//html"), "PASSWORD out of focus");
            if (!DriverAction.isExist(password_check)) {
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
            DriverAction.click(Locators.confirmpass, "CONFIRM PASSWORD");
            DriverAction.typeText(confirmpass, Keys.CONTROL + "a" + Keys.BACK_SPACE);
            DriverAction.typeText(confirmpass, conf_pass);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Change username case and check username availability")
    public void changecase() {
        try {
            DriverAction.click(Locators.username1, "USER NAME");
            DriverAction.getElement(username1).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
            DriverAction.typeText(Locators.username1, "jewelautomation".toUpperCase());
            if ("Username not Available".equals(DriverAction.getElementText(username_availability))) {
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
