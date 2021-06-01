package businesslogic.kitchen;

import businesslogic.shift.KitchenShift;
import businesslogic.user.User;

public class Task {

    private User cook;

    // TODO: to be implemented
    public Task(KitchenShift shift, User cook, int estimatedTime, String estimatedDoses) {

    }

    // TODO: to be implemented
    public boolean isInThePast() {
        return false;
    }

    // TODO: to be implemented
    public User getCook() {
        return null;
    }

    // TODO: to be implemented
    public void setShift(KitchenShift shift) {

    }

    // TODO: to be implemented
    public KitchenShift getShift() {
        return null;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }
}
