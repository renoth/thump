package de.ninjo.thump.dao;

import org.springframework.data.repository.CrudRepository;

import de.ninjo.thump.domain.Record;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
