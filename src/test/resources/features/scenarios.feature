Feature: Test CRUD methods for Product warehouse online shopper

  Scenario: Add Product record
    Given I Set POST product service api endpoint
    When I Set request HEADER
    And I Send a POST HTTP request
    Then I receive valid HTTP response code 200

  Scenario: Update a Product record
    Given I Set PUT product service api endpoint
    When I Set Update request BODY
    And I Send a PUT HTTP request
    Then I receive valid HTTP response code 200

  Scenario: Retrieve a Product record
    Given I Set GET product service api endpoint
    When I Set request HEADER
    And I Send a GET HTTP request
    Then I receive valid HTTP response code 200

  Scenario: Delete Product record
    Given I Set DELETE product service api endpoint
    When I Send DELETE HTTP request
    Then I receive valid HTTP response code 200
