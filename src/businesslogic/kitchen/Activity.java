package businesslogic.kitchen;

import businesslogic.recipe.KitchenProcedure;
import businesslogic.shift.KitchenShift;
import businesslogic.user.User;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public int getID() {
        return id;
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

    public String debugString() {
        return debugString(0, 2);
    }

    public String debugString(int indentation, int spacesPerIndent) {
        final String outerIndent = " ".repeat(indentation * spacesPerIndent);
        final String indent = " ".repeat((indentation + 1) * spacesPerIndent);
        final StringBuilder sb = new StringBuilder();

        sb.append(outerIndent).append("Attivita {\n");
        sb.append(indent).append("id: ").append(id).append('\n');
        sb.append(indent).append("procedura di cucina: ").append(kitchenProcedure).append('\n');
        sb.append(indent).append("dosi da preparare: '").append(dosesToPrepare).append('\'').append('\n');
        sb.append(indent).append("dosi gia' preparate: '").append(alreadyPreparedDoses).append('\'').append('\n');
        sb.append(indent).append("dosi proparate: '").append(preparedDoses).append('\'').append('\n');
        sb.append(indent).append("compiti:").append("{\n");
        tasks.forEach(task -> sb.append(task.debugString(indentation + 2, spacesPerIndent)).append(",\n"));
        sb.append(indent).append("}\n");
        sb.append(outerIndent).append("}");

        return sb.toString();
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, Activity> cache = new HashMap<>();

    public static List<Activity> loadActivitiesBySummarySheetID(int summarySheetID) {
        List<Activity> activities = new ArrayList<>();

        String query = String.format("SELECT * FROM catering.activities WHERE summary_sheet_id = %d ORDER BY position", summarySheetID);
        PersistenceManager.executeQuery(query, rs -> {
            final int activityID = rs.getInt("id");
            if (activityID <= 0) return;

            if (cache.containsKey(activityID)) {
                activities.add(cache.get(activityID));
            } else {
                Activity activity = new Activity();
                activity.id = activityID;
                activity.kitchenProcedure = KitchenProcedure.loadKitchenProcedureByID(rs.getInt("kitchen_procedure_id"));
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

    public static void saveAllNewActivities(int summarySheetID, List<Activity> activities) {
        String insert = "INSERT INTO catering.activities (summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES (?, ?, ?, ?, ?, ?)";
        PersistenceManager.executeBatchUpdate(insert, activities.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                Activity activity = activities.get(batchCount);
                ps.setInt(1, summarySheetID);
                ps.setInt(2, activity.kitchenProcedure.getId());
                ps.setString(3, activity.dosesToPrepare);
                ps.setString(4, activity.alreadyPreparedDoses);
                ps.setString(5, activity.preparedDoses);
                ps.setInt(6, batchCount);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                Activity activity = activities.get(count);
                activity.id = rs.getInt(1);

                cache.put(activity.id, activity);
            }
        });

        activities.forEach(activity -> Task.saveAllNewTasks(activity.id, activity.tasks));
    }

    public static void saveNewActivity(int summarySheetID, Activity activity, int position) {
        String insert = String.format("INSERT INTO catering.activities " +
                "(summary_sheet_id, kitchen_procedure_id, doses_to_prepare, already_prepared_doses, prepared_doses, position) VALUES " +
                "(%d, %d, '%s', '%s', '%s', %d)",
                summarySheetID, activity.kitchenProcedure.getId(), activity.dosesToPrepare,
                activity.alreadyPreparedDoses, activity.preparedDoses, position);

        PersistenceManager.executeUpdate(insert);
        activity.id = PersistenceManager.getLastId();

        cache.put(activity.id, activity);
    }

    public static void deleteActivity(Activity activity) {
        String delete = String.format("DELETE FROM catering.activities WHERE id = %d", activity.id);
        PersistenceManager.executeUpdate(delete);

        Task.deleteAllTasksInActivity(activity.id, activity.tasks);

        cache.remove(activity.id);
    }

    public static void saveActivityAnnotations(Activity activity) {
        String update = String.format("UPDATE catering.activities SET " +
                "doses_to_prepare = '%s', already_prepared_doses = '%s', prepared_doses = '%s' " +
                "WHERE id = %d",
                activity.dosesToPrepare, activity.alreadyPreparedDoses, activity.preparedDoses, activity.id);

        PersistenceManager.executeUpdate(update);
    }
}
