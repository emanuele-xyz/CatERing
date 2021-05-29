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

    public List<Activity> addActivity(KitchenProcedure kitchenProcedure, String toPrepare, String prepared) {
        List<Activity> res = new ArrayList<>();

        {
            Activity activity = new Activity(kitchenProcedure, toPrepare, prepared);
            activities.add(activity);
            res.add(activity);
        }

        {
            List<KitchenProcedure> kitchenProcedures = kitchenProcedure.getRequiredKitchenProcedures();
            kitchenProcedures.forEach(kp -> {
                Activity activity = new Activity(kp);
                activities.add(activity);
                res.add(activity);
            });
        }

        return res;
    }
}
