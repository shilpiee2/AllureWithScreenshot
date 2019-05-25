Feature: Validate Branch Locator new changes for ROI region 

Scenario Outline: 01- Validate navigation to Branch locator from AIB branch page.

Given customer is navigated to branchlocator page
When customer wants to enquire branch name "<branch>" and click search button
Then contact detail box appears for the respective branch
And  Go to branch hyperlink appears at box
Then Appointment info is displayed for respective "<branch>"
And customer select "<branch>" and clicks on Go to Branch hyperlink
Then customer navigated to respective "<branch>" page

Examples:

    | branch     | 
    | Rathgar    | 
    | Drumcondra |


# Remove Limerick IT branch from branch locator page

Scenario Outline: validate removal of Limerick Branch from the Branch Locator page

Given customer wants contact details of branch "<branch>" and enters in searchbox
Then branch is not provided with "<branch>" dropdown

     Examples:
    | branch      | 
    | Limerick IT |
