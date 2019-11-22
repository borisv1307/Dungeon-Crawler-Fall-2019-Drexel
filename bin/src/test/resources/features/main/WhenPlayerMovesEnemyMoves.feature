@IntegrationTest
Feature: When player moves Enemy moves

  Background: 
    Given the level design is:
      | XXXXXXXXX |
      | X       X |
      | X E     X |
      | X     P X |
      | XXXXXXXXX |

  Scenario: Move player left and enemy moves
    When the player moves left
    Then the Enemy is not located at (3, 3)

  Scenario: Move player right and enemy moves
    When the player moves right
    Then the Enemy is not located at (3, 3)

  Scenario: Move player up and enemy moves
    When the player moves up
    Then the Enemy is not located at (3, 3)

  Scenario: Move player down and enemy moves
    When the player moves down
    Then the Enemy is not located at (3, 3)
