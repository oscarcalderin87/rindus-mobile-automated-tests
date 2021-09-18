Feature: Todo's

  Background:
    Given The user selects the first user
    And The user access to the section "Todo's"
    And The Todo's screen is displayed

  @todos
  Scenario: Select task
    When The user selects the first task
    Then The first task is selected

  @todos
  Scenario: Add task
    When The user creates a new task
    Then A message with the text "Todo created successfully" is displayed
    And The new task is displayed in the list

  @todos
  Scenario: Delete task
    When The user creates a new task
    And The user deletes the created task
    Then A message with the text "Todo deleted successfully" is displayed
    And The created task was removed from the list

