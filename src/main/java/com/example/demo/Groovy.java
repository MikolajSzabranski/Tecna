package com.example.demo;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Groovy {
    @Id
    @SequenceGenerator(name = "code_sequence", sequenceName = "code_sequence", allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "code_sequence"
    )
    @Column
    private Long Id;
    @Column
    private String name;
    @Column
    private String code;

    public Groovy(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Groovy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
