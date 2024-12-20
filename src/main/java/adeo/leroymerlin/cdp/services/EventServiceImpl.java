package adeo.leroymerlin.cdp.services;

import adeo.leroymerlin.cdp.DTOs.BandDTO;
import adeo.leroymerlin.cdp.DTOs.EventDTO;
import adeo.leroymerlin.cdp.entities.Event;
import adeo.leroymerlin.cdp.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getEvents() {
        LOGGER.info("[EventService] Fetching all events");
        List<Event> events = eventRepository.findAll();
        LOGGER.info("[EventService] Retrieved {} events", events.size());
        return events;
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("[EventService] Deleting event with id: {}", id);
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            LOGGER.info("[EventService] Successfully deleted event with id: {}", id);
        } else {
            LOGGER.warn("[EventService] Event with id: {} does not exist", id);
        }
    }

    @Override
    public List<EventDTO> getFilteredEvents(String query) {
        LOGGER.info("[EventService] Fetching and filtering events with query: {}", query);
        List<EventDTO> events = eventRepository.findAll().stream()
                .filter(event -> event.getBands().stream()
                .anyMatch(band -> band.getMembers().stream()
                .anyMatch(member -> member.getName().toLowerCase().contains(query.toLowerCase()))))
                .map(event -> {
                    //add count to title
                    String eventTitleWithCount = event.getTitle() + " [" + event.getBands().size() + "]";
                    List<BandDTO> bandsWithCounts = event.getBands().stream()
                        .map(band -> {
                            //add count to band name
                            String bandNameWithCount = band.getName() + " [" + band.getMembers().size() + "]";
                            return new BandDTO(bandNameWithCount, band.getMembers());
                        })
                        .collect(Collectors.toList());
                    return new EventDTO(eventTitleWithCount, event.getImgUrl(), bandsWithCounts);
                })
                .collect(Collectors.toList());
        // Filter the events list in pure JAVA here

        return events;
    }

    @Override
    public void updateEvent(Long id, Event event) {
        LOGGER.info("[EventService] Updating event with id: {} and data: {}", id, event);
        event.setId(id); // Ensures the ID matches the path parameter, we can skip this step if we are 100% that the id of event is the same value of id
        Event updatedEvent = eventRepository.save(event);
        LOGGER.info("[EventService] Successfully updated event: {}", updatedEvent);
    }
}
