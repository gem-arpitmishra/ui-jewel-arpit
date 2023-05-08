package Functions;

import Objects.ObjForgotPassword;
import Objects.ObjSuperAdmin;
import Objects.Locators;
import com.gemini.generic.reporting.GemEcoUpload;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.*;

public class Functions_ForgotPassword_SuperAdmin extends GemEcoUpload {

    public static String retrieve_otp_from_outlook() {
        String otp = null;
        ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        DriverManager.getWebDriver().switchTo().window(tabs.get(1));
        DriverAction.launchUrl("https://outlook.live.com/owa/");
        DriverAction.click(ObjForgotPassword.outlook_login_formBtn, "Sign in");
        DriverAction.waitSec(2);
        DriverAction.typeText(ObjForgotPassword.outlook_username, "dummy_test54@outlook.com", "Email");
        DriverAction.click(ObjForgotPassword.outlook_nextBtn, "Next Button");
        DriverAction.waitSec(1);
        DriverAction.typeText(ObjForgotPassword.outlook_password, "dummy_test", "Password");
        DriverAction.click(ObjForgotPassword.outlook_signIn, "Sign-In Button");
        DriverAction.waitSec(3);
        DriverAction.click(ObjForgotPassword.outlook_NoBtn, "No Button");
       // if(DriverAction.isExist(ObjForgotPassword.outlook_email_notification,130)) {
            DriverAction.waitSec(200);
            DriverAction.click(ObjForgotPassword.outlook_search, "Search");
            DriverAction.typeText(ObjForgotPassword.outlook_search, "OTP" + Keys.ENTER, "Search");
            boolean flag = false;
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat simpleformat = new SimpleDateFormat("HH:mm");
            cal.add(Calendar.MINUTE,-3);
            for(int i=0;i<5;i++) {
                if (DriverAction.getElementText(ObjForgotPassword.outlook_time).equals(simpleformat.format(cal.getTime()))) {
                    DriverAction.click(ObjForgotPassword.outlook_email_open, "Selected email");
                    DriverAction.waitSec(3);
                    otp = DriverAction.getElementText(ObjForgotPassword.outlook_otp);
                    flag = true;
                    break;
                }
                cal.add(Calendar.MINUTE,1);
            }
            if (!flag) {
                GemTestReporter.addTestStep("OTP verification", "Failed to find OTP email", STATUS.FAIL, DriverAction.takeSnapShot());
                DriverAction.closeCurrentTab();
                DriverManager.getWebDriver().switchTo().window(tabs.get(0));
                return "not found";
            }
            DriverAction.closeCurrentTab();
            DriverManager.getWebDriver().switchTo().window(tabs.get(0));
            return otp;
        }


    public static void refreshSuperAdminScreen() {
        DriverAction.refresh();
        DriverAction.waitUntilElementAppear(ObjSuperAdmin.edit_company, 10);
        DriverAction.doubleClick(Locators.sno, "S_NO");
    }

    public boolean getAllCompanyNames() {
        List<String> companyName = new ArrayList<>();
        List<String> projectCompanyName = new ArrayList<>();
        boolean flag = false;
        while (!DriverAction.isExist(ObjSuperAdmin.nextPage_btn_disabled)) {
            companyName.addAll(DriverAction.getElementsText(ObjSuperAdmin.AllCompanyNames));
        }
        DriverAction.click(Locators.admin);
        while (!DriverAction.isExist(ObjSuperAdmin.nextPage_btn_disabled)) {
            projectCompanyName.addAll(DriverAction.getElementsText(ObjSuperAdmin.allProjectCompanyNames));
        }
        for (int i = 0; i < companyName.size(); i++) {
            for (int j = 0; j < projectCompanyName.size(); j++) {
                System.out.println("a");
            }
        }
        return flag;
    }
}
