@IntegrationTest
Feature: Move Opponent to new position 

Background: 
    Given the level design by introducing opponent is:
|XXXXXXXXXXXXXXXXXXXX|
|X                  X|
|X                  X|
|X                  X|
|X          O       X|
|X                  X|
|X                  X|
|X         P        X|
|X                  X|
|XXXXXXXXXXXXXXXXXXXX|

 Scenario: Move player up into opponent space
    When the player moves up to opponent space and the opponent current position is above the player
    Then the opponent should move to random position
    
     Scenario: Move player down into opponent space
    When the player moves down to opponent space and the opponent current position is below the player
    Then the opponent should move to random position
    
     Scenario: Move player left into opponent space
    When the player moves left to opponent space and the opponent current position is left side of the player
    Then the opponent should move to random position
    
     Scenario: Move player right into opponent space
    When the player moves right to opponent space and the opponent current position is right side of the player
    Then the opponent should move to random position
    