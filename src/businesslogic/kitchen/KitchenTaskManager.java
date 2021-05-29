package businesslogic.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.user.User;

import java.util.List;

public class KitchenTaskManager {

    private SummarySheet currentSummarySheet;
    private List<KitchenTaskEventReceiver> eventReceivers;

    // OPERATIONS

    public void generateSummarySheet(Event event, Service service) throws UseCaseLogicException, KitchenTaskException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if (!user.isChef() || !event.consistsOf(service) || !service.hasMenu() || service.getSummarySheet() != null) {
            throw new UseCaseLogicException();
        }

        if (!event.isAppointedTo(user)) {
            throw new KitchenTaskException();
        }

        currentSummarySheet = service.generateSummarySheet();
        notifySummarySheetCreated(event, service, currentSummarySheet);
    }

    // EVENT SENDER

    public void addEventReceiver(KitchenTaskEventReceiver receiver) {
        eventReceivers.add(receiver);
    }

    public void removeEventReceiver(KitchenTaskEventReceiver receiver) {
        eventReceivers.remove(receiver);
    }

    private void notifySummarySheetCreated(Event event, Service service, SummarySheet summarySheet) {
        for (KitchenTaskEventReceiver e : eventReceivers) {
            e.updateSummarySheetCreated(event, service, summarySheet);
        }
    }
}
