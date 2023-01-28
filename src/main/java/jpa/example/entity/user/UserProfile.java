package jpa.example.entity.user;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@Data
public class UserProfile {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;
    private String briefIntroduction;
    private LocalDate birthday;
    private String avatar;
    private Date updateAt;
    private String gender;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
