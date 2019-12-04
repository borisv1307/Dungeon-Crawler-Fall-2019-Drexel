@IntegrationTest
Feature: Move the player into Opponent space

     
    Scenario: Move up into opponent space
    	Given The opponent current position is above the player
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
    	When the player moves up to opponent area
    	Then the player is located at new position 10,6
    
     Scenario: Move down into opponent space
     	Given The opponent current position is below the player
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
    	When the player moves down to opponent area
    	Then the player is located at new position 10,8
    
     Scenario: Move left into opponent space
     	Given The opponent current position is left side of the player
|XXXXXXXXXXXXXXXXXXXX|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X         OP       X|
|X                  X|
|XXXXXXXXXXXXXXXXXXXX|
    	When the player moves left to opponent area
    	Then the player is located at new position 10,7  
    
     Scenario: Move right into opponent space
     	Given The opponent current position is right side of the player
|XXXXXXXXXXXXXXXXXXXX|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X                  X|
|X         PO       X|
|X                  X|
|XXXXXXXXXXXXXXXXXXXX|
    	When the player moves right to opponent area
    	Then the player is located at new position 11,7