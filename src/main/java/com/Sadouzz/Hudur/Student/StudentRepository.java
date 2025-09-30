package com.Sadouzz.Hudur.Student;
import com.Sadouzz.Hudur.Presence.Presence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByMatricule(String studentMatricule);
    List<Student> findByClasseId(Long classeId);
}
