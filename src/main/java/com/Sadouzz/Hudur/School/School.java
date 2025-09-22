package com.Sadouzz.Hudur.School;

import com.Sadouzz.Hudur.Classe.Classe;
import com.Sadouzz.Hudur.Student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "school")
    private List<Classe> classes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}