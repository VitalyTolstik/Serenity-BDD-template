Feature: Google demo

  @WEB @1
  Scenario: Google demo
    Given I opened url
    When I type 'onliner.by'
    And I click search button
    When I click on first link
    Then Onliner site is open

  @WEB @2
  Scenario: Google demo with error
    Given I opened url
    When I type 'vk'
    And I click search button
    When I click on first link
    Then Onliner site is open