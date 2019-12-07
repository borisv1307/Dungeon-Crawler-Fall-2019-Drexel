@IntegrationTest
Feature: Move the player into trap

  Background: 
    Given the level design is:
      |XXXXX| 
      |X T X|
      |XTPTX|
      |X T X|
      |XXXXX|

  Scenario: Move left into trap
    When the player moves left
    Then the player is located at (3, 3)

  Scenario: Move right into trap
    When the player moves right
    Then the player is located at (3, 3)

  Scenario: Move up into trap
    When the player moves up
    Then the player is located at (3, 3)

  Scenario: Move down into trap
    When the player moves down
    Then the player is located at (3, 3)
