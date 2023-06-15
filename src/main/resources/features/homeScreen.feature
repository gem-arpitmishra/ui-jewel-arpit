Feature: Home Screen

  Background: Navigate to Home Screen
    Given Navigate to login-screen
    Then Login to jewel

  Scenario: Verify Home screen is appearing or not
    Then Verify Home screen loading

  Scenario: Validate the content of the Home screen
    Then Verify the content of the Home screen

  Scenario: Validate the number of cards present on the Home screen
    Then Verify the Cards present on the home screen
    Then Validate card buttons navigate to corresponding page

    Scenario: Validate navigation Bar collapse and expand