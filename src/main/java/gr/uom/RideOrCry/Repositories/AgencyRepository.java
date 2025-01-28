package gr.uom.RideOrCry.Repositories;

import gr.uom.RideOrCry.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<User, String> {
    @Query("SELECT u from User u WHERE 'AGENCY' MEMBER OF u.roles AND u.name=:name")
    Optional<User> findAgencyByName(String name);

    @Query("SELECT u from User u WHERE 'AGENCY' MEMBER OF u.roles AND u.email=:email AND u.password=:password")
    Optional<User> findAgencyByEmailAndPassword(String email, String password);
}
