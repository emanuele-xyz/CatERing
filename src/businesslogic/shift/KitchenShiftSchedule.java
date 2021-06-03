package businesslogic.shift;

import java.util.ArrayList;
import java.util.List;

public class KitchenShiftSchedule {

    private final List<KitchenShift> kitchenShifts;

    public KitchenShiftSchedule() {
        kitchenShifts = new ArrayList<>();
    }

    public List<KitchenShift> getKitchenShifts() {
        return kitchenShifts;
    }
}
