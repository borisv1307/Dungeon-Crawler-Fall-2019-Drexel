@IntegrationTest
Feature: Tiles passed over are colored

  Background: 
    Given the level design is:
      | XXXXXXXXX |
      | X       X |
      | X       X |
      | X   P   X |
      | X       X |
      | X       X |
      | XXXXXXXXX |

  Scenario Outline: Color the tile trail on the right
    When the player moves left
    Then the tiles (<x>, 4) is BLUE
	Examples:
		|x|
		|5|
		|4|
		|3|
		
	Scenario Outline: Color the tile trail on the left
    When the player moves right
    Then the tiles (<x>, 4) is BLUE
	Examples:
		|x|
		|5|
		|6|
		|7|
		
	Scenario Outline: Color the tile trail below
    When the player moves up
    Then the tiles (5, <y>) is BLUE
	Examples:
		|y|
		|4|
		|3|
		
	Scenario Outline: Color the tile trail above
    When the player moves down
    Then the tiles (5, <y>) is BLUE
	Examples:
		|y|
		|4|
		|5|	