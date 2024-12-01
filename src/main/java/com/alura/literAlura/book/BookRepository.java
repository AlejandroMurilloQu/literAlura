package com.alura.literAlura.book;

import com.alura.literAlura.shared.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM books b WHERE lower(:language) = ANY(b.languages)", nativeQuery = true)
    public List<Book> findBooksByLanguage(String language);
}
