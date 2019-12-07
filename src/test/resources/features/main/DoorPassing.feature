Feature: Player passes through the door

Scenario: Player moves towards the door to pass it
    Given the level of game with door is:
| XXXXXX |
| X  DPX |
| XXXXXX |

    When the key is collected by the player
    Then the player is located on door location (5, 2) 
    