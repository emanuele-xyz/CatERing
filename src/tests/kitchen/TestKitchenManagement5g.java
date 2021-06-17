/*package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchen.KitchenTaskException;

public class TestKitchenManagement5g {
    public static void main(String[] args) {
        try {

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST MARK KITCHEN SHIFT AS FULL");
            System.out.println("-".repeat(80));

            CatERing.getInstance().getShiftManager().markKitchenShiftAsFull(kitchenShift);

        } catch (KitchenTaskException | UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}*/
