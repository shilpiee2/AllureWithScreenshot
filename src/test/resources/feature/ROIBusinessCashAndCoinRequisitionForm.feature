#Author: Ankit sirmor
Feature: Validate new changes for AIB Personal FX form

# Validating Limerick IT branch removal in ROI Personal Cash/Coin Requisition Form.    
    
Scenario Outline: 01- Validate navigation to Branch locator from ROI Business Cash/Coin Requisition Form.

Given that customer wants to enquire contact details about "<branch>" for ROI Business Branch Appointment Form and enters same in searchbox
And Enters "<branch>" details
Then customer is not provided with any "<branch>" information

     Examples:
    | branch      | 
    | Limerick Institute of Technology, Co. Limerick |