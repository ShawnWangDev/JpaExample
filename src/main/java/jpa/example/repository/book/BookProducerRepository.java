package jpa.example.repository.book;

import jpa.example.entity.book.BookProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookProducerRepository extends JpaRepository<BookProducer,Integer> {
    @Query(value = "from BookProducer where name=?1 and userId=?2")
    Optional<BookProducer> findByName(String name, Integer userId);
}
