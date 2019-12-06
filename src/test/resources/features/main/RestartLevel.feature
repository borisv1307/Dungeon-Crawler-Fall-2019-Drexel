@IntegrationTest
Feature: Restart the level when the final target is killed

  Background: 
    Given the level design is:
      | XXXXX |
      | X OPX |
      | X O X |
      | XXXXX |

  Scenario: Verify that level gets restarted after killing the final target
    Given the player has collected one target from (3, 2)
    And there is one target left at (3, 3)
    When the player moves down
    Then the level gets restarted after final target is removed by the player
    And the player is located at (4, 2)
    And the target is located at (3, 2)
