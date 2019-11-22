@IntegrationTest
Feature: Enemy should not move across the wall.

  Background: 
    Given the level design is:
      | XXXXXXXXX |
      | XE      X |
      | X       X |
      | X     P X |
      | XXXXXXXXX |

  Scenario Outline: Move player left and enemy moves
    When the player moves left
    Then the Enemy is not located at (<x>, <y>)

    Examples: 
      | x | y |
      | 1 | 2 |
      | 2 | 1 |

  Scenario Outline: Move player right and enemy moves
    When the player moves right
    Then the Enemy is not located at (<x>, <y>)

    Examples: 
      | x | y |
      | 1 | 2 |
      | 2 | 1 |

  Scenario Outline: Move player up and enemy moves
    When the player moves up
    Then the Enemy is not located at (<x>, <y>)

    Examples: 
      | x | y |
      | 1 | 2 |
      | 2 | 1 |

  Scenario Outline: Move player down and enemy moves
    When the player moves down
    Then the Enemy is not located at (<x>, <y>)

    Examples: 
      | x | y |
      | 1 | 2 |
      | 2 | 1 |
