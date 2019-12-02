Feature: Move the player to eat the food 
  Background: 
    Given the level design is:
      | XXXXXXXX |
      | X      X |
      | X     FX |
      | X     PX |
      | XXXXXXXX |


  Scenario: Move up in the board
    When the player moves up
    Then the player is located at (6, 2) and F will be disappered


