@IntegrationTest
Feature: Move the player into Opponent space

Background: 
    Given the level design by adding opponent is:
      | XXXXX |
      | X O X |
      | X P X |
      | X   X |
      | XXXXX |
      
    Scenario: Move up into opponent space
    When the player moves up to opponent space and opponent current position is above the player
    Then the player is located at new position 2,1
    
     Scenario: Move down into opponent space
    When the player moves down to opponent space and opponent current position is below the player
    Then the player is located at new position 2,3
    
     Scenario: Move left into opponent space
    When the player moves left to opponent space and opponent current position is left side of the player
    Then the player is located at new position 1,2  
    
     Scenario: Move right into opponent space
    When the player moves down to opponent space and opponent current position is right side of the player
    Then the player is located at new position 3,2