Feature: Player collects the key

Scenario: Player moves on key to collect it
    Given the level of game is:
| XXXXX |
| XK  X |
| XP  X |
| XXXXX |
    When the player moves on top of key
    Then the player is located on location (2, 2) 
    And key dissappears from location (2, 2)