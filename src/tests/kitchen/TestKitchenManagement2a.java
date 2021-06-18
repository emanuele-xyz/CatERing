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

public class TestKitchenManagement2a {
    public static void main(String[] args) {
        try {

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST REMOVE ACTIVITY");
            System.out.println("-".repeat(80));

            // Open summary sheet
            Event event = CatERing.getInstance().getEventManager().getEvent(4);
            Service service = event.getService(9);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(event, service);

            // Add activity
            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity activity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(4), "10 Etti", "3 Etti");

            // Add two tasks to activity
            KitchenShiftSchedule kss = CatERing.getInstance().getShiftManager().getKitchenShiftSchedule();
            List<KitchenShift> kitchenShifts = kss.getKitchenShifts();
            User cook = User.loadUserById(4);
            CatERing.getInstance().getKitchenTaskManager().createTask(activity, kitchenShifts.get(0), cook, 10, "1 etto");
            CatERing.getInstance().getKitchenTaskManager().createTask(activity, kitchenShifts.get(1), cook, 30, "4 etti");

            // Print summary sheet
            System.out.println("-".repeat(80));
            System.out.println("SUMMARY SHEET AFTER ADDING THE ACTIVITY");
            System.out.println("-".repeat(80));
            System.out.println(sh.debugString());

            // Remove Activity
            CatERing.getInstance().getKitchenTaskManager().removeActivity(activity);

            // Print summary sheet again
            System.out.println("-".repeat(80));
            System.out.println("SUMMARY SHEET AFTER REMOVING THE ACTIVITY");
            System.out.println("-".repeat(80));
            System.out.println(sh.debugString());

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
