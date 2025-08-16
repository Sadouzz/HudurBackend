package com.Sadouzz.Hudur.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(Long schoolId) {
        return schoolRepository.findById(schoolId); // Recherche l'événement dans la base de données
    }

    public Optional<School> getSchoolByName(String schoolName) {
        return Optional.ofNullable(schoolRepository.findByName(schoolName)); // Recherche l'événement dans la base de données
    }

    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }
}
