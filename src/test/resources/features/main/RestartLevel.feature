@IntegrationTest
Feature: Restart the level when the final target is killed

  Background: 
    Given the level design is:

		| XXXXX |
		| X OPX |
		| X O X |
		| XXXXX |
    
  Scenario: Verify that level gets restarted after killing the final target
  	When I create the level
    Then the player is located at (4, 2)
    And the target is located at (3, 2)
    When the player moves left
    Then the player is located at (3, 2)
    Then target is removed from location (3, 2)
    When the player moves down
    And the level gets restarted after final target is removed by the player
    Then the player is located at (4, 2)
    And the target is located at (3, 2)