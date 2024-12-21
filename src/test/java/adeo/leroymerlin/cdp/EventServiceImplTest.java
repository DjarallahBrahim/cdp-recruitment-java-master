package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.DTOs.EventDTO;
import adeo.leroymerlin.cdp.entities.Band;
import adeo.leroymerlin.cdp.entities.Event;
import adeo.leroymerlin.cdp.entities.Member;
import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.services.EventServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event sampleEvent;

    @BeforeEach
    void setUp() {
        sampleEvent = new Event();
        sampleEvent.setId(1L);
        sampleEvent.setTitle("Sample Event");
        sampleEvent.setImgUrl("sample.jpg");

        Band band = new Band();
        band.setId(1L);
        band.setName("Sample Band");

        Member member = new Member();
        member.setId(1L);
        member.setName("Sample Member");

        band.setMembers(Set.of(member));
        sampleEvent.setBands(Set.of(band));
    }

    @Test
    void testGetEvents() {
        // Arrange
        List<Event> events = List.of(sampleEvent);
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        // Act
        List<Event> result = eventService.getEvents();

        // Assert
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Sample Event", result.get(0).getTitle());
        Mockito.verify(eventRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testDeleteEventExists() {
        // Arrange
        Long eventId = 1L;
        Mockito.when(eventRepository.existsById(eventId)).thenReturn(true);

        // Act
        eventService.delete(eventId);

        // Assert
        Mockito.verify(eventRepository, Mockito.times(1)).deleteById(eventId);
        Mockito.verify(eventRepository, Mockito.times(1)).existsById(eventId);
    }

    @Test
    void testDeleteEventDoesNotExist() {
        // Arrange
        Long eventId = 1L;
        Mockito.when(eventRepository.existsById(eventId)).thenReturn(false);

        // Act
        eventService.delete(eventId);

        // Assert
        Mockito.verify(eventRepository, Mockito.never()).deleteById(eventId);
        Mockito.verify(eventRepository, Mockito.times(1)).existsById(eventId);
    }

    @Test
    void testGetFilteredEvents() {
        // Arrange
        List<Event> events = List.of(sampleEvent);
        Mockito.when(eventRepository.findAll()).thenReturn(events);

        // Act
        List<EventDTO> result = eventService.getFilteredEvents("Sample Member");

        // Assert
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Sample Event [1]", result.get(0).getTitle());
        Assertions.assertEquals("Sample Band [1]", result.get(0).getBands().get(0).getName());
        Mockito.verify(eventRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testUpdateEvent() {
        // Arrange
        Long eventId = 1L;
        Event updatedEvent = new Event();
        updatedEvent.setId(eventId);
        updatedEvent.setTitle("Updated Event");
        Mockito.when(eventRepository.save(updatedEvent)).thenReturn(updatedEvent);

        // Act
        eventService.updateEvent(eventId, updatedEvent);

        // Assert
        Mockito.verify(eventRepository, Mockito.times(1)).save(updatedEvent);
    }
}
