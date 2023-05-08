Feature: Test Tool

  Background: Jewel Login
    Given User login into Jewel Portal with valid credentials
      | username     | password     |
      | maulick_test | QXZhbmkwMDAx |
    When Navigate to "TestTool" Section

  Scenario: Jewel-TestTool : Page Loading validation
    Then Verify user is navigated to TestTool Suite Page
#
  Scenario: Jewel-TestTool : Total suite count validation
    Then Verify user is navigated to TestTool Suite Page
    And Verify the displayed suite count

  Scenario: Jewel-TestTool : Pagination validation
    When Verify user is navigated to TestTool Suite Page
    And Click on "next" pagination button
    Then Verify the "next" page displayed
    And Click on "previous" pagination button
    And Verify the "previous" page displayed

  Scenario Outline: Jewel-TestTool : Number of suites visible on page check
    When Verify user is navigated to TestTool Suite Page
    And Select display count "<count>" from filter
    Then Verify the number of suites displayed should be less than or equal to selected count

    Examples:
      | count |
      | 50    |

#  Scenario Outline: Jewel-TestTool : Suite sorting validation
#    When Verify user is navigated to TestTool Suite Page
#    And Click on header "<headerName>" to sort in sorting order "<order>"
#    Then Verify suite column should be in "<order>" order
#
#    Examples:
#      | headerName     | order      |
#      | Suite Name     | ascending  |
#      | Testcase Count | descending |

  Scenario Outline: Jewel-TestTool : Suite sorting column shift check
    When Verify user is navigated to TestTool Suite Page
    And Click on header "<headerName1>" to sort in sorting order "<order>"
    Then Click on header "<headerName2>" to sort in sorting order "<order>"
    And Verify sorting has been shifted to "<headerName2>" from "<headerName1>"

    Examples:
      | headerName1 | headerName2    | order     |
      | Suite Name  | Testcase Count | ascending |

  Scenario Outline: Jewel-TestTool : Filter Option Validation when all suites are checked
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "<headerName>"
    Then Select "<options>" options from the filter list
    And Verify all suites are visible inside suite table

    Examples:
      | headerName | options |
      | Suite Name | all     |

  Scenario Outline: Jewel-TestTool : Filter Option Validation when given suites are checked
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "<headerName>"
    Then Select "<options>" options from the filter list
    And Verify selected options "<options>" are visible for header "<headerName>" inside suite table

    Examples:
      | headerName | options                |
      | Suite Name | TEST,MANUAL TEST,TESTF |

  Scenario Outline: Jewel-TestTool : Filter Option Validation when multiple filters are selected
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "<headerName1>"
    Then Select "<options1>" options from the filter list
    And Click on filter for header "<headerName2>"
    Then Select "<options2>" options from the filter list
    And Verify selected options "<options1>" and "<options2>" are visible for header "<headerName1>" and"<headerName2>" inside suite table
    Examples:
      | headerName1 | options1               | headerName2 | options2  |
      | Suite Name  | TEST,MANUAL TEST,TESTF | Suite ID    | 1215,1160 |

  Scenario: Jewel-TestTool : Filter Option Available Project Validation
    Given Navigate to "Admin" Section
    And Get the total count of projects
    When Navigate to "TestTool" Section
    And Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Project Name"
    Then Verify the total available project

  Scenario Outline: Jewel-TestTool : Create new suite and verify created suite
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Select project name "<projectName>" options for creating suite
    And Enter suite name "<suiteName>" for creating suite
    And Click on create suite button
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Verify selected options "<suiteName>" and "<projectName>" are visible for header "Suite Name" and"Project Name" inside suite table

    Examples:
      | suiteName | projectName  |
      | random    | TEST-PROJECT |

  Scenario Outline: Jewel-TestTool : Create suite with duplicate validation
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Select project name "<projectName>" options for creating suite
    And Enter suite name "<suiteName>" for creating suite
    And Click on create suite button
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Verify duplicates suites for the same "<suiteName>"

    Examples:
      | suiteName | projectName  |
      | SK_TEST   | TEST-PROJECT |

  Scenario Outline: Jewel-TestTool : Create suite search project matched validation
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Enter project name "<projectName>" options for creating suite
    And Verify searched project name "<projectName>" "matched"

    Examples:
      | projectName  |
      | TEST-PROJECT |

  Scenario Outline: Jewel-TestTool : Create suite search project not matched validation
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Enter project name "<projectName>" options for creating suite
    And Verify searched project name "<projectName>" "not matched"

    Examples:
      | projectName |
      | XYZ         |

  Scenario Outline: Jewel-TestTool : Testcase pill visibility
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab

    Examples:
      | suiteName |
      | SK_TEST   |

  Scenario Outline: Jewel-TestTool : Suite-testcase count validation
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    Then Get the testcase count of "<suiteName>"
    And Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Verify the total test case count on test case tab

    Examples:
      | suiteName |
      | TESTING   |

  Scenario Outline: Jewel-TestTool : Delete testcase - validation after clicking NO button
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on delete button to delete the test case
    And Verify the alert box with message "<message>"
    And Click on "No" Button for deleting test case
    And Select testID from the filter for test case
    And Verify the test case should not be deleted

    Examples:
      | suiteName      | message                                  |
      | DELETE_TESTING | Are you Sure you want to delete Testcase |

  Scenario Outline: Jewel-TestTool : Delete testcase - validation after clicking YES button
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on delete button to delete the test case
    And Verify the alert box with message "<message>"
    And Click on "YES" Button for deleting test case
    And Verify deleted toast alert with message "<toast_message>"

    Examples:
      | suiteName      | message                                  | toast_message                  |
      | DELETE_TESTING | Are you Sure you want to delete Testcase | Test Case deleted Successfully |

  Scenario Outline: Jewel-TestTool : Create Suite and validate Suite Creation time
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Select project name "<projectName>" options for creating suite
    And Enter suite name "<suiteName>" for creating suite
    And Click on create suite button
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Verify creation time of the suite

    Examples:
      | suiteName | projectName  |
      | random    | TEST-PROJECT |

