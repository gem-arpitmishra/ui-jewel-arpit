package Objects;

import org.openqa.selenium.By;

public class ObjTestTool {
    public static By testTool = By.xpath("//div[text()=\"Test Tool\"]");
    public static By admin = By.xpath("//div[text()=\"Admin\"]");
    public static By suite = By.xpath("//div[text()=\"Suite\"]");
    public static By nextTestToolPageBtn = By.xpath("//button[@aria-label='Next Page']");
    public static By previousTestToolPageBtn = By.xpath("//button[@aria-label='Previous Page']");
    public static By LastTestToolPageBtn = By.xpath("//button[@aria-label='Last Page']");
    public static By suiteFirstRowData = By.xpath("//table[@class='p-datatable-table']//tr[1]/td[contains" +
            "(@class,'columnsPrime')]");
    public static By suiteTableRows = By.xpath("//table[@class='p-datatable-table']//tr");

    public static By suiteTableSuiteFirstRows(int column) {
        return By.xpath("//table[@class='p-datatable-table']/tbody/tr[not(contains(@class,'empty'))" +
                "][1]/td[" + column + "]");
    }

    public static By suiteTableSuiteAddButton(int column) {
        return By.xpath("//table[@class='p-datatable-table']/tbody/tr[not(contains(@class,'empty'))" +
                "][1]/td[" + column + "]//*[local-name()='svg']");
    }

    public static By suiteTableSuiteGitButton(int column) {
        return By.xpath("//table[@class='p-datatable-table']/tbody/tr[not(contains(@class,'empty'))" +
                "][1]/td[" + column + "]//*[local-name()='svg']");
    }
    public static By suiteTableSuiteMailButton(int column) {
        return By.xpath("//table[@class='p-datatable-table']/tbody/tr[not(contains(@class,'empty'))" +
                "][1]/td[" + column + "]//*[local-name()='svg']");
    }

    public static By suiteCountFilter = By.xpath("//div[@class='p-dropdown-trigger']");
    public static By suiteCountFilterOptions = By.xpath("//ul[@class='p-dropdown-items']/li");
    public static By suiteHeaderName = By.xpath("//table[@class='p-datatable-table']//tr/th//span[@class='p" +
            "-column-title']");
    public static By suiteHeaderSort = By.xpath("//table[@class='p-datatable-table']//tr/th");
    public static By suiteCounts = By.xpath("//span[@class='p-paginator-current']");
    public static By suiteFilterCount = By.xpath("//div[contains(@class,'p-dropdown')]/span[contains" +
            "(@class,'inputtext')]");
    public static By paginationActiveButtons = By.xpath("//span[@class='p-paginator-pages']/button");

    public static By suiteHeaderFilter(String header) {
        return By.xpath("//table[@class='p-datatable-table" +
                "']//tr/th//span[contains(text(),'" + header + "')]/..//button");
    }

    public static By suiteHeaderActiveFilter = By.xpath("//div[@class='p-multiselect-label-container']");
    public static By suiteHeaderFilterOptionAll = By.xpath("//div[@class='p-multiselect-header']/div");
    public static By suiteHeaderFilterOptions = By.xpath("//div[@class='p-multiselect-items-" +
            "wrapper']/ul/li//span[@class='image-text']/span");
    public static By suiteHeaderFilterResultLabel = By.xpath("//div[@class='p-multiselect-label-" +
            "container']/div");

    public static By suiteTableRowsData(int column) {
        return By.xpath("//table[@class='p-datatable-table']/tbody/tr/td[" + column + "]");
    }

    public static By buttonCreateSuite = By.xpath("//div[text()='Create Suite']");
    public static By projectNameDropdown = By.xpath("//span[text()='Select Project Name']");
    public static By projectNameSearchTextBox = By.xpath("//div[@class='p-dropdown-filter-container']/input");
    public static By projectNameOptionsList = By.xpath("//ul[@class='p-dropdown-items']/li");
    public static By suiteNameTextBox = By.xpath("//div[@class='p-dialog-content']//input[@placeholder" +
            "='Suite Name']");
    public static By buttonCreateSuiteFinish = By.xpath("//button[text()='Create Suite']");
    public static By testCasesHeader = By.xpath("//div[@class='p-datatable-header']");
    public static By testCasePillTableHeaders = By.xpath("//div[@id='pills-testcase']//table[@class='p" +
            "-datatable-table']/thead//th");
    public static By testCasePillTableRows = By.xpath("//div[@id='pills-testcase']//table[@class='p" +
            "-datatable-table']/tbody/tr");

    public static By testCasePillTableRowsData(int column) {
        return By.xpath("//div[@id='pills-testcase']//table[@class='p-datatable-table']/tbody/tr[1]/td[" + column +
                "]");
    }

