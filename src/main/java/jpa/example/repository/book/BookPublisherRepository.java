package jpa.example.repository.book;

import java.util.Optional;

import jpa.example.entity.book.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPublisherRepository extends JpaRepository<BookPublisher,Integer>{
    Optional<BookPublisher> findByNameAndUserId(String name,Integer userId);
}
