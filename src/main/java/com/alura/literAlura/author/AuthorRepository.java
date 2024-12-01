package com.alura.literAlura.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.birth_year <= :year AND a.death_year >= :year")
    public List<Author> findAuthorsAliveInYear(int year);

    public Optional<Author> findByName(String name);
}
