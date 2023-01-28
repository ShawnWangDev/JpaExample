package jpa.example.repository.book;

import java.util.List;
import java.util.Optional;

import jpa.example.entity.book.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer>{
    @Query(value = "from BookAuthor where name=?1 and userId=?2")
    Optional<BookAuthor> findByName(String name,Integer userId);

    List<BookAuthor> findByUserId(Integer userId);
}
