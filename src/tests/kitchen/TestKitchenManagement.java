package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.kitchen.Activity;
import businesslogic.kitchen.KitchenTaskException;
import businesslogic.kitchen.SummarySheet;
import businesslogic.recipe.Recipe;
import businesslogic.shift.KitchenShift;
import businesslogic.shift.KitchenShiftSchedule;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.util.List;

public class TestKitchenManagement {
    public static void main(String[] args) {
        try {

            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nTEST CREATE SUMMARY SHEET");
            Event e = CatERing.getInstance().getEventManager().getEvent(1);
            Service s = e.getService(2);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().generateSummarySheet(e, s);

            System.out.println("\nTEST ADD ACTIVITY");
            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity a1 = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(0), "12 porzioni", "nessuna porzione");
            Activity a2 = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(1), "7 piatti", "nessun piatto");
            Activity a3 = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(2), "1 Kg", "20 g");

            System.out.println("\nTEST MOVE ACTIVITY");
            CatERing.getInstance().getKitchenTaskManager().moveActivity(a3, 0);

            System.out.println("\nTEST GET KITCHEN SHIFTS SCHEDULE");
            KitchenShiftSchedule kss = CatERing.getInstance().getShiftManager().getKitchenShiftSchedule();
            List<KitchenShift> kitchenShifts = kss.getKitchenShifts();

            System.out.println("\nTEST CREATE TASK");
            User cook = CatERing.getInstance().getUserManager().getCooks().get(0);
            CatERing.getInstance().getKitchenTaskManager().createTask(a1, kitchenShifts.get(0), cook, 60, "3 porzioni");

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
