@Adhoc
Feature: If there's no empty spaces, then no treasure is spawned

  Background: 
    Given the level design is:
      | XXXXXXX |
      | XXXXTXX |
      | XXPXXXX |
      | XXXXXXX |
      | XXXXXXX |

  Scenario: No empty space
    Given the board contain 0 empty space
    Given the board contain 1 treasure
	When the game adds 1 treasure
    Then the treasure count is 1
	