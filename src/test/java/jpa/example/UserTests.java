package jpa.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jpa.example.repository.user.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import jpa.example.entity.user.UserProfile;
import jpa.example.entity.user.User;
import jpa.example.repository.user.UserRepository;

@SpringBootTest
public class UserTests {

    private User simpleUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRegisteredAt(new Date());
        return user;
    }
    
    private UserProfile simpleProfile(Integer userId, String briefIntro) {
        UserProfile profile=new UserProfile();
        User u=new User();
        u.setId(userId);
        profile.setUser(u);
        profile.setBriefIntroduction(briefIntro);
        profile.setBirthday(LocalDate.of(1900, 10, 10));
        return profile;
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Test
    void addUser() {
        User alice = simpleUser("Alice", "alice123");
        User ben = simpleUser("Ben", "ben123");
        alice=userRepository.save(alice);
        ben=userRepository.save(ben);
        UserProfile aliceProfile=simpleProfile(alice.getId(), "Hi! I am Alice.");
        UserProfile benProfile=simpleProfile(ben.getId(),"This is Ben!");
        profileRepository.save(aliceProfile);
        profileRepository.save(benProfile);
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setUsername("Ben");
        Example<User> exampleUser = Example.of(user);
        List<User> userList = userRepository.findAll(exampleUser);
        for (User u : userList) {
            UserProfile p = profileRepository.findByUserId(u.getId());
            profileRepository.delete(p);
            userRepository.delete(u);
        }
    }
}
