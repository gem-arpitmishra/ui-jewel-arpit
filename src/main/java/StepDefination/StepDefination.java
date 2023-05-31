package StepDefination;

import Functions.Functions_Admin;
import Objects.Locators;
import com.gemini.generic.reporting.GemEcoUpload;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.GemJarGlobalVar;
import com.gemini.generic.utils.ProjectConfigData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Objects.Locators.*;

public class StepDefination extends GemEcoUpload {
    public static String company = "Gemini" + Math.random();
    public static String str = "company" + Math.random();
    public static String projectNames = "SUITE_" + Math.random();
    Logger logger = LoggerFactory.getLogger(StepDefination.class);

    public void global() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(login_button, "Log In");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "arpit.mishra");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "arpit1234");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.autolyticsm, "Autolytics");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.reporting_new_btn, "Reporting");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.createReport, "Create Report");
            DriverAction.waitSec(15);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("^click on admin$")
    public void admin_Screen() {
        try {
            global2();
            DriverAction.click(admin, "Admin");
            DriverAction.waitUntilElementAppear(total_projects, 10);
        } catch (Exception e) {
            e.printStackTrace();
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Given("^login as super-admin$")
    public void loginAsSuper() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(login_btn, "Log In");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "super-admin");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "Gemecosystem#1234@");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(4);
            DriverAction.click(super_admin, "Super Admin");
            DriverAction.waitSec(6);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Given("You are on the login screen")
    public void you_are_on_the_login_screen() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(login_btn, "Login screen");
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Given("^login as admin$")
    public void loginAsAdmin() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.login_btn, "Log In");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "super-admin");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "Gemecosystem#1234@");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(4);
            DriverAction.click(admin, "Admin");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^login as super-admin again$")
    public void loginAsSuperAgain() {
        try {
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "super-admin");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "Gemecosystem#1234@");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(4);
            DriverAction.click(super_admin, "Super Admin");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^verify the company is created$")
    public void superAdminCompanyVerify() {
        try {
            DriverAction.doubleClick(sno, "S_NO");
            DriverAction.waitSec(2);
            String newCompany = DriverAction.getElementText(company_new_name);
            System.out.println("New name is: " + newCompany);
            System.out.println("Company Is: " + company);
            if (company.equalsIgnoreCase(newCompany)) {
                GemTestReporter.addTestStep("Company created validation", "Successful<br>Expected Company Name: " + newCompany + "<br>Actual Company Name: " + company, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Company created validation", "Unsuccessful<br>Expected Company Name: " + newCompany + "<br>Actual Company Name: " + company, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^verify it has been marked as verified$")
    public void verified_superAdmin() {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                Actions action2 = new Actions(DriverManager.getWebDriver());
                action2.moveToElement(DriverManager.getWebDriver().findElement(tick_option)).build().perform();
                action2.click(DriverManager.getWebDriver().findElement(tick_option)).build().perform();
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Click on Green tick", "Successfully : Clicked on Green tick", STATUS.PASS, DriverAction.takeSnapShot());
                WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
                wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
                String alert = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
                System.out.println("ALERT: " + alert);
                String expectedAlert = company + " is now verified!";
                System.out.println("COMPANY: " + expectedAlert);
                if (alert.equalsIgnoreCase(expectedAlert)) {
                    GemTestReporter.addTestStep("Company is now verified Alert Validation", "Successful<br>Expected Text: " + expectedAlert + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Company is now verified Alert Validation", "Unsuccessful<br>Expected Text: " + expectedAlert + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
                }
                String companyVerify = DriverAction.getElementText(verify_confirmation);
                DriverAction.waitSec(1);
                if (companyVerify.equals("VERIFIED")) {
                    GemTestReporter.addTestStep("Company VERIFIED validation", "Successful<br>Expected Text: " + companyVerify + "<br>Actual Text: " + companyVerify, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Company VERIFIED validation", "Unsuccessful<br>Expected Company Name: " + companyVerify + "<br>Actual Text: " + "UNVERIFIED", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                Actions action2 = new Actions(DriverManager.getWebDriver());
                action2.moveToElement(DriverManager.getWebDriver().findElement(tick_option)).build().perform();
                action2.click(DriverManager.getWebDriver().findElement(tick_option)).build().perform();
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Click on Green tick", "Successfully : Clicked on Green tick", STATUS.PASS, DriverAction.takeSnapShot());
//                WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 30);
//                wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
//                String alert = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
//                System.out.println("ALERT: " + alert);
//                String expectedAlert = company + " is now verified!";
//                System.out.println("COMPANY: " + expectedAlert);
//                if (alert.equalsIgnoreCase(expectedAlert)) {
//                    GemTestReporter.addTestStep("Company is now verified Alert Validation", "Successful<br>Expected Text: " + expectedAlert + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Company is now verified Alert Validation", "Unsuccessful<br>Expected Text: " + expectedAlert + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
//                }
                String companyVerify = DriverAction.getElementText(verify_confirmation);
                DriverAction.waitSec(1);
                if (companyVerify.equals("VERIFIED")) {
                    GemTestReporter.addTestStep("Company VERIFIED validation", "Successful<br>Expected Text: " + companyVerify + "<br>Actual Text: " + companyVerify, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Company VERIFIED validation", "Unsuccessful<br>Expected Company Name: " + companyVerify + "<br>Actual Text: " + "UNVERIFIED", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^verify company has been marked as unverified$")
    public void unverified_superAdmin() {
        try {
            DriverAction.doubleClick(sno, "S_NO");
            DriverAction.waitSec(2);
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(unlink_option)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(unlink_option)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Ban", "Successfully : Clicked on Ban", STATUS.PASS, DriverAction.takeSnapShot());
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
            String alert = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
            System.out.println("ALERT: " + alert);
            if (alert.contains("is now unverified!")) {
                GemTestReporter.addTestStep("Company is now verified Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company is now verified Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
            }
//            String companyVerify = DriverAction.getElementText(unverify_confirmation);
//            System.out.println("status: " + companyVerify);
//            DriverAction.waitSec(3);
//            if (companyVerify.equals("UNVERIFIED")) {
//                GemTestReporter.addTestStep("Company UNVERIFIED validation", "Successful<br>Expected Text: " + companyVerify + "<br>Actual Text: " + companyVerify, STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Company UNVERIFIED validation", "Unsuccessful<br>Expected Text: " + companyVerify + "<br>Actual Text: " + "VERIFIED", STATUS.FAIL, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^click on > and < and >> and <<$")
    public void nextPageSuper() {
        try {
            DriverAction.click(nextPageSuperAdmin, ">>");
            DriverAction.waitSec(2);
            DriverAction.click(lastPageSuperAdmin2, "<<");
            DriverAction.waitSec(2);
            DriverAction.click(nextPageSuperAdmin, ">");
            DriverAction.waitSec(2);
            DriverAction.click(nextPageSuperAdmin2, "<");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^From bottom right of the table change number of companies visible on screen$")
    public void changeCompanyNumberSuper() {
        try {
            WebElement element = DriverManager.getWebDriver().findElement(scroll_upto);
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            DriverAction.waitSec(2);
            DriverAction.click(select_after, "Number of companies");
            DriverAction.waitSec(1);
            DriverAction.click(select_10, "Click on 10");
            DriverAction.waitSec(1);
            String value = DriverAction.getElementText(valueOf_10);
            DriverAction.waitSec(1);
            String value2 = DriverAction.getElementText(actual_valueOf_10);
            if (value.equalsIgnoreCase(value2)) {
                GemTestReporter.addTestStep("Number of companies visible on page check", "Successful<br>Expected Text: " + value2 + "<br>Actual Text: " + value, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Number of companies visible on page check", "Unsuccessful<br>Expected Text: " + value2 + "<br>Actual Text: " + value, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^check sorting of super-admin page$")
    public void super_adminSorting() {
        DriverAction.doubleClick(sno);
        DriverAction.waitSec(1);
        String num = DriverAction.getElementText(total_superAdmin2);
        DriverAction.pageScroll(1000, 1000);
        DriverAction.waitSec(3);
        String s = DriverAction.getElementText(total_superAdmin);
        String sub = s.substring(6, 9);
        if (num.equalsIgnoreCase(sub)) {
            GemTestReporter.addTestStep("Sorting check", "Successful<br>Expected Text: " + sub + "<br>Actual Text: " + num, STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Sorting check", "Unsuccessful<br>Expected Text: " + sub + "<br>Actual Text: " + num, STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^Check select all option from dropdown$")
    public void selectAllDropDwn() {
        try {
            for (int i = 1; i <= 3; i++) {
                String xPathWithVariable = "(//button[@aria-label=\"Filter\"])" + "[" + i + "]";
                DriverAction.click(By.xpath(xPathWithVariable), "Filter: " + i);
                DriverAction.waitSec(2);
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^register a company$")
    public void superCompanyRegitser() {
        try {
            DriverAction.click(regitser_company);
            DriverAction.waitSec(2);
            str = "company" + Math.random();
            DriverAction.typeText(company_name_super, str);
            DriverAction.waitSec(2);
            DriverAction.click(domain_name, "Domain name");
            DriverAction.waitSec(2);
            DriverAction.typeText(domain_name, str + ".com" + Keys.ENTER);
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin, "REGISTER");
            DriverAction.waitSec(3);
            DriverAction.doubleClick(sno, "S_NO");
            DriverAction.waitSec(2);
            String s1 = DriverAction.getElementText(new_compName);
            if (str.equalsIgnoreCase(s1)) {
                GemTestReporter.addTestStep("Resgitered company validation", "Successful<br>Expected Text: " + str + "<br>Actual Text: " + s1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Resgitered company validation", "Unsuccessful<br>Expected Text: " + str + "<br>Actual Text: " + s1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Enter a company name that already exists (.+)$")
    public void companyAlready_Exist(String alert1) {
        try {
            DriverAction.click(regitser_company, "Register Company");
            DriverAction.waitSec(2);
            DriverAction.typeText(company_name_super, "apple");
            DriverAction.waitSec(3);
            String alert = DriverAction.getElementText(companyName_Alert);
            System.out.println("ALERT: " + alert);
            DriverAction.waitSec(3);
            if (alert.equalsIgnoreCase(alert1)) {
                GemTestReporter.addTestStep("Company Name Already Exists Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company Name Already Exists Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Enter a domain name that already exists (.+)$")
    public void domainAlready_Exist(String alert1) {
        try {
            DriverAction.click(regitser_company, "Register Company");
            DriverAction.waitSec(2);
            String s = "company" + Math.random();
            DriverAction.typeText(company_name_super, s);
            DriverAction.waitSec(2);
            DriverAction.click(domain_name, "Domain name");
            DriverAction.waitSec(2);
            DriverAction.typeText(domain_name, "geminisolutions.com" + Keys.ENTER);
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin, "REGISTER");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String alert = DriverAction.getElementText(Alert_admin1);
            if (alert.equalsIgnoreCase(alert1)) {
                GemTestReporter.addTestStep("Domain already exists with another company Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Domain already exists with another company Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Enter empty fields (.+)$")
    public void mandatoryField(String alert1) throws Exception {
        try {
            DriverAction.click(regitser_company, "Register Company");
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin, "REGISTER");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String alert = DriverAction.getElementText(Alert_admin1);
            if (alert.equalsIgnoreCase(alert1)) {
                GemTestReporter.addTestStep("Fill out the mandatory fields! Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Fill out the mandatory fields! Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

//    @Then("^delete company admin$")
//    public void deleteCompanyAdmin() throws Exception {
//        try {
//            Actions action2 = new Actions(DriverManager.getWebDriver());
//            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            DriverAction.waitSec(4);
//            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
//            String name = DriverAction.getElementText(nameBlock);
//            List<String> xpath = DriverAction.getElementsText(unlink);
//            String xPathWithVariable = "(//*[local-name()='svg' and @data-icon=\"ban\"]/*[local-name()='path'])" + "[" + xpath.size() + "]";
//            Actions action3 = new Actions(DriverManager.getWebDriver());
//            action3.moveToElement(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
//            action3.click(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
//            DriverAction.waitSec(4);
//            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
//            GemTestReporter.addTestStep("Click on Unlink", "Successfully : Clicked on Unlink", STATUS.PASS, DriverAction.takeSnapShot());
//            GemTestReporter.addTestStep("Admin removed", "Admin removed is: " + name, STATUS.INFO);
//            List<String> list = DriverAction.getElementsText(listBlock);
//            boolean flag = false;
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).equalsIgnoreCase(name)) {
//                    flag = true;
//                    break;
//                }
//            }
//            if (!flag) {
//                GemTestReporter.addTestStep("Admin removed validation", "Admin is NOT present in the list", STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Admin removed validation", "Admin is present in the list", STATUS.FAIL, DriverAction.takeSnapShot());
//            }
//            DriverAction.click(close_btn_super, "Close Button");
//            DriverAction.waitSec(4);
//            Actions action4 = new Actions(DriverManager.getWebDriver());
//            action4.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            action4.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            DriverAction.waitSec(4);
//            DriverAction.click(addAdmins);
//            DriverAction.waitSec(2);
//            DriverAction.click(selectUsersToAdd);
//            DriverAction.waitSec(2);
//            DriverAction.click(textAddName);
//            DriverAction.waitSec(2);
//            DriverAction.typeText(textAddName, "Ana Nya");
//            DriverAction.waitSec(2);
//            DriverAction.click(userToBeAdded);
//            DriverAction.waitSec(2);
//            DriverAction.click(addAdmins);
//            DriverAction.waitSec(2);
//            DriverAction.click(addAdmin_btn);
//            DriverAction.waitSec(5);
//        } catch (Exception e) {
//            logger.info("An exception occurred!", e);
//            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
//        }
//    }

    @Then("^add company admin$")
    public void addcompAdmin() {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                Actions action2 = new Actions(DriverManager.getWebDriver());
                action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
                action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
                String name = DriverAction.getElementText(nameBlock);
                List<String> xpath = DriverAction.getElementsText(unlink);
                String xPathWithVariable = "(//*[local-name()='svg' and @data-icon=\"ban\"]/*[local-name()='path'])" + "[" + xpath.size() + "]";
                Actions action3 = new Actions(DriverManager.getWebDriver());
                action3.moveToElement(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
                action3.click(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Click on Unlink", "Successfully : Clicked on Unlink", STATUS.PASS, DriverAction.takeSnapShot());
                GemTestReporter.addTestStep("Admin removed", "Admin removed is: " + name, STATUS.INFO);
                List<String> list = DriverAction.getElementsText(listBlock);
                boolean flag = false;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equalsIgnoreCase(name)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    GemTestReporter.addTestStep("Admin removed validation", "Admin is NOT present in the list", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Admin removed validation", "Admin is present in the list", STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.click(close_btn_super, "Close Button");
                DriverAction.waitSec(4);
                Actions action4 = new Actions(DriverManager.getWebDriver());
                action4.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
                action4.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
                DriverAction.waitSec(4);
                DriverAction.click(addAdmins);
                DriverAction.waitSec(2);
                DriverAction.click(selectUsersToAdd);
                DriverAction.waitSec(2);
                DriverAction.click(textAddName);
                DriverAction.waitSec(2);
                DriverAction.typeText(textAddName, "dummy4");
                DriverAction.waitSec(2);
                DriverAction.click(userToBeAdded);
                DriverAction.waitSec(2);
                DriverAction.click(addAdmins);
                DriverAction.waitSec(2);
                DriverAction.click(addAdmin_btn);
                DriverAction.waitSec(5);
                DriverAction.click(infoButton);
                DriverAction.waitSec(3);
                List<String> list3 = DriverAction.getElementsText(listBlock);
                boolean flag2 = false;
                for (int i = 0; i < list3.size(); i++) {
                    if (list3.get(i).equalsIgnoreCase(name)) {
                        flag2 = true;
                        break;
                    }
                }
                if (flag2) {
                    GemTestReporter.addTestStep("Admin added validation", "Admin is present in the list", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Admin added validation", "Admin is not present in the list", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                Actions action2 = new Actions(DriverManager.getWebDriver());
                action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil2)).build().perform();
                action2.click(DriverManager.getWebDriver().findElement(edit_pencil2)).build().perform();
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
                String name = DriverAction.getElementText(nameBlock);
                List<String> xpath = DriverAction.getElementsText(unlink);
                String xPathWithVariable = "(//*[local-name()='svg' and @data-icon=\"ban\"]/*[local-name()='path'])" + "[" + xpath.size() + "]";
                Actions action3 = new Actions(DriverManager.getWebDriver());
                action3.moveToElement(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
                action3.click(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
                DriverAction.waitSec(4);
                GemTestReporter.addTestStep("Click on Unlink", "Successfully : Clicked on Unlink", STATUS.PASS, DriverAction.takeSnapShot());
                GemTestReporter.addTestStep("Admin removed", "Admin removed is: " + name, STATUS.INFO);
                List<String> list = DriverAction.getElementsText(listBlock);
                boolean flag = false;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equalsIgnoreCase(name)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    GemTestReporter.addTestStep("Admin removed validation", "Admin is NOT present in the list", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Admin removed validation", "Admin is present in the list", STATUS.FAIL, DriverAction.takeSnapShot());
                }
                DriverAction.click(close_btn_super, "Close Button");
                DriverAction.waitSec(4);
                Actions action4 = new Actions(DriverManager.getWebDriver());
                action4.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil2)).build().perform();
                action4.click(DriverManager.getWebDriver().findElement(edit_pencil2)).build().perform();
                DriverAction.waitSec(4);
                DriverAction.click(addAdmins);
                DriverAction.waitSec(2);
                DriverAction.click(selectUsersToAdd);
                DriverAction.waitSec(2);
                DriverAction.click(textAddName);
                DriverAction.waitSec(2);
                DriverAction.typeText(textAddName, "dummy4");
                DriverAction.waitSec(2);
                DriverAction.click(userToBeAdded);
                DriverAction.waitSec(2);
                DriverAction.click(addAdmins);
                DriverAction.waitSec(2);
                DriverAction.click(addAdmin_btn);
                DriverAction.waitSec(5);
                DriverAction.click(infoButton);
                DriverAction.waitSec(3);
                List<String> list3 = DriverAction.getElementsText(listBlock);
                boolean flag2 = false;
                for (int i = 0; i < list3.size(); i++) {
                    if (list3.get(i).equalsIgnoreCase(name)) {
                        flag2 = true;
                        break;
                    }
                }
                if (flag2) {
                    GemTestReporter.addTestStep("Admin added validation", "Admin is present in the list", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Admin added validation", "Admin is not present in the list", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

//    @Then("^unverify company (.+)$")
//    public void unverifyCompanySuperAdmin(String alert) throws Exception {
//        try {
//            Actions action2 = new Actions(DriverManager.getWebDriver());
//            action2.moveToElement(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
//            action2.click(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
//            String s = DriverAction.getElementText(Alert_admin1);
//            System.out.println("Alert: " + s);
//            if (s.equalsIgnoreCase(alert)) {
//                GemTestReporter.addTestStep("apple is now unverified! Alert Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("apple is now unverified! Alert Validation", "Unsuccessful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
//            }
//            Actions action3 = new Actions(DriverManager.getWebDriver());
//            action3.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            action3.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            DriverAction.waitSec(4);
//            List<String> list = DriverAction.getElementsText(statusSuperAdmin);
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).equalsIgnoreCase("UNVERIFIED")) {
//                    GemTestReporter.addTestStep("Users blocked", "Status has been marked as UNVERIFIED", STATUS.PASS, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Users blocked", "Status has been marked as VERIFIED", STATUS.FAIL, DriverAction.takeSnapShot());
//                }
//            }
//            DriverAction.click(close_btn_super, "Close");
//            DriverAction.waitSec(3);
//        } catch (Exception e) {
//            logger.info("An exception occurred!", e);
//            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
//        }
//    }

    @Then("^verify company again (.+)$")
    public void verifyCompanySuper(String alert) throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(link_Main)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(link_Main)).build().perform();
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            if (s.equalsIgnoreCase(alert)) {
                GemTestReporter.addTestStep("Company verified successfully! Alert Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company verified successfully! Alert Validation", "Unsuccessful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

//    @Then("^unblock a user (.+)$")
//    public void unblockUser(String alert) throws Exception {
//        try {
//            Actions action2 = new Actions(DriverManager.getWebDriver());
//            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            DriverAction.waitSec(4);
//            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
//            WebElement element = DriverManager.getWebDriver().findElement(blocked_User);
//            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
//            DriverAction.waitSec(2);
//            String name = DriverAction.getElementText(blockedUser_Name);
//            GemTestReporter.addTestStep("Blocked user", "Blocked user name is: " + name, STATUS.INFO);
//            Actions action3 = new Actions(DriverManager.getWebDriver());
//            action3.moveToElement(DriverManager.getWebDriver().findElement(blocked_User)).build().perform();
//            action3.click(DriverManager.getWebDriver().findElement(blocked_User)).build().perform();
////            DriverAction.waitSec(4);
//            GemTestReporter.addTestStep("Click on Unblock user", "Successfully : Clicked on Unblock user", STATUS.PASS, DriverAction.takeSnapShot());
//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
//            String s = DriverAction.getElementText(Alert_admin1);
//            System.out.println("Alert: " + s);
//            if (s.equalsIgnoreCase(alert)) {
//                GemTestReporter.addTestStep("Users Unblocked Successfully Alert Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Users Unblocked Successfully Alert Validation", "Unsuccessful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
//            }
//            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
//            DriverAction.waitSec(2);
//            List<String> list = DriverAction.getElementsText(blockedUserList);
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).equalsIgnoreCase(name)) {
//                    GemTestReporter.addTestStep("Added user", "Added user is there in blocked list", STATUS.FAIL, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Added user", "Added user is not there in blocked list", STATUS.PASS, DriverAction.takeSnapShot());
//                }
//            }
//            DriverAction.click(close_btn_super);
//            DriverAction.waitSec(2);
//            Actions action4 = new Actions(DriverManager.getWebDriver());
//            action4.moveToElement(DriverManager.getWebDriver().findElement(profile)).build().perform();
//            action4.click(DriverManager.getWebDriver().findElement(profile)).build().perform();
//            DriverAction.waitSec(4);
//            DriverAction.click(Locators.username);
//            DriverAction.waitSec(1);
//            DriverAction.typeText(Locators.username, "apple_com");
//            DriverAction.waitSec(1);
//            DriverAction.click(Locators.passwordm);
//            DriverAction.waitSec(1);
//            DriverAction.typeText(Locators.passwordm, "Avani0001");
//            DriverAction.waitSec(1);
//            DriverAction.click(Locators.LoginButton, "Login Button");
//            DriverAction.waitSec(4);
//            DriverAction.click(admin);
//            DriverAction.waitSec(5);
//            DriverAction.click(myCompanyAdmin);
//            DriverAction.waitSec(3);
//            Actions action5 = new Actions(DriverManager.getWebDriver());
//            action5.moveToElement(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
//            action5.click(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
//            DriverAction.waitSec(4);
//        } catch (Exception e) {
//            logger.info("An exception occurred!", e);
//            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
//        }
//    }

    @Then("^check visibility of projects on admin screen$")
    public void checkVisibilityAdmin() throws Exception {
        try {
            DriverAction.waitSec(4);
            List<String> list = DriverAction.getElementsText(projectNameList);
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isEmpty()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                GemTestReporter.addTestStep("project of company is visible", "Projects of all the companies are visible with company names", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("project of company is visible", "Projects of all the companies are not visible with company names", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^edit field in admin$")
    public void editFieldAdmin() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(pencil_opt)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(pencil_opt)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Edit project details", "Successfully : Clicked on Edit project details", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.clearText(desciption);
            DriverAction.waitSec(2);
            DriverAction.typeText(desciption, Math.random() + "desc");
            DriverAction.waitSec(2);
            DriverAction.click(save_admin_button, "Save");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            String s2 = "Project is updated Successfully !!";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Project is updated Successfully! Alert Validation", "Expected Text: " + s + "<br>Actual Text: " + s2, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^validate total count of company$")
    public void superAdminCompany() throws Exception {
        try {
            DriverAction.doubleClick(sno, "S_NO");
            String companyCount = DriverAction.getElementText(companyCountSuper);
            DriverAction.pageScroll(1000, 1000);
            DriverAction.waitSec(2);
            String s = DriverAction.getElementText(company_Count);
            String sub = s.substring(6, 9);
            if (companyCount.equalsIgnoreCase(sub)) {
                GemTestReporter.addTestStep("Company count validation", "Successful<br>Expected Text: " + companyCount + "<br>Actual Text: " + sub, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company count validation", "Successful<br>Expected Text: " + companyCount + "<br>Actual Text: " + sub, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }


    @Then("^Check if page switcher is enabled$")
    public void changingArrows() throws Exception {
        try {
            DriverAction.pageScroll(1000, 1000);
            if (DriverAction.getElement(lastPageSuperAdmin).isEnabled() && DriverAction.getElement(nextPageSuperAdmin).isEnabled()) {
                GemTestReporter.addTestStep("Next Page button and Last Page button", "Next Page button and Last Page button are Enabled", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Next Page button and Last Page button", "Next Page button and Last Page button are not Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Given("^validate super-admin is not present$")
    public void superAdminIsPresent() throws Exception {
        try {
            global2();
            List<WebElement> buttons = DriverManager.getWebDriver().findElements(By.xpath("//div[@class=\"icon-text col-md-9 col-sm-12 text-md-start text-center\"]"));
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().contains("Super Admin")) {
                    GemTestReporter.addTestStep("Super Admin isDisplayed?", "Super Admin is Displayed", STATUS.FAIL, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Super Admin isDisplayed?", "Super Admin is not Displayed", STATUS.PASS, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
//        GemTestReporter.addTestStep("exception","Exception",STATUS.INFO);
    }

    @Then("Login to jewel")
    public void global2() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(login_btn, "Log In");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "jewelautomation");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "dummy_test@123");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "LogIn Button");
            DriverAction.waitSec(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("Enter username as {string}")
    public void enter_username_as(String string) {
        try {
            DriverAction.typeText(Locators.user_name, string);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Enter Password")
    public void enter_password() {
        try {
            DriverAction.typeText(Locators.password, "dummy_test@123");
            DriverAction.click(Locators.login_button, "Login Button");
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Enter Password as '(.*)'$")
    public void password(String pass) {
        try {
            DriverAction.typeText(Locators.password, pass);
            DriverAction.click(Locators.login_button, "Login Button");
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("Verify the text of the Home screen")
    public void verify_the_text_of_the_home_screen() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(Locators.home_button, "Home Button");
            String s = DriverAction.getElement(Locators.page_title).getText();
            GemTestReporter.addTestStep("Text of Home Screen", s, STATUS.INFO);

            STATUS status;
            if (s.equals("Jewel Applications")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Text of Home screen", "Expected :Jewel Applications", status, DriverAction.takeSnapShot());


        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR!", "Something wrong Happened", STATUS.FAIL);
        }
    }

    @Then("Click on the Autolytics screen")
    public void click_on_the_autolytics_screen() {
        try {
            DriverAction.click(Locators.home_button, "Home button");
            boolean present;
            try {
                DriverManager.getWebDriver().findElement(Locators.autolytics);
                present = true;
            } catch (NoSuchElementException e) {
                present = false;
            }
            if (present) {
                DriverAction.click(Locators.autolytics, "Autolytics Button");
                DriverAction.waitSec(4);
                String s = DriverAction.getCurrentURL();
                String url = ProjectConfigData.getProperty("launchUrl");
                String url_actual;
                if (url.contains("beta")) {
                    url_actual = "https://jewel-beta.gemecosystem.com/#/autolytics";
                } else {
                    url_actual = "https://jewel.gemecosystem.com/#/autolytics";
                }
                STATUS status;
                if (s.contains(url_actual)) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Autolytics Screen URL Validation", "Expected Text: " + url_actual + "<br>Actual Text: " + s, status, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Autolytics Button Status", "Not Found", STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
        }
    }


    @Then("Verify the Autolytics Screen")
    public void verify_the_autolytics_screen() {
        try {
            STATUS status;
            DriverAction.waitSec(2);
            DriverAction.click(Locators.home_button, "Home Button");
            DriverAction.click(Locators.autolytics, "Autolytics Button");
            String s = DriverAction.getCurrentURL();
            if (s.contains("autolytics")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("AUtolytics window", "Window is opened successfully", status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Status of Autolytics button", "Clicked Fsiled", STATUS.FAIL);
        }
    }


    @Then("Verify the Bridge Token Button is clickable or not")
    public void verify_the_bridge_token_button_is_clickable_or_not() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home Button");
        boolean present;
        try {
            DriverManager.getWebDriver().findElement(Locators.bridge_token);
            present = true;
        } catch (Exception e) {
            present = false;
        }
        if (present) {
            try {
                DriverAction.click(Locators.bridge_token, "Bridge token Button");
                String s = DriverAction.getCurrentURL();
                STATUS status;
                if (s.contains("bridge-token")) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Bridge Token window", "Window is opened successfully", status);
            } catch (Exception e) {
                logger.info("An exception occurred!", e);
                GemTestReporter.addTestStep("Status of Bridge Token button", "Clicked Failed", STATUS.FAIL);
            }
        } else {
            GemTestReporter.addTestStep("Bridge Token window", "Button NOt Present", STATUS.FAIL);

        }
    }

    @Then("Click on Copy Bridge Token")
    public void click_on_copy_bridge_token() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button);
        try {
            DriverAction.click(Locators.bridge_token, "Bridge token Button");
            String s = DriverAction.getCurrentURL();
            STATUS status;
            if (s.contains("bridge-token")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Bridge Token window", "Window is opened successfully", status);
            boolean present;
            try {
                DriverManager.getWebDriver().findElement(Locators.copy_bridge_token);
                present = true;
            } catch (Exception e) {
                present = false;
            }
            STATUS status1;
            if (present) {

                DriverAction.click(Locators.copy_bridge_token, "copy button", "Click was successful");
                DriverAction.waitSec(2);
                status1 = STATUS.PASS;
            } else {
                status1 = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Status of Copy button", "clicked Successful", status1, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Status of Bridge Token button", "Clicked Failed", STATUS.FAIL);
        }
    }

    @Then("Verify the Alert when copy button is clicked")
    public void verify_the_alert_when_copy_button_is_clicked() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home button");
        boolean present;
        try {
            DriverManager.getWebDriver().findElement(Locators.bridge_token);
            present = true;
        } catch (Exception e) {
            present = false;
        }
        if (present) {
            try {
                DriverAction.click(Locators.bridge_token, "Bridge token Button");
                String s = DriverAction.getCurrentURL();
                STATUS status;
                if (s.contains("bridge-token")) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Bridge Token window", "Status", status);
                DriverAction.click(Locators.copy_bridge_token, "copy button");
                DriverAction.waitSec(1);
                GemTestReporter.addTestStep("Status of Copy button", "clicked Successful", STATUS.PASS, DriverAction.takeSnapShot());
                String se = DriverAction.getElement(Locators.copy_alert).getText();
                STATUS hi;
                if (se.equals("Copied !")) {
                    hi = STATUS.PASS;
                } else {
                    hi = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Alert status", "Expected alert:Copied ", hi);

            } catch (Exception e) {
                logger.info("An exception occurred!", e);
                GemTestReporter.addTestStep("Status of Bridge Token button", "Clicked Failed", STATUS.FAIL);
            }
        } else {
            GemTestReporter.addTestStep("Status of Bridge Token button", "Not Found", STATUS.FAIL);
        }
    }

    @Then("Verify the date and time when Change Token button is clicked")
    public void verify_the_date_and_time_when_change_token_button_is_clicked() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home button", "Home button was clicked");
        try {
            DriverAction.click(Locators.bridge_token);
            DriverAction.click(Locators.change_token, "Change token button", "Change Token button was clicked");
            DriverAction.waitSec(1);
            GemTestReporter.addTestStep("Status of Change token button", "clicked Successful", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(2);
            String time = DriverAction.getElement(Locators.date_tab).getText();

            String timep = time.substring(11, 28);
            System.out.println("TIMEEEE:" + timep);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            LocalDateTime now = LocalDateTime.now();
            String loc = dtf.format(now);
            System.out.println("LOC:" + loc);
            System.out.println("timep: " + timep);
            STATUS status;
            if (StringUtils.contains(timep, loc)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Checking date", "Dates should match", status);
//            String times = time.substring(27, 35);
//
//            String[] hel = times.split(":", 3);
//
//            String jo = hel[0];
//            String koko = String.valueOf(jo.charAt(0));
//            if (koko.equals(" ")) {
//                jo = "0" + jo.substring(1);
//                hel[0] = jo;
//            }
//            DateTimeFormatter Time = DateTimeFormatter.ofPattern("hh/mm/ss");
//            LocalDateTime ti = LocalDateTime.now();
//            String timess = Time.format(ti);
//
//            String[] locat = timess.split("/", 3);
//            int flag = 0;
//            for (int o = 0; o < locat.length - 1; o++) {
//                int numberlocal = Integer.parseInt(locat[o]);
//                int get = Integer.parseInt(hel[o]);
//                if (numberlocal - get < 5) {
//                    flag = 0;
//                } else {
//                    flag = 1;
//                }
//            }
//            STATUS s;
//            if (flag == 0) {
//                s = STATUS.PASS;
//            } else {
//                s = STATUS.FAIL;
//            }
//            GemTestReporter.addTestStep("Comparing time", "Checking time of the system", s);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("Verify the Alert when Change Token button is clicked")
    public void verify_the_alert_when_change_token_button_is_clicked() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home button");
        try {
            DriverAction.click(Locators.bridge_token);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.change_token, "Change token button");
            DriverAction.waitSec(3);
            GemTestReporter.addTestStep("Status of Change token button", "clicked Successful", STATUS.PASS, DriverAction.takeSnapShot());
//            String fi = DriverAction.getElement(Locators.change_token_alert).getText();
//            System.out.println("FIIIII: " + fi);
//            STATUS status;
//            if (fi.contains("Bridge Token Changed Successfully.")) {
//                status = STATUS.PASS;
//            } else {
//                status = STATUS.FAIL;
//            }
            GemTestReporter.addTestStep("Alert status", "Expected alert: Bridge Token Changed Successfully.", STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Verify the content of the Home screen")
    public void verify_the_content_of_the_home_screen() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home Button");
        GemTestReporter.addTestStep("Successfully clicked", "Jewel Window", STATUS.INFO, DriverAction.takeSnapShot());
        try {
            String text = DriverAction.getElement(Locators.page_title).getText();
            STATUS s;
            if (text.equals("Jewel Applications")) {
                s = STATUS.PASS;
            } else {
                s = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Title validation", "Expected Title:Jewel Applications", s);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Verify the Cards present on the home screen")
    public void verify_the_cards_present_on_the_home_screen() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home Button");
        try {
            List num = DriverAction.getElements(Locators.cards);
            STATUS status;
            if (num.size() == 2) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Number of Cards Present ", "Expcted :2", status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Verify the Autolytics Cards present on the home screen")
    public void verify_the_autolytics_cards_present_on_the_home_screen() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home Button");
        try {
            String nuam = DriverAction.getElement(Locators.card_one).getText();
            STATUS s;
            if (nuam.equals("Autolytics")) {
                s = STATUS.PASS;
            } else {
                s = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Validating the Autolytics card", "Expcted :Autolytics", s);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Verify the Autolytics Card Content")
    public void verify_the_autolytics_card_content() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home button");
        try {

            String nuam = DriverAction.getElement(Locators.content_card_one).getText();

            STATUS s;
            if (nuam.equals("Autolytics provides the feature for generating descriptive reports to analyze the Testcases/Suite details and also enables users to share them effortlessly.")) {
                s = STATUS.PASS;
            } else {
                s = STATUS.FAIL;

            }
            GemTestReporter.addTestStep("Validating the Autolytics card", "Expcted :Autolytics provides the feature for generating descriptive reports to analyze the Testcases/Suite details and also enables users to share them effortlessly.", s);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Verify if the Bridge token card is visible")
    public void verify_if_the_bridge_token_card_is_visible() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home Button");
        try {

            String nuam = DriverAction.getElement(Locators.card_two).getText();
            STATUS status;
            if (nuam.equals("Bridge Token")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Validating the Bridge Token card", "Expcted :Bridge Token", status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate the content of the Bridge Token card")
    public void validate_the_content_of_the_bridge_token_card() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home Button");
        try {
            STATUS status;
            String nuam = DriverAction.getElement(Locators.content_card_two).getText();

            if (nuam.equals("Keeping in mind the Secure/Safe utilization, Jewel authenticates each user with a Bridge Token which can also be changed or retrieved back as per convenience.")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Validating the Bridge Token card Content", "Expcted :Keeping in mind the Secure/Safe utilization, Jewel authenticates each user with a Bridge Token which can also be changed or retrieved back as per convenience.", status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Validate if there is change token button available ,if so click it")
    public void validate_if_there_is_change_token_button_available_if_so_click_it() {
        DriverAction.waitSec(2);
        DriverAction.click(Locators.home_button, "Home Button");
        boolean present;
        try {
            DriverManager.getWebDriver().findElement(Locators.bridge_token);
            present = true;
        } catch (Exception e) {
            present = false;
        }
        if (present) {
            try {
                DriverAction.click(Locators.bridge_token, "Bridge Token button");
                DriverAction.click(Locators.change_token, "Change token button");
                DriverAction.waitSec(1);
                GemTestReporter.addTestStep("Status of Change token button", "clicked Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } catch (Exception e) {
                logger.info("An exception occurred!", e);
                GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
            }
        } else {
            GemTestReporter.addTestStep("Error", "No such element", STATUS.FAIL);
        }
    }

    @Then("Click on Create Report Tab")
    public void click_on_create_report_tab() {
        addnewtabs();
    }

    public void addnewtabs() {
        try {
            DriverAction.click(By.xpath("//div[text()='Autolytics']"), "Autolytics");
            DriverAction.click(Locators.reporting_new_btn, "Report button");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.create_report, "Create Report");
            DriverAction.waitSec(4);
            DriverAction.click(Locators.report_name_button, "Report Name");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.suite_run, "Suite run Report");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.project_name, "Project name");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.gemEcoApis, "GEMECOSYSTEM_APIS");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.cross_button, "Cross button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.env_tab, "Environment selection");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.select_env, "Select All");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.cbutton, "Cross button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.date_range_tab, "Date Range");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.select_date, "This Year");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.generate_button, "Generate button");
            DriverAction.waitSec(8);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Open few tabs {int}")
    public void open_few_tabs(Integer int1) {
        try {
            for (int i = 0; i < int1; i++) {
                DriverAction.click(Locators.new_tab, "New Tab Button", "Clicked Successful");
                addnewtabs();
            }
            List<WebElement> hi = DriverAction.getElements(Locators.left_tabs);
            STATUS status;
            if (hi.size() == int1 + 1) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Number of Tabs", "Expected " + int1, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("Click on SLide left button")
    public void Click_on_SLide_left_button() {
        try {
            DriverAction.click(Locators.left_most_tab, "Left Most Tab", "Clicked Successful");
            DriverAction.waitSec(2);
            DriverAction.switchToActiveElement();
            WebElement l = DriverAction.getElement(Locators.tab_select);
            String element = l.getAttribute("aria-selected");
            STATUS status;
            if (element.equals("true")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Left Most clicked Status", "Expected :Successfully clicked", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("Click on the SLide most Right button")
    public void click_on_the_s_lide_most_right_button() {
        try {
            DriverAction.click(Locators.right_most_tab, "Right Most Tab", "Clicked Successful");
            DriverAction.waitSec(1);
            WebElement l = DriverAction.getElement(Locators.right_most);
            String element = l.getAttribute("aria-selected");
            STATUS status;
            if (element.equals("true")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Right most Clicked Status", "Expected :Successfully clicked", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("Click on the slide to next left button")
    public void click_on_the_slide_to_next_left_button() {
        try {
            DriverAction.click(Locators.slide_left, "Slide Left Tab button", "Successfully clickeb");
            DriverAction.waitSec(1);
            WebElement l = DriverAction.getElement(Locators.left_next);
            String element = l.getAttribute("aria-selected");
            STATUS status;
            if (element.equals("true")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Left Tab Clicked Status", "Expected : Successfully clicked", status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR!", "Something wrong Happened", STATUS.FAIL);
        }
    }

    @Then("Click on the Slide to next right button")
    public void click_on_the_slide_to_next_right_button() {
        try {
            DriverAction.click(Locators.slide_right, "Slide Right Tab button", "Successfully clicked");
            DriverAction.waitSec(1);
            WebElement l = DriverAction.getElement(Locators.right_most);
            String element = l.getAttribute("aria-selected");
            STATUS status;
            if (element.equals("true")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Left Tab Clicked Status", "Expected : Successfully clicked", status, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR!", "Something wrong Happened", STATUS.FAIL);
        }
    }

    @Then("Validate the active tabs")
    public void validate_the_active_tabs() {

    }

    @Then("^Click on createReport with no params$")
    public void createReportNoP() {
        try {
            DriverAction.click(By.xpath("//div[text()='Autolytics']"), "Autolytics");
            DriverAction.click(Locators.reporting_new_btn, "Report button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.create_report, "Create Report");
            DriverAction.waitSec(4);
            DriverAction.click(Locators.generate_button, "Generate button");
            DriverAction.waitSec(10);
            String s = DriverAction.getElementText(allFiel);
            System.out.println("String: " + s);
            GemTestReporter.addTestStep("Alert All Field Required", "Expected: All fields are required !", STATUS.PASS, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("ERROR!", "Something wrong Happened", STATUS.FAIL);
        }
    }


    @Then("Open a new tab")
    public void open_a_new_tab() {
        try {
            addnewtabs();
            DriverAction.waitSec(5);
            DriverAction.click(Locators.copy_report_link);
            DriverAction.waitSec(4);
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("window.open()");// launching a new tab
            List<String> winHandles = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
            DriverAction.waitSec(2);
            DriverManager.getWebDriver().switchTo().window(winHandles.get(1));
            DriverAction.waitSec(5);
            String h = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            DriverAction.waitSec(5);
            DriverAction.navigateToUrl(h);
            DriverAction.waitSec(5);
            String url = DriverAction.getCurrentURL();
            if (url.equals(h)) {
                GemTestReporter.addTestStep("Current Url", url, STATUS.PASS);
                String s = DriverAction.getElement(Locators.check_text_of_report).getText();
                if (s.equalsIgnoreCase("Shared Report")) {
                    GemTestReporter.addTestStep("Heading validation", "Expected: Shared Report", STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Heading validation", "Expected: Shared Report", STATUS.FAIL);
                }
            } else {
                GemTestReporter.addTestStep("Current Url", url, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }
    ////////////////////////////////////////////////////////

    @Then("^user navigates back after loggin in$")
    public void logout() {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Logged in", "Logged in inside website", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.navigateBack();
            GemTestReporter.addTestStep("Login Page", "Redirected to welcome page", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^validating url of jewel$")
    public void jewelurl() {
        try {
            DriverAction.waitSec(2);
            String s1 = DriverAction.getCurrentURL();
            STATUS status;
            if (s1.contains("beta") || s1.equals("https://jewel.gemecosystem.com/")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.PASS;
            }
            GemTestReporter.addTestStep("Jewel Url Validation", "Successful<br>Expected Jewel URL: " + s1 + "<br>Actual Jewel URL: " + s1, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on pricing$")
    public void pricing() {
        try {
//            DriverAction.getElement(pricing);
            DriverAction.click(pricing);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate pricing url$")
    public void pricingurl() {
        try {
            DriverAction.waitSec(2);
            String s2 = DriverAction.getCurrentURL();
            STATUS status;
            if (s2.contains("beta") || s2.equals("https://jewel.gemecosystem.com/#/pricing")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Pricing Url Validation", "Successful<br>Expected Pricing URL: " + s2 + "<br>Actual Pricing URL: " + s2, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
//        int a = 1 / 0;
    }

    @Given("^clicking on pricing and checking for main heading (.+)$")
    public void clickpricing(String pricingHead) {
        try {
            DriverAction.waitSec(2);
            DriverAction.click(Locators.pricing);
            DriverAction.waitSec(4);
            String s3 = DriverAction.getElementText(Locators.pricingheading);
            STATUS status;
            if (s3.equals(pricingHead)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Pricing heading validation", "Successful<br>Expected heading1: " + pricingHead + "<br>Actual heading1: " + s3, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^checking for subheading (.+)$")
    public void subheading(String pricingSubHead) {
        try {
            String s4 = DriverAction.getElementText(Locators.pricingsubheading);
            STATUS status;
            if (s4.equals(pricingSubHead)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Pricing heading2 validation", "successful<br>Expected heading2: " + pricingSubHead + "<br>Actual heading2: " + s4, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^checking for text (.+)$")
    public void text(String Text) {
        try {
            String s4 = DriverAction.getElementText(Locators.pricingcontent);
            STATUS status;
            if (s4.equals(Text)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Pricing content validation", "Successful<br>Expected Pricing content: " + Text + "<br>Actual Pricing content: " + s4, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^clicking on jewel again validating main heading (.+)$")
    public void jewelhead(String mainHeading) {
        try {
            DriverAction.waitSec(3);
            String s5 = DriverAction.getElementText(Locators.jewelheading);
            STATUS status;
            if (s5.equals(mainHeading)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Jewel heading validation", "Successful<br>Expected heading: " + mainHeading + "<br>Actual head: " + s5, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validating subheading of jewel (.+)$")
    public void jewelShead(String subHeading) {
        try {
            String s6 = DriverAction.getElementText(Locators.jewelsubheading);
            STATUS status;
            if (s6.equals(subHeading)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Jewel content validation", "successful<br>Expected content: " + subHeading + "<br>Actual content: " + s6, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^validating whatisjewel (.+)$")
    public void whatisjewel(String whatisjewel) {
        try {
            DriverAction.waitSec(1);
            DriverAction.pageScroll(500, 500);
            DriverAction.waitSec(3);
            GemTestReporter.addTestStep("Scrolling down", "Scrolling is successful", STATUS.PASS, DriverAction.takeSnapShot());
            String s7 = DriverAction.getElementText(Locators.whatisjewel);
            STATUS status;
            if (s7.equals(whatisjewel)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("What is jewel validation", "Successful<br>Expected content: " + whatisjewel + "<br>Actual content: " + s7, status);

        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validating about us (.+)$")
    public void aboutus(String aboutus) {
        try {
            STATUS status;
            String s8 = DriverAction.getElementText(Locators.aboutus);
            if (s8.equals(aboutus)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("About us validation", "Successful<br>Expected content: " + aboutus + "<br>Actual content: " + s8, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validating content (.+)$")
    public void content(String content) {
        try {
            STATUS status;
            String s9 = DriverAction.getElementText(Locators.content);
            if (s9.contains(content)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Content validation", "Successful<br>Expected content: " + s9 + "<br>Actual content: " + s9, status);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^validating view report (.+)$")
    public void viewreport(String viewreport) {
        try {
            STATUS status;
            DriverAction.waitSec(1);
            DriverAction.pageScroll(500, 500);
            DriverAction.waitSec(3);
            String s10 = DriverAction.getElementText(Locators.viewreport);
            if (s10.contains(viewreport)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("View report validation", "successful<br>Expected heading: " + s10 + "<br>Actual content: " + s10, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validating view reportC (.+)$")
    public void viewcontent(String viewcontent) {
        try {
            String s11 = DriverAction.getElementText(Locators.viewreportcontent);
            STATUS status;
            if (s11.equals(viewcontent)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("View report content validation", "Successful<br>Expected content: " + s11 + "<br>Actual content: " + s11, status);

        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^validating run test (.+)$")
    public void runtest(String runtest) {
        try {
            DriverAction.waitSec(1);
            DriverAction.pageScroll(1000, 1000);
            DriverAction.waitSec(3);
            GemTestReporter.addTestStep("Scrolling down", "Scrolling is successful", STATUS.INFO, DriverAction.takeSnapShot());
            String s12 = DriverAction.getElementText(Locators.runtest);
            STATUS status;
            if (s12.equals(runtest)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Run test suite validation", "Sucessfull<br>Expected content: " + s12 + "<br>Actual content: " + s12, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validating run testC (.+)$")
    public void runsuite(String runcontentC) {
        try {
            String s13 = DriverAction.getElementText(Locators.runtestc);
            STATUS status;
            if (runcontentC.contains(s13)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Run test suite content validation", "Successful<br>Expected content: " + s13 + "<br>Actual content: " + s13, status);

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^click on facebook logo validate url (.+)$")
    public void facebook(String facebook) {
        try {
            STATUS status;
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.facebook, "Facebook Logo");
            DriverAction.waitSec(2);
            ArrayList<String> newTb = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb.get(1));
            DriverAction.waitSec(4);
            String s15 = DriverAction.getCurrentURL();
            if (s15.equals(facebook)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep(" Facebook Url Validation", "Successful<br>Expected URL: " + facebook + "<br>Actual URL: " + s15, status);
            DriverManager.getWebDriver().close();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Close facebook tab", "Successfully : Closed facebook tab", STATUS.PASS);
            DriverAction.switchToWindow(newTb.get(0));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
//        int a = 1 / 0;
    }

    @Given("click on twitter logo and validate url (.+)$")
    public void twitter(String twitter) {
        try {
            STATUS status;
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.twitter, "Twitter Logo");
            ArrayList<String> newTb1 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb1.get(1));
            DriverAction.waitSec(4);
            String s16 = DriverAction.getCurrentURL();
            if (s16.equals(twitter)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Twitter Url Validation", "Successful<br>Expected URL: " + twitter + "<br>Actual URL: " + s16, status);
            DriverManager.getWebDriver().close();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Close twitter tab", "Successfully : Closed twitter tab", STATUS.PASS);
            DriverAction.switchToWindow(newTb1.get(0));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
//        int a = 1 / 0;
    }

    @Given("^click on instagram logo and validate url (.+)$")
    public void instagram(String insta) {
        try {
            STATUS status;
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.instagram, "Instagram Logo");
            ArrayList<String> newTb2 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb2.get(1));
            DriverAction.waitSec(4);
            String s17 = DriverAction.getCurrentURL();
            if (s17.equals(insta)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Instagram Url Validation", "Successful<br>Expected URL: " + insta + "<br>Actual URL: " + s17, status);
            DriverManager.getWebDriver().close();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Close instagram tab", "Successfully : Closed Instagram tab", STATUS.PASS);
            DriverAction.switchToWindow(newTb2.get(0));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
//        int a = 1 / 0;
    }

    @Given("^click on linkedin logo and validate url (.+)$")
    public void linkedin(String linked) {
        try {
            STATUS status;
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.linkedin, "LinkedIn logo");
            ArrayList<String> newTb3 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb3.get(1));
            DriverAction.waitSec(4);
            String s18 = DriverAction.getCurrentURL();
            if (s18.contains(linked)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Linkedin Url Validation", "Successful<br>Expected URL: " + s18 + "<br>Actual URL: " + s18, status);
            DriverManager.getWebDriver().close();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Close LinkedIn tab", "Successfully : Closed LinkedIn tab", STATUS.PASS);
            DriverAction.switchToWindow(newTb3.get(0));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
//        int a = 1 / 0;
    }


    @Given("^click on jewel dashboard button$")
    public void jewelogo() {
        try {
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.jeweldashboard, "Jewel Dashboard");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
//        int a = 1 / 0;
    }

    @Given("^click on gemPYP and validate url (.+)$")
    public void gempyp(String pyp) {
        try {
            STATUS status;
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.gempyp, "GEMPYP");
            ArrayList<String> newTb4 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb4.get(1));
            DriverAction.waitSec(4);
            String s19 = DriverAction.getCurrentURL();
            if (s19.equals(pyp) || s19.equals("https://gempyp-beta.gemecosystem.com/")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("GemPYP Url Validation", "Successful<br>Expected URL: " + pyp + "<br>Actual URL: " + s19, status);
            DriverManager.getWebDriver().close();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Close GemPYP tab", "Successfully : Closed GEMPYP tab", STATUS.PASS);
            DriverAction.switchToWindow(newTb4.get(0));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^click on gemJAR and validate url (.+)$")
    public void gemjar(String jar) {
        try {
            STATUS status;
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.gemjar, "GEMJAR");
            ArrayList<String> newTb6 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb6.get(1));
            DriverAction.waitSec(4);
            String s21 = DriverAction.getCurrentURL();
            if (s21.equals(jar) || s21.equals("https://gemjar-beta.gemecosystem.com/")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("GemJAR Url Validation", "Successful<br>Expected URL: " + jar + "<br>Actual URL: " + s21, status);
            DriverManager.getWebDriver().close();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Close GemJAR tab", "Successfully : Closed GEMJAR tab", STATUS.PASS);
            DriverAction.switchToWindow(newTb6.get(0));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^click on pricing button$")
    public void pricingbutton() {
        try {
            DriverAction.waitSec(1);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.pricingbutton, "PRICING");
            DriverAction.waitSec(2);
            if (DriverAction.getCurrentURL().contains("pricing")) {
                GemTestReporter.addTestStep("Validate navigation to pricing page", "Navigated to pricing page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate navigation to pricing page", "Failed to navigate to pricing page", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened " + e, STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^enter credentials of new user$")
    public void newUser_signup() throws Exception {
        try {
            String firstName = "QA" + Math.random();
            DriverAction.typeText(Locators.first_name, firstName);
            DriverAction.waitSec(2);
            DriverAction.typeText(Locators.last_name, firstName);
            DriverAction.waitSec(2);
            String userName = "QA" + Math.random();
            DriverAction.typeText(Locators.user_name, userName);
            DriverAction.waitSec(2);
            String email = "qa@" + Math.random() + ".com";
            DriverAction.typeText(Locators.email, email);
            String pass = "Avani0001";
            DriverAction.typeText(Locators.password, pass);
            DriverAction.waitSec(2);
            DriverAction.typeText(Locators.confirm_pass, pass);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.register_button, "Register button");
            DriverAction.waitSec(5);
            DriverAction.click(select_company_name, "Select Company Name");
            DriverAction.waitSec(2);
//            String company = "Gemini" + Math.random();
            DriverAction.typeText(select_company_name, company);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.register_button, "Register button");
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^click on login and enter (.+) and (.+)$")
    public void loginn(String username, String password) {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.login_button, "Login");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, username);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, password);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on autolytics and create report$")
    public void autolytics() {
        try {
            DriverAction.click(Locators.autolyticsm, "Autolytics");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.reporting_new_btn, "Reporting");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.createReport, "Create Report");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select report name$")
    public void reportName() {
        try {
            DriverAction.click(Locators.reportNameScroll, "Report Name");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.suiteRunReport, "Suite Run Report");
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select project name$")
    public void projectName() {
        try {
            DriverAction.click(Locators.projectScroll, "Project");
            DriverAction.waitSec(5);
            DriverAction.click(Locators.gemEcoApis, "GEMECOSYSTEM_APIS");
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select enviroment$")
    public void enviromentName() {
        try {
            DriverAction.click(Locators.enviromentScroll, "Environment");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.checkboxxx, "Select all");
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select date range and click on generate$")
    public void dateRange() {
        try {
            DriverAction.click(Locators.dateRangeScroll, "Date Range");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.thisYear, "This Year");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
            DriverAction.waitSec(20);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate sorting of numbers$")
    public void sorting() {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.waitSec(1);
                DriverAction.doubleClick(Locators.sno, "S No");
                DriverAction.waitSec(2);
                String s2 = DriverAction.getElementText(Locators.span);
                DriverAction.pageScroll(2000, 2000);
                DriverAction.waitSec(4);
                STATUS status;
                String s3 = DriverAction.getElementText(Locators.total);
                String s4 = s3.substring(6, 8);
                if (s2.equals(s4)) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Sorting Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s4, status);
            } else {
                DriverAction.waitSec(1);
                DriverAction.doubleClick(Locators.sno, "S No");
                DriverAction.waitSec(2);
                String s2 = DriverAction.getElementText(Locators.span);
                DriverAction.pageScroll(2000, 2000);
                DriverAction.waitSec(4);
                STATUS status;
                String s3 = DriverAction.getElementText(Locators.total_prod);
                String s4 = s3.substring(6, 8);
                if (s2.equals(s4)) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Sorting Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s4, status);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^click on login and enters (.+) and (.+)$")
    public void loginnn(String usernames, String passwords) {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.login_button, "Log In");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, usernames);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, passwords);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on autolytics and create reports$")
    public void autolyticss() {
        try {
            DriverAction.click(Locators.autolyticsm);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.createReport);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select report names$")
    public void reportNames() {
        try {
            DriverAction.click(Locators.reportNameScroll, "Report Name");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.suiteRunReport, "Suite Run Report");
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select project names$")
    public void projectNames() {
        try {
            DriverAction.click(Locators.projectScroll, "Project Name");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.gemEcoApis, "PROJECT-DEMP");
            DriverAction.waitSec(5);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select enviroments$")
    public void enviromentNames() throws Exception {
        try {
            DriverAction.click(Locators.enviromentScroll);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.prod);
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^select date range and click on generates$")
    public void dateRanges() throws Exception {
        try {
            DriverAction.click(Locators.dateRangeScroll, "Date Range");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.thisYear, "This Year");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
            DriverAction.waitSec(20);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on copy button and check if report opens in new tab and click on new tab button and validate of shared report")
    public void buttons() throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.waitSec(1);
                DriverAction.click(Locators.copyButton, "Copy Button");
                DriverAction.waitSec(3);
                DriverAction.click(Locators.newTab, "Share Report");
                ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(1));
                DriverAction.waitSec(4);
                String string = DriverAction.getCurrentURL();
                STATUS status;
                if (string.contains("https://jewel-beta.gemecosystem.com/#/autolytics/shared-report?")) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Share report Url Validation", "Successful<br>Expected URL: " + string + "<br>Actual URL: " + string, status);
                DriverManager.getWebDriver().close();
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(0));
                DriverAction.waitSec(2);
            } else {
                DriverAction.waitSec(1);
                DriverAction.click(Locators.copyButton, "Copy Button");
                DriverAction.waitSec(3);
                DriverAction.click(Locators.newTab, "Share Report");
                ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(1));
                DriverAction.waitSec(4);
                String string = DriverAction.getCurrentURL();
                STATUS status;
                if (string.contains("https://jewel.gemecosystem.com/#/autolytics/shared-report?")) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Share report Url Validation", "Successful<br>Expected URL: " + string + "<br>Actual URL: " + string, status);
                DriverManager.getWebDriver().close();
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(0));
                DriverAction.waitSec(2);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on create report button$")
    public void createReportButton() throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.createReportButton, "Click on create report button", "Click on create report button successful");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.crossCreateReport);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on filter status and select pass$")
    public void filter() throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.click(Locators.filter, "Filter Status");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectPass, "Fail");
                String asert = DriverAction.getElementText(Locators.selct_pass_text);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Close Filter");
                DriverAction.waitSec(3);
                int count = 0;
                List<String> list = DriverAction.getElementsText(pass);
                for (int i = 0; i < list.size(); i++) {
                    if (asert.equalsIgnoreCase(list.get(i))) {
                        count++;
                    }
                }
                if (count == list.size()) {
                    GemTestReporter.addTestStep("Filter FAIL Validation", "Expected:" + asert, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer FAIL Validation", "Text we got" + list.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Filter FAIL Validation", "Expected:" + asert, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer FAIL Validation", "Text we got" + list.get(0), STATUS.FAIL);
                }
            } else {
                DriverAction.click(Locators.filter, "Filter Status");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectPass_prod, "Pass");
                String asert = DriverAction.getElementText(Locators.selct_pass_text_prod);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Close Filter");
                DriverAction.waitSec(3);
                int count = 0;
                List<String> list = DriverAction.getElementsText(prod_suiteRunRpt);
                for (int i = 0; i < list.size(); i++) {
                    if (asert.equalsIgnoreCase(list.get(i))) {
                        count++;
                    }
                }
                if (count == list.size()) {

                    GemTestReporter.addTestStep("Filter Pass Validation", "Expected: " + asert, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer Pass Validation", "Text we got: " + list.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Filter Pass Validation", "Expected: " + asert, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer Pass Validation", "Text we got: " + list.get(0), STATUS.FAIL);
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on exe$")
    public void exe() throws Exception {
        try {
            DriverAction.click(Locators.selectFilter1);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectPass);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.exe);
            DriverAction.waitSec(1);
            String asert1 = DriverAction.getElementText(Locators.select_exe_text);
            DriverAction.waitSec(3);
            DriverAction.click(Locators.closeFilter);
            DriverAction.waitSec(1);
            int count1 = 0;
            List<String> list1 = DriverAction.getElementsText(Locators.exe1);
            for (int i = 0; i < list1.size(); i++) {
                if (asert1.equalsIgnoreCase(list1.get(i))) {
                    count1++;
                }
            }
            if (count1 == list1.size()) {
                GemTestReporter.addTestStep(" Filter EXE Validation", "Expected:" + asert1, STATUS.INFO);
                GemTestReporter.addTestStep("Filer EXE Validation", "Text we got" + list1.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep(" Filter EXE Validation", "Expected:" + asert1, STATUS.INFO);
                GemTestReporter.addTestStep("Filer EXE Validation", "Text we got" + list1.get(0), STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on fail$")
    public void fail() throws Exception {
        try {
            DriverAction.click(Locators.selectFilter1);
            DriverAction.click(Locators.selectPass);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.fail);
            DriverAction.waitSec(1);
            String asert2 = DriverAction.getElementText(Locators.select_fail_text);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.closeFilter);
            DriverAction.waitSec(3);
            int count2 = 0;
            List<String> list2 = DriverAction.getElementsText(Locators.fail1);
            for (int i = 0; i < list2.size(); i++) {
                System.out.println(list2.get(i));
                if (list2.get(i).contains(asert2)) {
                    count2++;
                }
            }
            if (count2 == list2.size()) {
                GemTestReporter.addTestStep(" Filter FAIL Validation", "Expected:" + asert2, STATUS.INFO);
                GemTestReporter.addTestStep("Filer FAIL Validation", "Text we got" + list2.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep(" Filter FAIL Validation", "Expected:" + asert2, STATUS.INFO);
                GemTestReporter.addTestStep("Filer FAIL Validation", "Text we got" + list2.get(0), STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on warn$")
    public void warn() throws Exception {
        try {
            DriverAction.click(Locators.selectFilter1);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.fail);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.warn);
            DriverAction.waitSec(1);
            String asert3 = DriverAction.getElementText(Locators.select_warn_text);
            DriverAction.waitSec(3);
            int count3 = 0;
            List<String> list3 = DriverAction.getElementsText(Locators.warn1);
            for (int i = 0; i < list3.size(); i++) {
                if (asert3.equalsIgnoreCase(list3.get(i))) {
                    count3++;
                }
            }
            if (count3 == list3.size()) {
                GemTestReporter.addTestStep(" Filter WARN Validation", "Expected:" + asert3, STATUS.INFO);
                GemTestReporter.addTestStep("Filer WARN Validation", "Text we got" + list3.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep(" Filter WARN Validation", "Expected:" + asert3, STATUS.INFO);
                GemTestReporter.addTestStep("Filer WARN Validation", "Text we got" + list3.get(0), STATUS.FAIL);
            }
            DriverAction.click(Locators.warn);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.closeFilter);
            DriverAction.waitSec(1);
            DriverAction.doubleClick(pass);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on filter2 and select a date$")
    public void filter2() throws Exception {
        try {
            DriverAction.waitSec(2);
            DriverAction.pageScroll(1000, 1000);
            DriverAction.waitSec(2);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            js.executeScript("arguments[0].scrollIntoView();", DriverManager.getWebDriver().findElement(By.xpath("//*[text()='August 17, 2022 6:11:28 PM (IST)']")));
            DriverAction.waitSec(4);
            DriverAction.click(Locators.filter1);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectDate);
            DriverAction.waitSec(2);
            String asert4 = DriverAction.getElementText(Locators.selectDate);
            DriverAction.waitSec(3);
            int count4 = 0;
            List<String> list4 = DriverAction.getElementsText(Locators.select_date_text);
            for (int i = 0; i < list4.size(); i++) {
                if (asert4.equalsIgnoreCase(list4.get(i))) {
                    count4++;
                }
            }
            if (count4 == list4.size()) {
                GemTestReporter.addTestStep(" Filter DATE Validation", "Expected:" + asert4, STATUS.INFO);
                GemTestReporter.addTestStep("Filer DATE Validation", "Text we got" + list4.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep(" Filter DATE Validation", "Expected:" + asert4, STATUS.INFO);
                GemTestReporter.addTestStep("Filer DATE Validation", "Text we got" + list4.get(0), STATUS.FAIL);
            }
            DriverAction.click(Locators.selectDate);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.closeFilter);
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on action button and then validate (.+)$")
    public void action(String executionReport) throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.action);
            DriverAction.waitSec(3);
            String exe = DriverAction.getElementText(Locators.executionR);
            STATUS status;
            if (exe.equals(executionReport)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Execution Report tab Validation", "successful<br>Expected text: " + executionReport + "<br>Actual text: " + exe, status);

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on new tab button and validate (.+) of execution report$")
    public void newtabbb(String executionUrl) throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.newtb_button);
            ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb7.get(1));
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on share report button", "Click on share report button successful", STATUS.PASS, DriverAction.takeSnapShot());
            String string = DriverAction.getCurrentURL();
            STATUS status;
            if (string.equals(executionUrl)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep(" Execution report Url Validation", "Successful<br>Expected URL: " + executionUrl + "<br>Actual URL: " + string, status);

            DriverManager.getWebDriver().close();
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb7.get(0));
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on testcase detail and validate filter$")
    public void testCase() throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.testcaseDetail);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.selectFilter3);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_filter);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.click_filter);
            DriverAction.waitSec(1);
            String asert68 = DriverAction.getElementText(Locators.inside_filter_text);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.closeFilter);
            DriverAction.waitSec(3);
            int count2 = 0;
            List<String> listing = DriverAction.getElementsText(Locators.outside_filter_text);
            for (int i = 0; i < listing.size(); i++) {
                if (asert68.equalsIgnoreCase(listing.get(i))) {
                    count2++;
                }
            }
            if (count2 == listing.size()) {
                GemTestReporter.addTestStep(" Filter Start time Validation", "Expected:" + asert68, STATUS.INFO);
                GemTestReporter.addTestStep("Filer Start Validation", "Text we got" + listing.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep(" Filter Start time Validation", "Expected:" + asert68, STATUS.INFO);
                GemTestReporter.addTestStep("Filer Start time Validation", "Text we got" + listing.get(0), STATUS.FAIL);
            }
            DriverAction.doubleClick(Locators.close_filter1);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^click on login and enterss (.+) and (.+)$")
    public void login_again(String u, String p) throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.login_button);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, u);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, p);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.autolyticsm);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.createReport);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.reportNameScroll);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.suiteRunReport);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.projectScroll);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.gemEcoApis);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.enviromentScroll);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.prod);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.scroll_down);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_24);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate suite details are not found (.+)$")
    public void validate_suite(String suites) throws Exception {
        try {
            String suite = DriverAction.getElement(Locators.suite_detail).getText();
            System.out.println(suite);
            DriverAction.waitSec(1);
            STATUS status;
            if (suite.equals(suites)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Suite detail not found (alert) validation", "successful<br>Expected text: " + suites + "<br>Actual text: " + suite, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate all fields are required (.+)$")
    public void validate_field(String f) throws Exception {
        try {
            DriverAction.click(Locators.cross_b);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.open_n);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.createReport);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate);
            DriverAction.waitSec(2);
            String allF = DriverAction.getElement(Locators.all_f).getText();
            STATUS status;
            if (allF.equals(f)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("All fields are required ! (alert) validation", "successful<br>Expected text: " + f + "<br>Actual text: " + allF, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^open suite summary report and validate it has opened successfully (.+)$")
    public void suite_summary(String suite) throws Exception {
        try {
            DriverAction.click(Locators.reportNameScroll);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.suite_sum);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.projectScroll);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.gemEcoApis);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.enviromentScroll);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.prod);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.scroll_down);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.thisYear);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate);
            DriverAction.waitSec(4);
            String actual = DriverAction.getElement(Locators.suite_sum_text).getText();
            STATUS status;
            if (actual.equals(suite)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Suite summary report open validation", "successful<br>Expected text: " + suite + "<br>Actual text: " + actual, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("click er clickon loginnn and entersss (.+) and (.+)$")
    public void suite_su(String us, String pa) throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.waitSec(1);
                DriverAction.click(login_btn, "Log In");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.username, "Username");
                DriverAction.waitSec(1);
                DriverAction.typeText(Locators.username, us);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.passwordm, "Password");
                DriverAction.waitSec(1);
                DriverAction.typeText(Locators.passwordm, pa);
                DriverAction.waitSec(1);
                DriverAction.click(login_button, "Login Button");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.autolyticsm, "Autolytics");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.reporting_new_btn, "Reporting");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.createReport, "Create Report");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.reportNameScroll, "Report Name");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.suite_sum, "Suite Summary Report");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.projectScroll, "Project Name");
                DriverAction.waitSec(5);
                DriverAction.click(Locators.gemEcoApis, "PROJECT-DEMO");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.enviromentScroll, "Environment");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_all2, "Select All");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.dateRangeScroll, "Date Range");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.thisYear, "This Year");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.generate, "Generate");
                DriverAction.waitSec(25);
            } else {
                DriverAction.waitSec(1);
                DriverAction.click(login_btn, "Log In");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.username, "Username");
                DriverAction.waitSec(1);
                DriverAction.typeText(Locators.username, us);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.passwordm, "Password");
                DriverAction.waitSec(1);
                DriverAction.typeText(Locators.passwordm, pa);
                DriverAction.waitSec(1);
                DriverAction.click(login_button, "Login Button");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.autolyticsm, "Autolytics");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.reporting_new_btn, "Reporting");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.createReport, "Create Report");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.reportNameScroll, "Report Name");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.suite_sum, "Suite Summary Report");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.projectScroll, "Project Name");
                DriverAction.waitSec(5);
                DriverAction.click(Locators.gemEcoApis2, "PROJECT-DEMO2");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.enviromentScroll, "Environment");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_all2, "Select All");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.dateRangeScroll, "Date Range");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.thisYear, "This Year");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.generate, "Generate");
                DriverAction.waitSec(25);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate enviroment filter$")
    public void envrmnt_filtr() throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.click(Locators.envrmnt_fltrr, "Filter Environment");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.betaa_2, "BETA");
                DriverAction.waitSec(1);
                String asse = DriverAction.getElementText(Locators.beta_txt2);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Close Filter");
                DriverAction.waitSec(4);
                int count = 0;
                List<String> list = DriverAction.getElementsText(betas2);
                for (int i = 0; i < list.size(); i++) {
                    if (asse.equalsIgnoreCase(list.get(i))) {
                        count++;
                    }
                }
                if (count == list.size()) {

                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Expected: " + asse, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Text we got: " + list.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Expected: " + asse, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Text we got: " + list.get(0), STATUS.FAIL);
                }
//                DriverAction.click(Locators.select_pie, "Close Filter");
//                DriverAction.waitSec(1);
//                DriverAction.click(Locators.betaa2, "Deselect");
//                DriverAction.waitSec(1);
//                DriverAction.click(Locators.betas, "Deselect");
//                DriverAction.waitSec(2);
            } else {
                DriverAction.click(Locators.envrmnt_fltrr, "Filter Environment");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.betaa_txtingg, "Prod");
                DriverAction.waitSec(1);
                String asse = DriverAction.getElementText(Locators.beta_txt);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Close Filter");
                DriverAction.waitSec(4);
                int count = 0;
                List<String> list = DriverAction.getElementsText(betas);
                for (int i = 0; i < list.size(); i++) {
                    if (asse.equalsIgnoreCase(list.get(i))) {
                        count++;
                    }
                }
                if (count == list.size()) {

                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Expected: " + asse, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Text we got: " + list.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Expected: " + asse, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite summary report Filter Environment Validation", "Text we got: " + list.get(0), STATUS.FAIL);
                }
                DriverAction.click(Locators.select_pie, "Close Filter");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.betaa2, "Deselect");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.betas, "Deselect");
                DriverAction.waitSec(2);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @Then("^validate pie chart filter$")
    public void pie_chart_filter() throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.click(Locators.pie_filter, "Filter Suite Summary");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie2, "First Option");
                DriverAction.waitSec(2);
                String pie = DriverAction.getElementText(Locators.pie2);
                System.out.println("text: " + pie);
                DriverAction.click(Locators.closeFilter, "Close Filter");
                DriverAction.waitSec(2);
                int c = 0;
                List<String> pies = DriverAction.getElementsText(Locators.pie1);
                for (int i = 0; i < pies.size(); i++) {
                    if (pie.equalsIgnoreCase(pies.get(i))) {
                        c++;
                    }
                }
                System.out.println("size of pie: " + pies.size());
                System.out.println("COUNT: " + c);
                if (c == pies.size()) {
                    GemTestReporter.addTestStep("Filter Suite Summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer Suite Summary Validation", "Text we got: " + pies.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Filter Suite Summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer Suite Summary Validation", "Text we got: " + pies.get(0), STATUS.FAIL);
                }
            } else {
                DriverAction.click(Locators.pie_filter_prod, "Filter Suite Summary");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie2, "First Option");
                DriverAction.waitSec(1);
                String pie = DriverAction.getElementText(Locators.pie2);
                DriverAction.waitSec(3);
                int c = 0;
                List<String> pies = DriverAction.getElementsText(Locators.pie1);
                for (int i = 0; i < pies.size(); i++) {
                    if (pie.equalsIgnoreCase(pies.get(i))) {
                        c++;
                    }
                }
                if (c == pies.size()) {
                    GemTestReporter.addTestStep("Filter Suite Summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer Suite Summary Validation", "Text we got" + pies.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep(" Filter Suite Summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Filer Suite Summary Validation", "Text we got" + pies.get(0), STATUS.FAIL);
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check total records count$")
    public void check_total_record_count() throws Exception {
        try {
            DriverAction.click(Locators.no_select);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.close_pie);
            DriverAction.waitSec(1);
            DriverAction.pageScroll(1000, 1000);
            DriverAction.waitSec(3);
            GemTestReporter.addTestStep("Total Records", "Total records found", STATUS.INFO, DriverAction.takeSnapShot());
            List<String> pie_counn = DriverAction.getElementsText(Locators.pie_countt);
            System.out.println(pie_counn.size());
            String s8 = String.valueOf(pie_counn.size());
            String s3 = DriverAction.getElementText(Locators.total_text);
            String s4 = s3.substring(6, 7);
            System.out.println(s4);
            STATUS status;
            if (String.valueOf(pie_counn.size()).equals(s4)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Total record count Validation", "Successful<br>Expected Text: " + s8 + "<br>Actual Text: " + s4, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^uss on loginnnn$")
    public void login_againn() throws Exception {
        try {
            suite_su("arpit.mishra", "arpit1234");
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate edit report button and check you're report selected has been generated or not (.+)")
    public void edit_report_button(String suite_run_report) throws Exception {
        try {
            DriverAction.click(Locators.edit_report_btn, "Edit Report button");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.suite_rpt_clck, "Report Name");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.suiteRunReport, "Suite run report");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
//            GemTestReporter.addTestStep("Click on edit report button", "Edit report button has been clicked successfully!!", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(12);
            String suite_run_expc = DriverAction.getElementText(Locators.sun_rn_rpt_txt);
            STATUS status;
            if (suite_run_expc.equals(suite_run_report)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Edit report button generated correct report or not", "Successful<br>Expected Text: " + suite_run_report + "<br>Actual Text: " + suite_run_expc, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^generate suite run report (.+) and (.+)$")
    public void ss_feature(String us, String pa) throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.login_button, "Log In");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username, "Username");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, us);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm, "Password");
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, pa);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton, "Login Button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.autolyticsm, "Autolytics");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.reporting_new_btn, "Reporting");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.createReport, "Create Report");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.reportNameScroll, "Report Name");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.suiteRunReport, "Suite run report");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.projectScroll, "Project Name");
            DriverAction.waitSec(5);
            DriverAction.click(Locators.project_name_selection, "JEWEL_UI_JV");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.enviromentScroll, "Environment");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_all2, "Select All");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.dateRangeScroll, "Date Range");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.seven_days, "7 Days");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
            DriverAction.waitSec(25);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^launch gmail and enter pass$")
    public void gmail_otp_f() throws Exception {
        try {
            DriverAction.launchUrl("https://www.gmail.com");
            DriverAction.waitSec(5);
            DriverAction.click(By.xpath("//input[@type=\"email\"]"));
            DriverAction.waitSec(2);
            DriverAction.typeText(By.xpath("//input[@type=\"email\"]"), "dummy.bharad@gmail.com");
            DriverAction.waitSec(2);
            DriverAction.click(By.xpath("//span[text()=\"Next\"]"));
            DriverAction.waitSec(7);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^open execution report and validate button of screenshots$")
    public void screenShot_feature() throws Exception {
        try {
            DriverAction.click(Locators.filter, "Filter Status");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectPass, "Fail");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.filter, "Filter Status");
            DriverAction.waitSec(3);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Execution_btn)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Execution_btn)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Action button", "Successfully : Clicked on Action Button", STATUS.PASS, DriverAction.takeSnapShot());
//            WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 15));
//            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(wait_for_fail));
            DriverAction.waitSec(15);
            DriverAction.click(testcaseDetail);
            DriverAction.waitSec(10);
            Actions actions2 = new Actions(DriverManager.getWebDriver());
            actions2.moveToElement(DriverManager.getWebDriver().findElement(testcase_plus_opt)).build().perform();
            actions2.click(DriverManager.getWebDriver().findElement(testcase_plus_opt)).build().perform();
            DriverAction.waitSec(7);
            GemTestReporter.addTestStep("Click on +", "Successfully : Clicked on +", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(ss_click, "Screenshot");
            DriverAction.waitSec(10);
            DriverAction.click(open_full_screen, "Full Screen");
            DriverAction.waitSec(4);
            DriverAction.click(close_button_ss, "Cross Option");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^usserr cicks onn loginnnn$")
    public void login_again_1() throws Exception {
        try {
            suite_su("arpit.mishra", "arpit1234");
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the filter on the share report (.+)$")
    public void filter_share_report(String shareR) throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.click(Locators.pie_filter, "Filter Suite Summary");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie2, "First option");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.newTab, "Share Report");
                ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(1));
                DriverAction.waitSec(10);
                String check = DriverAction.getElementText(Locators.shared_reportt);
                GemTestReporter.addTestStep("Filter Suite Summary", "Filter Suite Summary appears the same on shared report: successful", STATUS.PASS, DriverAction.takeSnapShot());
                STATUS status;
                if (check.equals(shareR)) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Share report open Validation", "Successful<br>Expected Text: " + shareR + "<br>Actual Text: " + check, status);
                DriverAction.click(Locators.shared_pie, "Filter Suite Summary");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                String pie = DriverAction.getElementText(Locators.pie2);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(4);
                int c = 0;
                List<String> pies = DriverAction.getElementsText(Locators.pie1);
                for (int i = 0; i < pies.size(); i++) {
                    if (pie.equalsIgnoreCase(pies.get(i))) {
                        c++;
                    }
                }
                if (c == pies.size()) {
                    GemTestReporter.addTestStep("Shared report Filter Suite Summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Shared report Filer Suite Summary Validation", "Text we got: " + pies.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Shared report Filter Suite Summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Shared report Filer Suite Summary Validation", "Text we got: " + pies.get(0), STATUS.FAIL);
                }
                DriverManager.getWebDriver().close();
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(0));
                DriverAction.waitSec(2);
            } else {
                DriverAction.click(Locators.pie_filter_prod, "Filter Suite Summary");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie2, "First option");
                DriverAction.waitSec(2);
                DriverAction.click(Locators.newTab, "Share Report");
                ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(1));
                DriverAction.waitSec(5);
                String check = DriverAction.getElementText(Locators.shared_reportt);
                GemTestReporter.addTestStep("Filter Suite Summary", "Filter Suite Summary selected appears the same on shared report: successful", STATUS.PASS, DriverAction.takeSnapShot());
                STATUS status;
                if (check.equals(shareR)) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Share report open Validation", "Successful<br>Expected Text: " + shareR + "<br>Actual Text: " + check, status);
                DriverAction.click(Locators.shared_pie, "Filter Suite Summary");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_pie, "Select Option(s)");
                DriverAction.waitSec(1);
                String pie = DriverAction.getElementText(Locators.pie2);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(4);
                int c = 0;
                List<String> pies = DriverAction.getElementsText(Locators.pie1);
                for (int i = 0; i < pies.size(); i++) {
                    if (pie.equalsIgnoreCase(pies.get(i))) {
                        c++;
                    }
                }
                if (c == pies.size()) {
                    GemTestReporter.addTestStep("Shared report Filter Suite summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Shared report Filer Suite summary Validation", "Text we got" + pies.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Shared report Filter Suite summary Validation", "Expected: " + pie, STATUS.INFO);
                    GemTestReporter.addTestStep("Shared report Filer Suite summary Validation", "Text we got" + pies.get(0), STATUS.FAIL);
                }
                DriverManager.getWebDriver().close();
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(0));
                DriverAction.waitSec(2);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^user logs in again$")
    public void login_again_2() throws Exception {
        try {
            global();
            DriverAction.click(Locators.reportNameScroll, "Report Name");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.suite_diag, "Suite Diagnose Report");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.projectScroll, "Project");
            DriverAction.waitSec(5);
            DriverAction.click(Locators.gemEcoApis, "GEMECOSYSTEM_APIS");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.enviromentScroll, "Environment");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_all2, "Select All");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.dateRangeScroll, "Date Range");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.thisYear, "This Year");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
            DriverAction.waitSec(20);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Generates Suite Diagnose Report (.+)$")
    public void sdr(String Suite_d) throws Exception {
        try {
            String actual = DriverAction.getElementText(Locators.suite_diag);
            STATUS status;
            if (actual.equals(Suite_d)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Suite Diagnose Report open Validation", "Successful<br>Expected Text: " + Suite_d + "<br>Actual Text: " + actual, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate filter$")
    public void sdr_filter() throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.click(Locators.last_run_status, "Filter Last Run Status");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectPass, "Fail");
                DriverAction.waitSec(1);
                String asert3 = DriverAction.getElementText(Locators.selct_pass_text);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(3);
                int count3 = 0;
                List<String> list3 = DriverAction.getElementsText(Locators.pass);
                for (int i = 0; i < list3.size(); i++) {
                    if (asert3.equalsIgnoreCase(list3.get(i))) {
                        count3++;
                    }
                }
                if (count3 == list3.size()) {
                    GemTestReporter.addTestStep("Suite Diagnose Report Filter FAIL Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite Diagnose Report Filer FAIL Validation", "Text we got: " + list3.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Suite Diagnose Report Filter FAIL Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite Diagnose Report Filer FAIL Validation", "Text we got: " + list3.get(0), STATUS.FAIL);
                }
            } else {
                DriverAction.click(Locators.last_run_status_prod, "Filter Last Run Status");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectPass_prod, "Pass");
                DriverAction.waitSec(1);
                String asert3 = DriverAction.getElementText(Locators.selct_pass_text_prod);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(3);
                int count3 = 0;
                List<String> list3 = DriverAction.getElementsText(Locators.prod_suiteRunRpt);
                for (int i = 0; i < list3.size(); i++) {
                    if (asert3.equalsIgnoreCase(list3.get(i))) {
                        count3++;
                    }
                }
                if (count3 == list3.size()) {
                    GemTestReporter.addTestStep("Suite Diagnose Report Filter PASS Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite Diagnose Report Filer PASS Validation", "Text we got: " + list3.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Suite Diagnose Report Filter PASS Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Suite Diagnose Report Filer PASS Validation", "Text we got: " + list3.get(0), STATUS.FAIL);
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^user logs in again for Test Diagnose Report$")
    public void logs_again_tdr() throws Exception {
        try {
            global();
            DriverAction.click(Locators.reportNameScroll, "Report Name");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.testcase_diag, "Test diagnose report");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.projectScroll, "Project Name");
            DriverAction.waitSec(5);
            DriverAction.click(Locators.gemEcoApis, "GEMECOSYSTEM_APIS");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.enviromentScroll, "ENVIROMENT");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_all2, "Select All");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.dateRangeScroll, "Date Range");
            DriverAction.waitSec(1);
            DriverAction.click(thisYear, "This year");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
            DriverAction.waitSec(20);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Generates Test Diagnose report (.+)$")
    public void tdr(String testDiag) throws Exception {
        try {
            String actual = DriverAction.getElementText(Locators.testcase_diag);
            STATUS status;
            if (actual.equals(testDiag)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Test Diagnose Report open Validation", "Successful<br>Expected Text: " + testDiag + "<br>Actual Text: " + actual, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);

        }
    }

    @Then("^validates a filter$")
    public void test_diag_filter() throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.click(Locators.suite_run_filter, "Filter Suite Name");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_daily_beta_reg, "TestReport_run");
                DriverAction.waitSec(1);
                String asert3 = DriverAction.getElementText(Locators.daily_beta_reg_text);
                System.out.println("ASSSET# " + asert3);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(3);
                int count3 = 0;
                List<String> list3 = DriverAction.getElementsText(Locators.daily_betas_reg_text);
                for (int i = 0; i < list3.size(); i++) {
                    if (asert3.equalsIgnoreCase(list3.get(i))) {
                        count3++;
                    }
                }
                if (count3 == list3.size()) {
                    GemTestReporter.addTestStep("Test Diagnose Report Filter SUITE name Status Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Test Diagnose Report Filer SUITE name Status Validation", "Text we got: " + list3.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Test Diagnose Report Filter SUITE name Status Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Test Diagnose Report Filer SUITE name Status Validation", "Text we got: " + list3.get(0), STATUS.FAIL);
                }
            } else {
                DriverAction.click(Locators.suite_run_filter_prod, "Filter Suite Name");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.select_daily_beta_reg, "TEST REGRESSION");
                DriverAction.waitSec(1);
                String asert3 = DriverAction.getElementText(Locators.daily_beta_reg_text);
                System.out.println("ASSSET# " + asert3);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(3);
                int count3 = 0;
                List<String> list3 = DriverAction.getElementsText(Locators.daily_betas_reg_text_prod);
                for (int i = 0; i < list3.size(); i++) {
                    if (asert3.equalsIgnoreCase(list3.get(i))) {
                        count3++;
                    }
                }
                if (count3 == list3.size()) {
                    GemTestReporter.addTestStep("Test Diagnose Report Filter SUITE name Status Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Test Diagnose Report Filer SUITE name Status Validation", "Text we got: " + list3.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Test Diagnose Report Filter SUITE name Status Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Test Diagnose Report Filer SUITE name Status Validation", "Text we got: " + list3.get(0), STATUS.FAIL);
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^user logs in for Testcase Run Report$")
    public void logs_in_trr() throws Exception {
        try {
            global();
            DriverAction.click(Locators.reportNameScroll, "Report Name");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.testcase_run_rpt, "Test case run report");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.projectScroll, "Project Name");
            DriverAction.waitSec(5);
            DriverAction.click(Locators.gemEcoApis, "GEMECOSYSTEM_APIS");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.enviromentScroll, "ENVIROMENT");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_all2, "Select All");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.dateRangeScroll, "Date Range");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.thisYear, "This Year");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
            DriverAction.waitSec(20);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Generates Testcase Run Report (.+)$")
    public void trr(String testRun) throws Exception {
        try {
            String actual = DriverAction.getElementText(Locators.test_case_run_rpt_txt);
            System.out.println(actual);
            STATUS status;
            if (actual.equals(testRun)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Testcase Run Report open Validation", "Successful<br>Expected Text: " + testRun + "<br>Actual Text: " + actual, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validatess a filter$")
    public void filter_trr() throws Exception {
        try {
            DriverAction.click(Locators.project_name_fltr, "Filter Project Name");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.pygem_project_select, "Gemeco-api-jv");
            DriverAction.waitSec(2);
            String asert3 = DriverAction.getElementText(Locators.pygem_project_txt);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.closeFilter, "Filter Close");
            DriverAction.waitSec(3);
            int count3 = 0;
            List<String> list3 = DriverAction.getElementsText(Locators.pygem_project_txt);
            for (int i = 0; i < list3.size(); i++) {
                if (asert3.equalsIgnoreCase(list3.get(i))) {
                    count3++;
                }
            }
            if (count3 == list3.size()) {
                GemTestReporter.addTestStep("Testcase Run Report Filter Project Name Validation", "Expected: " + asert3, STATUS.INFO);
                GemTestReporter.addTestStep("Testcase Run Report Filer Project Name Validation", "Text we got: " + list3.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Testcase Run Report Filter Project Name Validation", "Expected: " + asert3, STATUS.INFO);
                GemTestReporter.addTestStep("Testcase Run Report Filer Project Name Validation", "Text we got: " + list3.get(0), STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^user logs in for Testcase Summary Report$")
    public void tsr_login() throws Exception {
        try {
            global();
            DriverAction.click(Locators.reportNameScroll, "Report Name");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.testcase_summ_rpt, "Test case summary report");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.projectScroll, "Project Name");
            DriverAction.waitSec(5);
            DriverAction.click(Locators.gemEcoApis, "GEMECOSYSTEM_APIS");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.enviromentScroll, "ENVIROMENT");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_all2, "Select All");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.dateRangeScroll, "Date Range");
            DriverAction.waitSec(1);
            DriverAction.click(thisYear, "This year");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.generate, "Generate");
            DriverAction.waitSec(25);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Generates Testcase Summary Report (.+)$")
    public void tsr_validation(String tsr) throws Exception {
        try {
            String actual = DriverAction.getElementText(Locators.testcase_summ_rpt);
            STATUS status;
            if (actual.equals(tsr)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Testcase Summary Report open Validation", "Successful<br>Expected Text: " + tsr + "<br>Actual Text: " + actual, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate a filterr$")
    public void tsr_filter() throws Exception {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                DriverAction.click(Locators.broken_index_filtr, "Filter Broken Index");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.broken_index_slct, "broken index");
                DriverAction.waitSec(2);
                String asert3 = DriverAction.getElementText(Locators.broken_index_txt);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(3);
                int count3 = 0;
                List<String> list3 = DriverAction.getElementsText(Locators.broken_index_txts);
                for (int i = 0; i < list3.size(); i++) {
                    if (asert3.equalsIgnoreCase(list3.get(i))) {
                        count3++;
                    }
                }
                if (count3 == list3.size()) {
                    GemTestReporter.addTestStep("Testcase Summary Report Filter Broken Index Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Testcase Summary Report Filer Broken Index Validation", "Text we got: " + list3.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Testcase Summary Report Filter Broken Index Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Testcase Summary Report Filer Broken Index Validation", "Text we got: " + list3.get(0), STATUS.FAIL);
                }
            } else {
                DriverAction.click(Locators.broken_index_filtr_prod, "Filter Broken Index");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.selectFilter, "Select Option(s)");
                DriverAction.waitSec(1);
                DriverAction.click(Locators.broken_index_slct, "Broken Index");
                DriverAction.waitSec(2);
                String asert3 = DriverAction.getElementText(Locators.broken_index_txt);
                DriverAction.waitSec(1);
                DriverAction.click(Locators.closeFilter, "Filter Close");
                DriverAction.waitSec(3);
                int count3 = 0;
                List<String> list3 = DriverAction.getElementsText(Locators.broken_index_prod);
                for (int i = 0; i < list3.size(); i++) {
                    if (asert3.equalsIgnoreCase(list3.get(i))) {
                        count3++;
                    }
                }
                if (count3 == list3.size()) {
                    GemTestReporter.addTestStep("Testcase Summary Report Filter Broken Index Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Testcase Summary Report Filer Broken Index Validation", "Text we got: " + list3.get(0), STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Testcase Summary Report Filter Broken Index Validation", "Expected: " + asert3, STATUS.INFO);
                    GemTestReporter.addTestStep("Testcase Summary Report Filer Broken Index Validation", "Text we got: " + list3.get(0), STATUS.FAIL);
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check the json file$")
    public void json_file() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn);
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Execute (playIcon)", "Successfully : Clicked on Execute (playIcon)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(modeScroll, "Mode");
            DriverAction.waitSec(1);
            DriverAction.click(modeSelect, "Optimize");
            DriverAction.waitSec(1);
            DriverAction.click(envScroll, "Enviroment");
            DriverAction.waitSec(1);
            DriverAction.click(envSelect, "Beta");
            DriverAction.waitSec(1);
            DriverAction.click(url, "Select Key");
            DriverAction.waitSec(1);
            DriverAction.click(urlSelect, "base_url");
            DriverAction.waitSec(1);
            DriverAction.typeText(valueInput, "https://apis-beta.gemecosystem.com");
            DriverAction.waitSec(1);
            DriverAction.click(executeButton, "Execute");
            DriverAction.waitSec(5);
            DriverAction.click(linkClick, "Report Link");
            ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb7.get(1));
            DriverAction.waitSec(7);
            WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 240));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(hundered));
            DriverAction.waitSec(15);
            GemTestReporter.addTestStep("Inside the link clicked", "Link clicked Successfully", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(testcaseDetail, "test case detail");
            DriverAction.waitSec(5);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            WebElement element = DriverManager.getWebDriver().findElement(Locators.see_more);
            js.executeScript("arguments[0].scrollIntoView();", element);
            DriverAction.waitSec(4);
            DriverAction.click(see_more, "See More");
            DriverAction.waitSec(2);
            DriverAction.click(click_here, "Click Here");
            DriverAction.waitSec(5);
            String s = DriverAction.getElementText(browser_isCompat);
            STATUS status;
            if (s.equals("If File is Browser Compatible it will open in new Window else it will download the file.")) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("If File is Browser Compatible it will open in new Window else it will download the file", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^verify the url of admin screen$")
    public void admin_url() throws Exception {
        try {
            String s = DriverAction.getCurrentURL();
            String url = ProjectConfigData.getProperty("launchUrl");
            String url_actual;
            if (url.contains("beta")) {
                url_actual = "https://jewel-beta.gemecosystem.com/#/admin";
            } else {
                url_actual = "https://jewel.gemecosystem.com/#/admin";
            }
            STATUS status;
            if (s.contains(url_actual)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Admin Screen URL Validation", "Expected Text: " + url_actual + "<br>Actual Text: " + s, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^verify action buttons$")
    public void admin_action() throws Exception {
        try {
            if (DriverAction.getElement(edit_project_details2).isDisplayed() && DriverAction.getElement(delete_project2).isDisplayed() && DriverAction.getElement(edit_access2).isDisplayed()) {
                GemTestReporter.addTestStep("Verify action buttons are enabled", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify action buttons are enabled", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^verify action button$")
    public void admin_action2() throws Exception {
        try {
            if (DriverAction.getElement(edit_project_details).isDisplayed() && DriverAction.getElement(delete_project).isDisplayed() && DriverAction.getElement(edit_access).isDisplayed()) {
                GemTestReporter.addTestStep("Verify action buttons are disabled", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify action buttons are disabled", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate sorting of admin screen$")
    public void validate_sorting_adminScreen() throws Exception {
        try {
            DriverAction.doubleClick(Locators.sno, "S No");
            DriverAction.waitSec(2);
            String s2 = DriverAction.getElementText(Locators.span);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(4);
            STATUS status;
            String s3 = DriverAction.getElementText(Locators.total_projects);
            String s4 = s3.substring(6, 7);
            System.out.println("--" + s2 + "--" + s4 + "--");
            if (s2.equals(s4)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Sorting Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s4, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @Then("^validate the alert when user don't have access$")
    public void validate_alert_when_user_have() throws Exception {
        try {
            String url1 = ProjectConfigData.getProperty("launchUrl");
            if (url1.contains("beta")) {
                DriverAction.click(request_access, "Request Access Button");
                DriverAction.waitSec(2);
                DriverAction.click(dropdown_of_request, "Open Project(s) dropdown");
                DriverAction.waitSec(2);
                DriverAction.click(By.xpath(inputBox_of_request.replace("@projectname", "SAMPLE_PROJECT")), "Select project");
                DriverAction.waitSec(2);
                DriverAction.click(select_access_request, "Select Access Role(s)");
                DriverAction.waitSec(2);
                DriverAction.click(admin_select, "Admin");
                DriverAction.waitSec(2);
                DriverAction.click(request_access_btn, "Request Access");
                WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
                String s = DriverAction.getElementText(Alert_admin1);
                System.out.println("Alert: " + s);
                String s2 = "Request has been send";
                STATUS status;
                if (s.equals(s2)) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Request has been send Alert Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s, status);
            } else {
                DriverAction.click(request_access, "Request Access Button");
                DriverAction.waitSec(2);
                DriverAction.click(dropdown_of_request, "Open Project(s) dropdown");
                DriverAction.waitSec(2);
                DriverAction.click(By.xpath(inputBox_of_request.replace("@projectname", "SAMPLE")), "Select project");
                DriverAction.waitSec(2);
                DriverAction.click(select_access_request, "Select Access Role(s)");
                DriverAction.waitSec(2);
                DriverAction.click(admin_select, "Admin");
                DriverAction.waitSec(2);
                DriverAction.click(request_access_btn, "Request Access");
                WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
                String s = DriverAction.getElementText(Alert_admin1);
                System.out.println("Alert: " + s);
                String s2 = "Request has been send";
                STATUS status;
                if (s.equals(s2)) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Request has been send Alert Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s, status);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate the alert user already have access for \"(.*)\"$")
    public void admin_screen_alert2(String projectName) {
        try {
            DriverAction.click(request_access, "Request Access Button");
            DriverAction.waitSec(2);
            DriverAction.click(dropdown_of_request, "Open Project(s) dropdown");
            DriverAction.waitSec(2);
            DriverAction.click(By.xpath(inputBox_of_request.replace("@projectname", projectName)), "Select project");
            DriverAction.waitSec(2);
            DriverAction.click(select_access_request, "Select Access Role(s)");
            DriverAction.waitSec(2);
            DriverAction.click(admin_select, "Admin");
            DriverAction.waitSec(2);
            DriverAction.click(request_access_btn, "Request Access");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            String s2 = "You already have Admin access for " + projectName;
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Request has been send Alert Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened " + e, STATUS.FAIL);
        }
    }

    @Then("^validate the project has been created on grid$")
    public void project_created_onGrid() throws Exception {
        try {
            DriverAction.click(create_project, "Create Project Button");
            DriverAction.waitSec(2);
            DriverAction.click(project_name_create_project, "Project Name");
            DriverAction.waitSec(2);
            DriverAction.typeText(project_name_create_project, projectNames);
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("New project name", "New Project name is: " + projectNames, STATUS.INFO);
            DriverAction.click(enviro_create_project, "Environment(s)");
            DriverAction.waitSec(2);
            DriverAction.typeText(enviro_create_project, projectNames);
            DriverAction.waitSec(2);
            DriverManager.getWebDriver().findElement(enviro_create_project).sendKeys(Keys.ENTER);
            DriverAction.waitSec(2);
            DriverAction.click(textArea, "Project Description");
            DriverAction.waitSec(2);
            DriverAction.typeText(textArea, projectNames);
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Description", "Description is: " + projectNames, STATUS.INFO);
            DriverAction.click(create_button_Admin, "Create");
            DriverAction.waitSec(2);
            String s3 = DriverAction.getElementText(Alert_admin1);
            String s2 = "Project is created Successfully !!";
            STATUS status;
            if (s3.equals(s2)) {
                GemTestReporter.addTestStep("Project is created Successfully !! Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Project is created Successfully !! Validation", "Unsuccessful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.FAIL);
            }
            DriverAction.doubleClick(Locators.sno, "S No");
            DriverAction.waitSec(4);
            String project_name = DriverAction.getElementText(project_name_Admin);
            if (projectNames.equals(project_name)) {
                GemTestReporter.addTestStep("Project has been added or not?", "Successful<br>Expected Text: " + projectNames + "<br>Actual Text: " + project_name, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Project has been added or not?", "Unsuccessful<br>Expected Text: " + projectNames + "<br>Actual Text: " + project_name, STATUS.FAIL);
            }
            String description = DriverAction.getElementText(project_desc_Admin);
            if (projectNames.equals(description)) {
                GemTestReporter.addTestStep("Description has been added or not?", "Successful<br>Expected Text: " + projectNames + "<br>Actual Text: " + description, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Description has been added or not?", "Unsuccessful<br>Expected Text: " + projectNames + "<br>Actual Text: " + description, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^delete the same project$")
    public void deleteProjectAdmin() throws Exception {
        GemTestReporter.addTestStep("Project Name to be deleted", "Project to be deleted is: " + projectNames, STATUS.INFO);
        Actions action2 = new Actions(DriverManager.getWebDriver());
        action2.moveToElement(DriverManager.getWebDriver().findElement(trashCan)).build().perform();
        action2.click(DriverManager.getWebDriver().findElement(trashCan)).build().perform();
        DriverAction.waitSec(4);
        GemTestReporter.addTestStep("Click on Delete Project", "Successfully : Clicked on Delete Project", STATUS.PASS, DriverAction.takeSnapShot());
        Actions action3 = new Actions(DriverManager.getWebDriver());
        action3.moveToElement(DriverManager.getWebDriver().findElement(yes)).build().perform();
        action3.click(DriverManager.getWebDriver().findElement(yes)).build().perform();
        DriverAction.waitSec(4);
        List<String> list = DriverAction.getElementsText(projectNameList);
        System.out.println("LIST SIZE: " + list.size());
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase(projectNames)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            GemTestReporter.addTestStep("Project has been deleted?", "Project has been deleted from the grid", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Project has been deleted?", "Project has not been deleted from the grid", STATUS.FAIL, DriverAction.takeSnapShot());

        }
    }

    @Then("^unverify the company and create new company with same domain$")
    public void createdUnverifyCom() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(unverifyButton)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(unverifyButton)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on unverify company", "Successfully : Clicked on unverify company", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(regitser_company);
            DriverAction.waitSec(2);
            DriverAction.typeText(company_name_super, str + "testdemo");
            DriverAction.waitSec(2);
            DriverAction.click(domain_name, "Domain name");
            DriverAction.waitSec(2);
            DriverAction.typeText(domain_name, str + ".com" + Keys.ENTER);
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin, "REGISTER");
            DriverAction.waitSec(3);
//            DriverAction.doubleClick(sno, "S_NO");
            DriverAction.waitSec(2);
            String s1 = DriverAction.getElementText(new_compName);
            String str1 = str + "testdemo";
            if (str1.equalsIgnoreCase(s1)) {
                GemTestReporter.addTestStep("Resgitered company validation", "Successful<br>Expected Text: " + str1 + "<br>Actual Text: " + s1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Resgitered company validation", "Unsuccessful<br>Expected Text: " + str1 + "<br>Actual Text: " + s1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            Actions action3 = new Actions(DriverManager.getWebDriver());
            action3.moveToElement(DriverManager.getWebDriver().findElement(tickmarkVerify)).build().perform();
            action3.click(DriverManager.getWebDriver().findElement(tickmarkVerify)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on verify", "Successfully : Clicked on verify company", STATUS.PASS, DriverAction.takeSnapShot());
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            String s2 = "Domain is already present in another company !!";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Domain is already present in another company !! Alert Validation", "Expected Text: " + s + "<br>Actual Text: " + s2, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

//    @Then("^verify admins are present$")
//    public void verifyAdminsOf() throws Exception {
//        try {
//            Actions action2 = new Actions(DriverManager.getWebDriver());
//            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
//            DriverAction.waitSec(4);
//            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
//            List<String> admins = DriverAction.getElementsText(adminsList);
//            for (int i = 0; i < admins.size(); i++) {
//                if (admins.get(i).isEmpty()) {
//                    GemTestReporter.addTestStep("Company admins visibility", "Company admins are not visible", STATUS.FAIL, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Company admins visibility", "Company admins are visible", STATUS.PASS, DriverAction.takeSnapShot());
//                }
//            }
//        } catch (Exception e) {
//            logger.info("Exception occurred", e);
//            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
//        }
//    }


    @Then("^validate the project has been not created on grid$")
    public void project_not_created_admin() throws Exception {
        try {
            DriverAction.click(create_project, "Create Project Button");
            DriverAction.waitSec(2);
            DriverAction.click(project_name_create_project, "Project Name");
            DriverAction.waitSec(1);
            DriverAction.typeText(project_name_create_project, "TEST-PROJECT");
            DriverAction.waitSec(2);
            DriverAction.click(enviro_create_project, "Environment(s)");
            DriverAction.waitSec(1);
            DriverAction.typeText(enviro_create_project, "TEST-PROJECT");
            DriverAction.waitSec(2);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.sendKeys(Keys.ENTER).build().perform();
            DriverAction.waitSec(2);
            DriverAction.click(textArea, "Project Description");
            DriverAction.waitSec(1);
            DriverAction.typeText(textArea, "TEST-PROJECT");
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Description", "Description is: TEST-PROJECT", STATUS.INFO);
            DriverAction.click(create_button_Admin, "Create");
            DriverAction.waitSec(2);
            String s3 = DriverAction.getElementText(Alert_admin1);
            String s2 = "Project with this name is already exists";
            if (s3.equals(s2)) {
                GemTestReporter.addTestStep("Project with this name is already exists Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Project with this name is already exists Validation", "Unsuccessful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the project has never been not created on grid$")
    public void project_not_created_adminnn() throws Exception {
        try {
            DriverAction.click(create_project, "Create Project Button");
            DriverAction.waitSec(2);
            DriverAction.click(create_button_Admin, "Create");
            DriverAction.waitSec(2);
            String s3 = DriverAction.getElementText(Alert_admin1);
            String s2 = "Please fill out all Mandatory Fields!";
            if (s3.equals(s2)) {
                GemTestReporter.addTestStep("Please fill out all Mandatory Fields! Alert Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Please fill out all Mandatory Fields! Alert Validation", "Unsuccessful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the project name cannot be modified$")
    public void admin_screen_project_name_mod() throws Exception {
        try {
            DriverAction.doubleClick(Locators.sno, "S No");
            DriverAction.waitSec(2);
            String project_name_before = DriverAction.getElementText(project_name_Admin);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverAction.getElement(edit_project_details2));
            actions.click();
            actions.perform();
            boolean clickable = Functions_Admin.elementClickable(Locators.project_name_create_project);
            DriverAction.click(save_admin_button, "Save");
            DriverAction.waitUntilElementAppear(project_name_Admin, 5);
            String new_project_name = DriverAction.getElementText(project_name_Admin);
            if (!clickable && project_name_before.equalsIgnoreCase(new_project_name)) {
                GemTestReporter.addTestStep("Validate if project name can be modified", "Project name cannot be modified", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate if project name can be modified", "Project name modified", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened " + e, STATUS.FAIL);
        }
    }

    @Then("^validate the description is getting modified$")
    public void desc_mod_admin() throws Exception {
        try {
            DriverAction.doubleClick(Locators.sno, "S No");
            DriverAction.waitSec(2);
            String description_nm_bf4 = DriverAction.getElementText(desc_name_admin);
            GemTestReporter.addTestStep("Description before modification", "Description is: " + description_nm_bf4, STATUS.INFO, DriverAction.takeSnapShot());
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverAction.getElement(edit_project_details2));
            actions.click().build().perform();
            DriverAction.waitSec(2);
            DriverAction.click(textArea, "Project Description");
            DriverAction.waitSec(2);
            DriverAction.clearText(textArea);
            DriverAction.waitSec(2);
            String s = "TESTER_" + Math.random();
            DriverAction.typeText(textArea, s);
            DriverAction.waitSec(2);
            DriverAction.click(save_admin_button, "Save");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(edit_details_dialog));
            String new_desc_name = DriverAction.getElementText(desc_name_admin);
            GemTestReporter.addTestStep("Description after modification", "Description is: " + new_desc_name, STATUS.INFO, DriverAction.takeSnapShot());
            if (new_desc_name.equals(s)) {
                GemTestReporter.addTestStep("Modified Description Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + new_desc_name, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Modified Description Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + new_desc_name, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^validate when user clicks on no$")
    public void admin_del_no() throws Exception {
        try {
            DriverAction.doubleClick(Locators.sno, "S No");
            DriverAction.waitSec(2);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Delete Project", "Successfully : Clicked on Delete Project", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(delete_no_btn, "No");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate when user clicks on yes$")
    public void admin_del_yes() throws Exception {
        try {
            String project_name = DriverAction.getElementText(project_name_Admin);
            GemTestReporter.addTestStep("Project which is being deleted", "Name is: " + project_name, STATUS.INFO, DriverAction.takeSnapShot());
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Delete Project", "Successfully : Clicked on Delete Project", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(delete_yes_btn, "Yes");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
            String s = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
            String s2 = "Project has been deleted successfully";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Project has been deleted temporarily and moved to Recycle Bin Alert Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Given("^Verify on first rendering only suit pill is displayed$")
    public void isSuiteDisplayed() throws Exception {
        try {
            global2();
            DriverAction.click(Locators.testTool, "Test tool");
//            WebDriverWait wait=new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.presenceOfElementLocated((By) DriverManager.getWebDriver().findElement(suite_id_btn)));
            DriverAction.waitSec(2);
            if (DriverAction.getElement(Locators.suite).isDisplayed()) {
                GemTestReporter.addTestStep("Suite Pill", "Suite pill is displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Suite Pill", "Suite pill is not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(suite, "Suite");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate the testcase imported appears on grid$")
    public void importedTestcases() throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            String s = DriverAction.getElementText(Locators.getTotal_COunt2);
            System.out.println("STRING::::" + s);
            String s2 = s.split(" ")[1];
            int total = Integer.parseInt(s2);
            System.out.println("TOTAL:" + total);
            GemTestReporter.addTestStep("Testcase count before", "Test case count before is: " + total, STATUS.PASS, DriverAction.takeSnapShot());
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            js.executeScript("window.scrollBy(-2000,-2000)");
            DriverAction.waitSec(2);
            DriverAction.click(import_from_lib, "Import from library");
            DriverAction.waitSec(6);
//            DriverAction.click(checkbox1, "Check box1");
//            DriverAction.waitSec(1);
            DriverAction.click(checkbox2, "Check box2");
            DriverAction.waitSec(1);
            DriverAction.click(checkbox3, "Check box3");
            DriverAction.waitSec(2);
            List<WebElement> list = DriverAction.getElements(unselectCheckbox);
            System.out.println(list.size());
            DriverAction.waitSec(1);
            DriverAction.click(importButton, "Import button");
            DriverAction.waitSec(2);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            String s1 = DriverAction.getElementText(Locators.getTotal_COunt2);
            System.out.println("STRING::::" + s);
            String s3 = s1.split(" ")[1];
            int total1 = Integer.parseInt(s3);
            System.out.println("TOTAL:" + total1);
            GemTestReporter.addTestStep("Testcase count after", "Testcase count after adding test case is: " + total1, STATUS.PASS, DriverAction.takeSnapShot());
            int sum = 0;
            int a = list.size();
            int b = Integer.parseInt(String.valueOf(total));
            sum = a + b;
            System.out.println(sum);
            int c = Integer.parseInt(String.valueOf(total1));
            if (c == sum) {
                GemTestReporter.addTestStep("Test case count updated or not?", "Successful<br>Expected Text: " + c + "<br>Actual Text: " + sum, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test case count updated or not?", "UnSuccessful<br>Expected Text: " + c + "<br>Actual Text: " + sum, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate a testcase has been deleted from the grid (.+)$")
    public void deletedTestcase(String actual) throws Exception {
        try {
            DriverAction.click(suite_id_btn);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(2);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(2);
            String s = DriverAction.getElementText(Locators.getTotal_COunt2);
            System.out.println("STRING::::" + s);
            String s2 = s.split(" ")[1];
            int total = Integer.parseInt(s2);
            System.out.println("TOTAL:" + total);
            GemTestReporter.addTestStep("Testcase count before deletion", "Test case count before is: " + total, STATUS.INFO, DriverAction.takeSnapShot());
            String deletedTestcase = DriverAction.getElementText(testcaseToBeDel);
            System.out.println("DELEteD" + deletedTestcase);
            Actions action = new Actions(DriverManager.getWebDriver());
            action.moveToElement(DriverManager.getWebDriver().findElement(binOption)).build().perform();
            action.click(DriverManager.getWebDriver().findElement(binOption)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Bin option click", "Bin Option has been clicked!!", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(yesOPtion, "Yes");
            DriverAction.waitSec(4);
            String testcaseDeletedS = DriverAction.getElementText(testcaseDeleted);
            if (testcaseDeletedS.equals(actual)) {
                GemTestReporter.addTestStep("Test case deleted Alert validation", "Successful<br>Expected Text: " + testcaseDeletedS + "<br>Actual Text: " + actual, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test case deleted Alert validation", "Successful<br>Expected Text: " + testcaseDeletedS + "<br>Actual Text: " + actual, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            ArrayList<String> list1 = new ArrayList<>();
            int i = 1;
            boolean result;
            int count = 0;
            boolean nextbtn = true;
            while (nextbtn) {
                DriverAction.pageScroll(2000, 2000);
                list1.addAll(DriverAction.getElementsText(testcaseIDList));
                DriverAction.waitSec(1);
                i++;
                if (DriverManager.getWebDriver().findElement(Locators.nextPageBtn2).isEnabled()) {
                    DriverAction.click(Locators.nextPageBtn2);
                } else {
                    nextbtn = false;
                }
            }
            for (int k = 0; k < list1.size(); k++) {
                result = list1.get(k).contains(deletedTestcase);
                if (result == true) {
                    count++;
                }
            }
            if (count == 0) {
                GemTestReporter.addTestStep("(Validation)Testcase has been deleted or not?", "Testcase not found in the grid", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                System.out.println("EXISTS");
            }
            System.out.println("TOTAL EXPECTED: " + list1.size());
            String s1 = DriverAction.getElementText(Locators.getTotal_COunt2);
            System.out.println("STRING::::" + s);
            String s3 = s1.split(" ")[1];
            int totall = Integer.parseInt(s3);
            System.out.println("TOTAL:" + totall);
            GemTestReporter.addTestStep("Testcase count after deletion", "Test case count after is: " + totall, STATUS.INFO, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate git when no url is passed$")
    public void validateGitNoUrl() {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            if (!DriverAction.getElement(integrateGIT).isEnabled()) {
                GemTestReporter.addTestStep("Integrate GIT button is not clickable?", "Integrate GIT button is not clickable", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Integrate GIT button is not clickable?", "Integrate GIT button is not clickable", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate git when wrong url is passed (.+)$")
    public void validateWrongUrl(String alert1) {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.typeText(GITlink, "a");
            DriverAction.waitSec(2);
            DriverAction.click(integrateGIT, "Integrate GIT");
            DriverAction.waitSec(5);
            String s = DriverAction.getElementText(Locators.noTestCase);
            s = s.replace("\n", "");
            System.out.println("*****" + s);
            if (s.contains(alert1)) {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "Successful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "unSuccessful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate git when wrong token is passed (.+)$")
    public void wrongTokenIs(String alert1) {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.typeText(GITlink, "abc");
            DriverAction.waitSec(2);
            DriverAction.click(repoIsPvt, "Check if Repository is Private.");
            DriverAction.waitSec(2);
            if (DriverAction.getElement(repoIsPvt).isSelected()) {
                GemTestReporter.addTestStep("Check if Repository is Private (Checkbox isSelected?", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Check if Repository is Private (Checkbox isSelected?", "UnSuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.typeText(accessToken, "a");
            DriverAction.waitSec(2);
            DriverAction.click(integrateGIT, "Integrate GIT");
            DriverAction.waitSec(5);
            String s = DriverAction.getElementText(Locators.noTestCase);
            s = s.replace("\n", "");
            System.out.println("*****" + s);
            if (s.contains(alert1)) {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "Successful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "unSuccessful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^integrate git when wrong auth is passed (.+)$")
    public void wrongAuth(String alert1) {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.typeText(GITlink, "https://github.com/gem-pawandeep/GemEcoSystem-API-JV/tree/master", "Repo Link");
            DriverAction.waitSec(2);
            DriverAction.click(repoIsPvt, "Check if Repository is Private.");
            DriverAction.waitSec(2);
            if (DriverAction.getElement(repoIsPvt).isSelected()) {
                GemTestReporter.addTestStep("Check if Repository is Private (Checkbox isSelected?", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Check if Repository is Private (Checkbox isSelected?", "UnSuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.typeText(accessToken, "a");
            DriverAction.waitSec(2);
            DriverAction.click(integrateGIT, "Integrate GIT");
            DriverAction.waitSec(5);
            String s = DriverAction.getElementText(Locators.noTestCase);
            s = s.replace("\n", "");
            System.out.println("*****" + s);
            if (s.contains(alert1)) {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "Successful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "unSuccessful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate git when correct url is passed$")
    public void gitcorrectUrl() {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            String suite_id = DriverAction.getElementText(new_suiteIDs);
            DriverAction.waitSec(1);
            String suite_name = DriverAction.getElementText(Locators.new_suiteName);
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT4)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT4)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.typeText(GITlink, "https://github.com/gem-pawandeep/Demo-API-BDD", "Repo Link");
            DriverAction.waitSec(2);
            DriverAction.click(integrateGIT, "Integrate GIT");
            DriverAction.waitSec(30);
            String actual_text = DriverAction.getElementText(textName);
            System.out.println("Actual text: " + actual_text);
            if (actual_text.contains(suite_name)) {
                GemTestReporter.addTestStep("SUITE_NAME validation", "Successful<br>Expected Text: " + suite_name + "<br>Actual Text: " + suite_name, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("SUITE_NAME validation", "UnSuccessful<br>Expected Text: " + suite_name + "<br>Actual Text: " + suite_name, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (actual_text.contains(suite_id)) {
                GemTestReporter.addTestStep("SUITE_ID validation", "Successful<br>Expected Text: " + suite_id + "<br>Actual Text: " + suite_id, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("SUITE_ID validation", "UnSuccessful<br>Expected Text: " + suite_id + "<br>Actual Text: " + suite_id, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (DriverAction.getElement(testcaseToBeDel).isDisplayed()) {
                GemTestReporter.addTestStep("After integrating testcases are displayed or not?", "Successful testcases are displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("After integrating testcases are displayed or not?", "UnSuccessful testcases are displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(suite, "Suite");
            DriverAction.waitSec(3);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(3);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            js.executeScript("arguments[0].scrollIntoView();", DriverManager.getWebDriver().findElement(By.xpath("(//*[local-name()='svg' and @data-icon=\"github\"]/*[local-name()='path'])[1]")));
            String value = DriverAction.getCSSValue(GIT4, "color");
            String color = String.valueOf(Color.fromString(value).asHex());
            String green = "#54b435";
            if (color.equals(green)) {
                GemTestReporter.addTestStep("GIT integration is successful or not?", "Successful<br>Expected color: " + color + "<br>Actual color: " + green, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("GIT integration is successful or not?", "UnSuccessful<br>Expected color: " + color + "<br>Actual color: " + green, STATUS.FAIL, DriverAction.takeSnapShot());
            }
//            System.out.println("COLOR: " + color);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate git when correct private repo is passed$")
    public void privateRepo() {
        try {
            DriverAction.click(jarFilter, "Framework filter select");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter);
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter);
            DriverAction.waitSec(1);
            String suite_id = DriverAction.getElementText(suite_ID);
            DriverAction.waitSec(1);
            String suite_name = DriverAction.getElementText(Locators.suite_name);
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.typeText(GITlink, "https://github.com/gem-maulickbharadwaj/JewelUi-AutomationBDD.git", "Repo Link");
            DriverAction.waitSec(2);
            DriverAction.click(repoIsPvt);
            DriverAction.waitSec(2);
            if (DriverAction.getElement(repoIsPvt).isSelected()) {
                GemTestReporter.addTestStep("Check if Repository is Private (Checkbox isSelected)?", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Check if Repository is Private (Checkbox isSelected)?", "UnSuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.typeText(accessToken, "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", "Access Token");
            DriverAction.waitSec(2);
            DriverAction.click(integrateGIT);
            DriverAction.waitSec(20);
            String actual_text = DriverAction.getElementText(textName);
            if (actual_text.contains(suite_name)) {
                GemTestReporter.addTestStep("SUITE_NAME validation", "Successful<br>Expected Text: " + suite_name + "<br>Actual Text: " + suite_name, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("SUITE_NAME validation", "UnSuccessful<br>Expected Text: " + suite_name + "<br>Actual Text: " + suite_name, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (actual_text.contains(suite_id)) {
                GemTestReporter.addTestStep("SUITE_ID validation", "Successful<br>Expected Text: " + suite_id + "<br>Actual Text: " + suite_id, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("SUITE_ID validation", "UnSuccessful<br>Expected Text: " + suite_id + "<br>Actual Text: " + suite_id, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (DriverAction.getElement(testcaseToBeDel).isDisplayed()) {
                GemTestReporter.addTestStep("After integrating testcases are displayed or not?", "Successful testcases are displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("After integrating testcases are displayed or not?", "UnSuccessful testcases are displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(suite);
            DriverAction.waitSec(3);
            DriverAction.click(jarFilter);
            DriverAction.waitSec(1);
            DriverAction.click(selectFilter);
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect);
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter);
            DriverAction.waitSec(3);
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
            js.executeScript("arguments[0].scrollIntoView();", DriverManager.getWebDriver().findElement(By.xpath("(//*[local-name()='svg' and @data-icon=\"github\"]/*[local-name()='path'])[1]")));
            String value = DriverAction.getCSSValue(GIT, "color");
            String color = String.valueOf(Color.fromString(value).asHex());
            String green = "#54b435";
            if (color.equals(green)) {
                GemTestReporter.addTestStep("GIT integration is successful or not?", "Successful<br>Expected color: " + color + "<br>Actual color: " + green, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("GIT integration is successful or not?", "UnSuccessful<br>Expected color: " + color + "<br>Actual color: " + green, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check remove git button$")
    public void removeGIT() {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            String suite_id = DriverAction.getElementText(suite_ID);
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT2)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            if (DriverAction.getElement(removeGIT).isDisplayed() && DriverAction.getElement(removeGIT).isEnabled()) {
                GemTestReporter.addTestStep("Remove Git", "Remove git is Displayed and Enabled", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Remove Git", "Remove git is not Displayed and Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(removeGIT, "Remove Git");
            DriverAction.waitSec(5);
            String s = DriverAction.getElementText(gitUnlink);
            System.out.println("String: " + s);
            String suite_id1 = "Git Unlinked from Suite Id: " + suite_id;
            System.out.println("New Suite Id :" + suite_id1);
            STATUS status;
            if (s.equals(suite_id1)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Alert Remove Git Validation", "Expected Text: " + suite_id1 + "<br>Actual Text: " + suite_id1, STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check upload git button (.+)$")
    public void uploadGIT(String actual) {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT3)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            if (DriverAction.getElement(updateGITBtn).isDisplayed() && DriverAction.getElement(updateGITBtn).isEnabled()) {
                GemTestReporter.addTestStep("Update/Reload Git", "Update/Reload Git is Displayed and Enabled", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Update/Reload Git", "Update/Reload Git is not Displayed and Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(updateGITBtn, "Update/Reload Git");
            DriverAction.waitSec(15);
//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
//            wait.until(ExpectedConditions.presenceOfElementLocated(updateGIT));
//            if (wait.until(ExpectedConditions.presenceOfElementLocated(updateGIT))) {
//                String s = DriverAction.getElementText(updateGIT);
//                System.out.println("STRING: " + s);
//            }
//            String s = DriverAction.getElementText(updateGIT);
//            String loginStatus = new WebDriverWait(DriverManager.getWebDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(By.className("Toastify__toast-body"))).getText();
//            System.out.println("STRING: " + loginStatus);
//            STATUS status;
//            if (actual.equals(loginStatus)) {
//                status = STATUS.PASS;
//            } else {
//                status = STATUS.FAIL;
//            }
//            GemTestReporter.addTestStep("Alert Update/Reload Git Validation", "Expected Text: " + loginStatus + "<br>Actual Text: " + actual, status, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("Alert Update/Reload Git Validation", "Expected Text: Testcase(s) updated successfully" + "<br>Actual Text: Testcase(s) updated successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check reload jar button$")
    public void reloadJar() {
        try {
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            DriverAction.click(selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(jarSelect, "GEMJAR");
            DriverAction.waitSec(1);
            DriverAction.click(jarFilter, "Filter Framework");
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(reloadJAR, "Reload JAR");
            DriverAction.waitSec(5);
            String s = DriverAction.getElementText(reloadJAR_txt);
            System.out.println("STRING: " + s);
            GemTestReporter.addTestStep("Alert Reload JAR Validation", "Expected Text: jar file creation started successfully" + "<br>Actual Text: jar file creation started successfully", STATUS.PASS, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Create suite$")
    public void lambda_feature() {
        try {
            DriverAction.click(create_suite_button, "Create Suite");
            DriverAction.waitSec(2);
            DriverAction.click(gemjar_select, "GEMJAR");
            DriverAction.waitSec(2);
            DriverAction.click(select_pr_name, "Project Name");
            DriverAction.waitSec(1);
            DriverAction.click(GEMeCO_api, "GEMECO-API-PY");
            DriverAction.waitSec(1);
            String s = "SUITE_" + Math.random();
            DriverAction.typeText(input_suite_nm, s);
            DriverAction.waitSec(1);
            DriverAction.click(create_suite, "Create Suite Button");
            DriverAction.waitSec(4);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^execute suite on ui$")
    public void execute_suite_ui() {
        try {
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(GIT)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on GIT", "Successfully : Clicked on GIT", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.typeText(GITlink, "https://github.com/gem-pawandeep/Demo-API-BDD.git", "Repo Link");
            DriverAction.waitSec(2);
            DriverAction.click(integrateGIT, "Integrate GIT");
            DriverAction.waitSec(30);
            DriverAction.click(suite);
            DriverAction.waitSec(2);
            Actions actions2 = new Actions(DriverManager.getWebDriver());
            actions2.moveToElement(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            actions2.click(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Execute (playIcon)", "Successfully : Clicked on Execute (playIcon)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(modeScroll2, "Mode");
            DriverAction.waitSec(1);
            DriverAction.click(modeSelect2, "Optimize");
            DriverAction.waitSec(1);
            DriverAction.click(envScroll2, "Enviroment");
            DriverAction.waitSec(1);
            DriverAction.click(envSelect, "Beta");
            DriverAction.waitSec(1);
            DriverAction.click(url2, "Select Key");
            DriverAction.waitSec(1);
            DriverAction.click(urlSelect, "base_url");
            DriverAction.waitSec(1);
            DriverAction.typeText(valueInput, "https://apis-beta.gemecosystem.com");
            DriverAction.waitSec(1);
            DriverAction.click(addDataButton, "Add data");
            DriverAction.waitSec(1);
            DriverAction.click(addDataOpt2, "Select Key");
            DriverAction.waitSec(1);
            DriverAction.click(thread_count, "thread-count");
            DriverAction.waitSec(1);
            DriverAction.typeText(addDataInput, "3");
            DriverAction.waitSec(1);
            DriverAction.click(executeButton, "Execute");
            DriverAction.waitSec(12);
            DriverAction.click(linkClick, "Report Link");
            ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.waitSec(2);
            DriverAction.switchToWindow(newTb7.get(1));
            DriverAction.waitSec(7);
            WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 240));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(hundered));
            DriverAction.waitSec(15);
            GemTestReporter.addTestStep("Inside the link clicked", "Link clicked Successfully", STATUS.PASS, DriverAction.takeSnapShot());
            String s = DriverAction.getElementText(getTheStatus);
            System.out.println("STATUS GOES HERE::::" + s);
            if (s.equals("PASS")) {
                GemTestReporter.addTestStep("STATUS pass validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else if (s.equals("EXE")) {
                GemTestReporter.addTestStep("STATUS EXE", "Status Stucked On EXE", STATUS.FAIL, DriverAction.takeSnapShot());
                return;
            } else if (s.equals("FAIL")) {
                GemTestReporter.addTestStep("STATUS FAIL validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            }
            DriverAction.click(testcaseDetail);
            DriverAction.waitSec(3);
            if (DriverAction.getElement(demo_test).isDisplayed()) {
                GemTestReporter.addTestStep("Testcase Displayed", "Testcase is Displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Testcase Displayed", "Testcase is not Displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate the test case created appears on grid$")
    public void testcaseAppears() throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            DriverAction.waitSec(2);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(createTestcasess, "Create testcase");
            DriverAction.waitSec(2);
            String s = "TESTER_" + Math.random();
            DriverAction.typeText(testcaseNamess, s);
            DriverAction.waitSec(1);
            DriverAction.typeText(categorys, "TESTING");
            DriverAction.waitSec(1);
            DriverAction.click(typess, "Select type");
            DriverAction.waitSec(1);
            DriverAction.click(typeSelect, "PYPREST");
            DriverAction.waitSec(1);
            DriverAction.click(selectKeys, "Select key");
            DriverAction.waitSec(1);
            DriverAction.click(api_slct, "api");
            DriverAction.waitSec(1);
            DriverAction.typeText(valueInput, "https://apis-beta.gemecosystem.com");
            DriverAction.waitSec(1);
            DriverAction.click(createTestcaseButton, "Create testcase button");
            DriverAction.waitSec(12);
            DriverAction.doubleClick(testcaseIDd);
            DriverAction.waitSec(3);
            String s1 = DriverAction.getElementText(firsttestcaseName);
            System.out.println("S1 goes here::" + s1);
            DriverAction.waitSec(2);
            if (s1.equals(s)) {
                GemTestReporter.addTestStep("New test case created has been added into the grid or not?", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("New test case created has been added into the grid or not?", "UnSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + s1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate pencil option on the grid$")
    public void pencilOpt() throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            String name = DriverAction.getElementText(beforeTestCaseName);
            System.out.println("NAME:" + name);
            GemTestReporter.addTestStep("Test case name before modification", "Name:" + name, STATUS.INFO, DriverAction.takeSnapShot());
            Actions action = new Actions(DriverManager.getWebDriver());
            action.moveToElement(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            action.click(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on pencil option", "Successfully : Clicked on pencil option", STATUS.PASS, DriverAction.takeSnapShot());
            String s = "TEST_" + Math.random();
            DriverAction.clearText(testcaseNamess);
            DriverAction.waitSec(1);
            DriverAction.typeText(testcaseNamess, s);
            DriverAction.waitSec(2);
            DriverAction.click(saveOption, "Save button");
            DriverAction.waitSec(10);
            String name2 = DriverAction.getElementText(beforeTestCaseName);
            GemTestReporter.addTestStep("Test case name after modification", "Name:" + name2, STATUS.INFO, DriverAction.takeSnapShot());
            System.out.println(name2 + "--name2");
            if (name2.equalsIgnoreCase(s)) {
                GemTestReporter.addTestStep("Test case name has been modified or not?", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + name2, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test case name has been modified or not?", "UnSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + name2, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the alert when testcase name is left empty (.+)$")
    public void testcasenameAsEmpty(String actual) throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(createTestcasess, "Create test case");
            DriverAction.waitSec(2);
            DriverAction.click(createTestcaseButton, "Create testcase button");
            DriverAction.waitSec(3);
            String s = DriverAction.getElementText(testcaseNotCreated);
            if (s.equals(actual)) {
                GemTestReporter.addTestStep("Test case name not entered Alert validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + actual, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test case name not entered Alert validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + actual, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate pencil option on the grid category modify$")
    public void categoryMod() throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            String name = DriverAction.getElementText(beforeCategoryName);
            System.out.println("NAME:" + name);
            GemTestReporter.addTestStep("Category name before modification", "Name:" + name, STATUS.INFO, DriverAction.takeSnapShot());
            Actions action = new Actions(DriverManager.getWebDriver());
            action.moveToElement(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            action.click(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            DriverAction.waitSec(2);
            String s = "METHOD_" + Math.random();
            DriverAction.clearText(categoryNamess);
            DriverAction.waitSec(1);
            DriverAction.typeText(categoryNamess, s);
            DriverAction.waitSec(2);
            DriverAction.click(saveOption, "Save button");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
            String Actual_Text = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
            DriverAction.waitSec(10);
            String name2 = DriverAction.getElementText(beforeCategoryName);
            GemTestReporter.addTestStep("Category name after modification", "Name:" + name2, STATUS.INFO, DriverAction.takeSnapShot());
            System.out.println(name2 + "--name2");
            String Expected_text = "TestCase updated Successfully";

            if (Expected_text.equals(Actual_Text)) {
                GemTestReporter.addTestStep("Testcase updated successfully alert validation", "Successful<br>Expected Text: " + Expected_text + "<br>Actual Text: " + Actual_Text, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Testcase updated successfully alert validation", "UnSuccessful<br>Expected Text: " + Expected_text + "<br>Actual Text: " + Actual_Text, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (name2.equalsIgnoreCase(s)) {
                GemTestReporter.addTestStep("Category name has been modified or not?", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + name2, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Category name has been modified or not?", "UnSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + name2, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate pencil option on the grid type modify$")
    public void typeModify() throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            String name = DriverAction.getElementText(typeName);
            System.out.println("NAME:" + name);
            GemTestReporter.addTestStep("Type name before modification", "Name:" + name, STATUS.INFO, DriverAction.takeSnapShot());
            Actions action = new Actions(DriverManager.getWebDriver());
            action.moveToElement(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            action.click(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            DriverAction.waitSec(2);
            DriverAction.click(modeScroll2, "Type Select");
            DriverAction.waitSec(2);
            String s = DriverAction.getElementText(dvSelect);
            DriverAction.click(dvSelect, "DV");
            DriverAction.waitSec(1);
            DriverAction.click(saveOption, "Save button");
            DriverAction.waitSec(5);
            String name2 = DriverAction.getElementText(typeName);
            GemTestReporter.addTestStep("Type name after modification", "Name:" + name2, STATUS.INFO, DriverAction.takeSnapShot());
            System.out.println(name2 + "--name2");
            if (name2.equalsIgnoreCase(s)) {
                GemTestReporter.addTestStep("Type name has been modified or not?", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + name2, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Type name has been modified or not?", "UnSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + name2, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate parameters has been added or not$")
    public void paramasRemove() throws Exception {
        try {
            DriverAction.click(suite_id_btn);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            Actions action = new Actions(DriverManager.getWebDriver());
            action.moveToElement(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            action.click(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Pencil option", "Successfully : Clicked on Pencil option", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(addDataButton, "Add data button");
            DriverAction.waitSec(2);
            DriverAction.click(selectKey, "Select key");
            DriverAction.waitSec(2);
            String dbConfig = DriverAction.getElementText(dbConfigPath);
            DriverAction.click(dbConfigPath, "db_config_path");
            DriverAction.waitSec(2);
            DriverAction.typeText(valueInpt, "https://apis-beta.gemecosystem.com");
            DriverAction.waitSec(2);
            DriverAction.click(saveOption, "Save button");
            DriverAction.waitSec(6);
            DriverAction.click(rightArrow, "right Arrow");
            DriverAction.waitSec(2);
            DriverAction.pageScroll(200, 200);
            DriverAction.waitSec(2);
            List<String> list = DriverAction.getElementsText(listTestcaseDetail);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("LISt--" + list.get(i));
                if (list.get(i).contains(dbConfig)) {
                    GemTestReporter.addTestStep("Add Data Select key exists in testcase details or not?", "Select key added: " + dbConfig, STATUS.PASS, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate edit when all the headers are removed (.+)$")
    public void oneHeader(String actual) throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            Actions action = new Actions(DriverManager.getWebDriver());
            action.moveToElement(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            action.click(DriverManager.getWebDriver().findElement(pencil)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Pencil option", "Successfully : Clicked on Pencil option", STATUS.PASS, DriverAction.takeSnapShot());
            List<WebElement> cross = DriverAction.getElements(editTestcaseCross);
            System.out.println("SIZE: " + cross.size());
            for (int i = 0; i < cross.size(); i++) {
                DriverAction.click(editTestcaseCross, "Cross option");
                DriverAction.waitSec(1);
            }
            DriverAction.click(saveOption, "Save button");
            DriverAction.waitSec(2);
//            String s = DriverAction.getElementText(testcaseUpdated);
//            if (s.equals(actual)) {
//                GemTestReporter.addTestStep("Testcase updated Successfully Alert validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + actual, STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Testcase updated Successfully Alert validation", "UnSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + actual, STATUS.FAIL, DriverAction.takeSnapShot());
//            }
            DriverAction.click(rightArrow, "right Arrow");
            DriverAction.waitSec(2);
            DriverAction.pageScroll(200, 200);
            DriverAction.waitSec(2);
            List<String> list = DriverAction.getElementsText(listTestcaseDetail);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("LISt--" + list.get(i));
                if (list.get(i).contains("run_flag")) {
                    GemTestReporter.addTestStep("Only run_flag key exists in testcase details or not?", "run_flag exists: run_flag", STATUS.PASS, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Create a new suite by clicking on create suite button and validate if it's created or not (.*)$")
    public void create_SUITE(String allField) throws Exception {
        try {
            DriverAction.click(Locators.create_suite_button, "Create Suite");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_gempyp, "GEMPYP");
            DriverAction.waitSec(1);
            DriverAction.click(back_gem_opt, "Back");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_gempyp, "GEMPYP");
            DriverAction.waitSec(1);
            DriverAction.click(select_pr_name, "Project Name");
            DriverAction.waitSec(4);
            DriverAction.click(GEMeCO_api, "GEMECO-API-PY");
            DriverAction.waitSec(2);
            DriverAction.typeText(input_suite_nm, "test_yashita");
            DriverAction.waitSec(1);
            DriverAction.click(create_suite, "Create Suite Button");
            DriverAction.waitSec(4);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin1));
            String allf = DriverAction.getElement(Alert_admin1).getAttribute("innerHTML");
            if (allf.equals(allField)) {
                GemTestReporter.addTestStep("Suite name already exists (no caps lock case) Alert validation", "Successful<br>Expected Text: " + allField + "<br>Actual Text: " + allf, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Suite name already exists (no caps lock case) Alert validation", "Successful<br>Expected Text: " + allField + "<br>Actual Text: " + allf, STATUS.FAIL, DriverAction.takeSnapShot());
            }
//            DriverAction.click(back_gem_opt);
//            DriverAction.waitSec(1);
//            DriverAction.click(Locators.select_gempyp);
//            DriverAction.waitSec(1);
//            DriverAction.click(select_pr_name, "Select Project Name", "Clicked on Select Project Name");
//            DriverAction.waitSec(1);
//            DriverAction.click(GEMeCO_api);
//            DriverAction.waitSec(1);
//            DriverAction.typeText(input_suite_nm, "TEST_YASHITA");
//            DriverAction.waitSec(1);
//            DriverAction.click(create_suite);
//            DriverAction.waitSec(3);
//            String allfi = DriverAction.getElementText(Locators.allFields);
//            if (allfi.equals(allField)) {
//                GemTestReporter.addTestStep("Suite name already exists (caps lock on case) Alert validation", "Successful<br>Expected Text: " + allf + "<br>Actual Text: " + allField, STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Suite name already exists (caps lock on case) Alert validation", "Successful<br>Expected Text: " + allf + "<br>Actual Text: " + allField, STATUS.FAIL, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Create a neww suite by clicking on create suite button and validate if it's created or not (.+)$")
    public void create_SUITE_NEG(String allField) throws Exception {
        try {
            DriverAction.click(Locators.create_suite_button, "Create Suite");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_gempyp, "GEMPYP");
            DriverAction.waitSec(1);
            DriverAction.click(select_pr_name, "Project Name");
            DriverAction.waitSec(1);
            DriverAction.click(GEMeCO_api, "GEMECO-API-PY");
            DriverAction.waitSec(1);
            DriverAction.typeText(input_suite_nm, "TEST_YASHITA");
            DriverAction.waitSec(1);
            DriverAction.click(create_suite, "Create Suite Button");
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin1));
            String allfi = DriverAction.getElement(Alert_admin1).getAttribute("innerHTML");
            if (allfi.equals(allField)) {
                GemTestReporter.addTestStep("Suite name already exists (caps lock on case) Alert validation", "Successful<br>Expected Text: " + allField + "<br>Actual Text: " + allfi, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Suite name already exists (caps lock on case) Alert validation", "Successful<br>Expected Text: " + allField + "<br>Actual Text: " + allfi, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Create a new suite when name is unique (.+)$")
    public void unique_suite(String unique) throws Exception {
        try {
            DriverAction.click(Locators.create_suite_button, "Create Suite");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_gempyp, "GEMPYP");
            DriverAction.waitSec(1);
            DriverAction.click(select_pr_name, "Project Name");
            DriverAction.waitSec(1);
            DriverAction.click(GEMeCO_api, "GEMECO-API-PY");
            DriverAction.waitSec(1);
            String s = "TESTER_" + Math.random();
            DriverAction.typeText(input_suite_nm, s);
            DriverAction.waitSec(1);
            DriverAction.click(create_suite, "Create Suite Button");
//            String allfi = DriverAction.getElementText(suite_created);
            DriverAction.waitSec(15);
////            String allfi = DriverAction.getElementText(suite_created);
//            if (allfi.equals(unique)) {
//                GemTestReporter.addTestStep("Suite name is unique Alert validation", "Successful<br>Expected Text: " + allfi + "<br>Actual Text: " + unique, STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Suite name is unique Alert validation", "Successful<br>Expected Text: " + allfi + "<br>Actual Text: " + unique, STATUS.FAIL, DriverAction.takeSnapShot());
//            }
            String s1 = DriverAction.getElementText(new_uniq);
            if (s1.equals(s)) {
                GemTestReporter.addTestStep("New Suite name is added or not? validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("New Suite name is added or not? validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate the record in suite tab$")
    public void sortingSuitePill() throws Exception {
        try {
            DriverAction.pageScroll(2000, 2000);
            //  List<WebElement> buttons = DriverAction.getElements(Locators.buttons);
            // System.out.println(buttons.size() + " Number of pages");
            ArrayList<String> list1 = new ArrayList<>();
            int i = 1;
            boolean nextbtn = true;
            while (nextbtn) {
                DriverAction.pageScroll(2000, 2000);
//               DriverAction.click(Locators.nextPageBtn);
                list1.addAll(DriverAction.getElementsText(Locators.suiteIDList));
                DriverAction.waitSec(1);
                i++;
                System.out.println("Iteration " + i);
                if (DriverManager.getWebDriver().findElement(Locators.nextPageBtn).isEnabled()) {
                    DriverAction.click(Locators.nextPageBtn, "Next Page");
                } else {
                    nextbtn = false;
                }
            }
            System.out.println("TOTAL EXPECTED: " + list1.size());
            String s = DriverAction.getElementText(Locators.total_COunt);
            System.out.println("STRING::::" + s);
            String s2 = s.split(" ")[1];
            int total = Integer.parseInt(s2);
            System.out.println("TOTAL:" + total);
            if (list1.size() == total) {
                GemTestReporter.addTestStep("Total Records Validation", "Successful<br>Expected Text: " + total + "<br>Actual Text: " + list1.size(), STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Total Records Validation", "Successful<br>Expected Text: " + total + "<br>Actual Text: " + list1.size(), STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Validate the record in testcase tab$")
    public void validateRecords() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            DriverAction.waitSec(2);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            DriverAction.waitSec(8);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.pageScroll(2000, 2000);
            //  List<WebElement> buttons = DriverAction.getElements(Locators.buttons);
            // System.out.println(buttons.size() + " Number of pages");
            ArrayList<String> list1 = new ArrayList<>();
            int i = 1;
            boolean nextbtn = true;
            while (nextbtn) {
                DriverAction.pageScroll(2000, 2000);
//               DriverAction.click(Locators.nextPageBtn);
                list1.addAll(DriverAction.getElementsText(Locators.testcaseIDList));
                DriverAction.waitSec(1);
                i++;
                System.out.println("Iteration " + i);
                if (DriverManager.getWebDriver().findElement(Locators.nextPageBtn2).isEnabled()) {
                    DriverAction.click(Locators.nextPageBtn2, "Next Time");
                } else {
                    nextbtn = false;
                }
            }
            System.out.println("TOTAL EXPECTED: " + list1.size());
            String s = DriverAction.getElementText(Locators.getTotal_COunt2);
            System.out.println("STRING::::" + s);
            String s2 = s.split(" ")[1];
            int total = Integer.parseInt(s2);
            System.out.println("TOTAL:" + total);
            if (list1.size() == total) {
                GemTestReporter.addTestStep("Total Records Validation", "Successful<br>Expected Text: " + total + "<br>Actual Text: " + list1.size(), STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Total Records Validation", "Successful<br>Expected Text: " + total + "<br>Actual Text: " + list1.size(), STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on play icon to execute the suite (.+)$")
    public void execute_suite(String Actual) throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn);
            DriverAction.waitSec(1);
            String s = DriverAction.getElementText(testcase_COUNT);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            DriverAction.waitSec(4);
            String s2 = DriverAction.getElementText(elementDivtext);
            s2 = s2.replace("\n", "");
            System.out.println("STRING: " + s2);
            if (s2.contains(s)) {
                GemTestReporter.addTestStep("TEST case count validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("TEST case count validation", "unSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(playiconCross, "Cross option");
            DriverAction.waitSec(1);
            Actions actionss = new Actions(DriverManager.getWebDriver());
            actionss.moveToElement(DriverManager.getWebDriver().findElement(playicon2)).build().perform();
            actionss.click(DriverManager.getWebDriver().findElement(playicon2)).build().perform();
            DriverAction.waitSec(6);
            String zero = DriverAction.getElementText(zeroTestCase);
            if (zero.equals(Actual)) {
                GemTestReporter.addTestStep("TEST case 0 count Alert validation", "Successful<br>Expected Text: " + Actual + "<br>Actual Text: " + Actual, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("TEST case 0 count Alert validation", "Successful<br>Expected Text: " + Actual + "<br>Actual Text: " + Actual, STATUS.PASS, DriverAction.takeSnapShot());
            }
            DriverAction.click(playiconCross, "Cross option");
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on link and validate values in that$")
    public void linkClick() throws Exception {
        try {
            String url1 = ProjectConfigData.getProperty("launchUrl");
            if (url1.contains("beta")) {
                DriverAction.click(linkClick, "Report Link");
                ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(1));
                DriverAction.waitSec(20);
                GemTestReporter.addTestStep("Inside the link clicked", "Link clicked Successfully", STATUS.PASS, DriverAction.takeSnapShot());
                String url = DriverAction.getCurrentURL();
                STATUS status;
                if (url.contains("https://jewel-beta.gemecosystem.com/#/autolytics/execution-report")) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Execution Screen Url Validation", "Successful<br>Expected URL: " + url + "<br>Actual URL: " + url, status);
            } else {
                DriverAction.click(linkClick, "Report Link");
                ArrayList<String> newTb7 = new ArrayList<>(DriverAction.getWindowHandles());
                DriverAction.waitSec(2);
                DriverAction.switchToWindow(newTb7.get(1));
                DriverAction.waitSec(20);
                GemTestReporter.addTestStep("Inside the link clicked", "Link clicked Successfully", STATUS.PASS, DriverAction.takeSnapShot());
                String url = DriverAction.getCurrentURL();
                STATUS status;
                if (url.contains("https://jewel.gemecosystem.com/#/autolytics/execution-report")) {
                    status = STATUS.PASS;
                } else {
                    status = STATUS.FAIL;
                }
                GemTestReporter.addTestStep("Execution Screen Url Validation", "Successful<br>Expected URL: " + url + "<br>Actual URL: " + url, status);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^enter values in play icon$")
    public void enterValues() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Execute (playIcon)", "Successfully : Clicked on Execute (playIcon)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(modeScroll, "Mode");
            DriverAction.waitSec(1);
            DriverAction.click(modeSelect, "Sequence");
            DriverAction.waitSec(1);
            DriverAction.click(envScroll, "Enviroment");
            DriverAction.waitSec(1);
            DriverAction.click(envSelect, "Beta");
            DriverAction.waitSec(1);
            DriverAction.click(url, "Select Key");
            DriverAction.waitSec(1);
            DriverAction.click(urlSelect, "base_url");
            DriverAction.waitSec(1);
            DriverAction.typeText(valueInput, "https://apis-beta.gemecosystem.com");
            DriverAction.waitSec(1);
            DriverAction.click(addDataButton, "Add data");
            DriverAction.waitSec(1);
            DriverAction.click(addDataOpt, "Select Key");
            DriverAction.waitSec(1);
            DriverAction.click(urlSelect, "base_url");
            DriverAction.waitSec(1);
            DriverAction.typeText(addDataInput, "abc");
            DriverAction.waitSec(1);
            DriverAction.click(addDataCross, "Add data cross button");
            DriverAction.waitSec(1);
            DriverAction.click(executeButton, "Execute");
            DriverAction.waitSec(5);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("^Validate button of testcase tab$")
    public void buttons_testcaseTab() throws Exception {
        try {
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            if (DriverAction.getElement(create_testcase_button).isDisplayed() && DriverAction.getElement(create_testcase_button).isEnabled()) {
                GemTestReporter.addTestStep("Create Testcase Button", "Create test case button is Dispplayed and Enabled", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Create Testcase Button", "Create test case button is Dispplayed and Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (DriverAction.getElement(importFromLib).isDisplayed() && DriverAction.getElement(importFromLib).isEnabled()) {
                GemTestReporter.addTestStep("Import From Library Button", "Import From Library button is Displayed and Enabled", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Import From Library Button", "Import From Library button is Displayed and Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^click on test case icon and verify tab pill is displayed and verfy test case count$")
    public void isTestDisplayed() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn);
            String s = DriverAction.getElementText(Locators.getTestcaseCountAlter);
            String suite_id = DriverAction.getElementText(Locators.suite_ID);
            String suite_name = DriverAction.getElementText(Locators.suite_name);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            DriverAction.waitSec(4);
            if (DriverAction.getElement(Locators.testcase).isDisplayed()) {
                GemTestReporter.addTestStep("Test cases", "Testcases is displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test cases", "Testcases is not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
//            List<String> list = DriverAction.getElementsText(Locators.tescaseSize);
//            String s2 = String.valueOf(list.size());
            STATUS status;
//            if (s2.equals(s)) {
//                status = STATUS.PASS;
//            } else {
//                status = STATUS.FAIL;
//            }
//            GemTestReporter.addTestStep("Test tool screen test case validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s2, status, DriverAction.takeSnapShot());
            String actual_text = DriverAction.getElementText(Locators.textName);
            if (actual_text.contains(suite_id) && (actual_text.contains(suite_name))) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("SUITE_ID validation", "Successful<br>Expected Text: " + suite_id + "<br>Actual Text: " + actual_text, status, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("SUITE_NAME validation", "Successful<br>Expected Text: " + suite_name + "<br>Actual Text: " + actual_text, status, DriverAction.takeSnapShot());
            DriverAction.pageScroll(1000, 1000);
            DriverAction.waitSec(2);
            List<String> list = DriverAction.getElementsText(Locators.tescaseSize);
            String s2 = String.valueOf(list.size());
            if (s2.equals(s)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Test tool screen test case validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s2, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^test case pill isDisplayed$")
    public void testcasePillisDIs() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
//            String s = DriverAction.getElementText(Locators.getTestcaseCountAlter);
//            String suite_id = DriverAction.getElementText(Locators.suite_ID);
//            String suite_name = DriverAction.getElementText(Locators.suite_name);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            if (DriverAction.getElement(Locators.testcase).isDisplayed()) {
                GemTestReporter.addTestStep("Test cases", "Testcases is displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test cases", "Testcases is not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate suite id in testcase pill$")
    public void suiteNamechck() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            String suite_id = DriverAction.getElementText(Locators.suite_ID);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            String actual_text = DriverAction.getElementText(Locators.textName);
            if (actual_text.contains(suite_id)) {
                GemTestReporter.addTestStep("SUITE_ID validation", "Successful<br>Expected Text: " + suite_id + "<br>Actual Text: " + suite_id, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("SUITE_ID validation", "UnSuccessful<br>Expected Text: " + suite_id + "<br>Actual Text: " + suite_id, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate suite name in testcase pill$")
    public void validateSuiteName() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            String suite_name = DriverAction.getElementText(Locators.suite_name);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            String actual_text = DriverAction.getElementText(Locators.textName);
            if (actual_text.contains(suite_name)) {
                GemTestReporter.addTestStep("SUITE_NAME validation", "Successful<br>Expected Text: " + suite_name + "<br>Actual Text: " + suite_name, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("SUITE_NAME validation", "UnSuccessful<br>Expected Text: " + suite_name + "<br>Actual Text: " + suite_name, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^total test case validation$")
    public void testcasesTotalV() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            String s = DriverAction.getElementText(Locators.getTestcaseCountAlter);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.pageScroll(1000, 1000);
            DriverAction.waitSec(2);
            List<String> list = DriverAction.getElementsText(Locators.tescaseSize);
            String s2 = String.valueOf(list.size());
            if (s2.equals(s)) {
                GemTestReporter.addTestStep("Test case pill test case validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s2, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test case pill test case validation", "UnSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + s2, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate test case count$")
    public void validateCount() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            DriverAction.waitSec(1);
            String s = DriverAction.getElementText(testcase_COUNT);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(playicon)).build().perform();
            DriverAction.waitSec(10);
            GemTestReporter.addTestStep("Click on Execute (playIcon)", "Successfully : Clicked on Execute (playIcon)", STATUS.PASS, DriverAction.takeSnapShot());
            String s2 = DriverAction.getElementText(elementDivtext);
            s2 = s2.replace("\n", "");
            System.out.println("STRING: " + s2);
            if (s2.contains(s)) {
                GemTestReporter.addTestStep("TEST case count validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("TEST case count validation", "unSuccessful<br>Expected Text: " + s + "<br>Actual Text: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(playiconCross, "Cross option");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate test case count is zero (.+)$")
    public void validateTCZero(String Actual) throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            DriverAction.waitSec(1);
            Actions actionss = new Actions(DriverManager.getWebDriver());
            actionss.moveToElement(DriverManager.getWebDriver().findElement(playicon2)).build().perform();
            actionss.click(DriverManager.getWebDriver().findElement(playicon2)).build().perform();
            DriverAction.waitSec(6);
            GemTestReporter.addTestStep("Click on Execute (playIcon)", "Successfully : Clicked on Execute (playIcon)", STATUS.PASS, DriverAction.takeSnapShot());
            String zero = DriverAction.getElementText(zeroTestCase);
            if (zero.equals(Actual)) {
                GemTestReporter.addTestStep("TEST case 0 count Alert validation", "Successful<br>Expected Text: " + Actual + "<br>Actual Text: " + Actual, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("TEST case 0 count Alert validation", "UnSuccessful<br>Expected Text: " + Actual + "<br>Actual Text: " + Actual, STATUS.PASS, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Check if create suite and upload button is present and clickable$")
    public void createSuiteBTnn() throws Exception {
        try {
            if (DriverAction.getElement(create_suite_button).isDisplayed()) {
                GemTestReporter.addTestStep("Create Suite Button", "Create Suite button is present and is displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Create Suite Button", "Create Suite button is present and is displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(create_suite_button, "Create Suite Button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.globalCross, "Cross icon");
            DriverAction.waitSec(2);
            if (DriverAction.getElement(upload_button).isDisplayed()) {
                GemTestReporter.addTestStep("Upload Button", "Upload button is present and is displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Create Suite Button", "Upload button is present and is displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(upload_button, "Upload button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.globalCross, "Cross icon");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check create suite button is present and clickable$")
    public void create_STBTN() throws Exception {
        try {
            if (DriverAction.getElement(create_suite_button).isDisplayed()) {
                GemTestReporter.addTestStep("Create Suite Button", "Create Suite button is present and is displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Create Suite Button", "Create Suite button is present and is displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(create_suite_button, "Create Suite Button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.globalCross, "Cross icon");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check upload button is present and clickable$")
    public void uploadBtn() throws Exception {
        try {
            if (DriverAction.getElement(upload_button).isDisplayed()) {
                GemTestReporter.addTestStep("Upload Button", "Upload button is present and is displayed", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Create Suite Button", "Upload button is present and is displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(upload_button, "Upload button");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.globalCross, "Cross icon");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Choose any file from system and check if it's uploaded$")
    public void fileUpload() throws Exception {
        try {
            DriverAction.click(upload_button);
            DriverAction.waitSec(2);
//            DriverAction.fileUpload(Locators.browseBtn, "C:\\Users\\ma.bharadwaj\\Downloads\\Dummy_API.xml");
//            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Check the working of filters in suite and testcase table$")
    public void suiteAndTestcase() {
        try {
            DriverAction.click(suite_id_btn);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.filterBroken, "Testcase count Filter click", "Testcase count Filter has been clicked!!");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_Zero, "Testcase count Filter select", "Testcase count Filter has been selected!!");
            DriverAction.waitSec(2);
            String s = DriverAction.getElementText(Locators.zeroText);
            System.out.println(s);
            DriverAction.click(Locators.closeFilter);
            DriverAction.waitSec(3);
            int count3 = 0;
            List<String> list3 = DriverAction.getElementsText(zero_list);
            for (int i = 0; i < list3.size(); i++) {
                if (s.equalsIgnoreCase(list3.get(i))) {
                    count3++;
                }
            }
            if (count3 == list3.size()) {
                GemTestReporter.addTestStep("Suite broken index filter validation", "Expected:" + s, STATUS.INFO);
                GemTestReporter.addTestStep("Suite broken index filter validation", "Text we got" + list3.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Suite broken index filter validation", "Expected:" + s, STATUS.INFO);
                GemTestReporter.addTestStep("Suite broken index filter validation", "Text we got" + list3.get(0), STATUS.FAIL);
            }
            DriverAction.click(selectFilter100);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_Zero);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.filterBroken);
            DriverAction.waitSec(1);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            DriverAction.waitSec(4);
            DriverAction.click(Locators.testcaseID, "testCaseID of testcases filter click", "testCase filter clicked!!");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter);
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_Zero, "Select testcaseID click", "Select testcaseID has been selected!!");
            DriverAction.waitSec(2);
            String s4 = DriverAction.getElementText(Locators.testCaseIdText);
            System.out.println(s);
            DriverAction.click(Locators.closeFilter);
            DriverAction.waitSec(3);
            int count = 0;
            List<String> list4 = DriverAction.getElementsText(testCaseIdTexts);
            for (int i = 0; i < list4.size(); i++) {
                if (s4.equalsIgnoreCase(list4.get(i))) {
                    count++;
                }
            }
            if (count == list4.size()) {
                GemTestReporter.addTestStep("Testcase testcaseID filter validation", "Expected:" + s4, STATUS.INFO);
                GemTestReporter.addTestStep("Testcase testcaseID filter validation", "Text we got" + list4.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Suite broken index filter validation", "Expected:" + s4, STATUS.INFO);
                GemTestReporter.addTestStep("Suite broken index filter validation", "Text we got" + list4.get(0), STATUS.FAIL);
            }
            DriverAction.click(Locators.suite);
            DriverAction.waitSec(2);
            DriverAction.doubleClick(suite_id_btn);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check filter for suite pill$")
    public void chckForSuite() {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            DriverAction.waitSec(2);
            DriverAction.click(Locators.filterBroken, "Filter Test case count");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_Zero, "Zero");
            DriverAction.waitSec(2);
            String s = DriverAction.getElementText(Locators.zeroText);
            System.out.println(s);
            DriverAction.click(Locators.closeFilter, "Filter Close");
            DriverAction.waitSec(3);
            int count3 = 0;
            List<String> list3 = DriverAction.getElementsText(zero_list);
            for (int i = 0; i < list3.size(); i++) {
                if (s.equalsIgnoreCase(list3.get(i))) {
                    count3++;
                }
            }
            if (count3 == list3.size()) {
                GemTestReporter.addTestStep("Suite broken index filter validation", "Expected: " + s, STATUS.INFO);
                GemTestReporter.addTestStep("Suite broken index filter validation", "Text we got: " + list3.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Suite broken index filter validation", "Expected: " + s, STATUS.INFO);
                GemTestReporter.addTestStep("Suite broken index filter validation", "Text we got: " + list3.get(0), STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^check filter for testcase pill$")
    public void checkFTestcase() throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(Locators.testcaseID, "Filter Testcase Id");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.selectFilter, "Select Option(s)");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.select_Zero, "Zero");
            DriverAction.waitSec(2);
            String s4 = DriverAction.getElementText(Locators.testCaseIdText);
            DriverAction.click(Locators.closeFilter, "Filter Close");
            DriverAction.waitSec(3);
            int count = 0;
            List<String> list4 = DriverAction.getElementsText(testCaseIdTexts);
            for (int i = 0; i < list4.size(); i++) {
                if (s4.equalsIgnoreCase(list4.get(i))) {
                    count++;
                }
            }
            if (count == list4.size()) {
                GemTestReporter.addTestStep("Testcase testcaseID filter validation", "Expected: " + s4, STATUS.INFO);
                GemTestReporter.addTestStep("Testcase testcaseID filter validation", "Text we got: " + list4.get(0), STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Testcase testcaseID filter validation", "Expected: " + s4, STATUS.INFO);
                GemTestReporter.addTestStep("Testcase testcaseID filter validation", "Text we got: " + list4.get(0), STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Check the alert in testcase tab (.+)$")
    public void alert_check_testcase(String alert1) throws Exception {
        try {
            DriverAction.click(suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(testCasePlus2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            String s = DriverAction.getElementText(Locators.noTestCase);
            s = s.replace("\n", "");
            System.out.println("*****" + s);
            if (s.contains(alert1)) {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "Successful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("No TestCase(s) Found Click on Create TestCase to add new TestCase(s) alert Validation", "unSuccessful<br>Expected Text: " + alert1 + "<br>Actual Text: " + s, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }


    @Then("^Check the sorting persists in suite and testcase table$")
    public void testFilter() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn);
            GemTestReporter.addTestStep("Sorting validation", "Check sorting has been in suite pill tab", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(3);
            List<String> list = DriverAction.getElementsText(Locators.suiteIDList);
            List<String> list2 = DriverAction.getElementsText(Locators.suiteIDList);
            Collections.reverse(list2);
            STATUS status;
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                for (int j = list2.size() - 1; j >= 0; j--) {
                    if (list.get(i).equals(list2.get(j))) {
                        count++;
                    }
                }
            }
            if (count == list.size()) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Sorting validation on pill tab", "Successful<br>Expected Text: " + count + "<br>Actual Text: " + list.size(), status);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus2)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Sorting validation", "Check sorting has been in test case pill tab", STATUS.INFO, DriverAction.takeSnapShot());
            List<String> list3 = DriverAction.getElementsText(Locators.testCaseIdTexts);
            List<String> list4 = DriverAction.getElementsText(Locators.testCaseIdTexts);
            Collections.reverse(list4);
            int count1 = 0;
            for (int i = 0; i < list3.size(); i++) {
                for (int j = list4.size() - 1; j >= 0; j--) {
                    if (list3.get(i).equals(list4.get(j))) {
                        count1++;
                    }
                }
            }
            if (count1 == list3.size()) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Sorting validation on testcase pill", "Successful<br>Expected Text: " + count1 + "<br>Actual Text: " + list3.size(), status);
            DriverAction.click(Locators.suite);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Check sorting in suite pill$")
    public void sortingChk() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite ID");
            GemTestReporter.addTestStep("Sorting validation", "Check sorting has been in suite pill tab", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.waitSec(3);
            List<String> list = DriverAction.getElementsText(Locators.suiteIDList);
            List<String> list2 = DriverAction.getElementsText(Locators.suiteIDList);
            Collections.reverse(list2);
            STATUS status;
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                for (int j = list2.size() - 1; j >= 0; j--) {
                    if (list.get(i).equals(list2.get(j))) {
                        count++;
                    }
                }
            }
            if (count == list.size()) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Sorting validation on pill tab", "Successful<br>Expected Text: " + count + "<br>Actual Text: " + list.size(), status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^Check sorting in test case pill$")
    public void sortingChkTest() throws Exception {
        try {
            DriverAction.click(Locators.suite_id_btn, "Suite Id");
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(Locators.testCasePlus3)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Testcase(s)", "Successfully : Clicked on Testcase(s)", STATUS.PASS, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("Sorting validation", "Check sorting has been in test case pill tab", STATUS.INFO, DriverAction.takeSnapShot());
            List<String> list3 = DriverAction.getElementsText(Locators.testCaseIdTexts);
            List<String> list4 = DriverAction.getElementsText(Locators.testCaseIdTexts);
            Collections.reverse(list4);
            STATUS status;
            int count1 = 0;
            for (int i = 0; i < list3.size(); i++) {
                for (int j = list4.size() - 1; j >= 0; j--) {
                    if (list3.get(i).equals(list4.get(j))) {
                        count1++;
                    }
                }
            }
            if (count1 == list3.size()) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Sorting validation on testcase pill", "Successful<br>Expected Text: " + count1 + "<br>Actual Text: " + list3.size(), status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @When("^User (.*) is added to project$")
    public void addUser_UserAccess(String username) throws Exception {
        String url = ProjectConfigData.getProperty("launchUrl");
        if (url.contains("beta")) {
            project_created_onGrid();
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_access2)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(edit_access2)).build().perform();
            DriverAction.waitSec(4);
            DriverAction.click(addUser_project, "Add Users tab");
            DriverAction.click(adduser_select, "Add user dropdown");
            DriverAction.typeText(addUser_search, "Shubham Kumar");
            DriverAction.click(add_user, "");
            DriverAction.click(select_role_empty, "Role dropdown");
            DriverAction.click(select_role, "Select Admin");
            DriverAction.click(adduser_btn, "Add user button");
            DriverAction.waitSec(4);
        } else {
            project_created_onGrid();
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_access2)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(edit_access2)).build().perform();
            DriverAction.waitSec(4);
            DriverAction.click(addUser_project, "Add Users tab");
            DriverAction.click(adduser_select, "Add user dropdown");
            DriverAction.typeText(addUser_search, username);
            DriverAction.click(add_user, "Select user");
            DriverAction.click(select_role_empty, "Role dropdown");
            DriverAction.click(select_role, "Select Admin");
            DriverAction.click(adduser_btn, "Add user button");
            DriverAction.waitSec(4);
        }
    }

    @Then("^Validate user (.*) is added$")
    public void validate_useradded(String username) {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                boolean flag = false;
                DriverAction.click(changerole_tab, "Change Role Tab");
                DriverAction.waitSec(3);
                List<String> users = DriverAction.getElementsText(users_list);
                System.out.println(users);
                for (int i = 0; i < users.size(); i++) {
                    System.out.println(username);
                    System.out.println(users.get(i));
                    if (users.get(i).contains("Shubham Kumar")) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    GemTestReporter.addTestStep("Validate user is added", "User added successfully", STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Validate user is added", "Failed to add user but alert for user added appeared", STATUS.FAIL);
                }
            } else {
                boolean flag = false;
                DriverAction.click(changerole_tab, "Change Role Tab");
                DriverAction.waitSec(2);
                List<String> users = DriverAction.getElementsText(users_list);
                System.out.println(users);
                for (int i = 0; i < users.size(); i++) {
                    System.out.println(username);
                    System.out.println(users.get(i));
                    if (users.get(i).contains(username)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    GemTestReporter.addTestStep("Validate user is added", "User added successfully", STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Validate user is added", "Failed to add user but alert for user added appeared", STATUS.FAIL);
                }
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate user is added", "Failed to add user", STATUS.FAIL);
        }
    }

    @Then("^Validate delete user (.*) function$")
    public void deleteUser(String username) {
        try {
            String url = ProjectConfigData.getProperty("launchUrl");
            if (url.contains("beta")) {
                Actions action2 = new Actions(DriverManager.getWebDriver());
                action2.moveToElement(DriverManager.getWebDriver().findElement(edit_access3)).build().perform();
                action2.click(DriverManager.getWebDriver().findElement(edit_access3)).build().perform();
//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(delete_user2));
                DriverAction.waitSec(3);
                Actions action = new Actions(DriverManager.getWebDriver());
                action.moveToElement(DriverManager.getWebDriver().findElement(delete_user2)).build().perform();
                action.click(DriverManager.getWebDriver().findElement(delete_user2)).build().perform();
                DriverAction.waitSec(3);
                boolean flag = false;
                List<String> users = DriverAction.getElementsText(users_list);
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).contains("Shubham Kumar")) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    GemTestReporter.addTestStep("Validate user is deleted", "User deleted successfully", STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Validate user is deleted", "Failed to delete user but alert for user deletion appeared", STATUS.FAIL);
                }
                DriverAction.click(addUser_project, "Add Users tab");
                DriverAction.click(adduser_select, "Add user dropdown");
                DriverAction.typeText(addUser_search, "Shubham Kumar");
                DriverAction.click(add_user, "Select user");
                DriverAction.click(select_role_empty, "Role dropdown");
                DriverAction.click(select_role, "Select Admin");
                DriverAction.click(adduser_btn, "Add user button");
                DriverAction.waitSec(4);
            } else {
//        DriverAction.doubleClick(Locators.sno, "S No");
//        DriverAction.waitSec(2);
                Actions action2 = new Actions(DriverManager.getWebDriver());
                action2.moveToElement(DriverManager.getWebDriver().findElement(edit_access5)).build().perform();
                action2.click(DriverManager.getWebDriver().findElement(edit_access5)).build().perform();
//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(delete_user2));
                DriverAction.waitSec(3);
                Actions action = new Actions(DriverManager.getWebDriver());
                action.moveToElement(DriverManager.getWebDriver().findElement(delete_user2)).build().perform();
                action.click(DriverManager.getWebDriver().findElement(delete_user2)).build().perform();
                DriverAction.waitSec(3);
                boolean flag = false;
                List<String> users = DriverAction.getElementsText(users_list);
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).contains(username)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    GemTestReporter.addTestStep("Validate user is deleted", "User deleted successfully", STATUS.PASS);
                } else {
                    GemTestReporter.addTestStep("Validate user is deleted", "Failed to delete user but alert for user deletion appeared", STATUS.FAIL);
                }
                DriverAction.click(addUser_project, "Add Users tab");
                DriverAction.click(adduser_select, "Add user dropdown");
                DriverAction.typeText(addUser_search, username);
                DriverAction.click(add_user, "Select user");
                DriverAction.click(select_role_empty, "Role dropdown");
                DriverAction.click(select_role, "Select Admin");
                DriverAction.click(adduser_btn, "Add user button");
                DriverAction.waitSec(4);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Validate user is deleted", "Failed to delete user", STATUS.FAIL);
        }

    }

    @Then("^Add an email$")
    public void email() {
        DriverAction.click(shareVia_testtool, "Open share button");
        DriverAction.click(email_to, "Add email-to");
        DriverAction.typeText(email_to, "dummy_test54@outlook.com" + Keys.ENTER);
        DriverAction.click(email_save, "Save emails");
    }

    @Then("^Verify email received$")
    public void verify_email() throws InterruptedException {
        Actions action = new Actions(DriverManager.getWebDriver());
        action.sendKeys(Keys.CONTROL + "t").build().perform();
        DriverAction.navigateToUrl("https://outlook.live.com/owa/");
        DriverAction.click(By.xpath("(//a[text()='Sign in'])[1]"));
        DriverAction.typeText(By.xpath("//input[@name='loginfmt']"), ("dummy_test54@outlook.com" + Keys.ENTER));
        Thread.sleep(5000);
        DriverAction.typeText(By.xpath("//input[@name='passwd']"), ("dummy_test" + Keys.ENTER));
        Thread.sleep(5000);
        DriverAction.click(By.xpath("//input[@id='idBtn_Back']"));
        Thread.sleep(5000);
        DriverAction.click(By.xpath("(//div[@role='region'])[3]/div/div/div/div[1]"));
        Thread.sleep(10000);
        System.out.println(DriverAction.getElementText(By.xpath("//div[@aria-label='Message body']/div/div/div/div/p[1]")));
        DriverAction.closeCurrentTab();
    }

    @When("On top of the page button should not be visible")
    public void gototopButton_visiblity() {
        String visibility = DriverAction.getAttributeName(gototop_button, "style");
        if (!visibility.contains("block")) {
            GemTestReporter.addTestStep("Validate visibility of go to top button", "Button not visible on top of screen", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate visibility of go to top button", "Button visible on top of screen", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Scroll down and check visibility of button")
    public void scrollToBottom_And_ValidateGoToTop_btn() {
        DriverAction.scrollToBottom();
        DriverAction.waitUntilElementAppear(gemjar, 5);
        DriverAction.waitSec(2);
        String visibility = DriverAction.getAttributeName(gototop_button, "style");
        if (visibility.contains("block")) {
            GemTestReporter.addTestStep("Validate visibility of go to top button", "Button visible on bottom of screen", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate visibility of go to top button", "Button not visible on bottom of screen", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @When("Click on go to top button")
    public void click_goToTop_btn() {
        DriverAction.click(gototop_button, "Go to Top button");
        DriverAction.waitUntilElementAppear(pricing, 5);
    }

    @Then("Should be navigated to top of the page")
    public void navigate_to_top() {
        DriverAction.waitSec(2);
        String visibility = DriverAction.getAttributeName(gototop_button, "style");
        if (!visibility.contains("block") && DriverAction.getElement(pricing).isDisplayed()) {
            GemTestReporter.addTestStep("Validate visibility of go to top button", "Button not visible on top of screen", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validate visibility of go to top button", "Button visible on top of screen", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("user clicks login button and validates the url")
    public void login_url() {
        DriverAction.click(Locators.login_button, "Login button");
        if (GemJarGlobalVar.environment.equals("prod")) {
            if (DriverAction.getCurrentURL().equals("https://jewel.gemecosystem.com/#/login")) {
                GemTestReporter.addTestStep("Validate login url", "Redirected to login page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate login url", "Failed to redirect to login page", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } else if (GemJarGlobalVar.environment.equals("beta")) {
            if (DriverAction.getCurrentURL().equals("https://jewel-beta.gemecosystem.com/#/login")) {

                GemTestReporter.addTestStep("Validate login url", "Redirected to login page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate login url", "Failed to redirect to login page", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate login url", "Unknown Environment", STATUS.FAIL, DriverAction.takeSnapShot());
        }
        DriverAction.navigateBack();
    }

    @Then("user clicks signup button and validates the url")
    public void signup_url() {
        DriverAction.click(Locators.signup, "SignUp button");
        if (GemJarGlobalVar.environment.equals("prod")) {
            if (DriverAction.getCurrentURL().equals("https://jewel.gemecosystem.com/#/signup")) {
                GemTestReporter.addTestStep("Validate login url", "Redirected to login page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate login url", "Failed to redirect to login page", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } else if (GemJarGlobalVar.environment.equals("beta")) {
            if (DriverAction.getCurrentURL().equals("https://jewel-beta.gemecosystem.com/#/signup")) {
                GemTestReporter.addTestStep("Validate login url", "Redirected to login page", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate login url", "Failed to redirect to login page", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } else {
            GemTestReporter.addTestStep("Validate login url", "Unknown Environment", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
}