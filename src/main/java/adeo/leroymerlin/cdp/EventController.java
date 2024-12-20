package adeo.leroymerlin.cdp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "/")
    public List<Event> findEvents() {
        LOGGER.info("[EventController] [GET] /api/events - findEvents triggered");
        return eventService.getEvents();
    }

    @GetMapping(value = "/search/{query}")
    public List<Event> findEvents(@PathVariable String query) {
        LOGGER.info("[EventController] [GET] /api/events/search/{} - findEvents triggered with query: {}", query, query);
        return eventService.getFilteredEvents(query);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEvent(@PathVariable Long id) {
        LOGGER.info("[EventController] [DELETE] /api/events/{} - deleteEvent triggered with id: {}", id, id);
        eventService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
        LOGGER.info("[EventController] [PUT] /api/events/{} - updateEvent triggered with id: {} and event: {}", id, id, event);
    }
}
