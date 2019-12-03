@UITest
Feature: Move Opponent to new position 

Scenario: Move player up into opponent space
	Given The Opponent current position is above the player
|XXXXXXXXXXXXXXXXXXXX|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X         O        X|
|X         P        X|
|X                  X|
|XXXXXXXXXXXXXXXXXXXX|
	When the player moves up to opponent space
	Then the opponent should move to random position
    
Scenario: Move player down into opponent space
    Given The Opponent current position is below the player
|XXXXXXXXXXXXXXXXXXXX|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X         P        X|
|X         O        X|
|XXXXXXXXXXXXXXXXXXXX|
    When the player moves down to opponent space
 	Then the opponent should move to random position
    
Scenario: Move player left into opponent space
    Given The Opponent current position is left side of the player
|XXXXXXXXXXXXXXXXXXXX|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X        OP        X|
|X                  X|
|XXXXXXXXXXXXXXXXXXXX|
   	When the player moves left to opponent space
   	Then the opponent should move to random position
    
Scenario: Move player right into opponent space
   	Given The Opponent current position is right side of the player
|XXXXXXXXXXXXXXXXXXXX|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X        PO        X|
|X                  X|
|XXXXXXXXXXXXXXXXXXXX|
    When the player moves right to opponent space
    Then the opponent should move to random position