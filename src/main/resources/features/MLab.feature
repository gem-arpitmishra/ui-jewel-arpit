Feature: MLab

  Background: Navigate to MLab screen
    Given Navigate to login-screen
    Then Login to jewel
    Then Navigate to MLab screen

  Scenario: Create folder (root)
    Then Create new folder at root
    And Verify folder is created
    And Verify Creation time and user

  Scenario: Create folder with folder-name empty
    When Click on create folder
    Then Enter project name
    Then Click create
    And Verify folder creation is unsuccessful

  Scenario: Create folder with project-name empty
    When Click on create folder
    Then Enter folder-name
    Then Click create
    And Verify folder creation is unsuccessful

  Scenario: Create folder with all fields empty
    When Click on create folder
    Then Click create
    And Verify folder creation is unsuccessful

  Scenario: Create folder (nesting)
    Then Create new folder at root
    And Verify folder is created
    Then Navigate to folder
    Then Create new folder
    And Verify folder is created
    And Verify Creation time and user

  Scenario: Create testcase (root)
    Then Create new testcase at root
    And Verify testcase is created
    And Verify Creation time and user

  Scenario: Create testcase with testcase-name empty
    When Click on create testcase
    Then Enter project name
    Then Fill description
    Then Click create testcase
    And Verify testcase creation is unsuccessful


  Scenario: Create testcase with project-name empty
    When Click on create testcase
    Then Enter testcase-name
    Then Fill description
    Then Click create testcase
    And Verify testcase creation is unsuccessful

  Scenario: Create testcase with description empty
    When Click on create testcase
    Then Enter testcase-name
    Then Enter project name
    Then Click create testcase
    And Verify testcase creation is unsuccessful

  Scenario: Create testcase with all fields empty
    When Click on create testcase
    Then Click create testcase
    And Verify testcase creation is unsuccessful

  Scenario: Create testcase (nesting)
    Then Create new folder at root
    And Verify folder is created
    Then Navigate to folder
    Then Create new testcase
    And Verify creation time and user

  Scenario: Testcase creation (project selector disabled at nesting)
    Then Create a new folder at root
    And Verify folder is created
    Then Navigate to folder
    When Click on Create testcase
    Then Verify project-name selector is disabled

  Scenario: Verify edit testcase name is disabled
    Then Create new testcase at root
    And Verify testcase is created
    When Open testcase
    Then Move to properties tab
    When Click on edit testcase button
    Then Verify edit testcase-name input is disabled

  Scenario: Verify edit project name is disabled
    Then Create new testcase at root
    And Verify testcase is created
    When Open testcase
    Then Move to properties tab
    When Click on edit testcase button
    Then Verify edit project-name input is disabled

  Scenario: Testcase creation nested(Assigned to selector has users only specific to project selected)
    Then Then Create a new folder at root
    And Verify folder is created
    Then Navigate to folder
    When Click on Create testcase
    Then Fill required details
    Then Validate assigned user selector has values linked to project


  Scenario: Testcase views (All tabs loading)
    When Open a testcase
    Then Verify testcase opens at test-steps by default
    Then Move to properties tab
    Then Move to test-steps tab
    Then Move to attached-testcases tab
    Then Move to attached-requirements tab

  Scenario: Testcase editing (severity, priority, type, status editing, description)
    Then Create new testcase at root
    And Verify testcase is created
    When Open testcase
    Then Move to properties tab
    When Click on edit testcase button
    Then Edit severity,priority,type,status
    Then Click save testcase
    Then Verify changes are saved
    And Testcase updation time and user is updates
    And Verify testcase version increased
    When Click on edit testcase button
    Then Edit description
    Then Click save testcase
    Then Verify changes are saved
    And Testcase updation time and user is updates
    And Verify testcase version increased

  Scenario: Testcase editing (assigned user)
    Then Create new testcase at root
    And Verify testcase is created
    When Open testcase
    Then Move to properties tab
    When Click on edit testcase button
    Then edit assigned user
    Then Click save testcase
    Then Verify changes are saved
    And Testcase updation time and user is updates
    And Verify testcase version increased

  Scenario: Duplicate testcase name check
    Then Create new testcase at root
    And Verify testcase is created
    Then Create new testcase with same name
    Then Verify duplicate creation is unsuccessful

  Scenario: Duplicate folder name check
    Then Create new folder at root
    And Verify folder is created
    Then Create new folder with same name
    Then Verify duplicate creation is unsuccessful

    Scenario: Attach requirements
      Then Create new testcase at root
      And Verify testcase is created
      When Open testcase
      When Move to attached-requirements tab
      Then Verify no attachment is attached by default