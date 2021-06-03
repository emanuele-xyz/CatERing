package businesslogic.kitchen;

import businesslogic.shift.KitchenShift;
import businesslogic.user.User;

public class Task {

    private KitchenShift shift;
    private User cook;
    private final boolean completed;
    private Integer estimatedTime;
    private String estimatedDoses;

    public Task(KitchenShift shift, User cook, Integer estimatedTime, String estimatedDoses) {
        this.shift = shift;
        this.completed = false;
        if (cook != null) this.cook = cook;
        this.estimatedTime = estimatedTime != null ? estimatedTime : 0;
        this.estimatedDoses = estimatedDoses != null ? estimatedDoses : "";
    }

    // TODO: this should be implemented in Shift Management use case
    public boolean isInThePast() {
        return false;
    }

    public User getCook() {
        return cook;
    }

    public KitchenShift getShift() {
        return shift;
    }

    public void setShift(KitchenShift shift) {
        this.shift = shift;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    public void removeCook() {
        cook = null;
    }

    public boolean hasCook() {
        return cook != null;
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
