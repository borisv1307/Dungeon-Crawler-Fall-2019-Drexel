@IntegrationTest
Feature: Move the player in different directions

 

   Background: 
    Given the level design is:
      | XXXXXXXXX |
      | X       X |
      | X       X |
      | X   E   X |
      | X  EPE  X |
      | X   E   X |
      | X       X |
      | XXXXXXXXX | 
  Scenario: Move player left to consume enemy
    When the player moves left
    Then the player is located at (4,5)
    
  Scenario: Move player right to consume enemy
    When the player moves right
    Then the player is located at (6, 5)
    
  Scenario: Move player up to consume enemy
    When the player moves up
    Then the player is located at (5, 4)
    
  Scenario: Move player down to consume enemy
    When the player moves down
    Then the player is located at (5, 6) 
    
  Scenario: Enemy killed by player
    When the  player moves two times left
    Then the enemy is turned into passable (3,5)
    
  Scenario: Enemy killed by player
    When the  player moves two times right
    Then the enemy is turned into passable (6,5)
    
  Scenario: Enemy killed by player
    When the  player moves two times up
    Then the enemy is turned into passable (4,4)
    
  Scenario: Enemy killed by player
    When the  player moves two times down
    Then the enemy is turned into passable (5,6)


    
  
