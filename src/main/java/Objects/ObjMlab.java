package Objects;

import org.openqa.selenium.By;

public class ObjMlab {
    public static By MLab_button=By.xpath("/div[text()='MLAB']");
    public static By newFolder_button=By.xpath("//button[text()='Create New Folder']");
    public static By newTestcase_button=By.xpath("//button[text()='Create New Testcase']");
    public static By properties_tab=By.xpath("//div[text()='Properties']");
    public static By testSteps_tab=By.xpath("/div[not(span) and text()='Test Steps']");
    public static By drafts_tab=By.xpath("//div[text()='Test Steps']/span[text()='[Draft]']");
    public static By attached_testcases_tab=By.xpath("//div[text()='Attached Testcases']");
    public static By attached_requirements_tab=By.xpath("//div[text()='Attached Requirements']");
    public static By link_suite_button=By.xpath("//button[text()='Link Suite']");
    public static By attach_requirements_button=By.xpath("//button[text()='Attach New Requirement']");
    public static By save_editTestcase_button=By.xpath("//button[text()='Save Testcase']");
    public static By tc_nameInput=By.xpath("//input[@placeholder='Testcase Name']");
    public static By decriptionInput=By.xpath("//textarea");
    public static By attachReq_button=By.xpath("//button[text()='Attach']");

}
