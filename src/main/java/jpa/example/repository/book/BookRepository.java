package jpa.example.repository.book;

import java.util.List;

import jpa.example.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
    List<Book> findByUserId(Integer id);
}
