package jpa.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jpa.example.entity.book.BookAuthor;
import jpa.example.entity.book.Book;
import jpa.example.entity.book.BookProducer;
import jpa.example.entity.book.BookPublisher;
import jpa.example.entity.user.User;
import jpa.example.repository.book.BookAuthorRepository;
import jpa.example.repository.book.BookProducerRepository;
import jpa.example.repository.book.BookRepository;
import jpa.example.repository.book.BookPublisherRepository;
import jpa.example.repository.user.UserRepository;
import jpa.example.util.book.BookAuthorUtil;
import jpa.example.util.book.BookTagUtil;

@SpringBootTest
public class BookTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookAuthorRepository bookAuthorRepository;

    @Autowired
    BookPublisherRepository bookPublisherRepository;

    @Autowired
    BookProducerRepository bookProducerRepository;

    void addBook(
            String title,
            Boolean isEbook,
            Integer userId,
            String tags,
            String authorNameRawArray,
            String publisherName,
            String producerName) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            System.out.println("no such user");
            return;
        }
        Book book = new Book();
        book.setTitle(title);
        book.setIsEbook(isEbook);
        book.setCreateAt(new Date());
        book.setUser(user.get());
        book.setTags(tags);
        // author
        List<String> authorNameList = BookAuthorUtil.authorsRawArrayToList(authorNameRawArray, ",，");
        List<BookAuthor> authorList = new ArrayList<>();
        for (String authorName : authorNameList) {
            Optional<BookAuthor> findAuthor = bookAuthorRepository.findByName(authorName, userId);
            if (findAuthor.isPresent()) {
                authorList.add(findAuthor.get());
            } else {
                BookAuthor newBookAuthor = new BookAuthor();
                newBookAuthor.setName(authorName);
                newBookAuthor.setUserId(user.get().getId());
                bookAuthorRepository.save(newBookAuthor);
                authorList.add(newBookAuthor);
            }
        }
        book.setBookAuthors(authorList);
        // publisher
        Optional<BookPublisher> findPublisher = bookPublisherRepository.findByNameAndUserId(publisherName,userId);
        if (findPublisher.isPresent()) {
            book.setBookPublisher(findPublisher.get());
        } else {
            BookPublisher newPublisher = new BookPublisher();
            newPublisher.setName(publisherName);
            newPublisher.setUserId(user.get().getId());
            newPublisher.setCreateAt(new Date());
            bookPublisherRepository.save(newPublisher);
            book.setBookPublisher(newPublisher);
        }
        // producer
        if(producerName!=null){
            Optional<BookProducer> findProducer = bookProducerRepository.findByName(producerName,userId);
            if(findProducer.isPresent()){
                book.setBookProducer(findProducer.get());
            }else{
                BookProducer newProducer= new BookProducer();
                newProducer.setName(producerName);
                newProducer.setUserId(userId);
                newProducer.setCreateAt(new Date());
                bookProducerRepository.save(newProducer);
                book.setBookProducer(newProducer);
            }
        }
        bookRepository.save(book);
    }

    @Test
    void addBooks() {
        addBook("Spring in Action", false, 1, "Java,Spring,CS", "Mr.Java,Mr.Jakarta", "APress","ProducerA");
        addBook("Java CookBook", false, 1, "Java,CS", "Mr.Java", "BPress","ProducerB");
        addBook("Java Tests", false, 1, "Java,CS", "Mr.Java", "BPress","ProducerB");
        addBook("Java Crush Course", true, 1, "Java,CS", "Mr.Coffee", "BPress","ProducerB");
        addBook("Java for Busy Guys", true, 1, "Java,CS", "Mr.Coffee", "BPress","ProducerB");
        addBook("Computer Network", false, 1, "network,CS", "Ms.Computer", "CPress",null);
        addBook("Le Comte de Monte-Cristo", false, 2, "novel", "Mr. French", "DPress",null);
        addBook("Computer Science", false, 2, "CS", "Ms.Computer", "EPress",null);
        addBook("Computing Theory", true, 2, "CS", "Ms.Computer", "EPress",null);
    }

    @Test
    void getBooksTag() {
        var books = bookRepository.findByUserId(1);
        for (var entry : BookTagUtil.bookListToTagMap(books, ",").entrySet()) {
            System.out.println("======");
            System.out.println("[" + entry.getKey() + "]");
            for (var book : entry.getValue()) {
                System.out.println(book.getTitle());
            }
        }
    }

    @Test
    void getAuthorsCreatedByUser() {
        List<BookAuthor> authors = bookAuthorRepository.findByUserId(1);
        for (BookAuthor author : authors) {
            System.out.println(">> " + author.getName());
            System.out.println(author.getBooks());
        }
    }
}
