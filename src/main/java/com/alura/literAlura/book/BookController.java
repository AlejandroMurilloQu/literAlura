package com.alura.literAlura.book;

import com.alura.literAlura.author.Author;
import com.alura.literAlura.author.AuthorRepository;
import com.alura.literAlura.shared.Languages;

import java.util.List;
import java.util.Optional;

public class BookController {
    private BookRepository repository;
    private AuthorRepository authorRepository;

    public BookController(BookRepository repository, AuthorRepository authorRepository){
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public boolean saveBook(Book book){
        Optional<Book> bookExists = repository.findById(book.getId());

        if(bookExists.isEmpty()) {
            List<Author> authors = book.getAuthors().stream().map(a -> {
                Optional<Author> autor = authorRepository.findByName(a.getName());

                if(autor.isPresent()){
                    Author author = autor.get();
                    author.getLibros().add(book);
                    return author;
                }
                return a;
            }).toList();
            book.setAuthors(authors);
            repository.save(book);
            return true;
        }
        return false;
    }

    public List<Book> getBooks(){
        return repository.findAll();
    }

    public List<Book> getBookByLanguage(Languages language){
        return repository.findBooksByLanguage(language.toString());
    }
}
