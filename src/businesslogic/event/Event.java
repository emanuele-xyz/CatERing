package businesslogic.event;

import businesslogic.user.User;
import persistence.PersistenceManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event {

    private int id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private int expectedParticipants;
    private User organizer;
    private final List<Service> services;

    public Event() {
        this.services = new ArrayList<>();
    }

    public boolean consistsOf(Service service) {
        return services.contains(service);
    }

    public boolean isAppointedTo(User user) {
        return this.organizer == user;
    }

    // TODO: to be implemented
    public Service getService(int id) {
        return services.stream().filter(service -> service.getId() == id).findAny().orElse(null);
    }

    // STATIC METHODS FOR PERSISTENCE

    private static final Map<Integer, Event> cache = new HashMap<>();

    // TODO: to be implemented
    public static Event loadEventByID(int eventID) {
        if (cache.containsKey(eventID)) return cache.get(eventID);

        String query = String.format("SELECT * FROM events WHERE id = %d", eventID);
        PersistenceManager.executeQuery(query, rs -> {
            int id = rs.getInt("id");
            if (id <= 0) return;

            Event event = new Event();
            event.id = id;
            event.name = rs.getString("name");
            event.dateStart = rs.getDate("date_start");
            event.dateEnd = rs.getDate("date_end");
            event.expectedParticipants = rs.getInt("expected_participants");
            event.organizer = User.loadUserById(rs.getInt("organizer_id"));
            event.services.addAll(Service.loadServicesByEventID(event.id));

            cache.put(event.id, event);
        });

        return cache.get(eventID);
    }
}
