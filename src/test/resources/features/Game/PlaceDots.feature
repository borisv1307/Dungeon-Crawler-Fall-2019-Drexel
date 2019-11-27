@GameTest
Feature: Move the player into empty space

  Background: 
    Given the level design is:
      | XXXXXXXXX |
      | X      OX |
      | X P  O  X |
      | X     O X |
      | X   O   X |
      | XXXXXXXXX |

  Scenario: Place dots in file
     When I create the level
    Then starting from the top-left:
    And the player's x coordinate is 3
    And the player's y coordinate is 3
    And (1, 1) is "X"
    And (2, 1) is "X"
    And (3, 1) is "X"
    And (4, 1) is "X"
    And (5, 1) is "X"
    And (6, 1) is "X"
    And (7, 1) is "X"
    And (8, 1) is "X"
    And (9, 1) is "X"
    And (1, 2) is "X"
    And (2, 2) is " "
    And (3, 2) is " "
    And (4, 2) is " "
    And (5, 2) is " "
    And (6, 2) is " "
    And (7, 2) is " "
    And (8, 2) is "O"
    And (9, 2) is "X"
    And (1, 3) is "X"
    And (2, 3) is " "
    And (4, 3) is " "
    And (5, 3) is " "
    And (6, 3) is "O"
    And (7, 3) is " "
    And (8, 3) is " "
    And (9, 3) is "X"
    And (1, 4) is "X"
    And (2, 4) is " "
    And (4, 4) is " "
    And (5, 4) is " "
    And (6, 4) is " "
    And (7, 4) is "O"
    And (8, 4) is " "
    And (9, 4) is "X"
    And (1, 5) is "X"
    And (2, 5) is " "
    And (4, 5) is " "
    And (5, 5) is "O"
    And (6, 5) is " "
    And (7, 5) is " "
    And (8, 5) is " "
    And (9, 5) is "X"
    And (1, 6) is "X"
    And (2, 6) is "X"
    And (4, 6) is "X"
    And (5, 6) is "X"
    And (6, 6) is "X"
    And (7, 6) is "X"
    And (8, 6) is "X"
    And (9, 6) is "X"
   