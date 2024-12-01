package com.alura.literAlura;

import com.alura.literAlura.author.AuthorRepository;
import com.alura.literAlura.book.BookRepository;
import com.alura.literAlura.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu(bookRepository, authorRepository);
		menu.ShowMenu();
	}
}
