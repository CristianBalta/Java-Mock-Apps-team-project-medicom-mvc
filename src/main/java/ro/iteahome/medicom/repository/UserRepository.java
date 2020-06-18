package ro.iteahome.medicom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.iteahome.medicom.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getById(int id);

    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    User getByEmailAndPassword(String email, String password);

    boolean existsByEmailAndPassword(String email, String password);
}
