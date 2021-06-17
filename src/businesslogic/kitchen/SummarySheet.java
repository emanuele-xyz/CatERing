package businesslogic.kitchen;

import businesslogic.event.Service;
import businesslogic.menu.Menu;
import businesslogic.recipe.KitchenProcedure;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SummarySheet {

    private int id;
    private final List<Activity> activities;

    public SummarySheet() {
        activities = new ArrayList<>();
    }

    public SummarySheet(Menu menu) {
        this();

        List<KitchenProcedure> kitchenProcedures = menu.getRequiredKitchenProcedures();
        for (KitchenProcedure kp : kitchenProcedures) {
            activities.add(new Activity(kp));
        }
    }

    public int getID() {
        return id;
    }

    public int getActivitiesSize() {
        return activities.size();
    }

    public List<Activity> addActivity(KitchenProcedure kitchenProcedure, String toPrepare, String prepared) {
        List<Activity> res = new ArrayList<>();

        List<KitchenProcedure> kitchenProcedures = kitchenProcedure.getRequiredKitchenProcedures();
        kitchenProcedures.forEach(kp -> {
            Activity activity;

            if (kp.equals(kitchenProcedure)) {
                activity = new Activity(kp, toPrepare, prepared);
            } else {
                activity = new Activity(kp);
            }

            activities.add(activity);
            res.add(activity);
        });

        return res;
    }

    public boolean hasActivity(Activity activity) {
        return activities.contains(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void moveActivity(Activity activity, int position) {
        activities.remove(activity);
        activities.add(position, activity);
    }

    public Activity getActivityByTask(Task task) {
        return activities.stream().filter(activity -> activity.hasTask(task)).findAny().orElse(null);
    }

    public int findActivityPosition(Activity activity) {
        return activities.indexOf(activity);
    }

    public String debugString() {
        return debugString(0, 2);
    }

    public String debugString(int indentation, int spacesPerIndent) {
        final String outerIndent = " ".repeat(indentation * spacesPerIndent);
        final String indent = " ".repeat((indentation + 1) * spacesPerIndent);
        final StringBuilder sb = new StringBuilder();

        sb.append(outerIndent).append("Foglio Riepilogativo {\n");
        sb.append(indent).append("id: ").append(id).append('\n');
        sb.append(indent).append("attivita: ").append("{\n");
        activities.forEach(activity -> sb.append(activity.debugString(indentation + 2, spacesPerIndent)).append(",\n"));
        sb.append(indent).append("}\n");
        sb.append(outerIndent).append("}");

        return sb.toString();
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, SummarySheet> cache = new HashMap<>();

    public static SummarySheet loadSummarySheetByServiceID(int serviceID) {
        SummarySheet sh = new SummarySheet();
        String query = String.format("SELECT * FROM summary_sheets WHERE service_id = %d", serviceID);
        PersistenceManager.executeQuery(query, rs -> {
            final int summarySheetID = rs.getInt("id");
            if (summarySheetID <= 0) return;

            sh.id = summarySheetID;
            if (!cache.containsKey(sh.id)) {
                sh.activities.addAll(Activity.loadActivitiesBySummarySheetID(sh.id));
                cache.put(sh.id, sh);
            }
        });

        return cache.get(sh.id);
    }

    // TODO: to be implemented
    public static void saveNewSummarySheet(Service service, SummarySheet summarySheet) {
        String insert = String.format("INSERT INTO catering.summary_sheets (service_id) VALUES (%d)", service.getId());
        summarySheet.id = PersistenceManager.executeUpdate(insert);

        Activity.saveAllNewActivities(summarySheet.id, summarySheet.activities);

        cache.put(summarySheet.id, summarySheet);
    }

    // TODO: to be implemented
    public static void saveActivitiesOrder(SummarySheet summarySheet) {
        String update = "UPDATE catering.activities SET position = ? WHERE id = ?";
        PersistenceManager.executeBatchUpdate(update, summarySheet.activities.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                Activity activity = summarySheet.activities.get(batchCount);
                ps.setInt(1, batchCount);
                ps.setInt(2, activity.getID());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) {
                // no generated IDs to handle
            }
        });
    }
}
