package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<User, String> {
    @Query("SELECT u from User u WHERE 'CITIZEN' MEMBER OF u.roles AND u.email=:email AND u.password=:password")
    Optional<User> findCitizenByEmailAndPassword(String email, String password);
}
