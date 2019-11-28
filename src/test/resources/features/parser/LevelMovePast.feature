@IntegrationTest
Feature: Move the player to next level

  Background: 
    Given grid containing past level 3 is:
      | XXXXX |
      | X   X |
      | X   X |
      | X  PR |
      | XXXXX |

  Scenario: Move player to past level
    When the player in level moves right to past
    Then the player is now located on new level tile after past at (3, 2)
    And the player has moved to past level 2
