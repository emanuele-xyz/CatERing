package businesslogic.shift;

import businesslogic.user.User;
import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitchenShift {

    private int id;
    private boolean isFull;

    private KitchenShift() {}

    public int getID() {
        return id;
    }

    public boolean isFull() {
        return isFull;
    }

    // TODO: this should be implemented in Shift Management use case
    public boolean isInThePast() {
        return false;
    }

    public void markAsFull() {
        this.isFull = true;
    }

    // TODO: this should be implemented in Shift Management use case
    public boolean hasCookAvailable(User cook) {
        return true;
    }

    public String debugString() {
        return debugString(0, 2);
    }

    public String debugString(int indentation, int spacesPerIndent) {
        final String outerIndent = " ".repeat(indentation * spacesPerIndent);
        final String indent = " ".repeat((indentation + 1) * spacesPerIndent);

        return outerIndent + "Turno di cucina {\n" +
                indent + "id: " + id + '\n' +
                indent + "al completo: " + isFull + '\n' +
                outerIndent + "}";
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, KitchenShift> cache = new HashMap<>();

    public static KitchenShift loadKitchenShiftByID(int kitchenShiftID) {
        if (cache.containsKey(kitchenShiftID)) return cache.get(kitchenShiftID);

        String query = String.format("SELECT * FROM kitchen_shift WHERE id = %d", kitchenShiftID);
        PersistenceManager.executeQuery(query, rs -> {
            final int id = rs.getInt("id");
            if (id <= 0) return;

            KitchenShift kitchenShift = new KitchenShift();
            kitchenShift.id = id;
            kitchenShift.isFull = rs.getBoolean("is_full");

            cache.put(kitchenShift.id, kitchenShift);
        });

        return cache.get(kitchenShiftID);
    }

    public static List<KitchenShift> loadAllKitchenShift() {
        List<KitchenShift> kitchenShifts = new ArrayList<>();

        String query = "SELECT * FROM kitchen_shifts";
        PersistenceManager.executeQuery(query, rs -> {
            final int id = rs.getInt("id");
            if (cache.containsKey(id)) {
                kitchenShifts.add(cache.get(id));
            } else {
                KitchenShift kitchenShift = new KitchenShift();
                kitchenShift.id = id;
                kitchenShift.isFull = rs.getBoolean("is_full");

                cache.put(kitchenShift.id, kitchenShift);
                kitchenShifts.add(kitchenShift);
            }
        });

        return kitchenShifts;
    }

    // TODO: to be implemented
    public static void updateMarkAsFull(KitchenShift kitchenShift) {
        String update = String.format("UPDATE catering.kitchen_shifts SET is_full = true WHERE id = %d", kitchenShift.id);
        PersistenceManager.executeUpdate(update);
    }
}
