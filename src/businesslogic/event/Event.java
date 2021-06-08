package businesslogic.event;

import businesslogic.user.User;

import java.util.ArrayList;
import java.util.List;

public class Event {

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
}
