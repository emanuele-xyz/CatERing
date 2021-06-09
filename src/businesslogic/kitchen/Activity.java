package businesslogic.kitchen;

import businesslogic.recipe.KitchenProcedure;
import businesslogic.recipe.Recipe;
import businesslogic.shift.KitchenShift;
import businesslogic.user.User;
import persistence.PersistenceManager;

import java.util.*;

public class Activity {

    private int id;
    private KitchenProcedure kitchenProcedure;
    private List<Task> tasks;
    private String dosesToPrepare;
    private String alreadyPreparedDoses;
    private String preparedDoses;

    private Activity() {}

    public Activity(KitchenProcedure kitchenProcedure) {
        this(kitchenProcedure, null, null);
    }

    public Activity(KitchenProcedure kitchenProcedure, String toPrepare, String prepared) {
        this.kitchenProcedure = kitchenProcedure;
        this.tasks = new ArrayList<>();
        this.dosesToPrepare = toPrepare != null ? toPrepare : "";
        this.alreadyPreparedDoses = prepared != null ? prepared : "";
        this.preparedDoses = "";
    }

    public boolean hasAlreadyBeenCarriedOut() {
        return tasks.stream().anyMatch(Task::isInThePast);
    }

    public void edit(String dosesToPrepare, String alreadyPreparedDoses, String preparedDoses) {
        if (dosesToPrepare != null) {
            this.dosesToPrepare = dosesToPrepare;
        }

        if (alreadyPreparedDoses != null) {
            this.alreadyPreparedDoses = alreadyPreparedDoses;
        }

        if (preparedDoses != null) {
            this.preparedDoses = preparedDoses;
        }
    }

    public Task createTask(KitchenShift shift, User cook, Integer estimatedTime, String estimatedDoses) {
        Task task = new Task(shift, cook, estimatedTime, estimatedDoses);
        tasks.add(task);
        return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public boolean hasTask(Task task) {
        return tasks.contains(task);
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, Activity> cache = new HashMap<>();

    // TODO: to be implemented
    public static List<Activity> loadActivitiesBySummarySheetID(int summarySheetID) {
        List<Activity> activities = new ArrayList<>();

        String query = String.format("SELECT * FROM activities WHERE summary_sheet_id = %d", summarySheetID);
        PersistenceManager.executeQuery(query, rs -> {
            final int activityID = rs.getInt("id");
            if (activityID <= 0) return;

            if (cache.containsKey(activityID)) {
                activities.add(cache.get(activityID));
            } else {
                Activity activity = new Activity();
                activity.id = activityID;
                activity.kitchenProcedure = Recipe.loadRecipeById(rs.getInt("kitchen_procedure_id")); // TODO: to be reworked
                activity.tasks = Task.loadTasksByActivityID(activity.id);
                activity.dosesToPrepare = rs.getString("doses_to_prepare");
                activity.alreadyPreparedDoses = rs.getString("already_prepared_doses");
                activity.preparedDoses = rs.getString("prepared_doses");

                activities.add(activity);
                cache.put(activity.id, activity);
            }
        });

        return activities;
    }
}
