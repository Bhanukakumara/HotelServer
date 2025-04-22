package edu.bks.HotelServer.Repository;

import edu.bks.HotelServer.entity.User;
import edu.bks.HotelServer.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);
}
