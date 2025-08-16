package com.Sadouzz.Hudur.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByEventId(Long eventId);
    List<Participation> findByClasseId(Long clsId);
    Participation findByEventIdAndClasseId(Long eventId, Long classId);
}
