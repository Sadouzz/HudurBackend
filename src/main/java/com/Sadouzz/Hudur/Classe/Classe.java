package com.Sadouzz.Hudur.Classe;

import com.Sadouzz.Hudur.Presence.Presence;
import com.Sadouzz.Hudur.School.School;
import com.Sadouzz.Hudur.Student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "classes")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String matricule;
    private String name, level;



    //private Long schoolId;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @JsonIgnore
    @OneToMany(mappedBy = "classe")
    private List<Student> students;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

//    public Long getSchoolId() {
//        return schoolId;
//    }
//
//    public void setSchoolId(Long schoolId) {
//        this.schoolId = schoolId;
//    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // NOUVEAU: Méthode pour obtenir le nombre d'étudiants (sera sérialisée en JSON)
    public int getStudentCount() {
        return students != null ? students.size() : 0;
    }
}
