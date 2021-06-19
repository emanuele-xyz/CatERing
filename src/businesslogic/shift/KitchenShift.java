package businesslogic.shift;

import businesslogic.user.User;
import persistence.PersistenceManager;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    // TODO: this should be implemented in Shift Management use case
    public boolean isInThePast() {

        // If date < current date -> yes
        // else if date == current date -> check time
        // else -> no

        // check time
        // current time > end time -> yes
        // else -> no

//        Instant a = date.toInstant().truncatedTo(ChronoUnit.DAYS);
//        Date now = new Date(System.currentTimeMillis());
//        Instant b = now.toInstant().truncatedTo(ChronoUnit.DAYS);
//        a.equals(b);

        /*
        if(jdatechooser1.getDate().getTime() > System.currentTimeMillis()){
          System.out.println("ok");
        }

        Time dbTime = // the time you obtained from the db
        long dbLong = dbTime.getTime();
        long now = System.currentTimeMillis();

        if (dbLong < now) // data in the db is in the past
        if (dbLong > now) // data in the db is in the future
        if (dbLong == now) // data in the db is exactly now
         */

        return false;
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
            // TODO: add date, start time and finish time
            kitchenShift.date = rs.getDate("date");
            kitchenShift.start = rs.getTime("time_start");
            kitchenShift.end = rs.getTime("time_end");
            // TODO: add cooks to availableCooks
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
                // TODO: add date, start time and finish time
                kitchenShift.date = rs.getDate("date");
                kitchenShift.start = rs.getTime("time_start");
                kitchenShift.end = rs.getTime("time_end");
                // TODO: add cooks to availableCooks
                kitchenShift.availableCooks.addAll(KitchenShift.loadAvailableCooks(kitchenShift.id));

                cache.put(kitchenShift.id, kitchenShift);
                kitchenShifts.add(kitchenShift);
            }
        });

        return kitchenShifts;
    }

    // TODO: to be implemented
    private static List<User> loadAvailableCooks(int kitchenShiftID) {
        List<Integer> userIDs = new ArrayList<>();
        return User.loadUsersByIDs(userIDs);
    }

    public static void updateMarkAsFull(KitchenShift kitchenShift) {
        String update = String.format("UPDATE catering.kitchen_shifts SET is_full = true WHERE id = %d", kitchenShift.id);
        PersistenceManager.executeUpdate(update);
    }
}
