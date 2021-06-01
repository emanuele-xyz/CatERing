package businesslogic.kitchen;

import businesslogic.recipe.KitchenProcedure;
import businesslogic.shift.KitchenShift;
import businesslogic.user.User;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private final KitchenProcedure kitchenProcedure;
    private final List<Task> tasks;
    private String dosesToPrepare;
    private String alreadyPreparedDoses;
    private String preparedDoses;

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

    public Task createTask(KitchenShift shift, User cook, int estimatedTime, String estimatedDoses) {
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
}
