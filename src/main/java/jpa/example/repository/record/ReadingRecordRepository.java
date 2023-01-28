package jpa.example.repository.record;

import java.util.List;

import jpa.example.entity.record.ReadingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRecordRepository extends JpaRepository<ReadingRecord, Integer>{
    List<ReadingRecord> findByUserId(Integer id);
}
