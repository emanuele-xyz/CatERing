package businesslogic.event;

import javafx.collections.ObservableList;

public class EventManager {

    public ObservableList<EventInfo> getEventInfo() {
        return EventInfo.loadAllEventInfo();
    }

    // TODO: to be implemented
    public Event getEvent(int id) {
        return Event.loadEventByID(id);
    }
}
