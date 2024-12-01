package com.alura.literAlura.book;

import com.alura.literAlura.author.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "books")
public class Book {
    @Id
    private Long id;
    private String title;
    @ManyToMany(mappedBy = "libros", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Author> authors;
    private List<String> languages;
    private Long download_count;

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Long getId() {
        return id;
    }

    public Book(DatosBook book){
        this.id = book.id();
        this.title = book.title();
        this.download_count = book.download_count();
        this.authors = book.authors().stream().map(a -> new Author(a, List.of(this))).toList();
        this.languages = book.languages();
    }

    public Book(){}
    @Override
    public String toString() {
        return "Titulo='" + title + '\'' + '\n' +
                "Autores: { " + '\n' + authors.stream().map(Author::toString).collect(Collectors.joining()) + "\n}\n" +
                "Idiomas: " + String.join(",", languages) + '\n' +
                "Descargas: " + download_count;
    }
}
