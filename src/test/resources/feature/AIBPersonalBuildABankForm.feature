#Author: Ankit sirmor
Feature: Validate new changes for AIB Personal FX form

# Validating Limerick IT branch removal in ROI Personal Build a bank Form.    
  
Scenario Outline: 01- Validate navigation to Branch locator from ROI personal Build a bank Form.

Given that customer wants to enquire contact details about "<branch>" for ROI personal Build a bank Form and enters same in searchbox
And Enters "<branch>" details
Then customer is not provided with any "<branch>" information

     Examples:
    | branch      | 
    | Limerick Institute of Technology, Co. Limerick |