package com.Sadouzz.Hudur.Classe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public List<Classe> getAllClasss() {
        return classRepository.findAll();
    }

    public Optional<Classe> getClassById(Long classId) {
        return classRepository.findById(classId); // Recherche l'événement dans la base de données
    }

    public Optional<Classe> getClassByName(String className) {
        return Optional.ofNullable(classRepository.findByName(className)); // Recherche l'événement dans la base de données
    }

    public Classe saveClass(Classe classe) {
        return classRepository.save(classe);
    }
}
