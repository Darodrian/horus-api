package com.caschile.horus.security.entity;

import jakarta.persistence.*;

@Entity
public class Correlativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String sequence_name;

    private int next_val;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequence_name() {
        return sequence_name;
    }

    public void setSequence_name(String sequence_name) {
        this.sequence_name = sequence_name;
    }

    public int getNext_val() {
        return next_val;
    }

    public void setNext_val(int next_val) {
        this.next_val = next_val;
    }
}
