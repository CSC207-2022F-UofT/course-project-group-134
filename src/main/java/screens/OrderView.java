package screens;

import entities.ResidenceType;
import get_menus_use_case.GetMenusController;
import get_menus_use_case.GetMenusResponseModel;
import order_use_case.FoodItemDetailsView;
import order_use_case.OrderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class OrderView extends JFrame implements OrderViewModel {
    private final JPanel pnl = new JPanel(new GridLayout(4,1));

    private final JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
    private final ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private final JPanel menusPanel = new JPanel(new GridLayout(1,2));
    private JComboBox<String> diningHallsDropdown;
    private final JButton orderButton = new JButton("Preview Order");
    Double totalPrice = 0.0;
    private final JLabel totalPriceString = new JLabel("Total Price: $0");

    private final JComboBox<String> residenceDropdown;
    private final ArrayList<JComboBox<String>> quantityDropdownsList = new ArrayList<>();
    private final OrderController orderController;
    private final GetMenusController getMenusController;
    private final String username;
    private final String email;

    public OrderView(OrderController orderController, GetMenusController getMenusController, String username, String email) {
        this.orderController = orderController;
        this.email = email;
        this.username = username;
        ResidenceType[] residenceStates = ResidenceType.values();
        String[] residenceTypeList = new String[residenceStates.length];
        this.getMenusController = getMenusController;

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
                            GetMenusResponseModel responseModel = getMenusController.getFoodItemNames((String) residenceDropdown.getSelectedItem());
                            showMenus(
                                    responseModel.getFoodItemNames(),
                                    responseModel.getFoodItemPrices(),
                                    responseModel.getFoodItemAllergens(),
                                    responseModel.getFoodItemIngredients(),
                                    responseModel.getFoodItemCalories(),
                                    responseModel.getFoodItemPopularities(),
                                    responseModel.getFoodItemStarAverages(),
                                    responseModel.getFoodItemReviews()
                            );
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
            new BuyerDefaultView(this.username, this.email);
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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }



    @Override
    public void showMenus(ArrayList<String> foodItemNames, ArrayList<Double> foodItemPrices, ArrayList<String[]> foodItemAllergens, ArrayList<String[]> foodItemIngredients, ArrayList<Integer> foodItemCalories, ArrayList<Integer> foodItemPopularities, ArrayList<Double> foodItemStarAverages, ArrayList<ArrayList<String>> foodItemReviews) {
        System.out.println("In view");
        System.out.println(foodItemNames.get(0));

        menusPanel.removeAll();
        bottomPanel.removeAll();
        quantityDropdownsList.clear();
        checkBoxes.clear();


        String[] quantities = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        for (int i= 0; i < 2; i++){
            JPanel tempFoodItemPanel = new JPanel(new GridLayout(1,3));

            JComboBox<String> tempFoodItemQuantity = new JComboBox<>(quantities);
            tempFoodItemQuantity.addActionListener(actionEvent -> {
                foodItemQuantityExtracted(foodItemPrices, tempFoodItemQuantity);
            });

            quantityDropdownsList.add(tempFoodItemQuantity);

            JButton tempDetailsButton = new JButton("Details");
            LabelComboboxPanel quantitiesPanel = new LabelComboboxPanel(
                    new JLabel("Quantity"), tempFoodItemQuantity);

            String itemName = foodItemNames.get(i);
            Double itemPrice = foodItemPrices.get(i);
            String[] itemAllergens = foodItemAllergens.get(i);
            String[] itemIngredients = foodItemIngredients.get(i);
            int itemCalories = foodItemCalories.get(i);
            int itemPopularity = foodItemPopularities.get(i);
            Double itemStarAverage = foodItemStarAverages.get(i);
            ArrayList<String> itemReviews = foodItemReviews.get(i);

            tempDetailsButton.addActionListener(actionEvent -> {
                FoodItemDetailsView foodItemDetailsView = new FoodItemDetailsView(
                     itemName, itemPrice, itemAllergens, itemIngredients, itemCalories, itemPopularity, itemStarAverage, itemReviews);
            });

            JCheckBox tempCheckBox = new JCheckBox(foodItemNames.get(i) + " ($" + foodItemPrices.get(i).toString() + ")");
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

    private void foodItemQuantityExtracted(ArrayList<Double> foodItemPrices, JComboBox<String> tempFoodItemQuantity) {
        totalPrice = 0.0;
        for (JComboBox<String> comboBox: quantityDropdownsList){
            totalPrice += Integer.parseInt(comboBox.getSelectedItem().toString()) * foodItemPrices.get(quantityDropdownsList.indexOf(comboBox));
        }
        totalPriceString.setText(String.format("Total Price: $%.2f",totalPrice));

        if (Integer.parseInt(tempFoodItemQuantity.getSelectedItem().toString()) == 0){
            checkBoxes.get(quantityDropdownsList.indexOf(tempFoodItemQuantity)).setSelected(false);
        }
        else if (Integer.parseInt(tempFoodItemQuantity.getSelectedItem().toString()) > 0){
            checkBoxes.get(quantityDropdownsList.indexOf(tempFoodItemQuantity)).setSelected(true);
        }
    }

    public void orderClicked(){
        ArrayList<String> selectedFoodItems = new ArrayList<>();
        ArrayList<Integer> selectedFoodItemQuantities = new ArrayList<>();
        ArrayList<Double> selectedFoodItemPrices = new ArrayList<>();
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
        new OrderPreviewScreen(selectedFoodItems.toArray(foodItemsArr), selectedFoodItemQuantities.toArray(foodQuantArr), selectedFoodItemPrices.toArray(foodPricesArr), totalPrice);
    }
}
