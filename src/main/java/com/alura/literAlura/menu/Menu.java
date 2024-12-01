package com.alura.literAlura.menu;

import com.alura.literAlura.author.Author;
import com.alura.literAlura.author.AuthorController;
import com.alura.literAlura.author.AuthorRepository;
import com.alura.literAlura.book.*;
import com.alura.literAlura.shared.Languages;
import com.alura.literAlura.shared.service.GetDataFromApi;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private BookController bookController;
    private AuthorController authorController;

    public Menu(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookController = new BookController(bookRepository, authorRepository);
        this.authorController = new AuthorController(authorRepository);
    }

    public void ShowMenu() {
        String menu = """
                1 - Buscar libro por titulo.
                2 - Listar libros registrados
                3 - Listar Autores registrados
                4 - listar autores vivos en un determinado año
                5 - listar libros por idioma
                0 - Salir
                """;

        boolean exit = false;

        while (!exit) {
            int option = -1;
            System.out.println("\nElija la opción a traves de su numero: ");
            System.out.println(menu);

            while (option == -1) {
                System.out.print("Opcion: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Entrada Incorrecta, ¡Ingrese un numero!");
                    scanner.nextLine();
                    continue;
                }
                option = scanner.nextInt();
                scanner.nextLine();
            }

            switch (option) {
                case 0:
                    System.out.println("Saliendo del programa...");
                    exit = true;
                    break;
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listRegisteredBoooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                default:
                    System.out.println("Numero de opcion invalido!");
                    break;
            }
        }
    }

    private void searchBookByTitle() {
        System.out.print("Ingrese el titulo del libro a buscar: ");
        String titulo = scanner.nextLine();
        Optional<DatosBook> searchedBook = GetDataFromApi.getBook(titulo);
        if(searchedBook.isEmpty()){
            System.out.println("No se ha encontrado un libro llamado: " + titulo);
            return;
        }
        Book book = new Book(searchedBook.get());
        System.out.println("Tu libro buscado: ");
        System.out.println(book);

        boolean saveBook = bookController.saveBook(book);
        if(saveBook) System.out.println("Se ha guardado el libro: " + book.getTitle() + " en la base de datos");

    }

    private void listRegisteredBoooks() {
        List<Book> registeredBooks = bookController.getBooks();
        System.out.println("\n---------------- LIBROS REGISTRADOS ----------------\n");
        registeredBooks.forEach(b -> System.out.println(b + "\n--------------------------------------------------------------------"));
    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorController.getAuthors();
        System.out.println("\n----------- AUTORES REGISTRADOS -----------\n");
        authors.forEach(a -> System.out.println(a + a.printBooks()+ "\n--------------------------------------------------------------------"));
    }

    private void listAuthorsByYear() {
        System.out.print("Digite el año a buscar autores vivos: ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        List<Author> authors = authorController.findByYear(anio);
        if(authors.isEmpty()) {
            System.out.printf("No se han encontrado autores en el año %d%n", anio);
            return;
        }
        System.out.printf("%n----------- Los autores vivos en el año %d son: -----------%n%n", anio);
        authors.forEach(a -> System.out.println(a + "\n"));

    }

    private void listBooksByLanguage() {
        System.out.println("Selecciona el lenguaje a buscar: \n");
        for (Languages language : Languages.values()){
            System.out.println(language + " - " + language.getName());
        }
        String languageIn = scanner.nextLine();
        try{
            Languages language = Languages.getFromString(languageIn);
            List<Book> books = bookController.getBookByLanguage(language);
            if(books.isEmpty()){
                System.out.println("No se encontraron libros en el idioma " + language.getName());
                return;
            }
            System.out.println("\nLibros en el idioma " + language.getName() + "\n");
            books.forEach(b -> System.out.println(b + "\n---------------------------------------------------------------------"));
        }catch (IllegalArgumentException e ){
            System.out.println("\n" + e.getMessage());
        }
    }
}
