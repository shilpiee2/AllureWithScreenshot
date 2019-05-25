#Author: Ankit sirmor
Feature: Validate new changes for AIB Business Appointment Form

# Validating Limerick IT branch removal in ROI Business Appointment Form.    
    
Scenario Outline: 01- Validate navigation to Branch locator from ROI Business Appointment Form.

Given that customer wants to enquire contact details about "<branch>" for ROI Business Appointment Form and enters same in searchbox
When Enters "<branch>" details
Then customer is not provided with any "<branch>" information

     Examples:
    | branch      | 
    | Limerick Institute of Technology, Co. Limerick |