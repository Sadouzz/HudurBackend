package com.Sadouzz.Hudur.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    @PostMapping
    public ResponseEntity<Participation> createParticipation(@RequestBody Participation participation) {
        return ResponseEntity.ok(participationService.saveParticipation(participation));
    }

    @GetMapping
    public List<Participation> getParticipation() {
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


