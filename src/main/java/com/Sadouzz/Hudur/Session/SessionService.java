package com.Sadouzz.Hudur.Session;
import com.Sadouzz.Hudur.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private  SessionRepository sessionRepository;

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public Optional<Session> getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId); // Recherche l'événement dans la base de données
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public List<Session> findByEvent(Long eventId) {
        return sessionRepository.findByEventId(eventId);
    }

    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }
}
