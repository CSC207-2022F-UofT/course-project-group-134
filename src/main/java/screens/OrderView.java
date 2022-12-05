package screens;

import entities.ResidenceType;
import get_menus_use_case.GetMenusController;
import order_history_use_case.OrderHistoryInputBoundary;
import order_use_case.OrderController;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class OrderView extends JFrame implements OrderViewModel {

    /**
     * The main JPanel for this view
     */
    private final JPanel pnl = new JPanel(new GridLayout(4,1));

    /**
     *This panel contains the "Order" button and displays the total price.
     */
    private final JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

    /**
     * An Arraylist containing all the required checkboxes for the food items
     */
    private final List<JCheckBox> checkBoxes = new ArrayList<>();

    /**
     * The panel which contains all the menu items. Each cell of this grid contains three things:
     * 1. A quantity dropdown
     * 2. A checkbox
     * 3. A "Details" button for getting the details of each food item
     */
    private final JPanel menusPanel = new JPanel(new GridLayout(1,2));

    /**
     * A dropdown menu containing all the dining halls
     */
    private JComboBox<String> diningHallsDropdown;

    /**
     * The button which (upon clicking it) takes the user to the order preview
     */
    private final JButton orderButton = new JButton("Preview Order");

    /**
     * A parameter which stores the total price of the items
     */
    Double totalPrice = 0.0;

    /**
     * The string which displays the total price on the screen
     */
    private final JLabel totalPriceString = new JLabel("Total Price: $0");

    /**
     * A dropdown which shows a list of the residences from which the user can order food.
     */
    private final JComboBox<String> residenceDropdown;

    /**
     * A list of dropdowns for the quantities of the food items
     */
    private final ArrayList<JComboBox<String>> quantityDropdownsList = new ArrayList<>();

    /**
     * The username of the currently logged-in user
     */
    private final String username;

    /**
     * The email of the currently logged-in user.
     */
    private final String email;

    /**
     * TAn instance of the orderHistoryInteractor
     */
    OrderHistoryInputBoundary orderHistoryInteractor;

    /**
     * A boolean that represents whether-or-not the OrderPreviewScreen is currently open.  This is used to
     * create an error preventive screen.
     */
    boolean orderPreviewClosed = true;

    public OrderView(OrderController orderController, GetMenusController getMenusController, String username, String email, OrderHistoryInputBoundary orderHistoryInteractor) {
        this.orderHistoryInteractor = orderHistoryInteractor;
        this.email = email;
        this.username = username;
        ResidenceType[] residenceStates = ResidenceType.values();
        String[] residenceTypeList = new String[residenceStates.length];

        for (int i = 0; i < residenceStates.length; i++) {
            residenceTypeList[i] = residenceStates[i].toString();
        }
        residenceDropdown = new JComboBox<>(residenceTypeList);
        LabelComboboxPanel residencePanel = new LabelComboboxPanel(
                new JLabel("Select your residence:"), residenceDropdown);

        residenceDropdown.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //pnl.remove(menusPanel);
                            totalPrice = 0.0;
                            totalPriceString.setText("Total Price: $" + totalPrice);
                            orderButton.setVisible(true);
                            totalPriceString.setVisible(true);
                            getMenusController.setUpInteractor((String) residenceDropdown.getSelectedItem());
                            List<String[]> foodDetails = getMenusController.getFoodDetails();
                            Map<String, List<String[]>> foodReviews = getMenusController.getFoodReviews();
                            showMenus(foodDetails, foodReviews);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

        orderButton.addActionListener(actionEvent -> {
            orderClicked();
        });

        JButton backButton = new JButton("Back to home");
        pnl.add(backButton);

        backButton.addActionListener(actionEvent -> {
            this.dispose();
            new BuyerDefaultView(this.username, this.email, orderHistoryInteractor);
        });

        pnl.add(residencePanel);
        pnl.add(this.menusPanel);

        bottomPanel.add(totalPriceString);
        bottomPanel.add(orderButton);
        pnl.add(bottomPanel);
        orderButton.setVisible(false);
        totalPriceString.setVisible(false);

        this.add(pnl);

        this.setTitle("Create Order");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }


    /**
     *
     * @param foodDetails The details of all the foodItems that need to be shown (details like description, price, etc.)
     * @param foodReviews A list of food reviews, including the star rating and username of the user who wrote the review
     */
    @Override
    public void showMenus(List<String[]> foodDetails, Map<String, List<String[]>> foodReviews) {

        menusPanel.removeAll();
        bottomPanel.removeAll();
        quantityDropdownsList.clear();
        checkBoxes.clear();


        String[] quantities = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        for (int i= 0; i < foodDetails.size(); i++){
            JPanel tempFoodItemPanel = new JPanel(new GridLayout(1,3));

            JComboBox<String> tempFoodItemQuantity = new JComboBox<>(quantities);

            List<Double> foodItemPrices = new ArrayList<>();
            for (String[] strArr: foodDetails){
                foodItemPrices.add(Double.parseDouble(strArr[1]));
            }

            tempFoodItemQuantity.addActionListener(actionEvent -> {
                foodItemQuantityExtracted(foodItemPrices, tempFoodItemQuantity);
            });

            quantityDropdownsList.add(tempFoodItemQuantity);

            JButton tempDetailsButton = new JButton("Details");
            LabelComboboxPanel quantitiesPanel = new LabelComboboxPanel(
                    new JLabel("Quantity"), tempFoodItemQuantity);

            String itemName = foodDetails.get(i)[0];
            Double itemPrice = Double.parseDouble(foodDetails.get(i)[1]);
            String[] itemAllergens = foodDetails.get(i)[2].split(",");
            String[] itemIngredients = foodDetails.get(i)[3].split(",");
            int itemCalories = Integer.parseInt(foodDetails.get(i)[4]);
            List<String[]> itemReviews = foodReviews.get(itemName);

            tempDetailsButton.addActionListener(actionEvent -> {
                new FoodItemDetailsView(
                     itemName, itemPrice, itemAllergens, itemIngredients, itemCalories, itemReviews);
            });

            JCheckBox tempCheckBox = new JCheckBox(foodDetails.get(i)[0] + " ($" + foodItemPrices.get(i).toString() + ")");
            tempCheckBox.addActionListener(actionEvent -> {
                if (tempCheckBox.isSelected()){
                    quantityDropdownsList.get(checkBoxes.indexOf(tempCheckBox)).setSelectedIndex(1);
                }
                else{
                    quantityDropdownsList.get(checkBoxes.indexOf(tempCheckBox)).setSelectedIndex(0);
                }
            });

            tempFoodItemPanel.add(tempCheckBox);
            tempFoodItemPanel.add(quantitiesPanel);
            tempFoodItemPanel.add(tempDetailsButton);
            checkBoxes.add(tempCheckBox);
            menusPanel.add(tempFoodItemPanel);
        }
        pnl.add(menusPanel);


        bottomPanel.add(totalPriceString);
        bottomPanel.add(orderButton);
        pnl.add(bottomPanel);
        this.setVisible(true);

    }

    /**
     * The below method is factored out - it's actually for an action listener for the quantity dropdown
     * @param foodItemPrices The prices of the food items
     * @param tempFoodItemQuantity The quantities of the food items
     */
    private void foodItemQuantityExtracted(List<Double> foodItemPrices, JComboBox<String> tempFoodItemQuantity) {
        totalPrice = 0.0;
        for (JComboBox<String> comboBox: quantityDropdownsList){
            totalPrice += Integer.parseInt(Objects.requireNonNull(comboBox.getSelectedItem()).toString()) * foodItemPrices.get(quantityDropdownsList.indexOf(comboBox));
        }
        totalPriceString.setText(String.format("Total Price: $%.2f",totalPrice));

        if (Integer.parseInt(Objects.requireNonNull(tempFoodItemQuantity.getSelectedItem()).toString()) == 0){
            checkBoxes.get(quantityDropdownsList.indexOf(tempFoodItemQuantity)).setSelected(false);
        }
        else if (Integer.parseInt(tempFoodItemQuantity.getSelectedItem().toString()) > 0){
            checkBoxes.get(quantityDropdownsList.indexOf(tempFoodItemQuantity)).setSelected(true);
        }
    }

    /**
     * Below method is called when the "order" button is clicked.  It creates the order preview view if
     * certain conditions are satisfied.
     */
    public void orderClicked(){
        if (totalPrice <= 0) {
            JOptionPane.showMessageDialog(null, "Please order something!", "Order Failed", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        if (!orderPreviewClosed) {
            JOptionPane.showMessageDialog(null, "Place an order or close the screen.", "Order Failed", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        List<String> selectedFoodItems = new ArrayList<>();
        List<Integer> selectedFoodItemQuantities = new ArrayList<>();
        List<Double> selectedFoodItemPrices = new ArrayList<>();
        String selectedResidence = Objects.requireNonNull(residenceDropdown.getSelectedItem()).toString();
        for (JCheckBox checkBox : checkBoxes){
            if (checkBox.isSelected()){
                String[] checkBoxText = checkBox.getText().split("\\$");
                selectedFoodItems.add(checkBoxText[0].substring(0, checkBoxText[0].length() - 1));
                selectedFoodItemQuantities.add(Integer.parseInt((String) Objects.requireNonNull(quantityDropdownsList.get(checkBoxes.indexOf(checkBox)).getSelectedItem())));
                selectedFoodItemPrices.add(Double.parseDouble(checkBoxText[1].substring(0, checkBoxText[1].length() - 1)));

            }
        }
        String[] foodItemsArr = new String[selectedFoodItems.size()];
        Integer[] foodQuantArr = new Integer[selectedFoodItemQuantities.size()];
        Double[] foodPricesArr = new Double[selectedFoodItemPrices.size()];
        OrderPreviewScreen orderPreviewScreen = new OrderPreviewScreen(this, this.username, this.email, selectedResidence, selectedFoodItems.toArray(foodItemsArr), selectedFoodItemQuantities.toArray(foodQuantArr), selectedFoodItemPrices.toArray(foodPricesArr), totalPrice);
        orderPreviewClosed = false;
    }
}
