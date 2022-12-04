package screens;

import entities.ResidenceType;
import get_menus_use_case.GetMenusController;
import order_history_use_case.OrderHistoryInputBoundary;
import order_use_case.FoodItemDetailsView;
import order_use_case.OrderController;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class OrderView extends JFrame implements OrderViewModel {
    private final JPanel pnl = new JPanel(new GridLayout(4,1));

    private final JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
    private final List<JCheckBox> checkBoxes = new ArrayList<>();
    private final JPanel menusPanel = new JPanel(new GridLayout(1,2));
    private JComboBox<String> diningHallsDropdown;
    private final JButton orderButton = new JButton("Preview Order");
    Double totalPrice = 0.0;
    private final JLabel totalPriceString = new JLabel("Total Price: $0");

    private final JComboBox<String> residenceDropdown;
    private final ArrayList<JComboBox<String>> quantityDropdownsList = new ArrayList<>();
    private final String username;
    private final String email;

    OrderHistoryInputBoundary orderHistoryInteractor;
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
