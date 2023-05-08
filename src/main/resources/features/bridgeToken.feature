Feature: Bridge Token

  Scenario: Validate the Bridge Token Card Present on the home screen
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify if the Bridge token card is visible

  Scenario: Validate the content of Bridge Token Card
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Validate the content of the Bridge Token card

  Scenario: Check if the change token button is clickable or not
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Validate if there is change token button available ,if so click it

  Scenario: Verify The Bridge Token screen is opening or not
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the Bridge Token Button is clickable or not

  Scenario: Click on copy Bridge Token
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Click on Copy Bridge Token

  Scenario: Check the alert
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the Alert when copy button is clicked

  Scenario: Check the alert of Change Token
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the Alert when Change Token button is clicked

  Scenario: Check the alert and time of Change Token button
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the date and time when Change Token button is clicked