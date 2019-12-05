@IntegrationTest
Feature: Move the player into energy space

  Background: 
    Given the level design is:
      | XXXXXX |
      | X  E X |
      | X EPEX |
      | X  E X |
      | XXXXXX |

  Scenario: Move left into energy space
    When the player moves left
    Then the player is located at (3, 3)

  Scenario: Move right into energy space
    When the player moves right
    Then the player is located at (5, 3)

  Scenario: Move up into energy space
    When the player moves up
    Then the player is located at (4, 2)

  Scenario: Move down into energy space
    When the player moves down
    Then the player is located at (4, 4)
