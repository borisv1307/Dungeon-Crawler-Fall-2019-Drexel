@IntegrationTest
Feature: Move the player to next level

  Background: 
    Given grid containing next level 2 is:
      | XXXXX |
      | X   X |
      | X  PN |
      | X   X |
      | XXXXX |

  Scenario: Move player to past level
    When the player in level moves right to next
    Then the player is now located on new level tile after next at (3, 3)
    And the player has moved to next level 3
