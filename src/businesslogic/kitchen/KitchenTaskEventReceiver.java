package businesslogic.kitchen;

import businesslogic.event.Event;
import businesslogic.event.Service;

import java.util.List;

public interface KitchenTaskEventReceiver {

    void updateSummarySheetCreated(Event event, Service service, SummarySheet summarySheet);
    void updateSummarySheetOpened(Event event, Service service, SummarySheet summarySheet);
    void updateActivityAdded(SummarySheet summarySheet, List<Activity> activities);
    void updateActivityDeleted(SummarySheet summarySheet, Activity activity);
    void updateActivityModified(SummarySheet summarySheet, Activity activity);
    void updateActivitiesRearranged(SummarySheet summarySheet);
    void updateTaskCreated(SummarySheet summarySheet, Activity activity, Task task);
    void updateTaskActivityModified(SummarySheet summarySheet, Activity oldActivity, Activity newActivity, Task task);
    void updateTaskShiftModified(SummarySheet summarySheet, Activity activity, Task task);
    void updateTaskCookModified(SummarySheet summarySheet, Activity activity, Task task);
}
