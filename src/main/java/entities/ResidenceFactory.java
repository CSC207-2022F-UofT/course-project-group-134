package entities;

import get_menus_use_case.MenuGateway;

public class ResidenceFactory {
    private final MenuGateway menuGateway;

    public ResidenceFactory(MenuGateway menuGateway){
        this.menuGateway = menuGateway;
    }

    public Residence createDiningHall(ResidenceType type) throws Exception {
        Menu menu = this.menuGateway.createMenu(type);
        return new Residence(type.name(), type.name(), menu);
    }
//NOTE: the below main method is a sample to test the running of DiningHallFactory

    /*public static void main(String[] args) throws Exception {
        MenuFactory menuFactory = new MenuFactory();
        DiningHallFactory diningHallFactory = new DiningHallFactory(menuFactory);
        DiningHall diningHall = diningHallFactory.createDiningHall(DiningHallTypes.CHESTNUT);
        System.out.println(Arrays.toString(diningHall.getMenu().getFoodItems().get(1).getIngredients()));
    }*/
}
