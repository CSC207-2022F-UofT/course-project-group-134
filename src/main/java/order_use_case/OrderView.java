package order_use_case;

import entities.ResidenceType;
import entities.UserType;
import get_menus_use_case.GetMenusController;
import get_menus_use_case.GetMenusResponseModel;
import screens.LabelComboboxPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class OrderView extends JFrame implements OrderViewModel{
    private JPanel pnl = new JPanel(new GridLayout(2,1));
    private JPanel menusPanel = new JPanel(new GridLayout(1,2));
    private JComboBox<String> diningHallsDropdown;
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
                    // show balance box based on userType selected
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
        this.setTitle("Sign up");
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
            JCheckBox tempCheckBox = new JCheckBox(foodItemNames.get(i) + " ($" + foodItemPrices.get(i).toString() + ")");
            checkBoxes.add(tempCheckBox);
            menusPanel.add(tempCheckBox);
        }
        this.menusPanel = menusPanel;
        pnl.add(menusPanel);
        this.add(pnl);
        /*this.setTitle("Sign up");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 400);
        this.setLocation(500, 100);*/
        this.setVisible(true);

    }
}
