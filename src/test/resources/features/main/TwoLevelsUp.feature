Feature: User advances two levels

  Background: 
    Given the level 1 is:
      | XXXXX |
      | XE  X |
      | X P X |
      | XXXXX |
    Given the level 2 is:
      | XXXXX |
      | XX  X |
      | XE  X |
      | X P X |
      | XXXXX |

  Scenario Outline: Move two levels up
    When the player reaches <level> and makes <movement>
    Then Current Level will be <outputLevel>

    Examples: 
      | level | movement | outputLevel |
      |     1 | left,up  |           2 |
      |     2 | up,left  |           3 |
