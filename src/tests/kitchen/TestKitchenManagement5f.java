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

public class TestKitchenManagement5f {
    public static void main(String[] args) {
        try {

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST REMOVE TASK");
            System.out.println("-".repeat(80));

            // Open summary sheet
            Event event = CatERing.getInstance().getEventManager().getEvent(4);
            Service service = event.getService(9);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(event, service);

            // Add activity
            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity activity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(4), "10 Etti", "3 Etti");

            // Add task to activity
            KitchenShiftSchedule kss = CatERing.getInstance().getShiftManager().getKitchenShiftSchedule();
            List<KitchenShift> kitchenShifts = kss.getKitchenShifts();
            User cook = User.loadUserById(4);
            Task task = CatERing.getInstance().getKitchenTaskManager().createTask(activity, kitchenShifts.get(0), cook, 10, "1 etto");

            // Print activity before task delete
            System.out.println("-".repeat(80));
            System.out.println("ACTIVITY BEFORE TASK DELETE");
            System.out.println("-".repeat(80));
            System.out.println(activity.debugString());

            // Remove task from activity
            CatERing.getInstance().getKitchenTaskManager().removeTask(task);

            // Print activity after task delete
            System.out.println("-".repeat(80));
            System.out.println("ACTIVITY AFTER TASK DELETE");
            System.out.println("-".repeat(80));
            System.out.println(activity.debugString());

            // Remove activity
            CatERing.getInstance().getKitchenTaskManager().removeActivity(activity);

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
