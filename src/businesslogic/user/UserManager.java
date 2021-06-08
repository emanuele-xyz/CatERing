package businesslogic.user;

import java.util.List;

public class UserManager {

    private User currentUser;

    // TODO: bisogna implementare il login vero!
    public void fakeLogin(String username) {
        this.currentUser = User.loadUser(username);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    // TODO: to be implemented
    public List<User> getCooks() {
        return null;
    }
}