    public static By testCasePillTableExpandButton = By.xpath("//div[@id='pills-testcase']//table[@class='p" +
            "-datatable-table']/tbody/tr[1]/td[1]/button");

    public static By testCasePillTableEditButton(int column) {
        return By.xpath("//div[@id='pills-testcase']//table[@class='p-datatable-table']/tbody/tr[1]/td[" + column +
                "]//*[local-name()='svg'][@data-icon='pencil']");
    }


    public static By testCasePillTableDeleteButton = By.xpath("//div[@id='pills-testcase']//" +
            "table[@class='p-datatable-table']/tbody/tr[1]/td//*[local-name()='svg'][@data-icon='trash']");

    public static By alertMessageHeader = By.xpath("//div[@class='p-dialog-content']//h5");
    public static By alertMessageYESButton = By.xpath("//div[@class='p-dialog-content']//div[contains(@class,'btn-success')]");
    public static By alertMessageNOButton = By.xpath("//div[@class='p-dialog-content']//div[contains(@class,'btn-danger')]");

    public static By testCaseHeaderFilter(int column) {
        return By.xpath("//div[@id='pills-testcase']//table[@class='p-datatable-table']/thead//th[" + column +
                "]//button[@class='p-column-filter-menu-button p-link']");
    }

    public static By toastAlertMessage = By.xpath("//div[@class='Toastify__toast-body']/div[2]");
    public static By createTestCaseButton = By.xpath("//div[text()='Create Testcase']");
    public static By createTestCaseFinishButton = By.xpath("//button[text()='Create Testcase']");
    public static By createTestCaseBaseProjectModal = By.xpath("//div[@class='p-dialog-header']");
    public static By gemjarBaseProjectSelectButton = By.xpath("//div[@class='card']//h4[text()='GemJAR']/../..//button");
    public static By gempypBaseProjectSelectButton = By.xpath("//div[@class='card']//h4[text()='GemPYP']/." +
            "./..//button");
    public static By testcaseNameInput = By.xpath("//input[@placeholder='Testcase Name']");
    public static By testcaseStepsInput = By.xpath("//textarea");
    public static By testcaseTypeDropdown = By.xpath("//span[text()='Select Type']");
    public static By testcaseTypeDropdownValue = By.xpath("(//div[@class='p-dialog-title']/../..//span[@class='p-dropdown-label p-inputtext'])[1]");
    public static By testcaseTypeRunFlagValue = By.xpath("(//div[@class='p-dialog-title']/../." +
            ".//span[@class='p-dropdown-label p-inputtext'])[2]");
    public static By testcaseTypeDropdownItems = By.xpath("//ul[@class='p-dropdown-items']/li");
    public static By testcaseCategory = By.xpath("//input[@placeholder='Category']");
    public static By testcaseDropdownSearchInput = By.xpath("//div[@class='p-dropdown-filter-container']/input");
    public static By testcaseRunFlagDropdown = By.xpath("//div[text()='Run Flag']/..//span[@class='p-dropdown-label p-inputtext']");
    public static By testcaseDescriptionHeader = By.xpath("//div[contains(@class,'overHeadAnalytics')]/h4");
    public static By testcaseEditDialog = By.xpath("//div[@class='p-dialog-title']");
    public static By createTestCaseSaveButton = By.xpath("//button[text()=' Save']");
    public static By executeButton = By.xpath("//button[text()='Execute']");
    public static By executeSuiteDialog = By.xpath("//div[@class='dialogExecuteSuite']");
    public static By IntegrateGitDialog = By.xpath("//div[@id='pr_id_4_header']");
    public static By AddEmailDialog = By.xpath("//div[@class='p-dialog-header']");
    public static By executeModeDropdown = By.xpath("//div[text()='Mode']/..//span[contains(@class," +
            "'p-dropdown-label p-inputtext')]");
    public static By executeEnvDropdown = By.xpath("//div[text()='Environment']/..//span[contains(@class," +
            "'p-dropdown-label p-inputtext')]");
    public static By executedViewReportLink = By.xpath("//div[text()='View Report']");
    public static By closeEditDialogButton = By.xpath("//button[@aria-label='Close']");
    public static By expectedTestCaseCount = By.xpath("(//td[@class='align-middle'])[1]//strong[contains" +
            "(text(),'Expected')]");
    public static By gitURLInput = By.xpath("//input[@placeholder='Git Link']");
    public static By emailTOInput = By.xpath("(//li[@class='p-chips-input-token']/input)[1]");
    public static By integrateGitButton = By.xpath("//button[text()='Integrate Git']");

    public static By commonButtons(String buttonName) {
        return By.xpath("//button[text()='" + buttonName + "']");
    }

}
