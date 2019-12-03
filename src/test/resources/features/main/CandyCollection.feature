Feature: Move the player into candy position to collect candies

  Background: 
    Given the level design is:
      | XXXXX |
      | X C X |
      | X P X |
      | XXXXX |

  Scenario: Move into candy position to collect it
    When the player moves up
    Then the player is located at (3, 2)
    And Candy disappears from (3, 2)