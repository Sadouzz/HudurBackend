package com.Sadouzz.Hudur.Student;
import com.Sadouzz.Hudur.Classe.ClassRepository;
import com.Sadouzz.Hudur.Classe.Classe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classeRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsOfClasse(Long classeId) {
        return studentRepository.findByClasseId(classeId);
    }

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId); // Recherche l'événement dans la base de données
    }

    public Optional<Student> getStudentByMatricule(String studentMatricule) {
        return studentRepository.findByMatricule(studentMatricule); // Recherche l'événement dans la base de données
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student saveStudent(StudentDTO dto) {
        Classe classe = classeRepository.findById(dto.getClassId())
                .orElseThrow(() -> new RuntimeException("Classe not found"));

        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setDateBirth(dto.getDateBirth());
        student.setPlaceBirth(dto.getPlaceBirth());
        student.setClasse(classe); // entité persistante

        return studentRepository.save(student);
    }
}
