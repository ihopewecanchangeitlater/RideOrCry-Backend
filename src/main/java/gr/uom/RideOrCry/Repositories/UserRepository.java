package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByAfmOrEmail(String afm, String email);
}