#  Scenario Outline: Jewel-TestTool : Update Suite and validate Suite Updation time
#    When Verify user is navigated to TestTool Suite Page
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    Then Click on test case plus button for "<suiteName>"
#    And Verify the suite name "<suiteName>" on test case tab
#    And Click on create test case button
#    And Select GemJAR as a base project
#    And Enter testcase name "<testcaseName>" to create test
#    And Select testcase type "<testcaseType>" to create test
#    And Enter testcase steps "<testcaseSteps>" to create test
#    And Enter category "<category>" to create test
#    And Select run flag "<runFlag>" to create test
#    And Click on create test case button for creating new test case
#    And Navigate back to test suite page
#    And Verify updation time of the suite
#
#    Examples:
#      | suiteName | projectName  | testcaseName | testcaseType | testcaseSteps        | category | runFlag |
#      | SK_TEST   | TEST-PROJECT | sample       | Scenario     | Given Launch browser | launch   | Y       |

#  Scenario Outline: Jewel-TestTool : Create testcase and select base project as GemJAR
#    When Verify user is navigated to TestTool Suite Page
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    Then Click on test case plus button for "<suiteName>"
#    And Verify the suite name "<suiteName>" on test case tab
#    And Click on create test case button
#    And Select GemJAR as a base project
#    And Enter testcase name "<testcaseName>" to create test
#    And Select testcase type "<testcaseType>" to create test
#    And Enter testcase steps "<testcaseSteps>" to create test
#    And Enter category "<category>" to create test
#    And Select run flag "<runFlag>" to create test
#    And Click on create test case button for creating new test case
#    And Verify create test case alert with message "<toast_message>"
#
#    Examples:
#      | suiteName      | projectName  | testcaseName | testcaseType | testcaseSteps        | category | runFlag | toast_message                  |
#      | DELETE_TESTING | TEST-PROJECT | sample       | Scenario     | Given Launch browser | launch   | Y       | Test Case created Successfully |

  Scenario Outline: Jewel-TestTool : Create testcase and select base project as GemPYP
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on create test case button
    And Select GemPYP as a base project
    And Enter testcase name "<testcaseName>" to create test
    And Enter category "<category>" to create test
    And Select testcase type "<testcaseType>" to create test
    And Select run flag "<runFlag>" to create test
    And Click on create test case button for creating new test case
    And Verify create test case alert with message "<toast_message>"

    Examples:
      | suiteName      | projectName  | testcaseName | testcaseType | category | runFlag | toast_message                  |
      | DELETE_TESTING | TEST-PROJECT | sample       | GEMPYP       | launch   | Y       | Test Case created Successfully |

  Scenario Outline: Jewel-TestTool : Create testcase with base project GemJAR and validate Test Type search dropdown
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on create test case button
    And Select GemJAR as a base project
    And Enter testcase type "<testcaseType>" to search test type
    And Verify the selected testcase type "<testcaseType>"
    Examples:
      | suiteName      | projectName  | testcaseType |
      | DELETE_TESTING | TEST-PROJECT | Scenario     |

  Scenario Outline: Jewel-TestTool : Create testcase with base project GemPYP and validate Test Type search dropdown
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on create test case button
    And Select GemPYP as a base project
    And Enter testcase type "<testcaseType>" to search test type
    And Verify the selected testcase type "<testcaseType>"
    Examples:
      | suiteName      | projectName  | testcaseType |
      | DELETE_TESTING | TEST-PROJECT | GEMPYP       |

  Scenario Outline: Jewel-TestTool : Create testcase with base project GemJAR and empty fields
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on create test case button
    And Select GemJAR as a base project
    And Click on create test case button for creating new test case
    And Verify create test case alert with message "<toast_message>"
    Examples:
      | suiteName      | projectName  | toast_message           |
      | DELETE_TESTING | TEST-PROJECT | All fields are Required |

  Scenario Outline: Jewel-TestTool : Create testcase with base project GemPYP and empty fields
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on create test case button
    And Select GemPYP as a base project
    And Click on create test case button for creating new test case
    And Verify create test case alert with message "<toast_message>"
    Examples:
      | suiteName      | projectName  | toast_message                                    |
      | DELETE_TESTING | TEST-PROJECT | Testcase Name, Category and Type Cannot be Empty |

  Scenario Outline: Jewel-TestTool : Create testcase with base project GemJAR and single empty fields
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on create test case button
    And Select GemJAR as a base project
    And Enter testcase name "<testcaseName>" to create test
    And Enter testcase steps "<testcaseSteps>" to create test
    And Enter category "<category>" to create test
    And Select run flag "<runFlag>" to create test
    And Click on create test case button for creating new test case
    And Verify create test case alert with message "<toast_message>"
    Examples:
      | suiteName      | projectName  | toast_message           | testcaseName | testcaseSteps        | category | runFlag |
      | DELETE_TESTING | TEST-PROJECT | All fields are Required | sample       | Given Launch browser | launch   | Y       |

  Scenario Outline: Jewel-TestTool : Create testcase with base project GemPYP and single empty fields
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Click on create test case button
    And Select GemJAR as a base project
    And Enter testcase name "<testcaseName>" to create test
    And Enter category "<category>" to create test
    And Select run flag "<runFlag>" to create test
    And Click on create test case button for creating new test case
    And Verify create test case alert with message "<toast_message>"
    Examples:
      | suiteName      | projectName  | toast_message           | testcaseName | category | runFlag |
      | DELETE_TESTING | TEST-PROJECT | All fields are Required | sample       | launch   | Y       |

  Scenario Outline: Jewel-TestTool : Verify Suite name on testcase screen
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab

    Examples:
      | suiteName |
      | SK_TEST   |

