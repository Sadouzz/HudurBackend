package com.Sadouzz.Hudur.Classe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<Classe> createClass(@RequestBody Classe classe) {
        return ResponseEntity.ok(classService.saveClass(classe));
    }

    @GetMapping
    public List<Classe> getClasss() {
        return classService.getAllClasss();
    }

    @GetMapping("/{classId}")
    public ResponseEntity<Classe> getClassById(@PathVariable Long classId) {
        Optional<Classe> classe = classService.getClassById(classId);
        if (classe.isPresent()) {
            return ResponseEntity.ok(classe.get());
        } else {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'événement n'est pas trouvé
        }
    }

    @GetMapping("/N/{className}")
    public ResponseEntity<Classe> getSchoolByName(@PathVariable String className) {
        Optional<Classe> classe = classService.getClassByName(className);
        if (classe.isPresent()) {
            return ResponseEntity.ok(classe.get());
        } else {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'événement n'est pas trouvé
        }
    }
}


