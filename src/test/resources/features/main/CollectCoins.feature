Feature: Collect Multiple Coins 

Scenario: Player moves on key to collect it
    Given the level of game is:
| XXXXXXXXXXX |
| X   C     X |
| XC  P     X |
| X    C    X |
| XXXXXXXXXXX |
    When the player collects coins available in grid
    Then the player is located on location (5, 2) collects coins
    And coin dissappears from location (5, 2) 