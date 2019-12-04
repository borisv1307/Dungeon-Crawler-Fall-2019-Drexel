Feature: The game reaches end

  Scenario Outline: Player reaches the last level
    Given the level 1 is reached:
      | XDXXXX |
      | X  XXX |
      | XX   X |
      | X P XX |
      | XXXXXX |
    Given the level 2 is reached:
      | XDXXXX |
      | X  XXX |
      | XX   X |
      | X  X X |
      | X P XX |
      | XXXXXX |
    Given the level 3 is reached:
      | XDXXXX |
      | X  XXX |
      | XX   X |
      | X  X X |
      | XX   X |
      | X P XX |
      | XXXXXX |
    Given the level 4 is reached:
      | XDXXXX |
      | X  XXX |
      | XX X X |
      | X  X X |
      | X P XX |
      | XXXXXX |
    Given the level 5 is reached:
      | XWXXXX |
      | X  XXX |
      | XX   X |
      | XX X X |
      | X P XX |
      | XXXXXX |
    When the player reaches <WINNER_POINT> of final level
    Then the game displays winning level:
      | XXXXXXXXXXXXXXXXXXX |
      | X                 X |
      | X X   X XXX X   X X |
      | X X   X X X XX  X X |
      | X X X X X X X X X X |
      | X X X X X X X  XX X |
      | X XXXXX XXX X   X X |
      | X                 X |
      | XXXXXXXXXXXXXXXXXXX |

    Examples: 
      | WINNER_POINT | LEVEL |
      |          2,1 |     5 |
