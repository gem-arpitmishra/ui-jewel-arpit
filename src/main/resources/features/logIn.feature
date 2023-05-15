Feature: Log In

  @smoke
  Scenario Outline: Launch login and logout jewel dashboard
    Given user clicks on logIn button and closes it
    Then user again clicks on logIn button and enters <Username> and <Password>
    Then user navigates back after loggin in
    Examples:
      | Username        | Password       |
      | jewelautomation | dummy_test@123 |


  Scenario: Validate the alert ,username and status when user login
    Given You are on the login screen
    Then Enter username as "jewelautomation"
    And Enter Password as 'dummy_test@123'
    Then Validate alert ,username and status of the window button

  Scenario: Validate not a user button
    Given You are on the login screen
    Then Click not a user button and validate navigation to signup screen

  @smoke
  Scenario: Login to Jewel (Negative)
    Given You are on the login screen
    Then Enter username as "abcd"
    And Enter Password
    Then Validate login is unsuccessful
    Then Enter username as "jewelautomation"
    And Enter incorrect password
    Then Validate login is unsuccessful