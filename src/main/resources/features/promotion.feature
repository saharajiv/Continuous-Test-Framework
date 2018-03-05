Feature: Promotion
  As a user I want to use a calculator So that I don't need to calculate myself

Background: 
   The promotions should be set up in the database

   
  Scenario: Add two numbers
    Given I have a calculator
    When I add 2 and 3
    Then the result should be 5

   
  Scenario: As a business user I want to test promotion on items on which multiple promotions are applicable
    Given I pass query request parameters
    When I call service for "addItemToBag"
    Then I should get the response json #checkFromDatabse #checkendPOint1
    
   @Test=promocode1
   @service=promotionsByPromoCode
   Scenario: As a business user I want to test whether a promocode exists or not
    Given I pass the correct promo code as "promocode1"
    When I call service to get the promotion details "promotionsByPromoCode"
    Then I should get Response Code 200
    And I should get the promotion Id and the promotion details
    