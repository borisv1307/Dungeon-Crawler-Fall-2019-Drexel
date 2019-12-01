Feature: The player comes back to start position when it touches the obstacles

  Scenario Outline: Move to start position of same level <level> in the game
    Given the level 1 is ongoing:
      | XDXXXX |
      | X  XXX |
      | XX   X |
      | X P XX |
      | XXXXXX |
    When the player hits <OBSTACLE_POINT> 
    Then the level will be <CURRENT_LEVEL> and position will be <START_POSITION>

    Examples: 
      | OBSTACLE_POINT | CURRENT_LEVEL | START_POSITION |
      |            5,4 |             1 |            3,4 |
      |            4,2 |             1 |            3,4 |
      |            5,2 |             1 |            3,4 |
      |            2,3 |             1 |            3,4 |
