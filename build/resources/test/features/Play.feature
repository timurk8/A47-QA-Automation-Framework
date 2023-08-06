Feature: Play feature

  Background:
    Given I open Login page

  Scenario: Play music after Login
    When I enter email "bugbusters@testpro.io"
    And I enter password "te$t$tudent"
    And I submit
    When I push the button play
    Then music starts playing