#  Scenario Outline: Jewel-TestTool : Validate Test Description on test case screen
#    When Verify user is navigated to TestTool Suite Page
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    Then Click on test case plus button for "<suiteName>"
#    And Verify the suite name "<suiteName>" on test case tab
#    And Click on expand button of testcase
#    And Verify testcase details for given test case
#
#    Examples:
#      | suiteName      | projectName  |
#      | DELETE_TESTING | TEST-PROJECT |

  Scenario Outline: Jewel-TestTool : Create Suite and validate Suite Creation username
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Select project name "<projectName>" options for creating suite
    And Enter suite name "<suiteName>" for creating suite
    And Click on create suite button
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Verify suite creation username

    Examples:
      | suiteName | projectName  |
      | random    | TEST-PROJECT |

#  Scenario Outline: Jewel-TestTool : Update Suite and validate Suite Updation username
#    When Verify user is navigated to TestTool Suite Page
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    Then Click on test case plus button for "<suiteName>"
#    And Verify the suite name "<suiteName>" on test case tab
#    And Click on create test case button
#    And Select GemJAR as a base project
#    And Enter testcase name "<testcaseName>" to create test
#    And Select testcase type "<testcaseType>" to create test
#    And Enter testcase steps "<testcaseSteps>" to create test
#    And Enter category "<category>" to create test
#    And Select run flag "<runFlag>" to create test
#    And Click on create test case button for creating new test case
#    And Navigate back to test suite page
#    And Verify suite updation username
#
#    Examples:
#      | suiteName | projectName  | testcaseName | testcaseType | testcaseSteps        | category | runFlag |
#      | SK_TEST   | TEST-PROJECT | sample       | Scenario     | Given Launch browser | launch   | Y       |

  Scenario Outline: Jewel-TestTool : Edit a testcase and verify the details on edit screen
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    Then Click on test case plus button for "<suiteName>"
    And Verify the suite name "<suiteName>" on test case tab
    And Get all the details of selected testcase
    And Click on edit button of testcase
    And Verify edit details for given test case

    Examples:
      | suiteName      | projectName  |
      | DELETE_TESTING | TEST-PROJECT |

