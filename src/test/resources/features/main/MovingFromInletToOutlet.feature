@IntegrationTest
Feature: Transfering Player from inlet to outlet

Background: 

   Scenario: Move player left into inlet
   Given the level design with player right of inlet and outlet is:
    | XXXXXXXXX |
    | X  IP   X |
    | X       X |
    | X    O  X |
    | XXXXXXXXX |
    When the player moves left into inlet
    Then the player is in outlet at (5, 3)
    
   Scenario: Move player right into inlet
    Given the level design with player left of inlet and outlet is:
    | XXXXXXXXX |
    | X PI    X |
    | X       X |
    | X    O  X |
    | XXXXXXXXX |
    When the player moves right into inlet
    Then the player is in outlet at (5, 3)
    
   Scenario: Move player down into inlet
    Given the level design with player above the inlet and outlet is:
    | XXXXXXXXX |
    | X  P    X |
    | X  I    X |
    | X    O  X |
    | XXXXXXXXX |
    When the player moves down into inlet
    Then the player is in outlet at (5, 3)

   Scenario: Move player up into inlet
    Given the level design with player below the inlet and outlet is:
    | XXXXXXXXX |
    | X  I    X |
    | X  P    X |
    | X    O  X |
    | XXXXXXXXX |
    When the player moves up into inlet
    Then the player is in outlet at (5, 3)
    
    