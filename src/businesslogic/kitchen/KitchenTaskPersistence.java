package businesslogic.kitchen;

import businesslogic.event.Event;
import businesslogic.event.Service;

import java.util.List;

public class KitchenTaskPersistence implements KitchenTaskEventReceiver {

    @Override
    public void updateSummarySheetCreated(Event event, Service service, SummarySheet summarySheet) {

    }

    @Override
    public void updateSummarySheetOpened(Event event, Service service, SummarySheet summarySheet) {

    }

    @Override
    public void updateActivityAdded(SummarySheet summarySheet, List<Activity> activities) {

    }

    @Override
    public void updateActivityDeleted(SummarySheet summarySheet, Activity activity) {

    }

    @Override
    public void updateActivityModified(SummarySheet summarySheet, Activity activity) {

    }

    @Override
    public void updateActivitiesRearranged(SummarySheet summarySheet) {

    }

    @Override
    public void updateTaskCreated(SummarySheet summarySheet, Activity activity, Task task) {

    }

    @Override
    public void updateTaskActivityModified(SummarySheet summarySheet, Activity oldActivity, Activity newActivity, Task task) {

    }

    @Override
    public void updateTaskShiftModified(SummarySheet summarySheet, Activity activity, Task task) {

    }

    @Override
    public void updateTaskCookModified(SummarySheet summarySheet, Activity activity, Task task) {

    }

    @Override
    public void updateTaskCookRemoved(SummarySheet summarySheet, Activity activity, Task task) {

    }

    @Override
    public void updateTaskEstimatesModified(SummarySheet summarySheet, Activity activity, Task task) {

    }

    @Override
    public void updateTaskDeleted(SummarySheet summarySheet, Activity activity, Task task) {

    }
}
