package jpa.example.entity.book;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

import jpa.example.entity.record.ReadingRecord;
import jpa.example.entity.user.User;

@Entity
@Table
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 13)
    private String isbn;
    @Column(nullable = false)
    private String title;
    private String subtitle;
    @Column(length = 1024)
    private String synopsis;
    @Column(columnDefinition = "TINYINT")
    private String version;
    private String marketPrice;
    private Float purchasedPrice;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer numberOfTextPages;
    private String cover;
    @Column(nullable = false)
    private Boolean isEbook;
    private Date createAt;
    private Date updateAt;
    private String tags;
    @Column(length = 64)
    private String subject;
    @Column(length = 4096)
    private String remark;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private BookPublisher bookPublisher;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private BookProducer bookProducer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
    private List<ReadingRecord> readingRecords;
    @ManyToMany(targetEntity = BookAuthor.class, fetch = FetchType.EAGER)
    private List<BookAuthor> bookAuthors;

    @Override
    public String toString() {
        return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", subtitle=" + subtitle + ", synopsis="
                + synopsis + ", version=" + version + ", marketPrice=" + marketPrice + ", purchasedPrice="
                + purchasedPrice + ", numberOfTextPages=" + numberOfTextPages + ", cover=" + cover + ", isEbook="
                + isEbook + ", createAt=" + createAt + ", updateAt=" + updateAt + ", tags=" + tags + ", subject="
                + subject + ", remark=" + remark + "]";
    }
}
