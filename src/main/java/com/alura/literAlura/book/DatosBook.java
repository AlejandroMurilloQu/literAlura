package com.alura.literAlura.book;

import com.alura.literAlura.author.DatosAuthor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBook(Long id, String title, List<DatosAuthor> authors, List<String> languages, Long download_count) {
}
