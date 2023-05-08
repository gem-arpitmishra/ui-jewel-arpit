Feature: Log In

  Scenario Outline: Launch login and logout jewel dashboard
    Given user clicks on logIn button and closes it
    Then user again clicks on logIn button and enters <Username> and <Password>
    Then user navigates back after loggin in
    Examples:
      | Username     | Password  |
      | arpit.mishra | arpit1234 |

  Scenario: Open a new tab in Ignito Mode
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    Then Enter Password
    And Logout the Account

  Scenario: Validate the alert ,username and status when user login
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Validate alert ,username and status of the window button
#
  Scenario: Validate not a user button
    Given You are on the login screen
    Then Click not a user button and validate navigation to signup screen

  Scenario: Login to Jewel (Negative)
    Given You are on the login screen
    Then Enter username as "abcd"
    And Enter Password
    Then Validate login is unsuccessful
    Then Enter username as "arpit.mishra"
    And Enter incorrect password
    Then Validate login is unsuccessful