Feature: Feature

  Scenario: Scenario
    Given Set request headers
      | x-test-header1 | testtest1       |
      | x-test-header2 | testtest2       |
    When open url http://www.google.com
    Given Set cookies
      |cookie|cookievalue|
    When open location http://www.google.com
    Then cookie is created
