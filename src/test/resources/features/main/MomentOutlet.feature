@IntegrationTest
Feature: Moving Player into outlet

Background: 

   Scenario: Move player left into outlet
   Given the level design with player right of outlet:
    | XXXXXXXXX |
    | X  OP   X |
    | X       X |
    | X    I  X |
    | XXXXXXXXX |
    When the player moves left into outlet
    Then the player is at (4, 1)
    
   Scenario: Move player right into outlet
    Given the level design with player left of outlet:
    | XXXXXXXXX |
    | X PO    X |
    | X       X |
    | X    I  X |
    | XXXXXXXXX |
    When the player moves right into outlet
    Then the player is at (2, 1)
    
   Scenario: Move player down into outlet
    Given the level design with player above the outlet:
    | XXXXXXXXX |
    | X  P    X |
    | X  O    X |
    | X    I  X |
    | XXXXXXXXX |
    When the player moves down into outlet
    Then the player is at (3, 1)

   Scenario: Move player up into outlet
    Given the level design with player below the outlet:
    | XXXXXXXXX |
    | X  O    X |
    | X  P    X |
    | X    I  X |
    | XXXXXXXXX |
    When the player moves up into outlet
    Then the player is at (3, 2)