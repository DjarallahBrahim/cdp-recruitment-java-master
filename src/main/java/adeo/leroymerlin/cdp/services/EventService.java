package adeo.leroymerlin.cdp.services;

import adeo.leroymerlin.cdp.DTOs.EventDTO;
import adeo.leroymerlin.cdp.entities.Event;

import java.util.List;

public interface EventService {

    List<Event> getEvents();

    void delete(Long id);

    List<EventDTO> getFilteredEvents(String query);

    void updateEvent(Long id, Event event);
}
