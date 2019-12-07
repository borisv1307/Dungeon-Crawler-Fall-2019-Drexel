@IntegrationTest
Feature: Popup display message
  
Scenario:
Given Popup not to display when count is one
      | XXXXXXXXX |
      | X       X |
      | X       X |
      | X   E   X |
      | X  EPE  X |
      | X       X |
      | X       X |
      | XXXXXXXXX |
When player moves up and kill one enemy
Then popup should not displayed

Scenario:
Given Popup to display when count is three
      | XXXXXXXXX |
      | X       X |
      | X       X |
      | X   E   X |
      | X  EPE  X |
      | X       X |
      | X       X |
      | XXXXXXXXX |
When player moved in any direction to kill third enemy
Then popup should displayed



    
