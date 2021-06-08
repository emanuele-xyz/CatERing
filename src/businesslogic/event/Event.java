package businesslogic.event;

import businesslogic.user.User;
import persistence.PersistenceManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Event {

    private int id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private int expectedParticipants;
    private int organizer;
    private final List<Service> services;

    public Event() {
        this.services = new ArrayList<>();
    }

    public boolean consistsOf(Service service) {
        return services.contains(service);
    }

    // TODO: to be implemented
    public boolean isAppointedTo(User user) {
        return false;
    }

    // TODO: to be implemented
    public Service getService(int id) {
        return null;
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final HashMap<Integer, Event> cache = new HashMap<>();

    public static Event loadEventByID(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }

        Event e = new Event();
        String query = String.format("SELECT * FROM events WHERE id=%d", id);
        PersistenceManager.executeQuery(query, rs -> {
            e.id = rs.getInt("id");
            e.name = rs.getString("name");
            e.dateStart = rs.getDate("date_start");
            e.dateEnd = rs.getDate("date_end");
            e.expectedParticipants = rs.getInt("expected_participants");
            e.organizer = rs.getInt("organizer_id");
        });

        // TODO: to complete
        query = String.format("SELECT * FROM services WHERE event_id=%d", e.id);
        PersistenceManager.executeQuery(query, rs -> {
            e.services.add();
        });

        return e;
    }
}
