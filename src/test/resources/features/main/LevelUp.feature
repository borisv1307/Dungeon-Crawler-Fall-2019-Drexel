Feature: Level Up the game

  Background: 
    Given the level 1 is:
      | XXXXX |
      | XE  X |
      | X P X |
      | XXXXX |

  Scenario Outline: Move one levels up
    When the player reaches <level> and makes <movement>
    Then Current Level will be <outputLevel>

    Examples: 
      | level | movement | outputLevel |
      |     1 | up,left  |           2 |
