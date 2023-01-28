package jpa.example.entity.book;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String headPortrait;
    @Column(length = 2048)
    private String briefIntroduction;
    private Integer userId;
    @ManyToMany(targetEntity = Book.class, fetch = FetchType.EAGER, mappedBy = "bookAuthors")
    private List<Book> books;

    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + ", headPortrait=" + headPortrait + ", briefIntroduction="
                + briefIntroduction + "]";
    }
}
