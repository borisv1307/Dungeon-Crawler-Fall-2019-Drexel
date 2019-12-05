Feature: All coins collected game ends

  Scenario: Player wins the game
    
    Given Level of game is:
|XXXXXXXXXXXXXXXXXXXX|
|X   C     C     C WX|
|X   P              X|
|X         C     C  X|
|X                  X|
|X         C        X|
|X                  X|
|X   C              X|
|X                  X|
|XXXXXXXXXXXXXXXXXXXX|
    When when player collects coin and hit location (18, 1)
    Then the game prints "Game won"
      
