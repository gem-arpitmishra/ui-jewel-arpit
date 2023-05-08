Feature: ForgotPassword

   Scenario: Back to Login Button validation
    Given You are on the login screen and move to forgot password
    Then Click Back to Login button and validate navigation to login screen

  Scenario: Invalid username check
    Given You are on the login screen and move to forgot password
    When User fills incorrect username
    And Enter a "password@8723" and "password@8723"
    Then Verify reset password fails

  Scenario: Password and Password Confirmation equality check
    Given You are on the login screen and move to forgot password
    When User fills a "jewelautomation"
    And Enter a "password@8723" and "conirmation-password@8723"
    Then Verify reset password fails
    When Enter a "password@8723" and "password@8723"
    Then Verify reset password selection success

  Scenario: Random OTP Validation
    Given You are on the login screen and move to forgot password
    When User fills a "jewelautomation"
    And Enter a "dummy_test@123" and "dummy_test@123"
    Then Verify reset password selection success
    Then Validate OTP recognition

  Scenario: OTP Validation
    Given You are on the login screen and move to forgot password
    When User fills a "jewelautomation"
    And Enter a "dummy_test@123" and "dummy_test@123"
    Then Verify reset password selection success
    Then Validate if OTP recognition is successful