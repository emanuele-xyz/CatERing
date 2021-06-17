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

public class TestKitchenManagement2a {
    public static void main(String[] args) {
        try {

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST REMOVE ACTIVITY");
            System.out.println("-".repeat(80));

            // We open a summary sheet
            Event event = CatERing.getInstance().getEventManager().getEvent(1);
            Service service = event.getService(2);
            SummarySheet sh = CatERing.getInstance().getKitchenTaskManager().openSummarySheet(event, service);

            // We add an activity to the summary sheet
            ObservableList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Activity activity = CatERing.getInstance().getKitchenTaskManager().addActivity(recipes.get(4), "10 Etti", "3 Etti");

            // Print summary sheet
            System.out.println("-".repeat(80));
            System.out.println("SUMMARY SHEET AFTER ADDING THE ACTIVITY");
            System.out.println("-".repeat(80));
            System.out.println(sh.debugString());

            // We remove the added activity
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
