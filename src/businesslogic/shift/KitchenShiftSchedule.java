package businesslogic.shift;

import java.util.List;

public class KitchenShiftSchedule {

    private final List<KitchenShift> kitchenShifts;

    public KitchenShiftSchedule() {
        kitchenShifts = KitchenShift.loadAllKitchenShift();
    }

    public List<KitchenShift> getKitchenShifts() {
        return kitchenShifts;
    }
}
