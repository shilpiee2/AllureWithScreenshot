#Author: Ankit sirmor
Feature: Validate Branch Locator new changes for Home buying calculator

# Validating new branch names in Home buying calculator.
Scenario Outline: 01- Validate Go to branch hyperlink availability for Rathgar and Drumcondra.

Given that customer wants to enquire contact details about "<branch>" and is present in Home buying calculator page
And enters "<branch>" name on searchbox
Then contact detail box appears for each "<branch>"
     Examples:
    | branch     | 
    | Rathgar    | 
    | Drumcondra | 


