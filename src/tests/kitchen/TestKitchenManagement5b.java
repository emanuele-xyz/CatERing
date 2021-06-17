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

public class TestKitchenManagement5b {
    public static void main(String[] args) {
        try {

            // ---------------------------------------------------------------------------------------------------------

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST EDIT TASK SHIFT");
            System.out.println("-".repeat(80));

            // Open summary sheet
            Event event = CatERing.getInstance().getEventManager().getEvent(1);
            Service service = event.getService(2);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(event, service);

            // Add Activity
            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity activity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(4), "10 Etti", "3 Etti");

            // Add Task
            KitchenShiftSchedule kss = CatERing.getInstance().getShiftManager().getKitchenShiftSchedule();
            List<KitchenShift> kitchenShifts = kss.getKitchenShifts();
            User cook = User.loadUserById(4);
            Task task = CatERing.getInstance().getKitchenTaskManager().createTask(activity, kitchenShifts.get(0), cook, 30, "2 etti");

            // Print task
            System.out.println("-".repeat(80));
            System.out.println("TASK BEFORE SHIFT EDIT");
            System.out.println("-".repeat(80));
            System.out.println(task.debugString());

            // Edit task shift
            CatERing.getInstance().getKitchenTaskManager().editTaskShift(task, kitchenShifts.get(1));

            // Print task
            System.out.println("-".repeat(80));
            System.out.println("TASK AFTER SHIFT EDIT");
            System.out.println("-".repeat(80));
            System.out.println(task.debugString());

            // Remove Activity
            CatERing.getInstance().getKitchenTaskManager().removeActivity(activity);

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
