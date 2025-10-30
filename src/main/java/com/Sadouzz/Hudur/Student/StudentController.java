package com.Sadouzz.Hudur.Student;
import com.Sadouzz.Hudur.Presence.Presence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;



    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student dto) {
        Student saved = studentService.saveStudent(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/classe/{classeId}")
    public List<Student> getStudentsByClasseId(@PathVariable Long classeId) {
        return studentService.getAllStudentsOfClasse(classeId);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);
        // Retourne 404 si l'événement n'est pas trouvé
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/matricule/{studentMatricule}")
    public ResponseEntity<Student> getStudentByMatricule(@PathVariable String studentMatricule) {
        Optional<Student> student = studentService.getStudentByMatricule(studentMatricule);
        // Retourne 404 si l'événement n'est pas trouvé
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}


