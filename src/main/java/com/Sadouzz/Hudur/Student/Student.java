package com.Sadouzz.Hudur.Student;
import com.Sadouzz.Hudur.Presence.Presence;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;*/
    @Id
    private String id;

    private String name, surname;
    private LocalDate dateBirth;
    private String placeBirth;

    private Long classId;

    @OneToMany(mappedBy = "student")
    private List<Presence> presences;

    // Getters et setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
