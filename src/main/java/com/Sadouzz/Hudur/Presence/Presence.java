package com.Sadouzz.Hudur.Presence;

import com.Sadouzz.Hudur.Event.Event;
import com.Sadouzz.Hudur.Session.Session;
import com.Sadouzz.Hudur.Session.SessionRepository;
import com.Sadouzz.Hudur.Student.Student;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "presences")
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private LocalDateTime presenceDate;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    public Presence(){

    }

    public Presence(Student student, Event event, LocalDateTime presenceDate, Session session) {
        this.student = student;
        this.event = event;
        this.presenceDate = presenceDate;
        this.session = session;
    }

    // Getters et Setters
    // Getter et Setter pour ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour Student
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // Getter et Setter pour Event
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    // Getter et Setter pour PresenceDate
    public LocalDateTime getPresenceDate() {
        return presenceDate;
    }

    public void setPresenceDate(LocalDateTime presenceDate) {
        this.presenceDate = presenceDate;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
