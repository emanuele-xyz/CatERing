package businesslogic.kitchen;

import businesslogic.shift.KitchenShift;
import businesslogic.user.User;

public class Task {

    private User cook;
    private Integer estimatedTime;
    private String estimatedDoses;

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

    public boolean hasCook() {
        return cook != null;
    }

    public void removeCook() {
        cook = null;
    }

    public void editEstimates(Integer estimatedTime, String estimatedDoses) {
        if (estimatedTime != null) {
            this.estimatedTime = estimatedTime;
        }

        if (estimatedDoses != null) {
            this.estimatedDoses = estimatedDoses;
        }
    }
}
