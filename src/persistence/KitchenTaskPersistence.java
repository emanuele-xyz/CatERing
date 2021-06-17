package persistence;

import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.kitchen.Activity;
import businesslogic.kitchen.KitchenTaskEventReceiver;
import businesslogic.kitchen.SummarySheet;
import businesslogic.kitchen.Task;

import java.util.List;

public class KitchenTaskPersistence implements KitchenTaskEventReceiver {

    @Override
    public void updateSummarySheetCreated(Event event, Service service, SummarySheet summarySheet) {
        SummarySheet.saveNewSummarySheet(service, summarySheet);
    }

    @Override
    public void updateSummarySheetOpened(Event event, Service service, SummarySheet summarySheet) {}

    @Override
    public void updateActivityAdded(SummarySheet summarySheet, List<Activity> activities) {
        int summarySheetID = summarySheet.getID();

        activities.forEach(activity -> {
            int position = summarySheet.findActivityPosition(activity);
            Activity.saveNewActivity(summarySheetID, activity, position);
        });
    }

    // TODO: to be implemented
    @Override
    public void updateActivityDeleted(SummarySheet summarySheet, Activity activity) {
        System.out.println("Deleting activity from summary sheet ...");
    }

    // TODO: to be implemented
    @Override
    public void updateActivityModified(SummarySheet summarySheet, Activity activity) {
        System.out.println("Modifying summary sheet activity ...");
    }

    @Override
    public void updateActivitiesRearranged(SummarySheet summarySheet) {
        SummarySheet.saveActivitiesOrder(summarySheet);
    }

    @Override
    public void updateTaskCreated(SummarySheet summarySheet, Activity activity, Task task) {
        Task.saveNewTask(activity.getID(), task);
    }

    // TODO: to be implemented
    @Override
    public void updateTaskActivityModified(SummarySheet summarySheet, Activity oldActivity, Activity newActivity, Task task) {
        System.out.println("Modifying task activity for summary sheet ...");
    }

    // TODO: to be implemented
    @Override
    public void updateTaskShiftModified(SummarySheet summarySheet, Activity activity, Task task) {
        System.out.println("Modifying task shift for summary sheet ...");
    }

    // TODO: to be implemented
    @Override
    public void updateTaskCookModified(SummarySheet summarySheet, Activity activity, Task task) {
        System.out.println("Modifying task cook for summary sheet ...");
    }

    // TODO: to be implemented
    @Override
    public void updateTaskCookRemoved(SummarySheet summarySheet, Activity activity, Task task) {
        System.out.println("Removing task cook for summary sheet ...");
    }

    // TODO: to be implemented
    @Override
    public void updateTaskEstimatesModified(SummarySheet summarySheet, Activity activity, Task task) {
        System.out.println("Updating task estimates for summary sheet ...");
    }

    // TODO: to be implemented
    @Override
    public void updateTaskDeleted(SummarySheet summarySheet, Activity activity, Task task) {
        System.out.println("Deleting task activity for summary sheet ...");
    }
}
