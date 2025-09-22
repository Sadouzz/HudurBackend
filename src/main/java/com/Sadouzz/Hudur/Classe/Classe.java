package com.Sadouzz.Hudur.Classe;

import com.Sadouzz.Hudur.Presence.Presence;
import com.Sadouzz.Hudur.School.School;
import com.Sadouzz.Hudur.Student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "classes")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, level;

    //private Long schoolId;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "classe")
    private List<Student> students;

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
}
