package Functions;

import com.gemini.generic.reporting.GemEcoUpload;
import com.gemini.generic.ui.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Functions_Admin extends GemEcoUpload {
    public static boolean elementClickable(By locator){
        try{
            WebDriverWait wait=new WebDriverWait(DriverManager.getWebDriver(),10);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
