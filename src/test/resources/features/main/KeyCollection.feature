Feature: Player collects the key

Scenario: Player moves on key to collect it
    Given the level of game is:
| XXXXXXXXXXXXXXXXXXXX |
| X                  X |
| X                  X |
| X                  X |
| X                  X |
| X                  X |
| X                  X |
| X         PK       X |
| X                  X |
| XXXXXXXXXXXXXXXXXXXX |
    When the player moves on top of key
    Then the player is located on location (12, 8) and key dissappears