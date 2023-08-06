Feature: Login feature

  Background:
    Given I open Login page

  Scenario: Login Success

    When I enter email "bugbusters@testpro.io"
    And I enter password "te$t$tudent"
    And I submit
    Then I am logged into the website

  Scenario: Login Incorrect Password
    When I enter email "bugbusters@testpro.io"
    And I enter password "incorrectpassword"
    And I submit
    Then I am not logged in


  Scenario: Login Email Not Existing
    When I enter email "noSuchEmail@test.com"
    And I enter password "te$t$tudent"
    And I submit
    Then I am not logged in

  Scenario: Empty Login and Password
    When I enter email ""
    And I enter password ""
    And I submit
    Then I am not logged in
