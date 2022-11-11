package entities;

public class DiningHallFactory {
    private MenuFactory menuFactory;

    public DiningHallFactory(MenuFactory menuFactory){
        this.menuFactory = menuFactory;
    }

}
