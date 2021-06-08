package businesslogic.shift;

import java.util.ArrayList;
import java.util.List;

public class ShiftManager {

    private final List<ShiftEventReceiver> eventReceivers;
    private final KitchenShiftSchedule kitchenShiftSchedule;

    public ShiftManager() {
        eventReceivers = new ArrayList<>();
        kitchenShiftSchedule = new KitchenShiftSchedule();
    }

    // OPERATIONS

    public KitchenShiftSchedule getKitchenShiftSchedule() {
        return kitchenShiftSchedule;
    }

    public void markKitchenShiftAsFull(KitchenShift kitchenShift) throws ShiftException {
        if (kitchenShift.isInThePast()) {
            throw new ShiftException();
        }

        kitchenShift.markAsFull();
        notifyKitchenShiftFulled(kitchenShift);
    }

    // EVENT SENDER

    private void notifyKitchenShiftFulled(KitchenShift kitchenShift) {
        eventReceivers.forEach(er -> er.updateKitchenShiftFulled(kitchenShift));
    }
}