#  Scenario Outline: Jewel-TestTool : Edit a testcase and verify the error while having empty field
#    When Verify user is navigated to TestTool Suite Page
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    Then Click on test case plus button for "<suiteName>"
#    And Verify the suite name "<suiteName>" on test case tab
#    And Get all the details of selected testcase
#    And Click on edit button of testcase
#    And Verify edit details for given test case
#    And Clear category field to make empty
#    And Click on save button to update the test case
#    And Verify create test case alert with message "<toast_message>"
#
#    Examples:
#      | suiteName      | projectName  | toast_message           |
#      | DELETE_TESTING | TEST-PROJECT | All fields are Required |

#  Scenario Outline: Jewel-TestTool : Edit test run flag to N and validate in test execution
#    When Verify user is navigated to TestTool Suite Page
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    Then Click on test case plus button for "<suiteName>"
#    And Verify the suite name "<suiteName>" on test case tab
#    And Get all the details of selected testcase
#    And Click on edit button of testcase
#    And Verify edit details for given test case
#    And Select run flag "<runFlag>" to create test
#    And Click on save button to update the test case
#    And Verify create test case alert with message "<toast_message>"
#    And Get the executable test case count
#    And Navigate back to test suite page
#    And Click on filter for header "Suite ID"
#    And Select suiteID options from the filter list
#    And Click on execute button to execute suite
#    And Select execution run mode "<mode>"
#    And Select execution environment "<environment>"
#    And Click on execute button
#    And Open generated report link
#    And Verify the total executable test case count
#
#    Examples:
#      | suiteName     | projectName  | toast_message                 | runFlag | mode     | environment |
#      | RUN_FLAG_TEST | TEST-PROJECT | TestCase updated Successfully | N       | Sequence | Beta        |

  Scenario Outline: Jewel-TestTool : Execute suite having all Run Flag N
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Testcase Count"
    And Select "<testCount>" options from the filter list
    And Get the suite ID
    And Click on execute button to execute suite
    And Verify create test case alert with message "<toast_message>"

    Examples:
      | testCount | toast_message                                                     |
      | 0         | 0 Testcase(s) Found. Create/Add new Testcase(s) to execute Suite. |

  Scenario Outline: Jewel-TestTool : Execute empty suite
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Click on execute button to execute suite
    And Select execution run mode "<mode>"
    And Select execution environment "<environment>"
    And Click on execute button
    And Verify create test case alert with message "<toast_message>"

    Examples:
      | suiteName      | projectName  | toast_message         | mode     | environment |
      | RUN_FLAG_ALL_N | TEST-PROJECT | Something Went Wrong! | Sequence | Beta        |

  Scenario Outline: Jewel-TestTool : Execute GemPYP Test Cases
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Click on execute button to execute suite
    And Select execution run mode "<mode>"
    And Select execution environment "<environment>"
    And Click on execute button
    And Open generated report link
    And Report should open in new tab

    Examples:
      | suiteName    | projectName  | mode     | environment |
      | GEMPYP_TESTS | TEST-PROJECT | Sequence | Beta        |

