package com.Sadouzz.Hudur.Presence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByStudentId(Long studentId);
    List<Presence> findByEventId(Long eventId);
    Presence findByStudentMatriculeAndEventId(String studentMatricule, Long eventId);
    Presence findByStudentMatriculeAndEventIdAndSessionId(String studentMatricule, Long eventId, Long sessionId);
}
