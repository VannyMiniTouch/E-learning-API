package co.istad.elearning.api.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndIsDeletedAndIsVerified(String email, Boolean isDeleted, Boolean isVerified);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
