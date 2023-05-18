Feature: Log In

  Background: Navigate to login-screen
    Given Navigate to login-screen

  @smoke
  Scenario: Login to Jewel (Positive)
    When Enter credentials for login
    And Click login
    Then Validate login is successful

  Scenario: Validate not a user button
    Then Click not a user button and validate navigation to signup screen

  @smoke
  Scenario: Login to Jewel (Negative)
    Then User fills incorrect username
    And Enter random password
    Then Validate login is unsuccessful
    Then Enter username as "jewelOTP"
    And Enter random password
    Then Validate login is unsuccessful