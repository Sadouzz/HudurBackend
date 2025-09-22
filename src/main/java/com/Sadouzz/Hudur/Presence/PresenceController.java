package com.Sadouzz.Hudur.Presence;

import com.Sadouzz.Hudur.Event.Event;
import com.Sadouzz.Hudur.Session.Session;
import com.Sadouzz.Hudur.Session.SessionRepository;
import com.Sadouzz.Hudur.Student.Student;
import com.Sadouzz.Hudur.Event.EventRepository;
import com.Sadouzz.Hudur.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/presence")
public class PresenceController {

    @Autowired
    private PresenceRepository presenceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping
    public ResponseEntity<?> markPresence(@RequestParam String studentId, @RequestParam Long eventId, @RequestParam Long sessionId) {
        Presence existingPresence = presenceRepository.findByStudentIdAndEventIdAndSessionId(studentId, eventId, sessionId);

        if (existingPresence != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Étudiant déjà présent pour cet événement/session.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session introuvable"));

        Presence presence = new Presence(student, event, LocalDateTime.now(), session);
        /*presence.setStudent(student);
        presence.setEvent(event);
        presence.setPresenceDate(LocalDateTime.now());*/

        Presence savedPresence = presenceRepository.save(presence);

        return ResponseEntity.ok(savedPresence);
    }


    @GetMapping("/event/{eventId}")
    public List<Presence> getPresencesByEvent(@PathVariable Long eventId) {
        return presenceRepository.findByEventId(eventId);
    }

    @GetMapping("/student/{studentId}")
    public List<Presence> getPresencesByStudent(@PathVariable String studentId) {
        return presenceRepository.findByStudentId(studentId);
    }
}
