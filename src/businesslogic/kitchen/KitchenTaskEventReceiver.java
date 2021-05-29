package businesslogic.kitchen;

import businesslogic.event.Event;
import businesslogic.event.Service;

public interface KitchenTaskEventReceiver {
    void updateSummarySheetCreated(Event event, Service service, SummarySheet summarySheet);
}
