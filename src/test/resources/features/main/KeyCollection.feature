Feature: Player collects the key

Scenario: Player moves on key to collect it
    Given the level of game is:
| XXXXXXXXXXX |
| X         X |
| XK        X |
| XP        X |
| XXXXXXXXXXX |
    When the player moves on top of key
    Then the player is located on location (2, 3) 
    And key dissappears from location (2, 3)