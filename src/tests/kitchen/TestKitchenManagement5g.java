package tests.kitchen;

import businesslogic.CatERing;
import businesslogic.shift.KitchenShift;
import businesslogic.shift.KitchenShiftSchedule;
import businesslogic.shift.ShiftException;

import java.util.List;

public class TestKitchenManagement5g {
    public static void main(String[] args) {
        try {

            // Login
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");

            System.out.println("-".repeat(80));
            System.out.println("TEST MARK KITCHEN SHIFT AS FULL");
            System.out.println("-".repeat(80));

            // Get kitchen shift
            KitchenShiftSchedule kss = CatERing.getInstance().getShiftManager().getKitchenShiftSchedule();
            List<KitchenShift> kitchenShifts = kss.getKitchenShifts();
            KitchenShift kitchenShift = kitchenShifts.get(2);

            // Print kitchen shift
            System.out.println("-".repeat(80));
            System.out.println("KITCHEN SHIFT BEFORE MARKING IT AS FULL");
            System.out.println("-".repeat(80));
            System.out.println(kitchenShift.debugString());

            // Mark kitchen shift as full
            CatERing.getInstance().getShiftManager().markKitchenShiftAsFull(kitchenShift);

            // Print kitchen shift
            System.out.println("-".repeat(80));
            System.out.println("KITCHEN SHIFT AFTER MARKING IT AS FULL");
            System.out.println("-".repeat(80));
            System.out.println(kitchenShift.debugString());

        } catch (ShiftException e) {
            System.out.println("Errore di logica nello use case");
            e.printStackTrace();
        }
    }
}
