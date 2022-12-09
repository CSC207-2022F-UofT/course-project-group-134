# Based Food Service App (Group 134)

## Presentation Link
https://docs.google.com/presentation/d/1eiFbZM0p27PR56dIkwfjLTG0PM841EZgh_D1iolnpoE/edit

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

1. Sign Up (Aditya Bandekar): This use case allows a user to sign up as a buyer or seller.  Both
types of users must enter a username, email, and password.  A seller must additionally
input the residence where their meal plan works and their meal plan balance.  A future
improvement to this use case is to allow the seller to connect their TCard to this app - 
this will ensure that their meal plan dollars on the app are linked to their actual dollars
2. Login (Aditya Bandekar): This allows users to sign in.  The sign in use case classes recognize
whether a buyer or seller has signed in, and accordingly updates the screens
3. Get Menus (Tejas Balaji): This use case is an intermediate use case when a buyer wishes to
buy food.  When the buyer clicks on "Create New Order", the get menus use case
takes over.  Based on the residence from which the buyer wishes to buy food, 
the get menus use case gathers the menu from that residence and displays it
to the user.  It also handles showing item descriptions, calculating total price,
and showing the user a preview of their order
4. Ordering (Deon Chan): Once the user has previewed their order, they made place the order.
The ordering use case handles updating the order csv file with the newly created
order, and updating the view accordingly (i.e., taking the user back to their
home screen and displaying the current order on their screen)
5. Selling (Vivian Liu): Once a buyer has made an order, every seller whose meal plan functions
in the dining hall to which the order was made can now see and accept the order.
Each seller sees a list of orders associated with their respective dining halls.
They can view the items ordered, the quantities of each item, and the total price.
A seller can only accept an order if they have enough meal plan balance to accept it.
Each time the seller accepts an order, the respective amount is deducted from their
meal plan balance.
Once the seller accepts the order, the buyer is required to confirm the order
from their side.  
6. Chat (Aaron Ma and Benjamin Liu): After a seller confirms an order, the buyer and seller can communicate via
the chat.  The chat provides an easy and reliable manner of allowing the two users to
discuss the meeting place and possibly mode of payment (from the buyer to the seller).
For each order made and accepted, a new chat interface is set up between the respective
buyer and seller.
7. Order History and Current Orders (Shravan Kruthick Sridhar): When a buyer accepts an order, they see the details of that order in
the "current orders panel" on their home page.  This includes information such as items
ordered, quantity of each item, total price, order status etc.  In addition, once a seller confirms
that order, the status of the order changes to "Seller Confirmed".  Once a buyer accepts
the order, the status changes to "Buyer confirmed".  Once the order has been successfully
delivered, the status changes to "Finished", and the order is moved to the "Order History"
panel on the home page
8. Review (Catherine Lacis): For each order in the "Order History" panel, a buyer can give a review for
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
usage of this is in the get_menus_use_case, where a Menu Factory (called the GetMenusGateway in the program) is used to 
generate menus based on the menu csv files for each residence. We also used the factory design pattern in the following classes: BuyerFactory.java, OrderFactory.java, ReviewFactory.java, SellerFactory.java, UserFactory.java.
2. The singleton design pattern used in the chat use case. Only one instance of the ChatDsGateway was allowed to be instantiated. This is to limit access of the chat files to a single gateway instance in order to prevent IOExceptions from occuring. The instance of ChatDsGateway is obtained by calling the static method ChatDsGateway.getInstance().
3. The Observer Design pattern was used in the screens. The buttons in JFrame implement the Observer Design Pattern. The action listeners act as observers in this case.

## Adherence to SOLID

1. SRP: Each file is only responsible for one functionality of that use case.  Since we followed Clean Architecture,
this principle was taken care of quite easily
2. OCP: More use cases can easily be added without modifying others (use cases don't really depend on each other).
Also, we have packaged our code well, and hence it is very easy to simply add a new package for a new use case
3. LSP: Buyer and Seller (which are the only subclasses of User) only add to methods from User.  Additionally,
we used Maps and Lists instead of HashMaps and ArrayLists as reference types.  This ensures that LSP is followed
4. ISP: There are multiple files for each use case (interactor, gateway, presenter, etc.), and all the files corresponding
to the same use case are in the same package.
5. DIP: We have used input/output boundaries wherever necessary to invert dependencies

## Improvements from Milestone 4 feedback
Our TA gave us a lot of feedback in multiple areas following our Milestone 4 submission.  We tried our best to 
work on this feedback.  Below are all the aspects of the project that we fixed by following the feeback:

### New improvements on functionality
1. Upon Buyer login, the message shows the actual username of the buyer rather than something like "entities.Buyer @xxxxxx".
2. Null pointer exception when seller clicks on "Accept Order" when there are no active orders fixed.
3. Chat use case fully implemented.
4. Seller can now accept multiple orders rather than just one.
5. Reviewing an order has been restricted to just one review per item. If a buyer tries to review that same item again,
then their old review gets overriden with the new one
6. Order History no longer displays a "null" as the seller username.
7. Review and food rating update the food item's description accurately.
8. Confirmation pop-ups implemented for logging out, accepting an order (seller), and making an order (buyer).

### Other new improvements
1. Javadoc added to all the use cases and all the important screen classes.
2. Comments added in code whenever deemed necessary.
3. We greatly increased the utilization of GitHub features ("Issues" used more frequently, created a template for 
pull requests, updated this README file, made more comments to code while reviewing).
4. Increased adherence to SOLID by keeping reference types as abstract as possible (see above for detailed info).

## Testing Information
Please note that after Milestone 4, we moved all the files of the form "(use_case)Main" (eg: GetMenusMain) to a new package
called the `use_case_mains` package.  This is because such files cannot really be tested - as their name suggests, they
simply act as "main" files for each use case.  They take care of things like initializing interactors, gateways, 
presenters, etc.  Thus, before we moved these files, the testing coverage did not accurately represent our actual coverage;
following the move, it does.

There are 40 tests in total. Below is the coverage information for each of the use cases.
<img width="1010" alt="image" src="https://user-images.githubusercontent.com/113255669/206610202-8e09ee22-d4a0-413d-a1fc-4abcfe6c482e.png">


Note that in many of the cases above, we do not have a 100% coverage due to the inability to test certain
exceptions.  Additionally, there are certain files (such as reviews.csv) which are local files; hence, if we were
to make any tests involving them, then the tests might fail on different computers since each computer is bound to
have different information in the file (This is the only reason why the get_menus_use_case does not have 100%
on all three aspects).  We also did not test the screens; running the program itself is a good test for them, and 
we did this multiple times.
