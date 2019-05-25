#Author: Ankit sirmor
Feature: Validate Branch Locator new changes for ROI business 

# Validating Limerick IT branch removal in ROI branch locator.    
    
Scenario Outline: 04- Validate navigation to Branch locator from AIB branch page.

Given that customer wants to enquire contact details about "<branch>" for ROI Business and enters same in searchbox
And clicks on search button
Then customer is not provided with "<branch>" dropdown

     Examples:
    | branch      | 
    | Limerick IT |