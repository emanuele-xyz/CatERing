package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.kitchen.Activity;
import businesslogic.kitchen.KitchenTaskException;
import businesslogic.kitchen.SummarySheet;
import businesslogic.kitchen.Task;
import businesslogic.recipe.Recipe;
import businesslogic.shift.KitchenShift;
import businesslogic.shift.KitchenShiftSchedule;
import businesslogic.user.User;
import javafx.collections.ObservableList;

import java.util.List;

public class TestKitchenManagement5a {
    public static void main(String[] args) {
        try {

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST EDIT TASK ACTIVITY");
            System.out.println("-".repeat(80));

            // Open summary sheet
            Event event = CatERing.getInstance().getEventManager().getEvent(1);
            Service service = event.getService(2);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(event, service);

            // Add activities
            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity activity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(4), "10 Etti", "3 Etti");
            Activity newActivity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(5), "8.7 Kg", "0 Kg");

            // Add task to activity
            KitchenShiftSchedule kss = CatERing.getInstance().getShiftManager().getKitchenShiftSchedule();
            List<KitchenShift> kitchenShifts = kss.getKitchenShifts();
            User cook = User.loadUserById(4);
            Task task = CatERing.getInstance().getKitchenTaskManager().createTask(activity, kitchenShifts.get(0), cook, 10, "1 piatto");

            // Print summary sheet before task edit
            System.out.println("-".repeat(80));
            System.out.println("SUMMARY SHEET BEFORE TASK EDIT");
            System.out.println("-".repeat(80));
            System.out.println(sh.debugString());

            // Edit task (replace activity task with a new task)
            CatERing.getInstance().getKitchenTaskManager().editTaskActivity(task, newActivity);

            // Print activity after task edit
            System.out.println("-".repeat(80));
            System.out.println("SUMMARY SHEET AFTER TASK EDIT");
            System.out.println("-".repeat(80));
            System.out.println(sh.debugString());

            // Remove activities
            CatERing.getInstance().getKitchenTaskManager().removeActivity(activity);
            CatERing.getInstance().getKitchenTaskManager().removeActivity(newActivity);

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
