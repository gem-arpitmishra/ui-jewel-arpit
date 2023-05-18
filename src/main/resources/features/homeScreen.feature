Feature: Home Screen


  Scenario: Verify Home screen is appearing or not
    Given You are on the login screen
    Then Enter username as "jewelautomation"
    And Enter Password as 'dummy_test@123'
    Then Verify the text of the Home screen

  Scenario: Validate the content of the Home screen
    Given You are on the login screen
    Then Enter username as "jewelautomation"
    And Enter Password as 'dummy_test@123'
    Then Verify the content of the Home screen

  Scenario: Validate the number of cards present on the Home screen
    Given You are on the login screen
    Then Enter username as "jewelautomation"
    And Enter Password as 'dummy_test@123'
    Then Verify the Cards present on the home screen

    Scenario: Validate navigation Bar collapse and expand