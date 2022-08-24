#Ui test cases will be added to this feature.
Feature: Trello UI test automation cases

  Background:
    Given User lands on trello home page
    And User click log in
    And "https://trello.com/login" should be displayed
    And User enters testu7533@gmail.com username
    And User click login with atlassian button
    And User enters Trellotest7533 password
    And User click login button

  Scenario Outline: TestCase_001 Users should create a board successfully through UI
    When User click create button
    And User click create board button
    And User set <BoardTitle> board title
    And User click create submit board button
    Then <BoardTitle> should create

    Examples:
      | BoardTitle |
      | testBoard  |

  Scenario Outline: TestCase_002 Users should create a card successfully through UI
    When User click create button
    And User click create board button
    And User set <BoardTitle> board title
    And User click create submit board button
    And <BoardTitle> should create
    And User create card to <List> list with <Description> card description

    Examples:
      | BoardTitle | List  | Description |
      | testBoard  | To Do | desc        |
