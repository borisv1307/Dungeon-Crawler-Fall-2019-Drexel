@Adhoc
Feature: Additional treasure does not appears without empty space

  Background: 
    Given the level design is:
      | XXXXXXX |
      | XXXXTXX |
      | XXPXXXX |
      | XX XXTX |
      | XXXXXXX |

  Scenario: No empty space
    Given the board contain 0 empty space
    Given the board contain 1 treasure
    Given treasure appears every 2 second
    When the board contains 1 treasure for 2 seconds
    Then the treasure count is 1 at 3 seconds
    Then the treasure count is 1 at 6 seconds

  Scenario: Treasure count cant exceed 2
    Given the board contain 1 empty space
    Given max treasure count is 2
    Given treasure appears every 2 second
    When the board time exceeds 2 seconds
    Then the treasure count is 2