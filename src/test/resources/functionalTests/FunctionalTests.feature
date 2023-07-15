Feature: End to End Tests for Registration and Successful login flow

Description: These tests are for creating and login account with correct credentials.

  @TC001 @Registration
  Scenario: Registration flow
    Given Define URI and base path as pre-requisites
    And Define headers for the post API call
    When Create account through post api call
    Then Validate the create account outcomes

  @TC002 @Login
  Scenario: Successful login flow
    Given Define URI and base path as pre-requisites
    And Define headers for the post API call
    When Login account through post api call
    Then Validate the login account outcomes