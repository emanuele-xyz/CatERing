package persistence;

import businesslogic.shift.KitchenShift;
import businesslogic.shift.ShiftEventReceiver;

public class ShiftPersistence implements ShiftEventReceiver {

    @Override
    public void updateKitchenShiftFulled(KitchenShift kitchenShift) {
        KitchenShift.updateMarkAsFull(kitchenShift);
    }
}
