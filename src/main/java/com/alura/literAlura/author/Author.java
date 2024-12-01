package com.alura.literAlura.author;

import com.alura.literAlura.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int birth_year;
    private int death_year;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> libros;

    public Author(){}

    public void setLibros(List<Book> libros) {
        this.libros = libros;
    }

    public String getName() {
        return name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public int getDeath_year() {
        return death_year;
    }

    public List<Book> getLibros() {
        return libros;
    }

    public Author(DatosAuthor author, List<Book> libros){
        this.name = author.name();
        this.birth_year = author.birth_year();
        this.death_year = author.death_year();
        this.libros = libros;
    }
    @Override
    public String toString() {
        return
                "Nombre = '" + name + '\'' + '\n' +
                        "Año de Nacimiento = " + birth_year + '\n' +
                        "Año de Muerte = " + death_year;
    }

    public String printBooks(){
        return "\nLibros: [" + '\n' +
                libros.stream().map(l -> "\nNombre: " + l.getTitle() + '\n' + "Descargas: " + l.getDownload_count() + "\n").collect(Collectors.joining()) + "\n]";
    }



}



