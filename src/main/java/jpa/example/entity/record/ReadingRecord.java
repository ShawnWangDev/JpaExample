package jpa.example.entity.record;

import java.util.Date;

import jakarta.persistence.*;
import jpa.example.entity.book.Book;
import lombok.Data;

@Entity
@Table
@Data
public class ReadingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Date startAt;
    private Date endAt;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer startPage;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer endPage;
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer durationSeconds;
    @Column(length = 1024)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @Override
    public String toString() {
        return "ReadingRecord [id=" + id + ", startAt=" + startAt + ", endAt=" + endAt + ", startPage=" + startPage
                + ", endPage=" + endPage + ", durationSeconds=" + durationSeconds + ", comment=" + comment + "]";
    }
}
