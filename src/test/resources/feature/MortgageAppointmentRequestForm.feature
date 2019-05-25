#Author: Ankit sirmor
Feature: Validate new changes for AIB Personal FX form

# Validating Limerick IT branch removal in ROI Personal Mortgage Appointment Request Form.    
    
Scenario Outline: 01- Validate navigation to Branch locator from ROI personal Mortgage Appointment Request Form.

Given that customer wants to enquire contact details about "<branch>" for ROI personal Mortgage Appointment Request and enters same in searchbox
When customer selects "<Appointment Preference>" from dropdown option
And Enters "<branch>" details
Then customer is not provided with any "<branch>" information

     Examples:
    | branch                                         | Appointment Preference |
    | Limerick Institute of Technology, Co. Limerick | I'd prefer a face to face conversation |