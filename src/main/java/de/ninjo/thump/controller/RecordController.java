package de.ninjo.thump.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    @RequestMapping("/hello")
    public ResponseEntity greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("oke des wa ales.");
        return ResponseEntity.ok("OKE");
    }
}
