Feature: Level Up the game

  Background: 
    Given the level 1 is:
      | XXXXX |
      | XE  X |
      | X P X |
      | XXXXX |

  Scenario Outline: Move one levels up
    When the player reaches <WIN_POINT> of level <level>
    Then Current Level will be <outputLevel>

    Examples: 
      | WIN_POINT | level | outputLevel |
      |       2,2 |     1 |           2 |
