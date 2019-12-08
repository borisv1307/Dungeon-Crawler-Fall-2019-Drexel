@IntegrationTest
Feature: Restart the level when the player hit obstacle

  Background: 
    Given the level design is:
      | XXXXX |
      | X  PX |
      | X F X |
      | XXXXX |

  Scenario: Verify when player hit obstacles the level gets restarted
    When the player moves right of level 1 the level gets restarted
    Then the player will be located at (3, 1)
    Then play level will be 1
