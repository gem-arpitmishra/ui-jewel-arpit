package Objects;

import org.openqa.selenium.By;

public class ObjForgotPassword {
    public static By username=By.xpath("//input[@id='usernameField']");
    public static By password=By.xpath("//input[@placeholder='Password']");
    public static By c_password=By.xpath("//input[@placeholder='Confirm Password']");
    public static By forgot_btn=By.xpath("//a[text()='Forgot Password?']");
    public static By backtoLogin_btn=By.xpath("//b[text()='Back to Login']");
    public static By submit_btn=By.xpath("//button[text()='Reset Password']");
    public static By outlook_login_formBtn=By.xpath("(//ul/li/a[text()='Sign in'])[1]");
    public static By outlook_username=By.xpath("//input[@type='email']");
    public static By outlook_password=By.xpath("//input[@type='password']");
    public static By outlook_search=By.xpath("//input[@aria-label='Search']");
    public static By outlook_nextBtn=By.xpath("//input[@id='idSIButton9']");
    public static By outlook_signIn=By.xpath("//input[@id='idSIButton9']");
    public static By outlook_NoBtn=By.xpath("//input[@value='No']");
    public static By outlook_time=By.xpath("(//div[text()='All results']/following-sibling::div/div/div/div/div/following-sibling::div/div/following-sibling::div/div/following-sibling::span)[1]");
    public static By outlook_email_open=By.xpath("((//div[@role='region'])[3]/div/div/div/div[@tabindex='-1'])[1]");
    public static By outlook_otp=By.xpath("//div[@aria-label='Message body']//b[2]");
    public static By otp_input=By.xpath("//input[@placeholder='One Time Password']");
    public static By confirm_btn=By.xpath("//div[text()='Confirm']");
    public static By outlook_email_notification=By.xpath("(//button/span/div/div[contains(text(),'OTP')])[1]");
}
