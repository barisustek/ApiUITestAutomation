Feature: Trello API test cases

  Scenario: TestCase_001 When POST /1/boards/ endpoint is called using the name parameter, then the board should be created.
    When POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard |
    Then Http status code should be 200 and "NewTestBoard" should return in response body

  Scenario: TestCase_002 When PUT /1/boards/ endpoint is called using boardID and parameters, then the board should be updated according to parameters.
    Given POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard_2 |
    And GET "id" from response body
    When PUT /1/boards/ endpoint is called for update board with board id and parameters
      | name   | NewTestBoard_Updated                              |
      | desc   | The board is full of unique features to be done   |
      | closed | true                                              |
    Then Http status code should be 200
    And GET "/1/boards/" endpoint is called with id and parameters
      | name   |
      | desc   |
      | closed |
    And Updated fields should be
      | name                 | desc                                              | closed |
      | NewTestBoard_Updated | The board is full of unique features to be done   | true   |

  Scenario: TestCase_003 When POST /1/boards/{id}/lists is called with board id, then the list should be created on a given board.
    Given POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard_2 |
    And GET "id" from response body
    When POST "/1/boards/{id}/lists" endpoint is called with board id and list parameters
      | name | TODOs          |
    Then Http status code should be 200
    And List fields should contains
      | name  |
      | TODOs |

  Scenario: TestCase_004 When POST /1/cards is called with lists id, then the card should be created on a given list.
    Given POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard_2 |
    And GET "id" from response body
    When POST "/1/boards/{id}/lists" endpoint is called with board id and list parameters
      | name | TODOs              |
    And GET "id" from response body
    And POST "/1/cards" endpoint is called with list id and card parameters
      | name | Great things to do |
    Then Http status code should be 200
    And Card fields should contains
      | name               |
      | Great things to do |

  Scenario: TestCase_005 When DELETE /1/boards/ is called with id, then the board should be deleted.
    Given POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard |
    And GET "id" from response body
    When DELETE "/1/boards/" endpoint is with board id
    Then Http status code should be 200