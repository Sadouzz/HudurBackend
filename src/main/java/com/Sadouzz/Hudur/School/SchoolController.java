package com.Sadouzz.Hudur.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        return ResponseEntity.ok(schoolService.saveSchool(school));
    }

    @GetMapping
    public List<School> getSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{schoolId}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long schoolId) {
        Optional<School> school = schoolService.getSchoolById(schoolId);
        if (school.isPresent()) {
            return ResponseEntity.ok(school.get());
        } else {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'événement n'est pas trouvé
        }
    }

    @GetMapping("/N/{schoolName}")
    public ResponseEntity<School> getSchoolByName(@PathVariable String schoolName) {
        Optional<School> school = schoolService.getSchoolByName(schoolName);
        if (school.isPresent()) {
            return ResponseEntity.ok(school.get());
        } else {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'événement n'est pas trouvé
        }
    }
}


