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
    When the player reaches <WIN_POINT> of level <level>
    Then Current Level will be <outputLevel>

    Examples: 
      | WIN_POINT | level | outputLevel |
      |       1,1 |     1 |           2 |
      |       2,3 |     2 |           3 |
