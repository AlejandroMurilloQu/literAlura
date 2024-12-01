package com.alura.literAlura.book;

import java.util.List;

public record BooksApiResponse(Long count, String next, String previous, List<DatosBook> results) {
}
