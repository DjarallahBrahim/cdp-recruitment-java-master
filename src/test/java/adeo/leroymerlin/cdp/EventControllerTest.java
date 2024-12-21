package adeo.leroymerlin.cdp;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import adeo.leroymerlin.cdp.DTOs.EventDTO;
import adeo.leroymerlin.cdp.controler.EventController;
import adeo.leroymerlin.cdp.entities.Event;
import adeo.leroymerlin.cdp.services.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Set;

class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    void testFindEvents() throws Exception {
        Event event = new Event();
        event.setId(1L);
        event.setTitle("Test Event");
        event.setBands(Set.of());
        when(eventService.getEvents()).thenReturn(List.of(event));

        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Event"));

        verify(eventService, times(1)).getEvents();
    }

    @Test
    void testFindEventsByQuery() throws Exception {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle("Filtered Event");
        when(eventService.getFilteredEvents("query")).thenReturn(List.of(eventDTO));

        mockMvc.perform(get("/api/events/search/query"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Filtered Event"));

        verify(eventService, times(1)).getFilteredEvents("query");
    }

    @Test
    void testDeleteEvent() throws Exception {
        Long eventId = 1L;

        mockMvc.perform(delete("/api/events/{id}", eventId))
                .andExpect(status().isOk());

        verify(eventService, times(1)).delete(eventId);
    }

    @Test
    void testUpdateEvent() throws Exception {
        Long eventId = 1L;
        Event event = new Event();
        event.setTitle("Updated Event");

        mockMvc.perform(put("/api/events/{id}", eventId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk());

        verify(eventService, times(1)).updateEvent(eq(eventId), any(Event.class));
    }
}
