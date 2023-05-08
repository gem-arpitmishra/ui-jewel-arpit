package Objects;

import org.openqa.selenium.By;

public class ObjSuperAdmin {
    public static By edit_company=By.xpath("(//*[local-name()='svg' and @data-icon='pencil']/*[local-name()='path'])[1]");
    public static By edit_domains=By.xpath("//button[text()='Edit Domains']");
    public static By domains_input=By.xpath("//ul//input");
    public static By update_domains=By.xpath("//button[text()='Update']");
    public static By company_domain=By.xpath("(//td[4])[1]");
    public static By AllCompanyNames=By.xpath("//td[2]");
    public static By nextPage_btn_disabled=By.xpath("//button[@aria-label='Next Page' and @disabled]");
    public static By allProjectCompanyNames=By.xpath("//td[3]");
}
