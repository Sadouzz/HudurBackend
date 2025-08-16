package com.Sadouzz.Hudur.Participation;

import com.Sadouzz.Hudur.Event.Event;
import com.Sadouzz.Hudur.Classe.Classe;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "participations")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Participation(Classe classe, Event event) {
        this.classe = classe;
        this.event = event;
    }
    public Participation() {
    }

    // Getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
