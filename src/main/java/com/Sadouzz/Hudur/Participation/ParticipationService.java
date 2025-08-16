package com.Sadouzz.Hudur.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationService {
    @Autowired
    private ParticipationRepository participationRepository;

    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    public Optional<Participation> getParticipationById(Long participationId) {
        return participationRepository.findById(participationId); // Recherche l'événement dans la base de données
    }


    public Participation saveParticipation(Participation participation) {
        return participationRepository.save(participation);
    }
}
