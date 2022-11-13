package entities;

public class ResidenceFactory {
    private MenuFactory menuFactory;

    public ResidenceFactory(MenuFactory menuFactory){
        this.menuFactory = menuFactory;
    }

    public Residence createDiningHall(ResidenceTypes type) throws Exception {
        Menu menu = this.menuFactory.createMenu(type);
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
