@IntegrationTest
Feature: Move the player into blocker space

  Background: 
    Given the level design is:
      | XXXXX |
      | X R X |
      | XEPTX |
      | X O X |
      | XXXXX |

  Scenario: Move left into blocker space
    When the player moves left
    Then the player is located at (50,50)

  Scenario: Move right into blocker space
    When the player moves right
    Then the player is located at (50,50)

  Scenario: Move up into blocker space
    When the player moves up
    Then the player is located at (50,50)

  Scenario: Move down into blocker space
    When the player moves down
    Then the player is located at (50,50)
