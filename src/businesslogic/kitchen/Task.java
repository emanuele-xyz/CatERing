package businesslogic.kitchen;

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

public class Task {

    private int id;
    private KitchenShift shift;
    private User cook;
    private boolean completed;
    private Integer estimatedTime;
    private String estimatedDoses;

    private Task() {}

    public Task(KitchenShift shift, User cook, Integer estimatedTime, String estimatedDoses) {
        this.shift = shift;
        this.completed = false;
        if (cook != null) this.cook = cook;
        this.estimatedTime = estimatedTime != null ? estimatedTime : 0;
        this.estimatedDoses = estimatedDoses != null ? estimatedDoses : "";
    }

    public boolean isInThePast() {
        return shift.isInThePast();
    }

    public User getCook() {
        return cook;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    public void removeCook() {
        cook = null;
    }

    public boolean hasCook() {
        return cook != null;
    }

    public KitchenShift getShift() {
        return shift;
    }

    public void setShift(KitchenShift shift) {
        this.shift = shift;
    }

    public void editEstimates(Integer estimatedTime, String estimatedDoses) {
        if (estimatedTime != null) {
            this.estimatedTime = estimatedTime;
        }

        if (estimatedDoses != null) {
            this.estimatedDoses = estimatedDoses;
        }
    }

    public String debugString() {
        return debugString(0, 2);
    }

    public String debugString(int indentation, int spacesPerIndent) {
        final String outerIndent = " ".repeat(indentation * spacesPerIndent);
        final String indent = " ".repeat((indentation + 1) * spacesPerIndent);

        return outerIndent + "Compito {\n" +
                indent + "id: " + id + '\n' +
                indent + "cuoco: " + cook + '\n' +
                indent + "completato: " + completed + '\n' +
                indent + "tempo stimato: " + estimatedTime + '\n' +
                indent + "dosi stimate: " + estimatedDoses + '\n' +
                shift.debugString(indentation + 1, spacesPerIndent) + '\n' +
                outerIndent + "}";
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, Task> cache = new HashMap<>();

    public static List<Task> loadTasksByActivityID(int activityID) {
        List<Task> tasks = new ArrayList<>();

        String query = String.format("SELECT * FROM tasks WHERE activity_id = %d", activityID);
        PersistenceManager.executeQuery(query, rs -> {
            final int taskID = rs.getInt("id");
            if (taskID <= 0) return;

            if (cache.containsKey(taskID)) {
                tasks.add(cache.get(taskID));
            } else {
                Task task = new Task();
                task.id = taskID;
                task.shift = KitchenShift.loadKitchenShiftByID(rs.getInt("kitchen_shift_id"));
                task.cook = User.loadUserById(rs.getInt("cook_id"));
                task.completed = rs.getBoolean("completed");
                task.estimatedTime = rs.getInt("estimated_time");
                task.estimatedDoses = rs.getString("estimated_doses");

                tasks.add(task);
                cache.put(task.id, task);
            }
        });

        return tasks;
    }

    public static void saveAllNewTasks(int activityID, List<Task> tasks) {
        String inset = "INSERT INTO catering.tasks (activity_id, kitchen_shift_id, cook_id, completed, estimated_time, estimated_doses) VALUES (?, ?, ?, ?, ?, ?)";
        PersistenceManager.executeBatchUpdate(inset, tasks.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                Task task = tasks.get(batchCount);
                ps.setInt(1, activityID);
                ps.setInt(2, task.shift.getID());
                ps.setInt(3, task.cook.getID());
                ps.setBoolean(4, task.completed);
                ps.setInt(5, task.estimatedTime);
                ps.setString(6, task.estimatedDoses);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                Task task = tasks.get(count);
                task.id = rs.getInt(1);

                cache.put(task.id, task);
            }
        });
    }

    public static void saveNewTask(int activityID, Task task) {
        String insert = String.format("INSERT INTO catering.tasks " +
                "(activity_id, kitchen_shift_id, cook_id, completed, estimated_time, estimated_doses) VALUES " +
                "(%d, %d, %d, %b, %d, '%s')",
                activityID, task.shift.getID(), task.cook.getID(), task.completed, task.estimatedTime, task.estimatedDoses);

        PersistenceManager.executeUpdate(insert);
        task.id = PersistenceManager.getLastId();

        cache.put(task.id, task);
    }

    public static void deleteTask(Task task) {
        String delete = String.format("DELETE FROM catering.tasks WHERE id = %d", task.id);
        PersistenceManager.executeUpdate(delete);

        cache.remove(task.id);
    }

    public static void updateActivity(int newActivityID, Task task) {
        String update = String.format("UPDATE catering.tasks SET activity_id = %d WHERE id = %d", newActivityID, task.id);
        PersistenceManager.executeUpdate(update);
    }

    public static void updateShift(Task task) {
        String update = String.format("UPDATE catering.tasks SET kitchen_shift_id = %d WHERE id = %d", task.shift.getID(), task.id);
        PersistenceManager.executeUpdate(update);
    }

    public static void updateCook(Task task) {
        String update = String.format("UPDATE catering.tasks SET cook_id = %d WHERE id = %d", task.cook.getID(), task.id);
        PersistenceManager.executeUpdate(update);
    }

    public static void updateRemoveCook(Task task) {
        String update = String.format("UPDATE catering.tasks SET cook_id = null WHERE id = %d", task.id);
        PersistenceManager.executeUpdate(update);
    }

    public static void updateEstimates(Task task) {
        String update = String.format("UPDATE catering.tasks SET estimated_time = %d, estimated_doses = '%s' WHERE id = %d",
                task.estimatedTime, task.estimatedDoses, task.id);

        PersistenceManager.executeUpdate(update);
    }
}
