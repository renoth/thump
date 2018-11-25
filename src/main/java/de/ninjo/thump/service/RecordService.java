package de.ninjo.thump.service;

import de.ninjo.thump.domain.Record;

public interface RecordService {
    /**
     * Stores the record in the database
     *
     * @param record The record to be stored
     */
    void storeRecord(Record record);
}
