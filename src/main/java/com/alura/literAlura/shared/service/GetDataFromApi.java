package com.alura.literAlura.shared.service;

import com.alura.literAlura.author.DatosAuthor;
import com.alura.literAlura.book.Book;
import com.alura.literAlura.book.BooksApiResponse;
import com.alura.literAlura.book.DatosBook;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

public class GetDataFromApi {

    public static String getData(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        try{
            response = client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }

    public static Optional<DatosBook> getBook(String title){
        String libroQuery = UriEncoder.encode(title);
        String libros = GetDataFromApi.getData("https://gutendex.com/books/?search=" + libroQuery);
        BooksApiResponse response = ConvertData.convert(libros, BooksApiResponse.class);
        if(response.count() == 0) return Optional.empty();
        return Optional.of(response.results().get(0));
    }
}
