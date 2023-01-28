package jpa.example.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.example.entity.user.UserProfile;

public interface ProfileRepository  extends JpaRepository<UserProfile,Integer>{
    UserProfile findByUserId(Integer id);
}
