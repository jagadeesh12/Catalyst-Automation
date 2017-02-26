 Feature: Create and Delete Org
  Scenario: User logs in, creates org and delete the org
    Given User logs in as Superadmin
    And User create an Org
    #And User delets the Org
    #And User create an Org
    And User creates Business group
    And User creates Project
    And User creates Chef Server
    And User creates Environment