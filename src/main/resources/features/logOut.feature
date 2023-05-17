Feature: Log Out

  @smoke
  Scenario: Verify Logout Button
    Given Navigate to login-screen
    When Login to Jewel
    Then Verify Logout button visibility
    And Click and verify Logout button