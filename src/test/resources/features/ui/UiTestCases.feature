#Ui test cases will be added to this feature.
Feature: Trello UI test automation cases

  Background:
    Given User opens trello home page
    And User clicks to log in
    And "https://trello.com/login" should be displayed
    And User enters username_info username
    And User clicks to login with Atlassian button
    And User enters password_info password
    And User clicks to login button

  Scenario Outline: TestCase_001 Users should create a board successfully through UI
    When User clicks to create button
    And User clicks to create board button
    And User sets <BoardTitle> to board title
    And User clicks to create submit board button
    Then <BoardTitle> should be created

    Examples:
      | BoardTitle |
      | testBoard  |

  Scenario Outline: TestCase_002 Users should create a card through Ui and control it through API
    When User clicks to create button
    And User clicks to create board button
    And User sets <BoardTitle> to board title
    And User clicks to create submit board button
    And <BoardTitle> should be created
    And User creates card to <List> list with <CardName> card description
    And GET /1/members/me/boards endpoint is called for board
    And GET /1/boards/{id}/cards/all endpoint is called
    And Check <CardName> card is created

    Examples:
      | BoardTitle | List  | CardName             |
      | testBoard  | To Do | Great features to do |

  Scenario Outline: TestCase_003 Users should create a card through Ui and update it through API
    When User clicks to create button
    And User clicks to create board button
    And User sets <BoardTitle> to board title
    And User clicks to create submit board button
    And <BoardTitle> should be created
    And User creates card to <List> list with <CardName> card description
    And GET /1/members/me/boards endpoint is called for board
    And GET /1/boards/{id}/cards/all endpoint is called
    And PUT /1/cards/{id} endpoint is called for update card with card id and parameters
      | desc | <Description> |
    And User clicks <CardName> in <List>
    Then Card description should equal to <Description>

    Examples:
      | BoardTitle | List  | CardName             | Description                                        |
      | testBoard  | To Do | Great features to do | This card is updated with a description using API  |

  Scenario Outline: TestCase_004 Users should create a card through Ui and move it to another List with API
    When User clicks to create button
    And User clicks to create board button
    And User sets <BoardTitle> to board title
    And User clicks to create submit board button
    And <BoardTitle> should be created
    And User creates card to <List> list with <CardName> card description
    And GET /1/members/me/boards endpoint is called for board
    And GET /1/boards/{id}/cards/all endpoint is called
    And GET /1/boards/{id}/lists endpoint is called with board Id
    And GET <NewList> list name
    And PUT /1/cards/{id} move card to new list with Id
    Then Http status code should be 200

    Examples:
      | BoardTitle | List  | CardName             | NewList |
      | testBoard  | To Do | Great features to do | Doing   |

