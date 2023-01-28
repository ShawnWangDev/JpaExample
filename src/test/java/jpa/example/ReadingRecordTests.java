package jpa.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jpa.example.entity.record.ReadingRecord;
import jpa.example.entity.book.Book;
import jpa.example.entity.user.User;
import jpa.example.repository.record.ReadingRecordRepository;

@SpringBootTest
public class ReadingRecordTests {
    @Autowired
    ReadingRecordRepository readingRecordRepository;

    @Test
    void addRecord() {
        addRecord(1, 1, "2023-01-23 18:00:00", "2023-01-23 18:00:00");
        addRecord(1, 1, "2023-01-26 11:00:00", "2023-01-23 18:00:00");
        addRecord(1, 1, "2033-01-26 11:00:00", "2033-01-33 18:00:00");
        addRecord(1, 2, "2023-01-24 11:00:00", "2023-01-24 18:00:00");
    }

    void addRecord(Integer userId, Integer bookId, String rawStartAt, String rawEndAt) {
        ReadingRecord record = new ReadingRecord();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startAt = null, endAt = null;
        try {
            startAt = sdf.parse(rawStartAt);
            endAt = sdf.parse(rawEndAt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long now = new Date().getTime();
        if (startAt.getTime() > now || endAt.getTime() > now) {
            System.out.println("start time or end time cannot beyond current time");
            return;
        }
        if (startAt.getTime() >= endAt.getTime()) {
            System.out.println("start time cannot early or equals end time");
            return;
        }
        int durationSeconds = (int) (endAt.getTime() - startAt.getTime()) / 1000;
        if (durationSeconds > 60 * 60 * 8) {
            System.out.println("reading duration cannot beyond 8 hours.");
            return;
        }
        System.out.println(durationSeconds / 60 + " minutes");
        record.setStartAt(startAt);
        record.setEndAt(endAt);
        record.setStartPage(1);
        record.setEndPage(50);
        record.setDurationSeconds(durationSeconds);
        // add a user
        User user = new User();
        user.setId(userId);
        record.setUserId(user.getId());
        // add a book
        Book book = new Book();
        book.setId(bookId);
        record.setBook(book);
        readingRecordRepository.save(record);
    }
}
