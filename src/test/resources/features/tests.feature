Feature: iDIN IDB System Tests Page Authenticate
  In order to login
  As a user
  I need a screen that initiates the RASS Authentication


  Scenario: Open authenticate screen as a user
    Given Set request headers
      | x-auth-ticket | ticket123456789        |
      | x-session-id  | RS0123456789012345     |
      | x-auth-level  | 0                      |
      | x-BRIT-host   | http://localhost:10501 |
    When open idp authenticate screen with timestamp 0 seconds in the past
    Then stop browser
