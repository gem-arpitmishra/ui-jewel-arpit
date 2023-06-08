Feature: Sign Up

  Background: Navigate to sign-up screen
    Given click on signup

  Scenario: Validate sign up of already registered user
    Then Fill all fields
    Then Click Register
    Then Validate if signUp is unsuccessful

  Scenario: Already registered button
    Then Click already registered button and validate navigated to login page

  Scenario: SignUp with empty fields (negative)
    Then Click Register
    Then Validate if signUp is unsuccessful

  Scenario Outline: SignUp with single empty field (negative)
    When Fill fields "<firstname>","<lastname>","<username>","<email>","<password>","<confirmpassword>","<Random>"
    Then Click Register
    Then Validate if signUp is unsuccessful
    Examples:
      | firstname | lastname | username | email        | password | confirmpassword | Random |
      |           | abc      | abc.abc  | a1bc@abc.com | Abcd@000 | Abcd@000        | N      |
      | abc       |          | abc.abc  | abc@abc.com  | Abcd@000 | Abcd@000        | N      |
      | abc       | abc      |          | abc@abc.com  | Abcd@000 | Abcd@000        | N      |
      | abc       | abc      | abc.abc  |              | Abcd@000 | Abcd@000        | N      |
      | abc       | abc      | abc.abc  | abc@abc.com  |          | Abcd@000        | N      |
      | abc       | abc      | abc.abc  | abc@abc.com  | Abcd@000 |                 | N      |

  Scenario: Username Availability Validation (negative)
    When Fill fields "jewel","Automation user","","dummy_test54@gemperf.com","dummy_test@123","dummy_test@123","N"
    Then check username availability for user already registered
    Then Click Register
    Then Validate if signUp is unsuccessful

  Scenario: Username Availability Validation (positive)
    When Fill fields "jewel","Automation user","","dummy_test54@gemperf.com","dummy_test@123","dummy_test@123","Y"
    Then check username availability for user not already registered
    Then Click Register
    Then Validate if signUp is successful

  Scenario: Username warning sign disappearing check
    Then Validate username availability sign appears on focusing on username field and disappears if it goes out of focus


  Scenario: Password strength and suggestion dialog
    Then Password strength dialog appears on focusing on password field and disappears if it goes out of focus

  Scenario: Confirmation password equality check
    When Fill fields "jewel","Automation user","","dummy_test54@gemperf.com","dummy_test@123","dummy_test@123","Y"
    When User enters "different" passwords in password and confirmation-password
    Then Click Register
    Then Validate if signUp is unsuccessful
    When User enters "dummy_test@123" passwords in password and confirmation-password
    Then Click Register
    Then Validate if signUp is successful

  Scenario: Username Case sensitivity check
    Then check username availability for user already registered
    Then Change username case and check username availability

  @smoke
  Scenario: SignUp (positive)
    When Fill fields "Test","user","","@gemperf.com","Dummy_test@123","Dummy_test@123","Y"
    Then Click Register
    Then Validate if signUp is successful