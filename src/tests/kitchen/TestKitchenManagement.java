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

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST FAKE LOGIN");
            System.out.println("-".repeat(80));

            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST CREATE SUMMARY SHEET");
            System.out.println("-".repeat(80));

            Event e = CatERing.getInstance().getEventManager().getEvent(4);
            Service s = e.getService(9);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().generateSummarySheet(e, s);
            System.out.println(e.debugString());

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST ADD ACTIVITY");
            System.out.println("-".repeat(80));

            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity activity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(0), "12 porzioni", "nessuna porzione");
            System.out.println(sh.debugString());

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST MOVE ACTIVITY");
            System.out.println("-".repeat(80));

            CatERing.getInstance().getKitchenTaskManager().moveActivity(activity, 0);
            System.out.println(sh.debugString());

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST GET KITCHEN SHIFTS SCHEDULE");
            System.out.println("-".repeat(80));

            KitchenShiftSchedule kss = CatERing.getInstance().getShiftManager().getKitchenShiftSchedule();
            List<KitchenShift> kitchenShifts = kss.getKitchenShifts();
            System.out.println(kss.debugString());

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST CREATE TASK");
            System.out.println("-".repeat(80));
            User cook = User.loadUserById(4);
            CatERing.getInstance().getKitchenTaskManager().createTask(activity, kitchenShifts.get(0), cook, 60, "3 porzioni");
            System.out.println(sh.debugString());

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
