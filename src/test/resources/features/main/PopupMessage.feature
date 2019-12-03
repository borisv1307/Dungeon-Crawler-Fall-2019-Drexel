@IntegrationTest
Feature: Display pop up message

Scenario: Pop up message shouldn't display
Given The number of enemies already killed is one
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

When the player moves up and kill enemy
Then Pop up message should not display
    
Scenario: Pop up message should display
Given The number of enemies already killed is nine
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

When the player moves up and kill enemy
Then Pop up message should display once