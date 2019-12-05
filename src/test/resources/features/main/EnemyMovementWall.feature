@IntegrationTest
Feature: When player moves Enemy moves

  Background: 
    Given the level design is:
      | XXXXX |
      | XEXXX |
      | XX PX |
      | XXXXX |

  Scenario: Move player left and enemy moves
    When the player moves left
    Then the Enemy located at (2,2)

  Scenario: Move player right and enemy moves
    When the player moves right
    Then the Enemy located at (2,2)

  Scenario: Move player up and enemy moves
    When the player moves up
    Then the Enemy located at (2,2)

  Scenario: Move player down and enemy moves
    When the player moves down
    Then the Enemy located at (2,2)
