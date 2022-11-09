package entities;

import entities.menu_factories.MenuFactory;

public class DiningHallFactory {
    private MenuFactory menuFactory;

    public DiningHallFactory(MenuFactory menuFactory){
        this.menuFactory = menuFactory;
    }

    public DiningHall create(DiningHallTypes types){
    }
}
