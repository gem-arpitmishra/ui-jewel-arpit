Feature: Sign Up

  Scenario Outline: Validate sign up of already registered user
    Given click on signup
    Then enter "<name>" "<last>" "<user>" "<email>" "<pass>" "<cpass>" "<company>"
    Examples:
      | name  | last            | user            | email                    | pass           | cpass          | company  |
      | jewel | Automation user | jewelautomation | dummy_test54@outlook.com | dummy_test@123 | dummy_test@123 | Jewel123 |

  Scenario: Signup screen
    Given You are on the Sign up screen
    Then Click on the Sign up Button
    And Enter random username
    Then Enter all the fields and Validate the status

  Scenario: Already registered button
    Given click on signup
    Then Click already registered button and validate navigated to login page

  Scenario: SignUp with empty fields (negative)
    Given click on signup
    Then Click register and validate if signUp is unsuccessful

  Scenario Outline: SignUp with single empty field (negative)
    Given click on signup
    When Fill fields "<firstname>","<lastname>","<username>","<email>","<password>","<confirmpassword>","<Random>"
    Then Click register and validate if signUp is unsuccessful
    Examples:
      | firstname | lastname | username | email        | password | confirmpassword | Random |
      |           | abc      | abc.abc  | a1bc@abc.com | Abcd@000 | Abcd@000        | N      |
      | abc       |          | abc.abc  | abc@abc.com  | Abcd@000 | Abcd@000        | N      |
      | abc       | abc      |          | abc@abc.com  | Abcd@000 | Abcd@000        | N      |
      | abc       | abc      | abc.abc  |              | Abcd@000 | Abcd@000        | N      |
      | abc       | abc      | abc.abc  | abc@abc.com  |          | Abcd@000        | N      |
      | abc       | abc      | abc.abc  | abc@abc.com  | Abcd@000 |                 | N      |

  Scenario: Username Availability Validation (negative)
    Given click on signup
    When Fill fields "jewel","Automation user","","dummy_test54@outlook.com","dummy_test@123","dummy_test@123","N"
    Then check username availability for user already registered
    Then Click register and validate if signUp is unsuccessful

  Scenario: Username Availability Validation (positive)
    Given click on signup
    When Fill fields "jewel","Automation user","","dummy_test54@outlook.com","dummy_test@123","dummy_test@123","Y"
    Then check username availability for user not already registered
    Then Click register and validate if signUp is successful

  Scenario: Username warning sign disappearing check
    Given click on signup
    Then Validate username availability sign appears on focusing on username field and disappears if it goes out of focus


  Scenario: Password strength and suggestion dialog
    Given click on signup
    Then Password strength dialog appears on focusing on password field and disappears if it goes out of focus

  Scenario: confirmation password equality check
    Given click on signup
    When Fill fields "jewel","Automation user","","dummy_test54@outlook.com","dummy_test@123","dummy_test@123","Y"
    When User enters "different" passwords in password and confirmation-password
    Then Click register and validate if signUp is unsuccessful
    When User enters "dummy_test@123" passwords in password and confirmation-password
    Then Click register and validate if signUp is successful

  Scenario: Username Case sensitivity check
    Given click on signup
    Then check username availability for user already registered
    Then Change username case and check username availability

  @smoke
  Scenario: SignUp (positive)
    Given click on signup
    When Fill fields "Test","user","","@gemperf.com","dummy_test@123","dummy_test@123","Y"
    Then Click register and validate if signUp is successful