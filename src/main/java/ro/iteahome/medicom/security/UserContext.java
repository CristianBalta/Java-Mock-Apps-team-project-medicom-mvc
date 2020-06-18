package ro.iteahome.medicom.security;

import ro.iteahome.medicom.model.entity.User;

public class UserContext { // TODO: Develop this for web session.

    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        UserContext.loggedInUser = user;
    }
}
