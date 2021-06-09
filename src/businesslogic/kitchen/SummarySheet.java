package businesslogic.kitchen;

import businesslogic.menu.Menu;
import businesslogic.recipe.KitchenProcedure;
import persistence.PersistenceManager;

import java.util.*;

public class SummarySheet {

    private int id;
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

    public boolean hasActivity(Activity activity) {
        return activities.contains(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void moveActivity(Activity activity, int position) {
        activities.remove(activity);
        activities.add(position, activity);
    }

    public Activity getActivityByTask(Task task) {
        return activities.stream().filter(activity -> activity.hasTask(task)).findAny().orElse(null);
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, SummarySheet> cache = new HashMap<>();

    // TODO: to be implemented
    public static SummarySheet loadSummarySheetByServiceID(int serviceID) {
        SummarySheet sh = new SummarySheet();
        String query = String.format("SELECT * FROM summary_sheets WHERE service_id = %d", serviceID);
        PersistenceManager.executeQuery(query, rs -> {
            final int summarySheetID = rs.getInt("id");
            if (summarySheetID <= 0) return;

            sh.id = summarySheetID;
            if (!cache.containsKey(sh.id)) {
                sh.activities.addAll(Activity.loadActivitiesBySummarySheetID(sh.id));
                cache.put(sh.id, sh);
            }
        });

        return cache.get(sh.id);
    }
}
