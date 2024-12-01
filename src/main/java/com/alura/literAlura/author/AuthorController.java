package com.alura.literAlura.author;

import java.util.List;

public class AuthorController {

    private AuthorRepository authorRepository;
    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public List<Author> findByYear(int year){
        return authorRepository.findAuthorsAliveInYear(year);
    }
}
