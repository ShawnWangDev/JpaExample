package jpa.example.entity.user;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import jpa.example.entity.book.Book;
import lombok.Data;

@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String username;
    @Column(length = 64, nullable = false)
    private String password;
    @Column(length = 128)
    private String roles;
    @Column(length = 64)
    private String email;
    @Column(length = 17)
    private String mobilePhone;
    private Date registeredAt;
    private Date lastLogin;
    @OneToOne(mappedBy = "user")
    private UserProfile profile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Book> books;
}
