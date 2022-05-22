package com.example.demo;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Person")
public class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence",allocationSize = 1)
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "person_sequence"
    )
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String first_name;
    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String last_name;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;
    @OneToMany(targetEntity = Groovy.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_Person", referencedColumnName = "id")
    private List<Groovy> groovies;

    public Person(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public List<Groovy> getGroovies() {
        return groovies;
    }

    public void setGroovies(List<Groovy> groovies) {
        this.groovies = groovies;
    }
}
