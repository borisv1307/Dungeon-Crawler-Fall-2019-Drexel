@IntegrationTest
Feature: Move the player into wall

  Background: 
    Given the level design is:
      | XXXXXX |
      | XP   X |
      | X  O X |
      | X    X |
      | XXXXXX |

  Scenario: Obstacle moves left towards wall
    When the obstacle moves left
    Then the obstacle is located at (3, 3)