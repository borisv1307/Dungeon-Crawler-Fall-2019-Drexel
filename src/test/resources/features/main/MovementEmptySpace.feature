@IntegrationTest
Feature: Move the player into empty space

  Background: 
    Given the level design is:
      | XXXXXXXXX |
      | X       X |
      | X       X |
      | X   P   X |
      | X       X |
      | X       X |
      | XXXXXXXXX |

  Scenario: Move left into empty space
    When the player moves left
    Then the player is located at (2, 4)

  Scenario: Move right into empty space
    When the player moves right
    Then the player is located at (8, 4)

  Scenario: Move up into empty space
    When the player moves up
    Then the player is located at (5, 2)

  Scenario: Move down into empty space
    When the player moves down
    Then the player is located at (5, 6)
