@IntegrationTest
Feature: Move the player into trap

  Background: 
    Given the level design is:
      |XXXXXXX| 
      |X     X|
      |X T   X|
      |X     X|
      |X   P X|
      |X     X|
      |XXXXXXX|

  Scenario: Move left and up and check that trap sprung
    When the player moves left
    And the player moves up
    Then the player is located at (4, 4)
    And trap has sprung 

  Scenario: Move up do not find trap
    When the player moves up
    Then the player is located at (5, 4)
    And no trap has sprung

  Scenario: Move down do not find trap
    When the player moves down
    Then the player is located at (5, 6)
    And no trap has sprung
    
   Scenario: Move left and left and left and up and up and second up does not work
    When the player moves left
    And the player moves left
    And the player moves left
    And the player moves up
    And the player moves up
    Then the player is located at (2, 4)
    And trap has sprung 
    
    Scenario: Move left and up and get stuck and free yourself and move up
    When the player moves left
    And the player moves up
    And the player inserts key
    And the player moves up
    Then the player is located at (4, 3)
     
