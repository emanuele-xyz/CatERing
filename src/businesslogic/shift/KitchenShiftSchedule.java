package businesslogic.shift;

import java.util.List;

public class KitchenShiftSchedule {

    private final List<KitchenShift> kitchenShifts;

    public KitchenShiftSchedule() {
        kitchenShifts = KitchenShift.loadAllKitchenShift();
    }

    public List<KitchenShift> getKitchenShifts() {
        return kitchenShifts;
    }

    public String debugString() {
        return debugString(0, 2);
    }

    public String debugString(int indentation, int spacesPerIndent) {
        final String outerIndent = " ".repeat(indentation * spacesPerIndent);
        final String indent = " ".repeat((indentation + 1) * spacesPerIndent);
        StringBuilder sb = new StringBuilder();

        sb.append(outerIndent).append("Tabellone dei Turni Preparatori {\n");
        kitchenShifts.forEach(ks -> sb.append(ks.debugString(indentation + 1, spacesPerIndent)).append(",\n"));
        sb.append(outerIndent).append("}");

        return sb.toString();
    }
}
