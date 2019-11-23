@Adhoc
Feature: When the player moves to the treasure, the player now has the treasure.

  Background: 
    Given the treasure design is:
      | XXXXX |
      | X   X |
      | X PTX |
      | X   X |
      | XXXXX |

  Scenario: Move up into empty space
    When the player moves up seeking treasure
    Then the treasure is located at (4, 3)
    Then the player seeking treasure is located at (3, 2)
    Then the player owns 0 treasure

  Scenario: Moves right into treasure space
    When the player moves right seeking treasure
    Then the player seeking treasure is located at (4, 3)
    Then the player owns 1 treasure
