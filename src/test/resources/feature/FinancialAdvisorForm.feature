#Author: Ankit sirmor
Feature: Validate Financial advisory form new changes for Preferred Contact Time

# Validating Preferred Contact Time for Financial Advisor Callback Request form 

Scenario: 01- Validate navigation to Financial Advisor Callback Request form.

Given that would like to talk or arrange an appointment with the Financial Advisor local AIB Branch and switches to Financial Advisor Callback Request form
And Wants to select Preferred Contact Time
Then Preferred Contact Time is displayed with all required options