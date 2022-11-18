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

public class OrderView extends JFrame implements OrderViewModel {
    private JPanel pnl = new JPanel(new GridLayout(4,1));
    private JPanel menusPanel = new JPanel(new GridLayout(1,2));
    private JComboBox<String> diningHallsDropdown;
    private JButton orderButton = new JButton("Order");
    private JComboBox<String> residenceDropdown;
    private OrderController orderController;
    private GetMenusController getMenusController;

    public OrderView(OrderController orderController, GetMenusController getMenusController) {
        this.orderController = orderController;
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
                            pnl.remove(menusPanel);
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

        pnl.add(residencePanel);
        pnl.add(this.menusPanel);
        this.add(pnl);

        this.setTitle("Create Order");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);
        this.setVisible(true);
    }



    @Override
    public void showMenus(ArrayList<String> foodItemNames, ArrayList<Double> foodItemPrices, ArrayList<String[]> foodItemAllergens, ArrayList<String[]> foodItemIngredients, ArrayList<Integer> foodItemCalories, ArrayList<Integer> foodItemPopularities, ArrayList<Double> foodItemStarAverages, ArrayList<ArrayList<String>> foodItemReviews) {
        System.out.println("In view");
        System.out.println(foodItemNames.get(0).toString());
        JPanel menusPanel = new JPanel(new GridLayout(1,2));
        ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
        for (int i= 0; i < 2; i++){
            JPanel tempFoodItemPanel = new JPanel(new GridLayout(1,2));
            JButton tempDetailsButton = new JButton("Details");

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
            tempFoodItemPanel.add(tempCheckBox);
            tempFoodItemPanel.add(tempDetailsButton);
            checkBoxes.add(tempCheckBox);
            menusPanel.add(tempFoodItemPanel);
        }
        this.menusPanel = menusPanel;
        pnl.add(menusPanel);
        pnl.add(orderButton);
        JTextField temp = new JTextField(20);
        temp.addActionListener(actionEvent ->{
            JOptionPane.showMessageDialog(null,
                    "Hello there",
                    "Testing",
                    JOptionPane.PLAIN_MESSAGE);
        });
        pnl.add(temp);
        this.setVisible(true);

    }


}
