@Adhoc
Feature: Treasure appears within an empty space

  Background: 
    Given the level design is:
      | XXXXXXX |
      | XXXX XX |
      | XXPXXXX |
      | XXXXXXX |
      | XXXXXXX |

  Scenario: Treasure appears within empty space
    Given the board contain 1 empty space
    Given treasure appears every 2 second
    When the board contains 0 treasure for 2 seconds
    Then the treasure is located at (4, 2)
    
  Scenario: Treasure does not appear within blocked space
    Given the board contain 1 empty space
    When the board contains 1 treasure
    Then the treasure is located at (4, 2)