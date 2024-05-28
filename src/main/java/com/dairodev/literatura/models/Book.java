package com.dairodev.literatura.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "books")
public class Book {
    @Id
    private int id;
    private String title;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "name")
    )
    private Set<Author> authors;
    private String languages;
    private int downloadCount;

    public Book() {
    }

    public Book(BookData bookData) {
        this.id = bookData.id();
        this.title = bookData.title();
        this.authors = bookData
                .authors()
                .stream()
                .map(authorData -> new Author(authorData))
                .collect(Collectors.toSet());
        this.languages = String.join(",", bookData.languages());
        this.downloadCount = bookData.downloadCount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        authors.forEach(author -> stringBuilder.append(author.toString() + "\n"));
        stringBuilder.append("]");
        return "{" +
                "id: " + id +
                ", title: '" + title + '\'' +
                ", authors: [" + stringBuilder +
                ", languages: '" + languages + '\'' +
                ", downloadCount: " + downloadCount +
                '}';
    }
}
