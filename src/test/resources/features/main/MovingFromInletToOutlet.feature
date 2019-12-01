@IntegrationTest
Feature: Transfering Player from inlet to outlet

Background: 
    Given the level design with inlet and outlet is:
    | XXXXXXX |
    | X I O X |
    | X     X |
    | X  P  X |
    | XXXXXXX |

   Scenario: Move player into inlet
    When the player moves (-1, -2) from current position into inlet
    Then the player is in outlet at (4, 1)
