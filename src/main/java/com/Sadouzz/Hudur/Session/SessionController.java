package com.Sadouzz.Hudur.Session;

import com.Sadouzz.Hudur.Classe.Classe;
import com.Sadouzz.Hudur.Event.Event;
import com.Sadouzz.Hudur.Event.EventRepository;
import com.Sadouzz.Hudur.Session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<?> createSession(
            @RequestParam Long eventId,
            @RequestParam Integer sessionNumber,
            @RequestParam String date,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam Integer dayNumber,
            @RequestParam Integer sessionOfDay) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        // Conversion des dates / heures
        LocalDate localDate = LocalDate.parse(date);   // "YYYY-MM-DD"
        LocalTime start = LocalTime.parse(startTime);  // "HH:mm"
        LocalTime end = LocalTime.parse(endTime);

        Session session = new Session();
        session.setEvent(event);
        session.setSessionNumber(sessionNumber);
        session.setDate(localDate);
        session.setStartTime(start);
        session.setEndTime(end);
        session.setDayNumber(dayNumber);
        session.setSessionOfDay(sessionOfDay);

        Session savedSession = sessionRepository.save(session);

        return ResponseEntity.ok(savedSession);
    }





    @GetMapping
    public List<Session> getAll() {
        return sessionService.findAll();
    }

    @GetMapping("/event/{eventId}")
    public List<Session> getByEvent(@PathVariable Long eventId) {
        return sessionService.findByEvent(eventId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sessionService.delete(id);
    }
}
