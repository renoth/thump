package de.ninjo.thump.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import de.ninjo.thump.dao.RecordRepository;
import de.ninjo.thump.domain.Record;

@RestController
public class RecordController {
    private static final Logger LOG = LoggerFactory.getLogger(RecordController.class);
    private final RecordRepository recordRepository;

    @Autowired
    public RecordController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @PostMapping(value = {"/log"}, consumes = {"application/json"})
    public ResponseEntity<Record> logRecord(@Valid @RequestBody Record record) {
        LOG.info("Received datapoint: {}", record);

        recordRepository.save(record);

        return ResponseEntity.created(URI.create("/log/" + record.getId())).body(record);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
