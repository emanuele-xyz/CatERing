package businesslogic.kitchen;

import businesslogic.menu.Menu;
import businesslogic.recipe.KitchenProcedure;

import java.util.ArrayList;
import java.util.List;

public class SummarySheet {

    private final List<Activity> activities;

    public SummarySheet() {
        this.activities = new ArrayList<>();
    }

    public SummarySheet(Menu menu) {
        activities = new ArrayList<>();

        List<KitchenProcedure> kitchenProcedures = menu.getRequiredKitchenProcedures();
        for (KitchenProcedure kp : kitchenProcedures) {
            activities.add(new Activity(kp));
        }
    }
}