#  Scenario Outline: Jewel-TestTool : Execute Suite having GemPYP and GemJAR Test Cases
#    When Verify user is navigated to TestTool Suite Page
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    And Click on execute button to execute suite
#    And Select execution run mode "<mode>"
#    And Select execution environment "<environment>"
#    And Click on execute button
#    And Open generated report link
#    And Report should open in new tab
#
#    Examples:
#      | suiteName      | projectName  | mode     | environment |
#      | DELETE_TESTING | TEST-PROJECT | Sequence | Beta        |

  Scenario Outline: Jewel-TestTool : Add testcases via Git
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Select project name "<projectName>" options for creating suite
    And Enter suite name "<suiteName>" for creating suite
    And Click on create suite button
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Click on git button to add test cases from git
    And Enter the github URL "<gitURL>"
    And Click on integrate Git button
    And Verify alert with message "Fetching Testcase(s)!!"
    And Wait for navigating to Test case screen
    And Verify alert with message "Jar file creation started successfully"
    And Navigate back to test suite page
    And Refresh the suite page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Verify git button should start blinking
    And Click on git button to validating the jar process
    And Verify new buttons "<buttons>" should be added into integrate git modal

    Examples:
      | suiteName | projectName  | gitURL                                         | buttons                                 |
      | random    | TEST-PROJECT | https://github.com/gem-maulickbharadwaj/mis-ui | Remove Git,Reload JAR,Update/Reload Git |

  Scenario Outline: Jewel-TestTool : Remove Git
    When Verify user is navigated to TestTool Suite Page
    And Click on create suite button for creating new suite
    Then Select project name "<projectName>" options for creating suite
    And Enter suite name "<suiteName>" for creating suite
    And Click on create suite button
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Click on git button to add test cases from git
    And Enter the github URL "<gitURL>"
    And Click on integrate Git button
    And Verify alert with message "Fetching Testcase(s)!!"
    And Wait for navigating to Test case screen
    And Verify alert with message "Jar file creation started successfully"
    And Navigate back to test suite page
    And Refresh the suite page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Verify git button should start blinking
    And Click on git button to validating the jar process
    And Verify new buttons "<buttons>" should be added into integrate git modal
    And click on Remove Git button
    And Verify alert with message "Git Unlinked from Suite Id"

    Examples:
      | suiteName | projectName  | gitURL                                         | buttons                                 |
      | random    | TEST-PROJECT | https://github.com/gem-maulickbharadwaj/mis-ui | Remove Git,Reload JAR,Update/Reload Git |


#  Scenario Outline: Jewel-TestTool : Add testcases via Git having no feature file
#    When Verify user is navigated to TestTool Suite Page
#    And Click on create suite button for creating new suite
#    Then Select project name "<projectName>" options for creating suite
#    And Enter suite name "<suiteName>" for creating suite
#    And Click on create suite button
#    And Click on filter for header "Suite Name"
#    And Select "<suiteName>" options from the filter list
#    And Click on filter for header "Project Name"
#    And Select "<projectName>" options from the filter list
#    And Click on git button to add test cases from git
#    And Enter the github URL "<gitURL>"
#    And Click on integrate Git button
#    And Verify alert with message "Feature files not found !!"
#
#    Examples:
#      | suiteName | projectName  | gitURL                                       |
#      | random    | TEST-PROJECT | https://github.com/gem-shubhamkumar/Demo.git |

  Scenario Outline: Jewel-TestTool : Add testcases via Git, when suite has already having testcase
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Click on git button to add test cases from git
    And Enter the github URL "<gitURL>"
    And Click on integrate Git button
    And Verify alert with message "Fetching Testcase(s)!!"
    And Wait for navigating to Test case screen
    And Verify alert with message "Jar file creation started successfully"
    And Navigate back to test suite page
    And Refresh the suite page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Verify git button should start blinking
    And Click on git button to validating the jar process
    And Verify new buttons "<buttons>" should be added into integrate git modal
    And click on Remove Git button
    And Verify alert with message "Git Unlinked from Suite Id"

    Examples:
      | suiteName  | projectName  | gitURL                                         | buttons                                 |
      | SUITE_6070 | TEST-PROJECT | https://github.com/gem-maulickbharadwaj/mis-ui | Remove Git,Reload JAR,Update/Reload Git |

  Scenario Outline: Jewel-TestTool : Add and Remove Emails
    When Verify user is navigated to TestTool Suite Page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Click on email button to add email functionality
    And Enter the email TO "<emailTO>"
    And Click on Save button
    And Verify alert with message "emails added successfully"
    And Refresh the suite page
    And Click on filter for header "Suite Name"
    And Select "<suiteName>" options from the filter list
    And Click on filter for header "Project Name"
    And Select "<projectName>" options from the filter list
    And Click on email button to add email functionality
    And Click on Unlink Email button
    And Verify alert with message "Saved Emails deleted successfully"

    Examples:
      | suiteName  | projectName  | emailTO        |
      | SUITE_8886 | TEST-PROJECT | test@gmail.com |
