@IntegrationTest
Feature: Move the player to kill the target

  Background: 
    Given the level design is:

		|XXXXX|
		|X OPX|
		|X O X|
		|XXXXX|
    
  Scenario: Verify that target does not exist after killing the target
    When the player moves left
    Then the player is located at (3, 2)
    Then target is removed from location (3, 2)