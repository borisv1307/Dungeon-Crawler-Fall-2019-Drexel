@Adhoc
Feature: If there's no empty spaces, then no treasure is spawned

  Background: 
    Given the level design is:
      | XXXXXXX |
      | XXXXXXX |
      | XXPXXXX |
      | XXXXXXX |
      | XXXXXXX |

  Scenario: No empty space
    When the game adds 1 treasure
    Then the treasure count is 0
