Feature: Home Screen


  Scenario: Verify Home screen is appearing or not
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the text of the Home screen

  Scenario: Validate the content of the Home screen
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the content of the Home screen

  Scenario: Validate the number of cards present on the Home screen
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the Cards present on the home screen