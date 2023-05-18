Feature: Log Out

  @smoke
  Scenario: Verify Logout Button
    Given Navigate to login-screen
    When Enter credentials for login
    And Click login
    Then Verify Logout button visibility
    And Click and verify Logout button