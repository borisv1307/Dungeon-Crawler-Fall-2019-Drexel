@IntegrationTest
Feature: Enemy moves randomly

  Background: 
    Given the level design is:
      | XXXXXX |
      | X    X |
      | X E XX |
      | X   PX |
      | XXXXXX |

  Scenario Outline: the enemy has decided to move
    When the enemy tries to move "<direction>"
    Then the Enemy located at (<x>,<y>)

    Examples: 
      | direction | x | y |
      | left      | 2 | 3 |
      | right     | 4 | 3 |
      | up        | 3 | 2 |
      | down      | 3 | 4 |
