Feature: Test

  Scenario: Open authenticate screen as a user
    Given Set request headers
      | x-test-header1 | testtest1       |
      | x-test-header2 | testtest2       |
    When open url http://www.google.com
    Given Set cookies
      |cookie|cookievalue|
    When open location http://www.google.com
    Then stop browser
