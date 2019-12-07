@IntegrationTest
Feature: Move the player into trap

  Background: 
    Given the level design is:
      |XXXXXXX| 
      |X  T  X|
      |X     X|
      |XT P TX|
      |X     X|
      |X  T  X|
      |XXXXXXX|

  Scenario: Move left into trap
    When the player moves left
    Then the player is located at (3, 4)
    And trap is sprung 

  Scenario: Move right into trap
    When the player moves right
    Then the player is located at (5, 4)

  Scenario: Move up into trap
    When the player moves up
    Then the player is located at (4, 3)

  Scenario: Move down into trap
    When the player moves down
    Then the player is located at (4, 5)
