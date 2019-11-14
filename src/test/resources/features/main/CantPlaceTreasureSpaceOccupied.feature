@Adhoc
Feature: Maximum number of treasure locations is 2

  Background: 
    Given the level design is:
      | XXXXXXX |
      | XXXXTXX |
      | XXPXXXX |
      | XXXXTXX |
      | XXXXXXX |

  Scenario: No empty space
    Given the board contain 0 empty space
    Given the board contain 1 treasure
    Given treasure appears every 2 second
    When the board contains 1 treasure for 2 seconds
    Then the treasure count is 1 at 3 seconds
    Then the treasure count is 1 at 6 seconds
    