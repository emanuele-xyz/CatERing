package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.kitchen.KitchenTaskException;
import businesslogic.kitchen.SummarySheet;

public class TestKitchenManagement1a {
    public static void main(String[] args) {
        try {

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST OPEN SUMMARY SHEET");
            System.out.println("-".repeat(80));

            Event event = CatERing.getInstance().getEventManager().getEvent(1);
            Service service = event.getService(2);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(event, service);
            System.out.println(sh.debugString());

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
