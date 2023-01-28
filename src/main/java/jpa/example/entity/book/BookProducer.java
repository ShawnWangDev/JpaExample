package jpa.example.entity.book;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class BookProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String logo;
    private String websiteUrl;
    private String briefIntroduction;
    private Date createAt;
    private Date updateAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookProducer",fetch = FetchType.LAZY)
    private List<Book> books;
    private Integer userId;
}
