Feature: User reaches last level

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
    Given the level 3 is:
      | XXXXX |
      | XX XX |
      | XE  X |
      | X P X |
      | XXXXX |

  Scenario Outline: user finishes last level
    When the player reaches <level> and position <position>
    And a new level is trying to get created
    Then the game will EXIT
    And last level message is thrown

    Examples: 
      | level | position |
      |     3 |      2,3 |