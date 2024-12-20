package adeo.leroymerlin.cdp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        LOGGER.info("[EventService] Fetching all events");
        List<Event> events = eventRepository.findAll();
        LOGGER.info("[EventService] Retrieved {} events", events.size());
        return events;
    }

    public void delete(Long id) {
        LOGGER.info("[EventService] Deleting event with id: {}", id);
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            LOGGER.info("[EventService] Successfully deleted event with id: {}", id);
        } else {
            LOGGER.warn("[EventService] Event with id: {} does not exist", id);
        }
    }

    public List<Event> getFilteredEvents(String query) {
        LOGGER.info("[EventService] Fetching and filtering events with query: {}", query);
        List<Event> events = eventRepository.findAll();
        // Filter the events list in pure JAVA here

        return events;
    }
}
