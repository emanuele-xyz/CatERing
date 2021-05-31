package businesslogic.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.recipe.KitchenProcedure;
import businesslogic.shift.KitchenShift;
import businesslogic.user.User;

import java.util.List;
import java.util.function.Consumer;

public class KitchenTaskManager {

    private SummarySheet currentSummarySheet;
    private List<KitchenTaskEventReceiver> eventReceivers;

    // OPERATIONS

    public void generateSummarySheet(Event event, Service service) throws UseCaseLogicException, KitchenTaskException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef() || !event.consistsOf(service) || !service.hasMenu() || service.getSummarySheet() != null) {
            throw new UseCaseLogicException();
        }

        if (!user.tookOn(event)) {
            throw new KitchenTaskException();
        }

        currentSummarySheet = service.generateSummarySheet();
        notifySummarySheetCreated(event, service, currentSummarySheet);
    }

    public void openSummarySheet(Event event, Service service) throws UseCaseLogicException, KitchenTaskException {
        if (!event.consistsOf(service) || service.getSummarySheet() == null) {
            throw new UseCaseLogicException();
        }

        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!user.tookOn(event)) {
            throw new KitchenTaskException();
        }

        currentSummarySheet = service.getSummarySheet();
        notifySummarySheetOpened(event, service, currentSummarySheet);
    }

    public void addActivity(KitchenProcedure kitchenProcedure, String toPrepare, String prepared) throws UseCaseLogicException {
        if (currentSummarySheet == null) {
            throw new UseCaseLogicException();
        }

        List<Activity> addedActivities = currentSummarySheet.addActivity(kitchenProcedure, toPrepare, prepared);
        notifyActivityAdded(currentSummarySheet, addedActivities);
    }

    private void removeActivity(Activity activity) throws UseCaseLogicException, KitchenTaskException {
        if (currentSummarySheet == null || !currentSummarySheet.hasActivity(activity)) {
            throw new UseCaseLogicException();
        }

        if (activity.hasAlreadyBeenCarriedOut()) {
            throw new KitchenTaskException();
        }

        currentSummarySheet.removeActivity(activity);
        notifyActivityDeleted(currentSummarySheet, activity);
    }

    public void editActivity(Activity activity, String dosesToPrepare, String alreadyPreparedDoses, String preparedDoses) throws UseCaseLogicException {
        if (currentSummarySheet == null || !currentSummarySheet.hasActivity(activity)) {
            throw new UseCaseLogicException();
        }

        activity.edit(dosesToPrepare, alreadyPreparedDoses, preparedDoses);
        notifyActivityModified(currentSummarySheet, activity);
    }

    public void moveActivity(Activity activity, int position) throws UseCaseLogicException {
        if (currentSummarySheet == null) {
            throw new UseCaseLogicException();
        }

        currentSummarySheet.moveActivity(activity, position);
        notifyActivitiesRearranged(currentSummarySheet);
    }

    public void createTask(Activity activity, KitchenShift shift, User cook, int estimatedTime, String estimatedDoses) throws UseCaseLogicException, KitchenTaskException {
        if (currentSummarySheet == null || !currentSummarySheet.hasActivity(activity)) {
            throw new UseCaseLogicException();
        }

        if (shift.isFull() || shift.isInThePast() || !cook.isAvailable(shift)) {
            throw new KitchenTaskException();
        }

        Task task = activity.createTask(shift, cook, estimatedTime, estimatedDoses);
        notifyTaskCreated(currentSummarySheet, activity, task);
    }

    public void editTaskActivity(Task task, Activity newActivity) throws UseCaseLogicException, KitchenTaskException {

        Activity oldActivity = currentSummarySheet.getActivityByTask(task);

        if (currentSummarySheet == null || oldActivity == null) {
            throw new UseCaseLogicException();
        }

        if (task.isInThePast()) {
            throw new KitchenTaskException();
        }

        oldActivity.removeTask(task);
        newActivity.addTask(task);

        notifyTaskActivityModified(currentSummarySheet, oldActivity, newActivity, task);
    }

    // EVENT SENDER

    public void addEventReceiver(KitchenTaskEventReceiver receiver) {
        eventReceivers.add(receiver);
    }

    public void removeEventReceiver(KitchenTaskEventReceiver receiver) {
        eventReceivers.remove(receiver);
    }

    private void notifySummarySheetCreated(Event event, Service service, SummarySheet summarySheet) {
        eventReceiversForEach(er -> er.updateSummarySheetCreated(event, service, summarySheet));
    }

    private void notifySummarySheetOpened(Event event, Service service, SummarySheet summarySheet) {
        eventReceiversForEach(er -> er.updateSummarySheetOpened(event, service, summarySheet));
    }

    private void notifyActivityAdded(SummarySheet summarySheet, List<Activity> activities) {
        eventReceiversForEach(er -> er.updateActivityAdded(summarySheet, activities));
    }

    private void notifyActivityDeleted(SummarySheet summarySheet, Activity activity) {
        eventReceiversForEach(er -> er.updateActivityDeleted(summarySheet, activity));
    }

    private void notifyActivityModified(SummarySheet summarySheet, Activity activity) {
        eventReceiversForEach(er -> er.updateActivityModified(summarySheet, activity));
    }

    private void notifyActivitiesRearranged(SummarySheet summarySheet) {
        eventReceiversForEach(er -> er.updateActivitiesRearranged(summarySheet));
    }

    private void notifyTaskCreated(SummarySheet summarySheet, Activity activity, Task task) {
        eventReceiversForEach(er -> er.updateTaskCreated(summarySheet, activity, task));
    }

    private void notifyTaskActivityModified(SummarySheet summarySheet, Activity oldActivity, Activity newActivity, Task task) {
        eventReceiversForEach(er -> er.updateTaskActivityModified(summarySheet, oldActivity, newActivity, task));
    }

    // UTILITY

    private void eventReceiversForEach(Consumer<? super KitchenTaskEventReceiver> action) {
        eventReceivers.forEach(action);
    }
}
