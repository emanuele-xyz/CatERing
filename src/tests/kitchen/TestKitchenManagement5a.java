/*package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchen.KitchenTaskException;

public class TestKitchenManagement5a {
    public static void main(String[] args) {
        try {

            // ---------------------------------------------------------------------------------------------------------

            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST EDIT TASK ACTIVITY");
            System.out.println("-".repeat(80));

            CatERing.getInstance().getKitchenTaskManager().editTaskActivity(task, newActivity);

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}*/
