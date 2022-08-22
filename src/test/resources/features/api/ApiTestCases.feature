#TODO : 3 of 5 cases were added. Two more to go.
Feature: Trello api boards test cases

  Scenario: TestCase_001 When POST /1/boards/ endpoint called using name parameter, then board should be new board created
    When POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard |
    Then Http status code should be 200 and "NewTestBoard" should return in response body

  Scenario: TestCase_002 When PUT /1/boards/ endpoint called using boardID and parameters, then board should be updated according to parameters
    Given POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard_2 |
    And GET "id" from response body
    When PUT "/1/boards/" endpoint is called with board id and parameters
      | name   | NewTestBoard_Updated                              |
      | desc   | Board that is full of amazing features to be done |
      | closed | true                                              |
    Then Http status code should be 200
    And GET "/1/boards/" endpoint is called id and parameters
      | name   |
      | desc   |
      | closed |
    And Updated fields should be
      | name                 | desc                                              | closed |
      | NewTestBoard_Updated | Board that is full of amazing features to be done | true   |

  Scenario: TestCase_003 When DELETE /1/boards/ is called with id, then board should be deleted
    Given POST "/1/boards/" endpoint is called with parameters
      | name | NewTestBoard |
    And GET "id" from response body
    When DELETE "/1/boards/" endpoint is with board id
    Then Http status code should be 200