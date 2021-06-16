package businesslogic.kitchen;

import businesslogic.shift.KitchenShift;
import businesslogic.user.User;
import persistence.PersistenceManager;

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

    public KitchenShift getShift() {
        return shift;
    }

    public void setShift(KitchenShift shift) {
        this.shift = shift;
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

    // TODO: to be implemented
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
}
