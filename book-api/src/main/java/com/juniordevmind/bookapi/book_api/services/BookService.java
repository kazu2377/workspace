package com.juniordevmind.bookapi.book_api.services;

import java.util.List;

import com.juniordevmind.bookapi.book_api.dtos.BookDto;
import com.juniordevmind.bookapi.book_api.dtos.CreateBookDto;
import com.juniordevmind.bookapi.book_api.dtos.UpdateBookDto;

public interface BookService {
  public List<BookDto> getBooks();

  // public AuthorDto getAuthor(UUID id);

  // public AuthorDto createAuthor(CreateAuthorDto dto);

  // public void deleteAuthor(UUID id);

  // public void updateAuthor(UpdateAuthorDto dto, UUID id);

  public BookDto getBook(String id);

  public BookDto createBook(CreateBookDto dto);

  public void deleteBook(String id);

  public void updateBook(UpdateBookDto dto, String id);

}
