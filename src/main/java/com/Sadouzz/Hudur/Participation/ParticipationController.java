package com.Sadouzz.Hudur.Participation;
import com.Sadouzz.Hudur.Classe.ClassRepository;
import com.Sadouzz.Hudur.Classe.Classe;
import com.Sadouzz.Hudur.Event.Event;
import com.Sadouzz.Hudur.Event.EventRepository;
import com.Sadouzz.Hudur.Presence.Presence;
import com.Sadouzz.Hudur.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private ClassRepository clsRepository;
    @Autowired
    private EventRepository eventRepository;


    @PostMapping
    public ResponseEntity<?> createParticipation(@RequestParam Long eventId, @RequestParam Long classId) {
        Participation existingParticipation = participationRepository.findByEventIdAndClasseId(eventId, classId);

        if (existingParticipation != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Participation déjà présente.");
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement introuvable"));

        Classe cls = clsRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));

        Participation participation = new Participation(cls, event);

        Participation savedParticipation = participationRepository.save(participation);

        return ResponseEntity.ok(savedParticipation);
    }

    @GetMapping
    public List<Participation> getParticipations() {
        return participationService.getAllParticipations();
    }

    @GetMapping("/{participationId}")
    public ResponseEntity<Participation> getParticipationById(@PathVariable Long participationId) {
        Optional<Participation> participation = participationService.getParticipationById(participationId);
        if (participation.isPresent()) {
            return ResponseEntity.ok(participation.get());
        } else {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'événement n'est pas trouvé
        }
    }

}


