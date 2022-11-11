package logout_use_case;

import entities.Database;

public class LogoutInteractor {

    public void logout(Database database) {
        database.setCurrentUser(null);
    }
}
