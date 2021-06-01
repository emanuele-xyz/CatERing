package businesslogic.shift;

import java.util.List;

public class ShiftManager {

    private List<ShiftEventReceiver> eventReceivers;

    // OPERATIONS

    // TODO: to be implemented
    public KitchenShiftSchedule getKitchenShiftsSchedule() {
        return null;
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
