package get_menus_use_case;

import entities.Menu;
import entities.ResidenceType;

public interface MenuGatewayInterface {
    Menu createMenu(ResidenceType type) throws Exception;
}
