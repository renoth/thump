package de.ninjo.thump.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.ninjo.thump.dao.RecordRepository;
import de.ninjo.thump.domain.Record;

@Component
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    @Transactional
    public void storeRecord(Record record) {
        record.setTimestamp(LocalDateTime.now());

        recordRepository.save(record);
    }
}
