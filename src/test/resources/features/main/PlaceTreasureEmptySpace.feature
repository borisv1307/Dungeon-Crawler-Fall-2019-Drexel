@IntegrationTest
Feature: If there is a single empty space, then the treasure is spawned there

  Background: 
    Given the treasure design is:
      | XXXXXXX |
      | X XXXXX |
      | XXPXXXX |
      | XXXXXXX |
      | XXXXXXX |

  Scenario: Treasure appears within empty space
    When the game adds 1 treasure
    Then the treasure is located at (2, 2)
