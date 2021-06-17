package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.Event;
import businesslogic.event.Service;
import businesslogic.kitchen.Activity;
import businesslogic.kitchen.KitchenTaskException;
import businesslogic.kitchen.SummarySheet;
import businesslogic.recipe.Recipe;
import javafx.collections.ObservableList;

public class TestKitchenManagement2b {
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
            System.out.println("TEST EDIT ACTIVITY");
            System.out.println("-".repeat(80));

            // Open summary sheet
            Event event = CatERing.getInstance().getEventManager().getEvent(1);
            Service service = event.getService(2);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(event, service);

            // Add activity
            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity activity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(4), "10 Etti", "3 Etti");

            // Print summary sheet before editing activity
            System.out.println("-".repeat(80));
            System.out.println("SUMMARY SHEET BEFORE EDITING THE ACTIVITY");
            System.out.println("-".repeat(80));
            System.out.println(sh.debugString());

            // Edit activity
            CatERing.getInstance().getKitchenTaskManager().editActivity(activity, "20 piatti", "0 piatti", "5 piatti");

            // Print summary sheet after editing activity
            System.out.println("-".repeat(80));
            System.out.println("SUMMARY SHEET AFTER EDITING THE ACTIVITY");
            System.out.println("-".repeat(80));
            System.out.println(sh.debugString());

            // Remove activity
            CatERing.getInstance().getKitchenTaskManager().removeActivity(activity);

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
