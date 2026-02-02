C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name,
the product names, and the names of the parts. 
--Updated mainscreen.html. Line 14, Line 19. 
Modified the page title & header.

D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include
navigation to and from the “About” page and the main screen. 
--Added an About page for the bakery customer by creating about.html in the templates directory. 
The page describes Sweet Crumbs Bakery and its use of the inventory management system. 
Navigation was added between the main screen(Line20) and the About page(Line27) using Thymeleaf links. Controller mapping was added to 
route requests to the About page(Line 55). About.html

E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five
products in your sample inventory and should not overwrite existing data in the database. 
--Sample inventory was added for a bakery shop consisting of five parts and five products.(Line 47-97) Mainscreencontroller.java

F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
The “Buy Now” button must be next to the buttons that update and delete products.
The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
Display a message that indicates the success or failure of a purchase. 
--A Buy Now button was added next to the Update and Delete buttons in the product list. mainscreen.html (Line 89-92) When clicked, the button attempts
to purchase one unit of the selected product. If inventory is available, the product inventory is reduced by one and a 
success message is displayed. If inventory is unavailable, a failure message is shown. Associated part inventory is not affected. MainsScreenController.html (Line 114-129)
confirmation.html page added

G.  Modify the parts to track maximum and minimum inventory by doing the following:
Add additional fields to the part entity for maximum and minimum inventory.
Modify the sample inventory to include the maximum and minimum fields.
Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
Rename the file the persistent storage is saved to.
Modify the code to enforce that the inventory is between or at the minimum and maximum value.
--Minimum and maximum inventory fields were added to the Part entity to track inventory limits. Part.java(Line 32-36 / Line 98-112)
The sample bakery inventory was updated to include minimum and maximum inventory values and is only added when both the part and product lists are empty. MainScreenControllerr.java (Line 56-89)
Additional text input fields were added to set minimum and maximum inventory values when adding or updating inhouse parts. InhousePartForm.html (Line 24-44 )
Additional text input fields were added to set minimum and maximum inventory values when adding or updating outsourced parts. OutsourcedPartForm.html (Line 25-45)
The persistent storage file name was updated to reflect the bakery application. application.properties(Line 6)
Logic was added to enforce that part inventory remains between or at the defined minimum and maximum values when parts are added or updated. PartServiceImpl.java (Line 59-69)

H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.
Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.
Display error messages when adding and updating parts if the inventory is greater than the maximum.
--Updated to account for minimum inventory when product inventory changes. EnufPartsValidator.java (Line 36-41)
Custom validation annotation for part minimum and maximum inventory. ValidMinMaxInventory.java
Validation logic enforcing inventory bounds. MinMaxInventoryValidator.java
Displays validation error messages for inventory constraints. InhousePartForm.html (Line 13-17)
Displays validation error messages for inventory constraints. OutsourcedPartForm.html (Line 14-18)

I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.
--wo unit tests were added to verify that part inventory cannot be below the minimum or above the maximum allowed values. PartTest.java (Line(160-176))

J.  Remove the class files for any unused validators in order to clean your code.
--Unused validator classes were removed to clean the codebase.Files: ValidProductPrice.java, PriceProductValidator.java, ValidDeletePart.java, DeletePartValidator