@IntegrationTest
Feature: Move the player into blocker space

  Background: 
    Given the level design is:
      | XXXXX |
      | X   X |
      | X P X |
      | X O X |
      | XXXXX |

  Scenario: Move down into blocker space
    When the player moves down
    Then the player is located at (50,50)
