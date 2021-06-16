package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchen.KitchenTaskException;

public class TestKitchenManagement5d {
    public static void main(String[] args) {
        try {

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST FAKE LOGIN");
            System.out.println("-".repeat(80));

            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            // ---------------------------------------------------------------------------------------------------------

            System.out.println("-".repeat(80));
            System.out.println("TEST REMOVE TASK COOK");
            System.out.println("-".repeat(80));

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
