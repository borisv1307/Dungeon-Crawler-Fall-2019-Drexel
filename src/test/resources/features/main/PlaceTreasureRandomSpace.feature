@Adhoc
Feature: Treasure appears within an random empty space

  Background: 
    Given the level design is:
      | XXXXXXX |
      | X     X |
      | XXPXXXX |
      | XXXXXXX |
      | XXXXXXX |

  Scenario: Treasure appears within empty space up to max
    Given the board contain 5 empty space
    Given the board contain 0 treasure
    Given max treasure count is 3
    Given treasure appears every 2 second
    Then treasure count is 1
    When time reaches 5 seconds
    Then treasure count is 2
    When time reaches 7 seconds
    Then treasure count is 3
    When time reaches 9 seconds
    Then treasure count is 3
    When time reaches 11 seconds
    Then treasure count is 3