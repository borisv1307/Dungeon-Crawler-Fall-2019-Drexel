@IntegrationTest
Feature: Move the player to kill the target

  Background: 
    Given the level design is:
		|XXXXXXXXXXXXXXXXXXXX|
		|X                  X|
		|X                  X|
		|X                  X|
		|X                  X|
		|X     OP           X|
		|X                  X|
		|X                  X|
		|X                  X|
		|XXXXXXXXXXXXXXXXXXXX|

  Scenario: Move player left into the target
    When the player moves left
    Then the player is located at (7, 6)
    
  Scenario: Verify that target does not exist after killing the target
    When the player moves left
    Then the player is located at (7, 6)
    Then target is removed from location (7, 6)
    
    