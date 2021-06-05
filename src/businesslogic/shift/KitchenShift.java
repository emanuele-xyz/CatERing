package businesslogic.shift;

import businesslogic.user.User;

public class KitchenShift {

    private boolean isFull;

    public boolean isFull() {
        return isFull;
    }

    // TODO: this should be implemented in Shift Management use case
    public boolean isInThePast() {
        return false;
    }

    public void markAsFull() {
        this.isFull = true;
    }

    // TODO: this should be implemented in Shift Management use case
    public boolean hasCookAvailable(User cook) {
        return true;
    }
}
