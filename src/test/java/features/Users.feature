Feature: Users

  @users
  Scenario: Select last user
    Given The Users screen is displayed
    When The user selects the last user
    Then The User Details screen is displayed
    And The details about the selected user are shown

  @users
  Scenario: Check sections
    Given The user selects the first user
    When The User Details screen is displayed
    Then The following sections are shown
      | Albums |
      | Todo's |
      | Posts  |

  @users
  Scenario: Select last Todo
    Given The user selects the first user
    When The User Details screen is displayed
    Then The user selects the last todo
    And The last todo is selected

  @users
  Scenario: Return to User Screen
    Given The user selects the first user
    When The User Details screen is displayed
    Then The user goes back to the User Screen
    And The Users screen is displayed