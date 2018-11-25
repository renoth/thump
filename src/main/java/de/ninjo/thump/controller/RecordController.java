package de.ninjo.thump.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.ninjo.thump.domain.Record;
import de.ninjo.thump.service.RecordServiceImpl;

@RestController
public class RecordController {
    private static final Logger LOG = LoggerFactory.getLogger(RecordController.class);
    private final RecordServiceImpl recordService;

    @Autowired
    public RecordController(RecordServiceImpl recordService) {
        this.recordService = recordService;
    }

    @PostMapping(value = {"/log"}, consumes = {"application/json"})
    public ResponseEntity logRecord(@RequestBody Record record) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Received datapoint: {}", record);
        }

        recordService.storeRecord(record);

        return ResponseEntity.ok("Logged");
    }
}
