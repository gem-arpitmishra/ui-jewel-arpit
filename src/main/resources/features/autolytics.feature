Feature: Autolytics

  @smoke
  Scenario:Click on the Autolytics button
    Given You are on the login screen
    Then Enter username as "jewelautomation"
    And Enter Password
    Then Click on the Autolytics screen

  Scenario: Verify The autolytics screen is opening or not
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the Autolytics Screen

  Scenario: Validate the Autolytics Card on the Home screen
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the Autolytics Cards present on the home screen

  Scenario: Validate the Autolytics Card Cotent
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Verify the Autolytics Card Content

  Scenario: Autolytics Screen
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Click on the Autolytics screen
    And Click on Create Report Tab
    Then Open few tabs 4
    And Click on SLide left button
    Then Click on the SLide most Right button
    And Click on the slide to next left button
    Then Click on the Slide to next right button
    And Validate the active tabs

  Scenario: Autolytics screen when there are no params passed in createReport
    Given You are on the login screen
    Then Enter username as "arpit.mishra"
    And Enter Password
    Then Click on the Autolytics screen
    And Click on createReport with no params

  Scenario Outline: Validating sorting on Autolytics columns
    Given click on login and enter <username> and <password>
    Then click on autolytics and create report
    Then select report name
    Then select project name
    Then select enviroment
    Then select date range and click on generate
    Then validate sorting of numbers
    Examples:
      | username     | password  |
      | arpit.mishra | arpit1234 |
#
  Scenario Outline: Validate filters of suite run report
    Given click on login and enters <usernames> and <passwords>
    Then click on autolytics and create report
    Then select report names
    Then select project names
    Then select enviroment
    Then select date range and click on generates
    Then click on copy button and check if report opens in new tab and click on new tab button and validate of shared report
    Then click on filter status and select pass
    Examples:
      | usernames       | passwords      |
      | jewelautomation | dummy_test@123 |
#
  @smoke
  Scenario Outline:Validate new filers of suite summary report
    Given click er clickon loginnn and entersss <usernamess> and <passwordss>
    Then validate enviroment filter
#    Then validate pie chart filter
    Examples:
      | usernamess      | passwordss     |
      | jewelautomation | dummy_test@123 |
#
  Scenario Outline:Validate the edit report button
    Given uss on loginnnn
    Then validate edit report button and check you're report selected has been generated or not <report_gen>
    Examples:
      | report_gen       |
      | Suite Run Report |
#
  Scenario Outline:Check if filters applied are still there on the shared report link
    Given usserr cicks onn loginnnn
    Then validate the filter on the share report <sharedReport>
    Examples:
      | sharedReport  |
      | Shared Report |
#
  Scenario Outline:Check for Suite Diagnose Report and validate A filter of it
    Given user logs in again
    Then Generates Suite Diagnose Report <sdr>
    Then validate filter
    Examples:
      | sdr                   |
      | Suite Diagnose Report |
#
  @fail
  Scenario Outline:Check for Test Diagnose Report and vaidate a filter of it
    Given user logs in again for Test Diagnose Report
    Then Generates Test Diagnose report <tdr>
    Then validates a filter
    Examples:
      | tdr                      |
      | Testcase Diagnose Report |
#
  Scenario Outline:Check for Testcase Run Report and validate a filter of it
    Given user logs in for Testcase Run Report
    Then Generates Testcase Run Report <trr>
    Then validatess a filter
    Examples:
      | trr                 |
      | Testcase Run Report |
#
  Scenario Outline:Check for Testcase Summary Report and validate filter of it
    Given user logs in for Testcase Summary Report
    Then Generates Testcase Summary Report <tsr>
    Then validate a filterr
    Examples:
      | tsr                     |
      | Testcase Summary Report |
#
#  Scenario Outline: Check the screenshot functionality and validate buttons
#    Given generate suite run report <username> and <password>
#    Then open execution report and validate button of screenshots
#    Examples:
#      | username     | password  |
#      | arpit.mishra | arpit1234 |

