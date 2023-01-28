package jpa.example.repository.user;

import java.util.List;
import java.util.Optional;

import jpa.example.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer>{
    Optional<List<User>> findByUsername(String username);
}
