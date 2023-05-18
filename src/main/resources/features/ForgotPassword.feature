Feature: ForgotPassword
  
  Background: Navigate to Forgot-Password-Screen
    Given Navigate to login-screen
    Then Click on Forgot-Password

  Scenario: Back to Login Button validation
    Then Click Back to Login button and validate navigation to login screen

  Scenario: Invalid username check
    When User fills incorrect username
    And Enter a "password@8723" and "password@8723"
    Then Verify reset password fails

  Scenario: Password and Password Confirmation equality check
    When User fills a "jewelautomation"
    And Enter a "password@8723" and "conirmation-password@8723"
    Then Verify reset password fails
    When Enter a "password@8723" and "password@8723"
    Then Verify reset password selection success

  Scenario: Random OTP Validation
    When User fills a "jewelautomation"
    And Enter a "dummy_test@123" and "dummy_test@123"
    Then Verify reset password selection success
    Then Validate OTP recognition

  @smoke
  Scenario: OTP Validation
    When User fills a "jewelOTP"
    And Enter a "dummy_test@123" and "dummy_test@123"
    Then Verify reset password selection success
    Then Validate if OTP recognition is successful