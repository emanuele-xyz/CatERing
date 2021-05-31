package businesslogic.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.recipe.KitchenProcedure;
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

    private void eventReceiversForEach(Consumer<? super KitchenTaskEventReceiver> action) {
        eventReceivers.forEach(action);
    }
}
