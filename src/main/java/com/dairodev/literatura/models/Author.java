package com.dairodev.literatura.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity(name = "authors")
public class Author {
    @Id
    private String name;
    private int birthYear;
    private int deathYear;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books;

    public Author() {
    }

    public Author(AuthorData authorData) {
        this.name = authorData.name();
        this.birthYear = authorData.birthYear();
        this.deathYear = authorData.deathYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "{" +
                "name: '" + name + '\'' +
                ", birthYear: " + birthYear +
                ", deathYear: " + deathYear +
                '}';
    }
}
