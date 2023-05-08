Feature: Log Out

  Scenario: Verify Logout Button
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify Logout button is visible or not
    Then Click and verify the Logout button