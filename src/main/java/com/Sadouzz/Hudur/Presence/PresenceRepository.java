package com.Sadouzz.Hudur.Presence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByStudentId(String studentId);
    List<Presence> findByEventId(Long eventId);
    Presence findByStudentIdAndEventId(String studentId, Long eventId);
    Presence findByStudentIdAndEventIdAndSessionId(String studentId, Long eventId, Long sessionId);
}
