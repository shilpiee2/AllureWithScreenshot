#Author: Ankit sirmor
Feature: Validate new changes in ROI personal Branch Appointment Form.

# Validating Limerick IT branch removal in ROI Personal Branch Appointment Form.    
    
Scenario Outline: 01- Validate navigation to Branch locator from ROI personal Branch Appointment Form.

Given that customer wants to enquire contact details about "<branch>" for ROI personal Branch Appointment Form and enters same in searchbox
And Enters "<branch>" details
Then customer is not provided with any "<branch>" information

     Examples:
    | branch      | 
    | Limerick Institute of Technology, Co. Limerick |