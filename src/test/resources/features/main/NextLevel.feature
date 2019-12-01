Feature: The game goes to the next level

  Scenario Outline: Move to next level <level> in the game
    Given the level 1 is:
      | XDXXXX |
      | X  XXX |
      | XX   X |
      | X P XX |
      | XXXXXX |
    When the player reaches <DESTINATION> of current level <LEVEL>
    Then the next level will be <NEXT_LEVEL>

    Examples: 
      | DESTINATION | LEVEL | NEXT_LEVEL |
      |         2,1 |     1 |          2 |

  