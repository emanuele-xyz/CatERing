package businesslogic.event;

import businesslogic.kitchen.SummarySheet;
import businesslogic.menu.Menu;
import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    private int id;
    private Menu menu;
    private SummarySheet summarySheet;

    private Service() {}

    public Service(Menu menu) {
        this.menu = menu;
        this.summarySheet = null;
    }

    public boolean hasMenu() {
        return menu != null;
    }

    public SummarySheet getSummarySheet() {
        return summarySheet;
    }

    public SummarySheet generateSummarySheet() {
        return summarySheet = new SummarySheet(menu);
    }

    public int getId() {
        return id;
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, Service> cache = new HashMap<>();

    // TODO: to be implemented
    public static List<Service> loadServicesByEventID(int eventID) {
        List<Service> services = new ArrayList<>();

        String query = String.format("SELECT * FROM services WHERE event_id = %d", eventID);
        PersistenceManager.executeQuery(query, rs -> {
            final int serviceID = rs.getInt("id");
            if (serviceID <= 0) return;

            if (cache.containsKey(serviceID)) {
                services.add(cache.get(serviceID));
            } else {
                Service service = new Service();
                service.id = serviceID;
                service.menu = Menu.loadMenuByID(rs.getInt("approved_menu_id"));
                service.summarySheet = SummarySheet.loadSummarySheetByServiceID(service.id);

                cache.put(service.id, service);
                services.add(service);
            }
        });

        return services;
    }
}
