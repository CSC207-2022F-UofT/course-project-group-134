# Based Food Service App (Group 134)

## Overview

One of the difficulties that students living on campus at U of T often 
face is ending up with much more meal plan balance than they can use up.
To combat this pertinent issue, we present to you the Based Food Service App.

Students with high meal plan dollars can sign up as a "seller", and can
input their meal plan balance.  Other students who wish to buy food on
campus can sign up as "buyers".  Buyers can order food items from their
favorite dining halls or residences.  When a seller logs in, they see 
all the current orders associated with the Dining Halls accessible from their
meal plan.  Sellers can then accept these orders, buy the food requested by
the buyer (using their meal plan dollars), and communicate using the chat feature 
on a mututal meeting place with the buyer.  The two parties meet and exchange the food 
and money. What a simple and easy way to exhaust excess meal plan dollars!

## Use Cases Implemented

1. Sign Up: This use case allows a user to sign up as a buyer or seller.  Both
types of users must enter a username, email, and password.  A seller must additionally
input the residence where their meal plan works and their meal plan balance.  A future
improvement to this use case is to allow the seller to connect their TCard to this app - 
this will ensure that their meal plan dollars on the app are linked to their actual dollars
2. Sign in: This allows users to sign in.  The sign in use case classes recognize
whether a buyer or seller has signed in, and accordingly updates the screens
3. Get Menus: This use case is an intermediate use case when a buyer wishes to
buy food.  When the buyer clicks on "Create New Order", the get menus use case
takes over.  Based on the residence from which the buyer wishes to buy food, 
the get menus use case gathers the menu from that residence and displays it
to the user.  It also handles showing item descriptions, calculating total price,
and showing the user a preview of their order
4. Ordering: Once the user has previewed their order, they made place the order.
The ordering use case handles updating the order csv file with the newly created
order, and updating the view accordingly (i.e., taking the user back to their
home screen and displaying the current order on their screen)
5. Selling: Once a buyer has made an order, every seller whose meal plan functions
in the dining hall to which the order was made can now see and accept the order.
Each seller sees a list of orders associated with their respective dining halls.
They can view the items ordered, the quantities of each item, and the total price.
A seller can only accept an order if they have enough meal plan balance to accept it.
Each time the seller accepts an order, the respective amount is deducted from their
meal plan balance.
Once the seller accepts the order, the buyer is required to confirm the order
from their side.  
6. Chat: After a seller confirms an order, the buyer and seller can communicate via
the chat.  The chat provides an easy and reliable manner of allowing the two users to
discuss the meeting place and possibly mode of payment (from the buyer to the seller).
For each order made and accepted, a new chat interface is set up between the respective
buyer and seller.
7. Order History and Current Orders: When a buyer accepts an order, they see the details of that order in
the "current orders panel" on their home page.  This includes information such as items
ordered, quantity of each item, total price, order status etc.  In addition, once a seller confirms
that order, the status of the order changes to "Seller Confirmed".  Once a buyer accepts
the order, the status changes to "Buyer confirmed".  Once the order has been successfully
delivered, the status changes to "Finished", and the order is moved to the "Order History"
panel on the home page
8. Review: For each order in the "Order History" panel, a buyer can give a review for
each item they ordered.  In addition to a written review, they may also assign a star
rating to the food item.  Once they place the review, other buyers can see this review
when they order that same item from that particular residence/dining hall.  The "star
average" of each food item also gets updated accordingly when a buyer writes a review.

## Adherence to Clean Architecture

The whole program strongly adheres to Clean Architecture.  Each use case has a controller
that is called by the view when necessary.  This controller has an input boundary
attribute when points to a use case interactor.  The use case interactor manipulates
entites and conducts the main logic required to conduct the necessary task.  It 
uses a gateway to access information from csv data storage files.  Once it has finished
its task, it signals the presenter to prepare a success or fail view.  The presenter then
returns the necessary information to the view, which then updates itself accordingly.
Dependency inversion has been taken care of in very use case; there is never an instance
when a class in a lower layer depends on a class in an upper layer.  To invert
these dependencies, java interfaces have been set up accordingly.

## Design Patterns Used

1. The Factory design pattern has been used in this program.  The most notable
usage of this is in the get_menus_use_case, where a Menu Factory is used to 
generate menus based on the menu csv files for each residence. 

# Project Template

This is a template repository for CSC 207 projects. 
This repository contains starter code for a gradle project.
It also contains workflow documents that give instructions on how to manage your Github repository and how to use Github Projects for efficient collaboration.

## Checklist For Your Project
- [ ] Verify the correct settings for your project repository
- [ ] Set up Github Projects
- [ ] Create the implementation plan using issues and Github Projects
- [ ] Create deveopment branches for your features
- [ ] Use pull requests to merge finished features into main branch
- [ ] Conduct code reviews

**If your team has trouble with any of these steps, please ask on Piazza. For example, with how GitHub Classroom works, your team *may* not have permissions to do some of the first few steps, in which case we'll post alternative instructions as needed.**

## Workflow Documents

* Github Workflow: Please refer to the workflow that was introduced in the first lab. You should follow this when working on your code. The following document provides additional details too.

* [Project Planning and Development Guide](project_plan_dev.md): This document helps you to understand how to create and maintain a project plan for your class project. **This document helps you to complete the Implementation Plan Milestone.**

## Gradle Project
Import this project into your Intellij editor. It should automatically recognise this as a gradle repository.
The starter code was built using SDK version 11.0.1. Ensure that you are using this version for this project. (You can, of course, change the SDK version as per your requirement if your team has all agreed to use a different version)

You have been provided with two starter files for demonstration: HelloWorld and HelloWorldTest.

You will find HelloWorld in `src/main/java/tutorial` directory. Right click on the HelloWorld file and click on `Run HelloWorld.main()`.
This should run the program and print on your console.

You will find HelloWorldTest in `src/test/java/tutorial` directory. Right click on the HelloWorldTest file and click on `Run HelloWorldTest`.
All tests should pass. Your team can remove this sample of how testing works once you start adding your project code to the repo.

Moving forward, we expect you to maintain this project structure. You *should* use Gradle as the build environment, but it is fine if your team prefers to use something else -- just remove the gradle files and push your preferred project setup. Assuming you stick with Gradle, your source code should go into `src/main/java` (you can keep creating more subdirectories as per your project requirement). Every source class can auto-generate a test file for you. For example, open HelloWorld.java file and click on the `HelloWorld` variable as shown in the image below. You should see an option `Generate` and on clicking this your should see an option `Test`. Clicking on this will generate a JUnit test file for `HelloWorld` class. This was used to generate the `HelloWorldTest`.

![image](https://user-images.githubusercontent.com/5333020/196066655-d3c97bf4-fdbd-46b0-b6ae-aeb8dbcf351d.png)

You can create another simple class and try generating a test for this class.
