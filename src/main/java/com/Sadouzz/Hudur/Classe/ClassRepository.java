package com.Sadouzz.Hudur.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classe, Long> {
    Classe findByName(String className);
}
