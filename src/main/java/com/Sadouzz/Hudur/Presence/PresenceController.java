package com.Sadouzz.Hudur.Presence;

import com.Sadouzz.Hudur.Classe.Classe;
import com.Sadouzz.Hudur.Event.Event;
import com.Sadouzz.Hudur.Participation.ParticipationRepository;
import com.Sadouzz.Hudur.Participation.ParticipationService;
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

    @Autowired
    private ParticipationService participationService;

    @PostMapping
    public ResponseEntity<?> markPresence(@RequestParam String studentMatricule, @RequestParam Long eventId, @RequestParam Long sessionId) {
        Presence existingPresence = presenceRepository.findByStudentMatriculeAndEventIdAndSessionId(studentMatricule, eventId, sessionId);

        if (existingPresence != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Étudiant déjà présent pour cet événement/session.");
        }

        Student student = studentRepository.findByMatricule(studentMatricule)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        Classe classe = student.getClasse();

        if(participationService.getByEventIdAndClasseId(eventId, classe.getId()).isPresent())
        {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new RuntimeException("Événement introuvable"));

            Session session = sessionRepository.findById(sessionId)
                    .orElseThrow(() -> new RuntimeException("Session introuvable"));

            Presence presence = new Presence(student, event, LocalDateTime.now(), session);

            Presence savedPresence = presenceRepository.save(presence);

            return ResponseEntity.ok(savedPresence);

        }
        else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Étudiant pas pris en compte pour cet événement.");
        }

    }


    @GetMapping("/event/{eventId}")
    public List<Presence> getPresencesByEvent(@PathVariable Long eventId) {
        return presenceRepository.findByEventId(eventId);
    }

    @GetMapping("/student/{studentId}")
    public List<Presence> getPresencesByStudent(@PathVariable Long studentId) {
        return presenceRepository.findByStudentId(studentId);
    }

    @GetMapping("/student/m/{studentMatricule}")
    public List<Presence> getPresencesByStudent(@PathVariable String studentMatricule) {
        return presenceRepository.findByStudentMatricule(studentMatricule);
    }
}
