package jpa.example.entity.book;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class BookPublisher {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookPublisher",fetch = FetchType.LAZY)
    private List<Book> books;
    private Integer userId;

    @Override
    public String toString() {
        return "Publisher [id=" + id + ", name=" + name + ", logo=" + logo + ", websiteUrl=" + websiteUrl
                + ", briefIntroduction=" + briefIntroduction + ", createAt=" + createAt + ", updateAt=" + updateAt
                + "]";
    }
}
