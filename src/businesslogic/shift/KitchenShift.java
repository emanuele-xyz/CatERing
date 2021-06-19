package businesslogic.shift;

import businesslogic.user.User;
import persistence.PersistenceManager;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

public class KitchenShift {

    private int id;
    private boolean isFull;
    private Date date;
    private Time start;
    private Time end;
    private final Set<User> availableCooks;

    private KitchenShift() {
        availableCooks = new HashSet<>();
    }

    public int getID() {
        return id;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isInThePast() {
        // If date < current date -> yes
        // else if date == current date -> check time
        // else -> no

        // check time
        // current time > end time -> yes
        // else -> no

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        java.util.Date currentDate = calendar.getTime();

        if (date.before(currentDate)) {
            return true;
        } else if (date.equals(currentDate)) {
            // Check time
            Calendar tmp = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
            java.util.Date currentTime = calendar.getTime();

            return currentTime.after(end);

        } else {
            return false;
        }
    }

    public void markAsFull() {
        this.isFull = true;
    }

    public boolean hasCookAvailable(User cook) {
        return availableCooks.contains(cook);
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
            kitchenShift.date = rs.getDate("date");
            kitchenShift.start = rs.getTime("time_start");
            kitchenShift.end = rs.getTime("time_end");
            kitchenShift.availableCooks.addAll(KitchenShift.loadAvailableCooks(kitchenShift.id));

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
                kitchenShift.date = rs.getDate("date");
                kitchenShift.start = rs.getTime("time_start");
                kitchenShift.end = rs.getTime("time_end");
                kitchenShift.availableCooks.addAll(KitchenShift.loadAvailableCooks(kitchenShift.id));

                cache.put(kitchenShift.id, kitchenShift);
                kitchenShifts.add(kitchenShift);
            }
        });

        return kitchenShifts;
    }

    private static List<User> loadAvailableCooks(int kitchenShiftID) {
        List<Integer> userIDs = new ArrayList<>();

        // Populate userIDs
        String query = String.format("SELECT cook_id FROM catering.kitchen_shift_availabilities WHERE kitchen_shift_id = %d", kitchenShiftID);
        PersistenceManager.executeQuery(query, rs -> {
            final int cookID = rs.getInt("cook_id");
            if (cookID <= 0) return;

            userIDs.add(cookID);
        });

        return userIDs.stream().map(User::loadUserById).collect(Collectors.toList());
    }

    public static void updateMarkAsFull(KitchenShift kitchenShift) {
        String update = String.format("UPDATE catering.kitchen_shifts SET is_full = true WHERE id = %d", kitchenShift.id);
        PersistenceManager.executeUpdate(update);
    }
}
