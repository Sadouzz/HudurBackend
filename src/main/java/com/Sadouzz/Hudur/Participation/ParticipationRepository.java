package com.Sadouzz.Hudur.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByEventId(Long eventId);
    List<Participation> findByClasseId(Long clsId);
    Optional<Participation> findByEventIdAndClasseId(Long eventId, Long classId);
}
