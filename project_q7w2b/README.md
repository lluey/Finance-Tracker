# **Finance Tracker**

This budgeting application helps you budget each dollar you have. It tracks your expenses so that you can see where all your hard-earned cash goes towards, thus helping you make better financial decisions. 

This is best suited to you if you:
 - have a hard time with budgeting 
 - make unnecessary expenses (bad spending habits)
 - don't want to be in debt
 - want financial security
 - want to save money in general
 
 This project is of interest to me because I feel like I should be financially smarter. Especially as a uni student, it's important to keep track of your money to prevent falling deeper and deeper into debt.
 
 ### User Stories
 - As a user, I want to be able to add a purchase to a list of purchases
 - As a user, I want to be able to add more money to my budget
 - As a user, I want to be able to see how much money I have left
 - As a user, I want to be able to see how much money I've spent in total 
 - As a user, I want to be able to optionally delete purchases
 - As a user, I want to be able to save my purchase list in a file
 - As a user, I want to be able to optionally reload my purchase list from file
 
 ### Instructions for Grader
 - you can reload the state of my application by clicking ok when the program starts
 - you can generate the first required event by pressing the add purchase button and filling in the specifications
 - you can generate the second required event by selecting which boxes you wish to delete, then pressing the delete selected button
 - my visual component is the graph displayed automatically
 - you can save the state of my application by pressing the save button 
 
 ### Phase 4: Task 2
 Robust class 1: Purchase
 
 method: constructor
 - ensures that there are no purchases that cost negative money
 
 Robust class 2: Finances
 
 method: addMoney
 - ensures that user does not add negative money to their balance
 
 ### Phase 4: Task 3
 - moved botPanel to a new class and renamed it buttons due to the single responsibility principle to increase cohesion
 - moved total amount and remaining balance to be displayed in the application so that the Buttons class could be used for exclusively buttons
 - deleted the balance class and moved the functionality to finances due to redundant class-dependency, thus reducing coupling
 - moved totalAmount to be stored in Finances rather than PurchaseList because it made more sense in terms of its purpose and application, thus increasing cohesion