Feature: Validate Mortgage call back form 

# Validating error messages on AIB- Personal "Mortgage call back" form

Scenario: 01- Validate captcha error message displayed to customer 

Given that customer wants to enquire about mortgage and is filling mortgage call back form 
When customer does not provide any input and clicks on Submit button 
Then customer is provided with Captcha error message 
	
	
Scenario: 02- Validate all field error message displayed to customer 

Given that customer has clicked on captcha button 
When customer clicks submit button without providing any other input 
Then customer is provided with error messages for all the fields 
	
	
Scenario: 03- Validate all field error message displayed to customer one by one 

Given that customer has clicked on captcha button 
And clicks on submit button everytime after providing input for all fields one by one 
Then customer is provided with error messages for all the fields expect for the one which he entered 

# Validating all field names, Dropdown values and Title present on AIB- Personal "Mortgage call back" form
	
Scenario: 04- Validate all the field names present in UI. 

Given that customer wants to enquire about mortgage and is filling mortgage call back form 
Then customer should be able to locate all the field names on mortgage call back form 
And Title should be present on mortgage call back form 
And Dropdown: Preferred Contact Time has all the expected values 
	
	
