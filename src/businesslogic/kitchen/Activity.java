package businesslogic.kitchen;

import businesslogic.recipe.KitchenProcedure;
import businesslogic.shift.KitchenShift;
import businesslogic.user.User;

import java.util.List;

public class Activity {

    private String dosesToPrepare;
    private String alreadyPreparedDoses;
    private String preparedDoses;
    private List<Task> tasks;

    // TODO: to be implemented
    public Activity(KitchenProcedure kitchenProcedure) {
    }

    // TODO: to be implemented
    public Activity(KitchenProcedure kitchenProcedure, String toPrepare, String prepared) {

    }

    // TODO: to be implemented
    public boolean hasAlreadyBeenCarriedOut() {
        return false;
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
}
