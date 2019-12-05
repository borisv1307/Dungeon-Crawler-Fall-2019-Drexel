Feature: Player passes through the door

Scenario: Player moves towards the door to pass it
    Given the level of game with door is:
| XXXXXXXXXXX |
| X   D     X |
| X   P     X |
| XXXXXXXXXXX |
    When the key is not loacted on location (2, 3)
    Then the player is located on door location (5, 2) 
    