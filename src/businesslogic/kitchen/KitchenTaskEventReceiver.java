package businesslogic.kitchen;

import businesslogic.event.Event;
import businesslogic.event.Service;

import java.util.List;

public interface KitchenTaskEventReceiver {

    void updateSummarySheetCreated(Event event, Service service, SummarySheet summarySheet);
    void updateSummarySheetOpened(Event event, Service service, SummarySheet summarySheet);
    void updateActivityAdded(SummarySheet summarySheet, List<Activity> activities);
    void updateActivityDeleted(SummarySheet summarySheet, Activity activity);
}
