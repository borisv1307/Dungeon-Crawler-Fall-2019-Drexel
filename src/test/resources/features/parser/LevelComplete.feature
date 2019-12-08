@IntegrationTest
Feature: Tiles passed over are colored

  Background: 
    Given the level design is:
      | XXXXXXXXX |
      | X      PX |
      | XXXXXXXXX |

  Scenario Outline: Color the tile trail on the right
    When the player moves left
    Then row 2 is covered and level is Complete
	Examples:
		|x|
		|8|
		|7|
		|6|
		|5|
		|4|
		|3|
		|2|