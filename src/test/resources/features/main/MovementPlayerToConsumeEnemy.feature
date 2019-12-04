@IntegrationTest
Feature: Move the player into wall

 

   Background: 
    Given the level design is:
      | XXXXXX |
      | X  E X |
      | X EPEX |
      | X  E X |
      | XXXXXX | 
  Scenario: Move player left to consume enemy
    When the player moves left
    Then the player is located at (3, 3)
    
  Scenario: Move player right to consume enemy
    When the player moves right
    Then the player is located at (5, 3)
    
  Scenario: Move player up to consume enemy
    When the player moves up
    Then the player is located at (4, 2)
    
   Scenario: Enemy appended to player
    When the player moves left
    Then the enemy is appended to player (4, 2)

 

  Scenario: Enemy removed from it's position
    When the player moves left
    Then the enemy is removed from location (4, 2)
    
  Scenario: Move player down to consume enemy
    When the player moves down
    Then the player is located at (4, 4) 
