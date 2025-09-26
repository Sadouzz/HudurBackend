package com.Sadouzz.Hudur.User;
import com.Sadouzz.Hudur.School.School;
import com.Sadouzz.Hudur.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
