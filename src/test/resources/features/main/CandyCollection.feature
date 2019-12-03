Feature: Move the player into candy position to collect candies

  Background: 
    Given the level design is:
      | XXXXX |
      | X   X |
      | X P X |
      | X C X |
      | XXXXX |

  Scenario: Move into candy position to collect it
    When the player moves into Candy position (3, 4)
    Then the player is located at (3, 4)
    And Candy disappears from (3, 4)