package com.Sadouzz.Hudur.Student;
import com.Sadouzz.Hudur.Classe.Classe;
import com.Sadouzz.Hudur.Event.Event;
import com.Sadouzz.Hudur.Presence.Presence;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;

    private String name, surname;
    private LocalDate dateBirth;
    private String placeBirth, email;

    //private Long classId;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classe classe;

    @OneToMany(mappedBy = "student")
    private List<Presence> presences;

    // Getters et setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getCompleteName()
    {
        return name + " " + surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    public Long getClassId() {
//        return classId;
//    }
//
//    public void setClassId(Long classId) {
//        this.classId = classId;
//    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
