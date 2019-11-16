@Adhoc
Feature: If there is a single empty space, then the treasure is spawned there

  Background: 
    Given the level design is:
      | XXXXXXX |
      | XXXX XX |
      | XXPXXXX |
      | XXXXXXX |
      | XXXXXXX |

  Scenario: Treasure appears within empty space
    Given the board contain 1 empty space
    Given the board contain 0 treasure
	When the game adds 1 treasure
    Then the treasure is located at (4, 2)