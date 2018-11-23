package de.ninjo.thump.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Record {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
