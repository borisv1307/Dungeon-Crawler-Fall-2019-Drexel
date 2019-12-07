@IntegrationTest
Feature: Move the player to add enemy

   Background: 
    Given the level design for adding enemy:
      | XXXXXXXXX |
      | X       X |
      | X       X |
      | X   E   X |
      | X  EPE  X |
      | X   E   X |
      | X       X |
      | XXXXXXXXX | 
  Scenario: Move player left to add enemy
    When the player moves left two times
    Then the enemy turned into passable at (3, 5)
    
    Scenario: Move player right to add enemy
    When the player moves right two times
    Then the enemy turned into passable at (5, 6)
    
    Scenario: Move player up to add enemy
    When the player moves up two times
    Then the enemy turned into passable at (5, 3)
    
    Scenario: Move player down to add enemy
    When the player moves down two times
    Then the enemy turned into passable at (6, 5)
 