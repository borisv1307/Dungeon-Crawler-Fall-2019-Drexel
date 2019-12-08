Feature: Move the player to next level

  Scenario Outline: Player collects the food
    Given the level design is:
      | XXXXXX |
      | X F  X |
      | X    X |
      | XP  XX |
      | XXXXXX |
    When the player reaches (2,1)  of level <CURRENT_LEVEL>
    Then the player moves to level <NEXT_LEVEL> 
    And the player color will be RED at <NEXT_LEVEL> 

    Examples: 
      | CURRENT_LEVEL | NEXT_LEVEL |
      |             1 |          2 | 
