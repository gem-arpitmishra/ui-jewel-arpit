Feature: Log Out

  @smoke
  Scenario: Verify Logout Button
    Given You are on the login screen
    Then Enter username as "jewelautomation"
    And Enter Password as 'dummy_test@123'
    Then Verify Logout button is visible or not
    Then Click and verify the Logout button