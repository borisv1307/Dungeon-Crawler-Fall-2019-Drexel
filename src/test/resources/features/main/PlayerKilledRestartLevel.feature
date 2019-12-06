Feature: if player and enemy is in the same coordinate

  Background: 
    Given the level design is:
      | XXXX |
      | XPEX |
      | XXXX |

  Scenario: Enemy and Player moves into each other
    When the player moves left
    And the enemy tries to move "left"
    Then the level is restarted
