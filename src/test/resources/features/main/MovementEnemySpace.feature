@IntegrationTest
Feature: Move the player into enemy space

  Background: 
    Given the level design is:
      | XXXXXX |
      | X  T X |
      | X TPTX |
      | X  T X |
      | XXXXXX |

  Scenario: Move left into enemy space
    When the player moves left
    Then the player is disappeared to (11, 11)

  Scenario: Move right into enemy space
    When the player moves right
    Then the player is disappeared to (11, 11)

  Scenario: Move up into enemy space
    When the player moves up
    Then the player is disappeared to (11, 11)

  Scenario: Move down into enemy space
    When the player moves down
    Then the player is disappeared to (11, 11)
