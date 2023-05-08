package StepDefination;

import Objects.Locators;
import Objects.TestToolLocators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.ProjectConfigData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static Objects.Locators.suite;
import static Objects.Locators.testcaseUpdated;

public class TestToolSteps {
    Logger logger = LoggerFactory.getLogger(StepDefination.class);

    public static String tempData = null;
    public static String SUITE_ID = null;
    public static String SUITE_CREATION_TIME = null;
    public static List<String> expected_testDetails = null;
    public static String SUITE_UPDATION_TIME = null;


    @Given("User login into Jewel Portal with valid credentials")
    public void userLoginIntoJewelPortalWithValidCredentials(DataTable data) {
        try {
            List<Map<String, String>> credentials = data.asMaps(String.class, String.class);
            String username = credentials.get(0).get("username");
            String password = credentials.get(0).get("password");
            byte[] decodingString = Base64.decodeBase64(password);
            String passwordDecoded = new String(decodingString);
            DriverAction.click(Locators.logIn, "Log In");
            DriverAction.typeText(Locators.username, username, "Username");
            DriverAction.typeText(Locators.password, "Enter Password", "Password entered successfully",
                    passwordDecoded);
            DriverAction.click(Locators.LoginButton, "LogIn Button");
            DriverAction.waitSec(5);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Failed to login", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @When("Navigate to {string} Section")
    public void navigateToSection(String section) {
        try {
            if (section.equalsIgnoreCase("TestTool"))
                DriverAction.click(TestToolLocators.testTool, "Test tool");
            if (section.equalsIgnoreCase("Admin"))
                DriverAction.click(TestToolLocators.admin, "Admin");
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to navigate to " + section +
                    "Section", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Verify user is navigated to TestTool Suite Page")
    public void verifyUserIsNavigatedToTestToolSuitePage() {
        try {
            DriverAction.waitSec(2);
            if (DriverAction.getElement(TestToolLocators.suite).isDisplayed()) {
                GemTestReporter.addTestStep("Suite Pill", "Suite pill is displayed",
                        STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Suite Pill", "Suite pill is not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(suite, "Suite");
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to navigate on Test Tool Suite page",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on {string} pagination button")
    public void clickOnPaginationButton(String page) {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                tempData = DriverAction.getElementText(TestToolLocators.nextTestToolPageBtn);
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
            if (page.equalsIgnoreCase("NEXT")) {
                if (DriverAction.getElement(TestToolLocators.nextTestToolPageBtn).isDisplayed()) {
                    DriverAction.click(TestToolLocators.nextTestToolPageBtn, "Next");
                } else {
                    GemTestReporter.addTestStep("NEXT button", "Fail to click on " + page + " button",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else if (page.equalsIgnoreCase("PREVIOUS")) {
                if (DriverAction.getElement(TestToolLocators.previousTestToolPageBtn).isDisplayed()) {
                    DriverAction.click(TestToolLocators.previousTestToolPageBtn, "Previous");
                } else {
                    GemTestReporter.addTestStep("PREVIOUS button", "Fail to click on " + page + " button",
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on " + page + " button",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Verify the {string} page displayed")
    public void verifyThePageDisplayed(String page) {
        try {
            DriverAction.waitSec(2);
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                if (DriverAction.getElementText(TestToolLocators.nextTestToolPageBtn).equals(tempData)) {
                    GemTestReporter.addTestStep("Verify " + page + " Page", "Successfully navigated to "
                            + page, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verify " + page + " Page", "Fail to navigated to "
                            + page, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to navigate to " + page + " button",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select display count {string} from filter")
    public void selectDisplayCountFromFilter(String count) {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteCountFilter).isDisplayed()) {
                DriverAction.click(TestToolLocators.suiteCountFilter, "Suite Filter");
            } else {
                GemTestReporter.addTestStep("Suite Filter", "Fail to click on suite filter",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

            List<WebElement> options = DriverAction.getElements(TestToolLocators.suiteCountFilterOptions);
            for (WebElement option : options) {
                if (option.getText().equals(count)) {
                    tempData = count;
                    DriverAction.click(option, "suite count");
                    break;
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select suite counts",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Verify the number of suites displayed should be less than or equal to selected count")
    public void verifyTheNumberOfSuitesDisplayedShouldBeLessThanOrEqualToSelectedCount() {
        try {
            DriverAction.waitSec(2);
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int rows = DriverAction.getElements(TestToolLocators.suiteTableRows).size();
                if (Integer.parseInt(tempData) <= rows - 1) {
                    GemTestReporter.addTestStep("Table Suite Count", "Successfully count " +
                                    "matched. Expected - " + tempData + " Actual - " + (rows - 1), STATUS.PASS,
                            DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Table Suite Count", "Failed count " +
                                    "matched. Expected - " + tempData + " Actual - " + (rows - 1),
                            STATUS.FAIL,
                            DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to verify suite counts",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on header {string} to sort in sorting order {string}")
    public void clickOnHeaderToSortInSortingOrder(String header, String order) {
        try {
            int i = 0;
            if (DriverAction.getElement(TestToolLocators.suiteHeaderName).isDisplayed()) {
                List<WebElement> headers = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (WebElement headerElement : headers) {
                    if (headerElement.getText().equals(header)) {
                        tempData = i + "";
                        if (order.equalsIgnoreCase("ascending")) {
                            DriverAction.click(headerElement, header + "Header");
                        } else if (order.equalsIgnoreCase("descending")) {
                            DriverAction.click(headerElement, header + "Header");
                            DriverAction.click(headerElement, header + "Header");
                        }
                        break;
                    }
                    i++;
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on sort",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Verify suite column should be in {string} order")
    public void verifySuiteColumnShouldBeInSortedOrder(String order) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteHeaderSort));
            if (DriverAction.getElement(TestToolLocators.suiteHeaderSort).isDisplayed()) {
                List<WebElement> headers = DriverAction.getElements(TestToolLocators.suiteHeaderSort);
                String actualValue = headers.get(Integer.parseInt(tempData)).getAttribute("aria-sort");
                if (order.equalsIgnoreCase(actualValue)) {
                    GemTestReporter.addTestStep("Verifying Sorting", "Column sorted " +
                                    "successfully. Expected - " + order + " Actual - " + actualValue,
                            STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Sorting", "Column not sorted. "
                                    + "Expected - " + order + " Actual - " + actualValue,
                            STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to verify sorted columns",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify sorting has been shifted to {string} from {string}")
    public void verifySortingHasBeenShiftedToFrom(String header2, String header1) {
        try {
            int oldHeaderIndex = 0;
            if (DriverAction.getElement(TestToolLocators.suiteHeaderSort).isDisplayed()) {
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase(header1)) {
                        oldHeaderIndex = i;
                        break;
                    }
                }

                List<WebElement> headers = DriverAction.getElements(TestToolLocators.suiteHeaderSort);
                String oldHeaderOrder = headers.get(oldHeaderIndex).getAttribute("aria-sort");
                String newHeaderOrder = headers.get(Integer.parseInt(tempData)).getAttribute("aria-sort");
                if (oldHeaderOrder.equalsIgnoreCase("none") && !newHeaderOrder.equalsIgnoreCase("none")) {
                    GemTestReporter.addTestStep("Shifting Column from " + header1 + " to " + header2, "Column shifted " +
                            "successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Shifting Column from " + header1 + " to " + header2, "Column shifted " +
                            "fail", STATUS.FAIL, DriverAction.takeSnapShot());
                }

            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to column shifting after sorting",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify the displayed suite count")
    public void verifyTheDisplayedSuiteCount() {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteHeaderSort).isDisplayed()) {
                int filterValue =
                        Integer.parseInt(DriverAction.getElementText(TestToolLocators.suiteFilterCount));
                String expectedCount = DriverAction.getElementText(TestToolLocators.suiteCounts);
                int actualCount = 0;

                DriverAction.click(TestToolLocators.LastTestToolPageBtn, "Last Page");
                DriverAction.waitSec(1);
                List<String> pageNumbers = DriverAction.getElementsText(TestToolLocators.paginationActiveButtons);
                int lastPageNumber = Integer.parseInt(pageNumbers.get(pageNumbers.size() - 1));

                int lastPageSuiteCounts = DriverAction.getElements(TestToolLocators.suiteTableRows).size() - 1;

                actualCount = filterValue * (lastPageNumber - 1) + lastPageSuiteCounts;

                if (expectedCount.contains(actualCount + "")) {
                    GemTestReporter.addTestStep("Verifying Suite counts",
                            "Suite counts matching pass. Expected - " + expectedCount + " " +
                                    "Actual - " + actualCount, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Suite counts",
                            "Suite counts matching fail. Expected - " + expectedCount + " " +
                                    "Actual - " + actualCount, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate suites count.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on filter for header {string}")
    public void clickOnFilterForHeader(String header) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(40));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteHeaderFilter(header)));
            if (DriverAction.getElement(TestToolLocators.suiteHeaderSort).isDisplayed()) {
                List<WebElement> headers = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (WebElement headerElement : headers) {
                    if (headerElement.getText().equals(header)) {
                        DriverAction.click(TestToolLocators.suiteHeaderFilter(header), header + "Header");
                        break;
                    }
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on header filter.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Select {string} options from the filter list")
    public void selectAllOptionsFromTheFilterList(String options) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteHeaderActiveFilter));
            if (DriverAction.getElement(TestToolLocators.suiteHeaderActiveFilter).isDisplayed()) {
                DriverAction.click(TestToolLocators.suiteHeaderActiveFilter, "Filter Dropdown");
                if (options.equalsIgnoreCase("Random")) {
                    options = tempData;
                }
                if (options.equalsIgnoreCase("All")) {
                    DriverAction.click(TestToolLocators.suiteHeaderFilterOptionAll, "Filter All option");
                    DriverAction.waitSec(2);
                } else {
                    List<String> filterOptions = Arrays.asList(options.split(","));
                    List<WebElement> filterValues =
                            DriverAction.getElements(TestToolLocators.suiteHeaderFilterOptions);

                    for (String option : filterOptions) {
                        for (WebElement filterValue : filterValues) {
                            if (option.equalsIgnoreCase(filterValue.getText())) {
                                DriverAction.click(filterValue, "Filter - " + filterValue.getText());
                                break;
                            }
                        }
                    }
                }

            } else {
                GemTestReporter.addTestStep("Filter Dropdown", "Filter dropdown not active",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select filter options.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify all suites are visible inside suite table")
    public void verifyAllSuitesAreVisibleInsideSuiteTable() {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteHeaderName).isDisplayed()) {
                String paginationSuiteCount = DriverAction.getElementText(TestToolLocators.suiteCounts);
                int expectedCount = getNumberFromString(paginationSuiteCount);

                String filterSuiteCount = DriverAction.getElementText(TestToolLocators.
                        suiteHeaderFilterResultLabel);
                int actualCount = getNumberFromString(filterSuiteCount);

                if (actualCount <= expectedCount) {
                    GemTestReporter.addTestStep("Verifying Suite counts after filter",
                            "Suite counts matching pass. Expected - " + expectedCount + " " +
                                    "Actual - " + actualCount, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Suite counts after filter",
                            "Suite counts matching fail. Expected - " + expectedCount + " " +
                                    "Actual - " + actualCount, STATUS.FAIL, DriverAction.takeSnapShot());
                }


            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate suites count.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    public static int getNumberFromString(String line) {
        String regex = "[^\\d]+";
        String[] str = line.split(regex);
        String result = null;
        for (String st : str) {
            result = st;
        }
        return Integer.parseInt(result);
    }

    @And("Verify selected options {string} are visible for header {string} inside suite table")
    public void verifySelectedOptionsAreVisibleForHeaderInsideSuiteTable(String options, String header) {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase(header)) {
                        column = i + 1;
                        break;
                    }
                }

                List<String> columnData =
                        DriverAction.getElementsText(TestToolLocators.suiteTableRowsData(column));

                List<String> filterOptions = Arrays.asList(options.split(","));

                for (String option : filterOptions) {
                    if (columnData.contains(option)) {
                        GemTestReporter.addTestStep("Verifying Suite",
                                "Suite displays successfully - " + option,
                                STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verifying Suite",
                                "Suite not display - " + option,
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate selected suites.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify selected options {string} and {string} are visible for header {string} and{string} inside suite table")
    public void verifySelectedOptionsAndAreVisibleForHeaderAndInsideSuiteTable(String option1, String option2,
                                                                               String header1, String header2) {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column1 = 0;
                int column2 = 0;


                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase(header1)) {
                        column1 = i + 1;
                        break;
                    }
                }

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase(header2)) {
                        column2 = i + 1;
                        break;
                    }
                }

                if (option1.equalsIgnoreCase("Random")) {
                    option1 = tempData;
                }

                List<String> columnData1 =
                        DriverAction.getElementsText(TestToolLocators.suiteTableRowsData(column1));

                List<String> columnData2 =
                        DriverAction.getElementsText(TestToolLocators.suiteTableRowsData(column2));

                List<String> filterOptions1 = Arrays.asList(option1.split(","));
                List<String> filterOptions2 = Arrays.asList(option2.split(","));

                for (String option : columnData1) {
                    if (filterOptions1.contains(option)) {
                        GemTestReporter.addTestStep("Verifying Suite",
                                "Suite displays successfully - " + option,
                                STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verifying Suite",
                                "Suite not display - " + option,
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }


                for (String option : columnData2) {
                    if (filterOptions2.contains(option)) {
                        GemTestReporter.addTestStep("Verifying Suite",
                                "Suite displays successfully - " + option,
                                STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verifying Suite",
                                "Suite not display - " + option,
                                STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate selected suites.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on create suite button for creating new suite")
    public void clickOnCreateSuiteButtonForCreatingNewSuite() {
        try {
            if (DriverAction.getElement(TestToolLocators.buttonCreateSuite).isDisplayed()) {
                DriverAction.click(TestToolLocators.buttonCreateSuite, "Create Suite");
            } else {
                GemTestReporter.addTestStep("Create Suite Button", "Create Suite Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on create suite button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Select project name {string} options for creating suite")
    public void selectProjectNameOptionsForCreatingSuite(String projectName) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.projectNameDropdown));
            if (DriverAction.getElement(TestToolLocators.projectNameDropdown).isDisplayed()) {
                DriverAction.click(TestToolLocators.projectNameDropdown, "Project Name Dropdown");
                DriverAction.waitSec(2);
                WebElement listItem =
                        DriverAction.getElements(TestToolLocators.projectNameOptionsList).get(0);
                wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(listItem,
                        "No results found")));
                DriverAction.typeText(TestToolLocators.projectNameSearchTextBox, projectName, "Project Name");
                DriverAction.click(DriverAction.getElements(TestToolLocators.projectNameOptionsList).get(0)
                        , projectName);
            } else {
                GemTestReporter.addTestStep("Create Suite Popup", "Create Suite popup not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select project name from dropdown.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Enter suite name {string} for creating suite")
    public void enterSuiteNameForCreatingSuite(String suiteName) {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteNameTextBox).isDisplayed()) {
                if (suiteName.equalsIgnoreCase("Random")) {
                    int randomDigit = (int) (Math.random() * (9999 - 1111 + 1) + 1111);
                    suiteName = "SUITE_" + randomDigit;
                    tempData = suiteName;
                }
                DriverAction.typeText(TestToolLocators.suiteNameTextBox, suiteName, "Suite Name");
            } else {
                GemTestReporter.addTestStep("Create Suite Popup", "Suite Name text box not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to enter suite name.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on create suite button")
    public void clickOnCreateSuiteButton() {
        try {
            if (DriverAction.getElement(TestToolLocators.buttonCreateSuiteFinish).isDisplayed()) {
                DriverAction.click(TestToolLocators.buttonCreateSuiteFinish, "Create Suite");
                SUITE_CREATION_TIME = getFormattedDate(new Date());
                DriverAction.waitSec(5);
                DriverAction.refresh();
            } else {
                GemTestReporter.addTestStep("Create Suite Button", "Create Suite Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on create suite button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify duplicates suites for the same {string}")
    public void verifyDuplicatesSuitesForTheSame(String suiteName) {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int rows = DriverAction.getElements(TestToolLocators.suiteTableRows).size() - 1;
                if (rows > 1) {
                    GemTestReporter.addTestStep("Verifying Suite counts",
                            "Duplicate Suite found", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Suite counts",
                            "Duplicate Suite not found", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Create Suite Button", "Fail to validate suite table"
                        , STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate suite table.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify searched project name {string} {string}")
    public void verifySearchedProjectName(String projectName, String isMatch) {
        try {
            if (DriverAction.getElement(TestToolLocators.projectNameDropdown).isDisplayed()) {
                if (isMatch.equalsIgnoreCase("Matched")) {
                    if (DriverAction.getElementsText(TestToolLocators.projectNameOptionsList).get(0)
                            .equalsIgnoreCase(projectName)) {
                        GemTestReporter.addTestStep("Project Name", "Project found"
                                , STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Project Name", "Project not found"
                                , STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    if (DriverAction.getElementsText(TestToolLocators.projectNameOptionsList).get(0)
                            .equalsIgnoreCase("No available options")) {
                        GemTestReporter.addTestStep("Project Name", "Project not found"
                                , STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Project Name", "Project found"
                                , STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }
            } else {
                GemTestReporter.addTestStep("Create Suite Popup", "Create Suite popup not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate project name.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Enter project name {string} options for creating suite")
    public void enterProjectNameOptionsForCreatingSuite(String projectName) {
        try {
            if (DriverAction.getElement(TestToolLocators.projectNameDropdown).isDisplayed()) {
                DriverAction.click(TestToolLocators.projectNameDropdown, "Project Name Dropdown");
                DriverAction.waitSec(2);
                WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
                WebElement listItem =
                        DriverAction.getElements(TestToolLocators.projectNameOptionsList).get(0);
                wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(listItem,
                        "No results found")));
                DriverAction.typeText(TestToolLocators.projectNameSearchTextBox, projectName, "Project Name");
            } else {
                GemTestReporter.addTestStep("Create Suite Popup", "Create Suite popup not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select project name from dropdown.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Click on test case plus button for {string}")
    public void clickOnTestCasePlusButtonFor(String suiteName) {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column1 = 0;
                int column2 = 0;

                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Suite ID")) {
                        column1 = i + 1;
                        break;
                    }
                }

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Testcase(s)")) {
                        column2 = i + 1;
                        break;
                    }
                }

                String suiteId = DriverAction.getElementText(TestToolLocators.suiteTableSuiteFirstRows(column1));
                SUITE_ID = suiteId;

                DriverAction.click(TestToolLocators.suiteTableSuiteAddButton(column2), "Add");
                DriverAction.waitSec(2);

            } else {
                GemTestReporter.addTestStep("Suite Table", "Fail to validate suite table"
                        , STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on plus button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify the suite name {string} on test case tab")
    public void verifyTheSuiteNameOnTestCaseTab(String suiteName) {
        try {
            DriverAction.waitSec(3);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.textToBePresentInElement(DriverAction.getElement(TestToolLocators.testCasesHeader)
                    , suiteName));
            if (DriverAction.getElement(TestToolLocators.testCasesHeader).isDisplayed()) {
                if (suiteName.equalsIgnoreCase("random")) {
                    suiteName = tempData;
                }
                String expectedHeader = suiteName + "(" + SUITE_ID + ")";
                String actualHeader = DriverAction.getElementText(TestToolLocators.testCasesHeader);

                if (actualHeader.equalsIgnoreCase(expectedHeader)) {
                    GemTestReporter.addTestStep("Verifying Header on Test Case Tab",
                            "Header matched successfully. Expected - " + expectedHeader + " " +
                                    "Actual - " + actualHeader, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Header on Test Case Tab",
                            "Header matched failed. Expected - " + expectedHeader + " " +
                                    "Actual - " + actualHeader, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Testcase Header", "Fail to load Testcase Header"
                        , STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate suite name.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Get the testcase count of {string}")
    public void getTheTestcaseCountOf(String suiteName) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteFirstRowData));

            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Testcase Count")) {
                        column = i + 1;
                        break;
                    }
                }

                String suiteCount =
                        DriverAction.getElementText(TestToolLocators.suiteTableSuiteFirstRows(column));
                tempData = suiteCount;

            } else {
                GemTestReporter.addTestStep("Suite Table", "Fail to validate suite table"
                        , STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to get suite count.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify the total test case count on test case tab")
    public void verifyTheTotalTestCaseCountOnTestCaseTab() {
        try {
            if (DriverAction.getElements(TestToolLocators.suiteCounts).get(1).isDisplayed()) {
                int expectedCount = getNumberFromString(DriverAction.getElementsText(TestToolLocators.suiteCounts).get(1));
                int actualCount = Integer.parseInt(tempData);
                if (expectedCount == actualCount) {
                    GemTestReporter.addTestStep("Verifying Test Case Count",
                            "Count matched successfully. Expected - " + expectedCount + " " +
                                    "Actual - " + actualCount, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Test Case Count",
                            "Count matched failed. Expected - " + expectedCount + " " +
                                    "Actual - " + actualCount, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Test Case count", "Fail to get test counts"
                        , STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate test case count.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on delete button to delete the test case")
    public void clickOnDeleteButtonToDeleteTheTestCase() {
        try {
            if (DriverAction.getElement(TestToolLocators.testCasePillTableHeaders).isDisplayed()) {

                int column = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.testCasePillTableHeaders);

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Testcase ID")) {
                        column = i + 1;
                        break;
                    }
                }
                if (DriverAction.getElements(TestToolLocators.testCasePillTableRows).size() >= 1) {
                    tempData =
                            DriverAction.getElementText(TestToolLocators.testCasePillTableRowsData(column));
                    DriverAction.click(TestToolLocators.testCasePillTableDeleteButton, "Delete");
                } else {
                    GemTestReporter.addTestStep("Test Case Pill Table", "Test case rows not" +
                            " available", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Test Case Pill Table", "Test case table not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on delete button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify the alert box with message {string}")
    public void verifyTheAlertBoxWithMessage(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.alertMessageHeader));
            if (DriverAction.getElement(TestToolLocators.alertMessageHeader).isDisplayed()) {
                String actualMessage = DriverAction.getElementText(TestToolLocators.alertMessageHeader);

                if (actualMessage.contains(message)) {
                    GemTestReporter.addTestStep("Verifying Alert Message",
                            "Message matched successfully. Expected - " + message + " " +
                                    "Actual - " + actualMessage, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Alert Message",
                            "Message matched failed. Expected - " + message + " " +
                                    "Actual - " + actualMessage, STATUS.FAIL, DriverAction.takeSnapShot());
                }

            } else {
                GemTestReporter.addTestStep("Test Case Delete Alert", "Test case alert not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate alert message.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on {string} Button for deleting test case")
    public void clickOnButtonForDeletingTestCase(String button) {
        try {
            if (button.equalsIgnoreCase("No")) {
                if (DriverAction.getElement(TestToolLocators.alertMessageNOButton).isDisplayed()) {
                    DriverAction.click(TestToolLocators.alertMessageNOButton, "Alert - No");
                } else {
                    GemTestReporter.addTestStep("Alert No Button", "No button not" +
                            " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                if (DriverAction.getElement(TestToolLocators.alertMessageYESButton).isDisplayed()) {
                    DriverAction.click(TestToolLocators.alertMessageYESButton, "Alert - Yes");
                } else {
                    GemTestReporter.addTestStep("Alert Yes Button", "Yes button not" +
                            " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on " + button + " button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select testID from the filter for test case")
    public void selectTestIDFromTheFilterForTestCase() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.testCasePillTableHeaders));

            if (DriverAction.getElement(TestToolLocators.testCasePillTableHeaders).isDisplayed()) {
                int column = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.testCasePillTableHeaders);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Testcase ID")) {
                        column = i + 1;
                        break;
                    }
                }
                DriverAction.click(TestToolLocators.testCaseHeaderFilter(column), "filter");
                DriverAction.click(TestToolLocators.suiteHeaderActiveFilter, "Filter Dropdown");
                List<WebElement> filterValues =
                        DriverAction.getElements(TestToolLocators.suiteHeaderFilterOptions);
                DriverAction.waitSec(2);

                for (WebElement filterValue : filterValues) {
                    if (tempData.equalsIgnoreCase(filterValue.getText())) {
                        DriverAction.click(filterValue, "Filter - " + filterValue.getText());
                        break;
                    }
                }
            } else {
                GemTestReporter.addTestStep("Test Case Header", "Header not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select filter for TestCaseId.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify the test case should not be deleted")
    public void verifyTheTestCaseShouldNotBeDeleted() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.testCasePillTableRows));
            if (DriverAction.getElement(TestToolLocators.testCasePillTableRows).isDisplayed()) {
                List<WebElement> rows = DriverAction.getElements(TestToolLocators.testCasePillTableRows);
                if (rows.size() == 1) {
                    GemTestReporter.addTestStep("Verifying Test Case",
                            "Test case not deleted", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Test Case",
                            "Test case deleted", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Test Case rows",
                        "Test case rows not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate deleted test case.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }


    @And("Verify the test case should be deleted")
    public void verifyTheTestCaseShouldBeDeleted() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.testCasePillTableRows));
            if (DriverAction.getElement(TestToolLocators.testCasePillTableRows).isDisplayed()) {
                List<WebElement> rows = DriverAction.getElements(TestToolLocators.testCasePillTableRows);
                if (rows.size() == 0) {
                    GemTestReporter.addTestStep("Verifying Test Case",
                            "Test case deleted", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Test Case",
                            "Test case not deleted", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Test Case rows",
                        "Test case rows not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate deleted test case.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify deleted toast alert with message {string}")
    public void verifyDeletedToastAlertWithMessage(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.toastAlertMessage));
            if (DriverAction.getElement(TestToolLocators.toastAlertMessage).isDisplayed()) {
                String actualMessage = DriverAction.getElementText(TestToolLocators.toastAlertMessage);
                if (actualMessage.equalsIgnoreCase(message)) {
                    GemTestReporter.addTestStep("Verifying Toast Message",
                            "Alert Message matched successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Toast Message",
                            "Alert Message not matched", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Toast Alert Message",
                        "Toast Alert not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate toast message.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify creation time of the suite")
    public void verifyCreationTimeOfTheSuite() {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column1 = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Creation Time")) {
                        column1 = i + 1;
                        break;
                    }
                }
                List<String> columnData =
                        DriverAction.getElementsText(TestToolLocators.suiteTableRowsData(column1));

                SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy h:mm:ss a (z)");
                String expectedDate = getFormattedDate(inputFormat.parse(columnData.get(0)));

                if (expectedDate.equalsIgnoreCase(SUITE_CREATION_TIME)) {
                    GemTestReporter.addTestStep("Verifying Suite Creation Time",
                            "Creation Time matched. Expected: " + expectedDate + " Actual: " +
                                    SUITE_CREATION_TIME, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Suite Creation Time",
                            "Creation Time not matched. Expected: " + expectedDate + " Actual: " +
                                    SUITE_CREATION_TIME, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate suite creation time.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    public String getFormattedDate(Date date) {
        try {
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(date);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to convert date into format dd-mm-yyyy.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
            return null;
        }
    }

    @And("Click on create test case button")
    public void clickOnCreateTestCaseButton() {
        try {
            if (DriverAction.getElement(TestToolLocators.createTestCaseButton).isDisplayed()) {
                DriverAction.click(TestToolLocators.createTestCaseButton, "Create Test Case");
                DriverAction.waitSec(3);
            } else {
                GemTestReporter.addTestStep("Create Test Case Button", "Create Test Case Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on create test case button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select GemJAR as a base project")
    public void selectGemJARAsABaseProject() {
        try {
            if (DriverAction.getElement(TestToolLocators.createTestCaseBaseProjectModal).isDisplayed()) {
                DriverAction.click(TestToolLocators.gemjarBaseProjectSelectButton, "GemJar");
            } else {
                GemTestReporter.addTestStep("Create Test Case Modal", "Create Test Case Modal not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select GemJAR base project.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Enter testcase name {string} to create test")
    public void enterTestcaseNameToCreateTest(String name) {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseNameInput).isDisplayed()) {
                DriverAction.typeText(TestToolLocators.testcaseNameInput, name, "testcase name");
            } else {
                GemTestReporter.addTestStep("Test Case Name", "Name input box not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to enter testcase name.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select testcase type {string} to create test")
    public void selectTestcaseTypeToCreateTest(String type) {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseTypeDropdown).isDisplayed()) {
                DriverAction.click(TestToolLocators.testcaseTypeDropdown, "testcase type");
                List<WebElement> options =
                        DriverAction.getElements(TestToolLocators.testcaseTypeDropdownItems);
                for (WebElement option : options) {
                    if (option.getText().equalsIgnoreCase(type)) {
                        DriverAction.click(option, type);
                        break;
                    }
                }
            } else {
                GemTestReporter.addTestStep("Test Case Type", "Testcase Type dropdown not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select type.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Enter testcase steps {string} to create test")
    public void enterTestcaseStepsToCreateTest(String steps) {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseStepsInput).isDisplayed()) {
                DriverAction.typeText(TestToolLocators.testcaseStepsInput, steps, "testcase steps");
            } else {
                GemTestReporter.addTestStep("Test Case Name", "Steps input box not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to enter steps.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Enter category {string} to create test")
    public void enterCategoryToCreateTest(String category) {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseCategory).isDisplayed()) {
                DriverAction.typeText(TestToolLocators.testcaseCategory, category, "testcase category");
            } else {
                GemTestReporter.addTestStep("Test Case Name", "Category input box not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to enter category.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select run flag {string} to create test")
    public void selectRunFlagToCreateTest(String runFlag) {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseRunFlagDropdown).isDisplayed()) {
                DriverAction.click(TestToolLocators.testcaseRunFlagDropdown, "runflag");
                List<WebElement> options =
                        DriverAction.getElements(TestToolLocators.testcaseTypeDropdownItems);
                for (WebElement option : options) {
                    if (option.getText().equalsIgnoreCase(runFlag)) {
                        DriverAction.click(option, runFlag);
                        break;
                    }
                }
            } else {
                GemTestReporter.addTestStep("Test Case Type", "RunFlag dropdown not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select run flag.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on create test case button for creating new test case")
    public void clickOnCreateTestCaseButtonForCreatingNewTestCase() {
        try {
            if (DriverAction.getElement(TestToolLocators.createTestCaseFinishButton).isDisplayed()) {
                DriverAction.click(TestToolLocators.createTestCaseFinishButton, "Create Test Case");
                SUITE_UPDATION_TIME = getFormattedDate(new Date());
            } else {
                GemTestReporter.addTestStep("Create Test Case Button", "Create Test Case Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }

//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
//            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.closeEditDialogButton)));

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on create test case button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Navigate back to test suite page")
    public void navigateBackToTestSuitePage() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(DriverAction.getElement(TestToolLocators.suite)));
            if (DriverAction.getElement(TestToolLocators.suite).isDisplayed()) {
                DriverAction.click(TestToolLocators.suite, "Suite Pill");
                DriverAction.waitSec(3);
            } else {
                GemTestReporter.addTestStep("Navigate to Suite Page", "Suite Pill not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to navigate on suite page.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify updation time of the suite")
    public void verifyUpdationTimeOfTheSuite() {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column1 = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Updation Time")) {
                        column1 = i + 1;
                        break;
                    }
                }
                List<String> columnData =
                        DriverAction.getElementsText(TestToolLocators.suiteTableRowsData(column1));

                SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy h:mm:ss a (z)");
                String expectedDate = getFormattedDate(inputFormat.parse(columnData.get(0)));

                if (expectedDate.equalsIgnoreCase(SUITE_UPDATION_TIME)) {
                    GemTestReporter.addTestStep("Verifying Suite Updation Time",
                            "Creation Time matched. Expected: " + expectedDate + " Actual: " +
                                    SUITE_UPDATION_TIME, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Suite Updation Time",
                            "Creation Time not matched. Expected: " + expectedDate + " Actual: " +
                                    SUITE_UPDATION_TIME, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to verify updation time.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify create test case alert with message {string}")
    public void verifyCreateTestCaseAlertWithMessage(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.toastAlertMessage));
            if (DriverAction.getElement(TestToolLocators.toastAlertMessage).isDisplayed()) {
                String actualMessage = DriverAction.getElementText(TestToolLocators.toastAlertMessage);
                if (actualMessage.equalsIgnoreCase(message)) {
                    GemTestReporter.addTestStep("Verifying Toast Message",
                            "Alert Message matched successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Toast Message",
                            "Alert Message not matched", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Toast Alert Message",
                        "Toast Alert not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate toast message.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select GemPYP as a base project")
    public void selectGemPYPAsABaseProject() {
        try {
            if (DriverAction.getElement(TestToolLocators.createTestCaseBaseProjectModal).isDisplayed()) {
                DriverAction.click(TestToolLocators.gempypBaseProjectSelectButton, "GemJar");
            } else {
                GemTestReporter.addTestStep("Create Test Case Modal", "Create Test Case Modal not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select GemPYP base project.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Enter testcase type {string} to search test type")
    public void enterTestcaseTypeToSearchTestType(String type) {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseTypeDropdown).isDisplayed()) {
                DriverAction.click(TestToolLocators.testcaseTypeDropdown, "testcase type");
                DriverAction.typeText(TestToolLocators.testcaseDropdownSearchInput, type, "type");
            } else {
                GemTestReporter.addTestStep("Test Case Type", "Testcase Type dropdown not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to enter test type.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify the selected testcase type {string}")
    public void verifyTheSelectedTestcaseType(String type) {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseTypeDropdownItems).isDisplayed()) {
                String actualType =
                        DriverAction.getElementsText(TestToolLocators.testcaseTypeDropdownItems).get(0);
                if (actualType.equalsIgnoreCase(type)) {
                    GemTestReporter.addTestStep("Verifying Selected Test Case Type",
                            "Test type matched successfully", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Selected Test Case Type",
                            "Test type not matched", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Create Test Case Modal", "Testcase type dropdown items not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to verify selected testcase type.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on expand button of testcase")
    public void clickOnExpandButtonOfTestcase() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.testCasePillTableRows));
            if (DriverAction.getElement(TestToolLocators.testCasePillTableRows).isDisplayed()) {
                DriverAction.click(TestToolLocators.testCasePillTableExpandButton, "expand");
            } else {
                GemTestReporter.addTestStep("Test Case rows",
                        "Test case rows not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on expand button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify testcase details for given test case")
    public void verifyTestcaseDetailsForGivenTestCase() {
        try {
            if (DriverAction.getElement(TestToolLocators.testcaseDescriptionHeader).isDisplayed()) {
                GemTestReporter.addTestStep("Test Description", "Test Description is displayed",
                        STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Test Description", "Test Description is not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate description screen.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify suite creation username")
    public void verifySuiteCreationUsername() {
        try {
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column1 = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Created By")) {
                        column1 = i + 1;
                        break;
                    }
                }
                List<String> columnData =
                        DriverAction.getElementsText(TestToolLocators.suiteTableRowsData(column1));

                String actualUsername = columnData.get(0);
                String expectedUsername = ProjectConfigData.getProperty("username");

                if (expectedUsername.equalsIgnoreCase(actualUsername)) {
                    GemTestReporter.addTestStep("Verifying Suite Creation Username",
                            "Creation Username matched. Expected: " + expectedUsername + " Actual: " +
                                    actualUsername, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Suite Creation Username",
                            "Creation Username not matched. Expected: " + expectedUsername + " Actual: " +
                                    actualUsername, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate suite creation username.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify suite updation username")
    public void verifySuiteUpdationUsername() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteFirstRowData));
//            DriverAction.waitSec(3);
            if (DriverAction.getElement(TestToolLocators.suiteFirstRowData).isDisplayed()) {
                int column1 = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Updated By")) {
                        column1 = i + 1;
                        break;
                    }
                }
                List<String> columnData =
                        DriverAction.getElementsText(TestToolLocators.suiteTableRowsData(column1));

                String actualUsername = columnData.get(0);
                String expectedUsername = ProjectConfigData.getProperty("username");

                if (expectedUsername.equalsIgnoreCase(actualUsername)) {
                    GemTestReporter.addTestStep("Verifying Suite Creation Username",
                            "Creation Username matched. Expected: " + expectedUsername + " Actual: " +
                                    actualUsername, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Suite Creation Username",
                            "Creation Username not matched. Expected: " + expectedUsername + " Actual: " +
                                    actualUsername, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Suite Table", "Suite Table not displayed",
                        STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate suite updation username.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Get all the details of selected testcase")
    public void getAllTheDetailsOfSelectedTestcase() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.testCasePillTableRows));

            int name_column = 0;
            int category_column = 0;
            int type_column = 0;
            int flag_column = 0;

            if (DriverAction.getElement(TestToolLocators.testCasePillTableRows).isDisplayed()) {
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.testCasePillTableHeaders);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Testcase Name")) {
                        name_column = i + 1;
                        break;
                    }
                }

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Category")) {
                        category_column = i + 1;
                        break;
                    }
                }

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("Type")) {
                        type_column = i + 1;
                        break;
                    }
                }

                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("RUN FLAG")) {
                        flag_column = i + 1;
                        break;
                    }
                }
                expected_testDetails = new ArrayList<>();
                expected_testDetails.add(DriverAction.getElementText(TestToolLocators.testCasePillTableRowsData(name_column)));
                expected_testDetails.add(DriverAction.getElementText(TestToolLocators.testCasePillTableRowsData(type_column)));
                expected_testDetails.add(DriverAction.getElementText(TestToolLocators.testCasePillTableRowsData(category_column)));
                expected_testDetails.add(DriverAction.getElementText(TestToolLocators.testCasePillTableRowsData(flag_column)));

            } else {
                GemTestReporter.addTestStep("Test Case rows",
                        "Test case rows not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to get test details.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on edit button of testcase")
    public void clickOnEditButtonOfTestcase() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.testCasePillTableRows));

            int column = 0;
            List<WebElement> headersName = DriverAction.getElements(TestToolLocators.testCasePillTableHeaders);
            for (int i = 0; i < headersName.size(); i++) {
                if (headersName.get(i).getText().equalsIgnoreCase("Edit")) {
                    column = i + 1;
                    break;
                }
            }
            if (DriverAction.getElement(TestToolLocators.testCasePillTableEditButton(column)).isDisplayed()) {
                DriverAction.click(TestToolLocators.testCasePillTableEditButton(column), "edit");
            } else {
                GemTestReporter.addTestStep("Test Case Edit Button",
                        "Test case edit button not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on edit testcase button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify edit details for given test case")
    public void verifyEditDetailsForGivenTestCase() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.testcaseEditDialog));

            if (DriverAction.getElement(TestToolLocators.testcaseEditDialog).isDisplayed()) {
                List<String> actualDetails = new ArrayList<>();
                actualDetails.add(DriverAction.getAttributeName(TestToolLocators.testcaseNameInput, "value"));
                actualDetails.add(DriverAction.getElementText(TestToolLocators.testcaseTypeDropdownValue));
                actualDetails.add(DriverAction.getAttributeName(TestToolLocators.testcaseCategory, "value"));
                actualDetails.add(DriverAction.getElementText(TestToolLocators.testcaseTypeRunFlagValue));

                System.out.println(actualDetails);

                if (expected_testDetails.equals(actualDetails)) {
                    GemTestReporter.addTestStep("Verifying Edit Test Details",
                            "Details matched. Expected: " + expected_testDetails + " Actual: " +
                                    actualDetails, STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Verifying Edit Test Details",
                            "Details not matched. Expected: " + expected_testDetails + " Actual: " +
                                    actualDetails, STATUS.FAIL, DriverAction.takeSnapShot());
                }
            } else {
                GemTestReporter.addTestStep("Test Case edit details",
                        "Edit dialog not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate test case details on edit screen.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Clear category field to make empty")
    public void clearCategoryFieldToMakeEmpty() {
        try {
            DriverAction.clearText(TestToolLocators.testcaseCategory);
            DriverAction.click(TestToolLocators.testcaseNameInput);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to clear test category field.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on save button to update the test case")
    public void clickOnSaveButtonToUpdateTheTestCase() {
        try {
            if (DriverAction.getElement(TestToolLocators.createTestCaseSaveButton).isDisplayed()) {
                DriverAction.click(TestToolLocators.createTestCaseSaveButton, "save");
            } else {
                GemTestReporter.addTestStep("Test Case save button",
                        "Save button not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click save button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Get the executable test case count")
    public void getTheExecutableTestCaseCount() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.closeEditDialogButton)));

            if (DriverAction.getElement(TestToolLocators.testCasePillTableHeaders).isDisplayed()) {
                int column = 0;
                List<WebElement> headersName = DriverAction.getElements(TestToolLocators.testCasePillTableHeaders);
                for (int i = 0; i < headersName.size(); i++) {
                    if (headersName.get(i).getText().equalsIgnoreCase("RUN FLAG")) {
                        column = i + 1;
                        break;
                    }
                }
                DriverAction.click(TestToolLocators.testCaseHeaderFilter(column), "filter");
                DriverAction.click(TestToolLocators.suiteHeaderActiveFilter, "Filter Dropdown");
                List<WebElement> filterValues =
                        DriverAction.getElements(TestToolLocators.suiteHeaderFilterOptions);
                DriverAction.waitSec(2);

                for (WebElement filterValue : filterValues) {
                    if (filterValue.getText().equalsIgnoreCase("N")) {
                        DriverAction.click(filterValue, "Filter - " + filterValue.getText());
                        break;
                    }
                }

                int unexecutableCounts =
                        DriverAction.getElements(TestToolLocators.testCasePillTableRows).size();

                tempData =
                        (getNumberFromString(DriverAction.getElementsText(TestToolLocators.suiteCounts).get(1))
                                - unexecutableCounts) + "";

                SUITE_ID =
                        "" + getNumberFromString(DriverAction.getElementText(TestToolLocators.testCasesHeader));

            } else {
                GemTestReporter.addTestStep("Test Case Header", "Header not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to get executable test count.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on execute button to execute suite")
    public void clickOnExecuteButtonToExecuteSuite() {
        try {
            int column = 0;
            List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
            for (int i = 0; i < headersName.size(); i++) {
                if (headersName.get(i).getText().equalsIgnoreCase("Execute")) {
                    column = i + 1;
                    break;
                }
            }
            if (DriverAction.getElements(TestToolLocators.suiteTableSuiteFirstRows(column)).get(0).isDisplayed()) {
                DriverAction.click(DriverAction.getElements(TestToolLocators.suiteTableSuiteFirstRows(column))
                        .get(0), "execute");
            } else {
                GemTestReporter.addTestStep("Execution Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on execute button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select execution run mode {string}")
    public void selectExecutionRunMode(String mode) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.executeSuiteDialog));
            if (DriverAction.getElement(TestToolLocators.executeSuiteDialog).isDisplayed()) {
                DriverAction.click(TestToolLocators.executeModeDropdown, "mode");
                List<WebElement> options =
                        DriverAction.getElements(TestToolLocators.testcaseTypeDropdownItems);
                for (WebElement option : options) {
                    if (option.getText().equalsIgnoreCase(mode)) {
                        DriverAction.click(option, mode);
                        break;
                    }
                }
            } else {
                GemTestReporter.addTestStep("Execution Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select run mode.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select execution environment {string}")
    public void selectExecutionEnvironment(String env) {
        try {
            if (DriverAction.getElement(TestToolLocators.executeSuiteDialog).isDisplayed()) {
                DriverAction.click(TestToolLocators.executeEnvDropdown, "environment");
                List<WebElement> options =
                        DriverAction.getElements(TestToolLocators.testcaseTypeDropdownItems);
                for (WebElement option : options) {
                    if (option.getText().equalsIgnoreCase(env)) {
                        DriverAction.click(option, env);
                        break;
                    }
                }
            } else {
                GemTestReporter.addTestStep("Execution Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select environment.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on execute button")
    public void clickOnExecuteButton() {
        try {
            if (DriverAction.getElement(TestToolLocators.executeButton).isDisplayed()) {
                DriverAction.click(TestToolLocators.executeButton, "execute");
            } else {
                GemTestReporter.addTestStep("Execution Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on execute button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Open generated report link")
    public void openGeneratedReportLink() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.executedViewReportLink));
            if (DriverAction.getElement(TestToolLocators.executedViewReportLink).isDisplayed()) {
                DriverAction.click(TestToolLocators.executedViewReportLink, "view report");
            } else {
                GemTestReporter.addTestStep("View Report Link", "Link not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to open report link.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify the total executable test case count")
    public void verifyTheTotalExecutableTestCaseCount() {
        try {
            List<String> windows = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.switchToWindow(windows.get(windows.size() - 1));

            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.expectedTestCaseCount));
            String actualCount = DriverAction.getElementText(TestToolLocators.expectedTestCaseCount);

            if (actualCount.contains(tempData)) {
                GemTestReporter.addTestStep("Verifying Executable Test Count",
                        "Count matched. Expected: " + tempData + " Actual: " +
                                actualCount, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verifying Executable Test Count",
                        "Count not matched. Expected: " + tempData + " Actual: " +
                                actualCount, STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate test case count.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Select suiteID options from the filter list")
    public void selectSuiteIDOptionsFromTheFilterList() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteHeaderActiveFilter));
            if (DriverAction.getElement(TestToolLocators.suiteHeaderActiveFilter).isDisplayed()) {
                DriverAction.click(TestToolLocators.suiteHeaderActiveFilter, "Filter Dropdown");
                List<WebElement> filterValues =
                        DriverAction.getElements(TestToolLocators.suiteHeaderFilterOptions);

                for (WebElement filterValue : filterValues) {
                    if (SUITE_ID.equalsIgnoreCase(filterValue.getText())) {
                        DriverAction.click(filterValue, "Filter - " + filterValue.getText());
                        break;
                    }
                }
            }
        } catch (
                Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to select filter options.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Get the suite ID")
    public void getTheSuiteID() {
        try {
            List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);

            int column = 0;
            for (int i = 0; i < headersName.size(); i++) {
                if (headersName.get(i).getText().equalsIgnoreCase("Suite ID")) {
                    column = i + 1;
                    break;
                }
            }

            String suiteId = DriverAction.getElementText(TestToolLocators.suiteTableSuiteFirstRows(column));
            SUITE_ID = suiteId;

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to get the suite ID.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Report should open in new tab")
    public void reportShouldOpenInNewTab() {
        try {
            List<String> windows = new ArrayList<>(DriverAction.getWindowHandles());
            DriverAction.switchToWindow(windows.get(windows.size() - 1));

            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.expectedTestCaseCount));
            String actualCount = DriverAction.getElementText(TestToolLocators.expectedTestCaseCount);

            if (DriverAction.getElement(TestToolLocators.expectedTestCaseCount).isDisplayed()) {
                GemTestReporter.addTestStep("View Report",
                        "Opened successfully", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("View Report",
                        "Opened failed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to open report.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on git button to add test cases from git")
    public void clickOnGitButtonToAddTestCasesFromGit() {
        try {
            int column = 0;
            List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
            for (int i = 0; i < headersName.size(); i++) {
                if (headersName.get(i).getText().equalsIgnoreCase("Version Control")) {
                    column = i + 1;
                    break;
                }
            }
            if (DriverAction.getElements(TestToolLocators.suiteTableSuiteGitButton(column)).get(0).isDisplayed()) {
                DriverAction.click(DriverAction.getElements(TestToolLocators.suiteTableSuiteGitButton(column))
                        .get(0), "execute");
            } else {
                GemTestReporter.addTestStep("Git Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on git button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Enter the github URL {string}")
    public void enterTheGithubURL(String url) {
        try {
            if (DriverAction.getElement(TestToolLocators.IntegrateGitDialog).isDisplayed()) {
                DriverAction.typeText(TestToolLocators.gitURLInput, url, "url");
            } else {
                GemTestReporter.addTestStep("Integrate Git Modal", "Modal not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to enter github url.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on integrate Git button")
    public void clickOnIntegrateGitButton() {
        try {
            if (DriverAction.getElement(TestToolLocators.integrateGitButton).isDisplayed()) {
                DriverAction.click(TestToolLocators.integrateGitButton, "Integrate button");
            } else {
                GemTestReporter.addTestStep("Integrate Git Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }


        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on integrate git button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify git button should start blinking")
    public void verifyGitButtonShouldStartBlinking() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            int column = 0;
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteHeaderName));
            List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
            for (int i = 0; i < headersName.size(); i++) {
                if (headersName.get(i).getText().equalsIgnoreCase("Version Control")) {
                    column = i + 1;
                    break;
                }
            }
            wait.until(ExpectedConditions.elementToBeClickable(DriverAction.getElements(TestToolLocators.
                    suiteTableSuiteGitButton(column)).get(0)));
            if (DriverAction.getElements(TestToolLocators.suiteTableSuiteGitButton(column)).get(0).getAttribute(
                    "class").contains("blink")) {
                GemTestReporter.addTestStep("Git Button", "Git button is blinking" +
                        "", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Git Button", "Git button is not blinking" +
                        "", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to verify blinking git button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on git button to validating the jar process")
    public void clickOnGitButtonToValidatingTheJarProcess() {
        try {
            int column = 0;
            List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
            for (int i = 0; i < headersName.size(); i++) {
                if (headersName.get(i).getText().equalsIgnoreCase("Version Control")) {
                    column = i + 1;
                    break;
                }
            }
            if (DriverAction.getElements(TestToolLocators.suiteTableSuiteGitButton(column)).get(0).isDisplayed()) {
                DriverAction.click(DriverAction.getElements(TestToolLocators.suiteTableSuiteGitButton(column))
                        .get(0), "execute");
            } else {
                GemTestReporter.addTestStep("Execution Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validating jar process.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify new buttons {string} should be added into integrate git modal")
    public void verifyNewButtonsShouldBeAddedIntoIntegrateGitModal(String buttonsList) {
        try {
            if (DriverAction.getElement(TestToolLocators.IntegrateGitDialog).isDisplayed()) {
                List<String> buttons = Arrays.asList(buttonsList.split(","));
                String status = "pass";
                for (String button : buttons) {
                    if (!DriverAction.getElement(TestToolLocators.commonButtons(button)).isDisplayed()) {
                        GemTestReporter.addTestStep(button, button + " not" +
                                " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
                        status = "fail";
                    }
                }
                if (status.equalsIgnoreCase("pass")) {
                    GemTestReporter.addTestStep("Integrate Git new Buttons", "All buttons are" +
                            " displayed", STATUS.PASS);
                }
            } else {
                GemTestReporter.addTestStep("Integrate Git Modal", "Modal not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate new added buttons.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("click on Remove Git button")
    public void clickOnRemoveGitButton() {
        try {
            if (DriverAction.getElement(TestToolLocators.commonButtons("Remove Git")).isDisplayed()) {
                DriverAction.click(TestToolLocators.commonButtons("Remove Git"), "Remove Git");
            } else {
                GemTestReporter.addTestStep("Remove Git Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on remove git button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Verify alert with message {string}")
    public void verifyAlertWithMessage(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.toastAlertMessage));
            if (DriverAction.getElement(TestToolLocators.toastAlertMessage).isDisplayed()) {
                List<String> toastMessages =
                        DriverAction.getElementsText(TestToolLocators.toastAlertMessage);
//                String actualMessage = DriverAction.getElementText(TestToolLocators.toastAlertMessage);
                System.out.println(toastMessages);
                if (message.equalsIgnoreCase("Git Unlinked from Suite Id")) {
                    if (toastMessages.get(0).contains("Git Unlinked from Suite Id")) {
                        GemTestReporter.addTestStep("Verifying Toast Message",
                                "Alert Message matched successfully", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verifying Toast Message",
                                "Alert Message not matched", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                } else {
                    if (toastMessages.contains(message)) {
                        GemTestReporter.addTestStep("Verifying Toast Message",
                                "Alert Message matched successfully", STATUS.PASS, DriverAction.takeSnapShot());
                    } else {
                        GemTestReporter.addTestStep("Verifying Toast Message",
                                "Alert Message not matched", STATUS.FAIL, DriverAction.takeSnapShot());
                    }
                }
//                if (actualMessage.equalsIgnoreCase(message)) {
//                    GemTestReporter.addTestStep("Verifying Toast Message",
//                            "Alert Message matched successfully", STATUS.PASS, DriverAction.takeSnapShot());
//                } else {
//                    GemTestReporter.addTestStep("Verifying Toast Message",
//                            "Alert Message not matched", STATUS.FAIL, DriverAction.takeSnapShot());
//                }
            } else {
                GemTestReporter.addTestStep("Toast Alert Message",
                        "Toast Alert not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            System.out.println(message + "\n" + e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate toast message.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Wait for navigating to Test case screen")
    public void waitForNavigatingToTestCaseScreen() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(TestToolLocators.createTestCaseButton));
            if (!DriverAction.getElement(TestToolLocators.createTestCaseButton).isDisplayed()) {
                GemTestReporter.addTestStep("Test Case Page",
                        "Page not displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.waitSec(1);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate toast message.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Refresh the suite page")
    public void refreshTheSuitePage() {
        try {
            DriverAction.refresh();
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to refresh page.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on email button to add email functionality")
    public void clickOnEmailButtonToAddEmailFunctionality() {
        try {
            int column = 0;
            List<WebElement> headersName = DriverAction.getElements(TestToolLocators.suiteHeaderName);
            for (int i = 0; i < headersName.size(); i++) {
                if (headersName.get(i).getText().equalsIgnoreCase("Share Via")) {
                    column = i + 1;
                    break;
                }
            }
            if (DriverAction.getElements(TestToolLocators.suiteTableSuiteMailButton(column)).get(0).isDisplayed()) {
                DriverAction.click(DriverAction.getElements(TestToolLocators.suiteTableSuiteMailButton(column))
                        .get(0), "email");
            } else {
                GemTestReporter.addTestStep("Mail Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on mail button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Enter the email TO {string}")
    public void enterTheEmailTO(String TO) {
        try {
            if (DriverAction.getElement(TestToolLocators.AddEmailDialog).isDisplayed()) {
                DriverAction.typeText(TestToolLocators.emailTOInput, TO, "email to");
                DriverAction.getElement(TestToolLocators.emailTOInput).sendKeys(Keys.ENTER);
            } else {
                GemTestReporter.addTestStep("Add Email Modal", "Modal not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to enter email recipients.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on Save button")
    public void clickOnSaveButton() {
        try {
            if (DriverAction.getElement(TestToolLocators.commonButtons("Save ")).isDisplayed()) {
                DriverAction.click(TestToolLocators.commonButtons("Save "), "Save");
            } else {
                GemTestReporter.addTestStep("Email Save Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on save button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Click on Unlink Email button")
    public void clickOnUnlinkEmailButton() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(TestToolLocators.commonButtons("Unlink " +
                    "Email")));
            DriverAction.waitSec(1);
            if (DriverAction.getElement(TestToolLocators.commonButtons("Unlink Email")).isDisplayed()) {
                DriverAction.click(TestToolLocators.commonButtons("Unlink Email"), "Unlink");
            } else {
                GemTestReporter.addTestStep("Email unlink Button", "Button not" +
                        " displayed", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to click on unlink button.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @And("Get the total count of projects")
    public void getTheTotalCountOfProjects() {
        try {
            DriverAction.waitSec(2);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfAllElements(DriverAction.getElements(TestToolLocators.suiteCounts).get(0)));
            wait.until(ExpectedConditions.textToBePresentInElement(DriverAction.getElements(TestToolLocators.suiteCounts).get(0), "Project(s) Found"));

            if (DriverAction.getElements(TestToolLocators.suiteCounts).get(0).isDisplayed()) {

                tempData =
                        "" + (getNumberFromString(DriverAction.getElementsText(TestToolLocators.
                                suiteCounts).get(0)));

            } else {
                GemTestReporter.addTestStep("Project count", "Project count not displayed"
                        , STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to get count of project from admin screen.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("Verify the total available project")
    public void verifyTheTotalAvailableProject() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(TestToolLocators.suiteHeaderActiveFilter));
            if (DriverAction.getElement(TestToolLocators.suiteHeaderActiveFilter).isDisplayed()) {
                DriverAction.click(TestToolLocators.suiteHeaderActiveFilter, "Filter Dropdown");
            }
            List<WebElement> filterValues =
                    DriverAction.getElements(TestToolLocators.suiteHeaderFilterOptions);
            int actualCount = filterValues.size();
            int expectedCount = Integer.parseInt(tempData);

            if (expectedCount >= actualCount) {
                GemTestReporter.addTestStep("Verifying Project Count",
                        "Project matched successfully. Expected - " + expectedCount + " " +
                                "Actual - " + actualCount, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verifying Project Count",
                        "Project matched failed. Expected - " + expectedCount + " " +
                                "Actual - " + actualCount, STATUS.FAIL, DriverAction.takeSnapShot());
            }

        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Fail to validate total project count.",
                    STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
}
