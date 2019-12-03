Feature: Move the player to eat the food

  Background: 
    Given the level design is:
      | XXXXX |
      | X F X |
      | X P X |
      | XXXXX |

  Scenario: Move up in the board
    When the player moves up
    Then the player will be moved to location (2, 1)
    And food will be disappered from (2,1)
