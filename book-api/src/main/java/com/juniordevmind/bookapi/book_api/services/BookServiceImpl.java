package com.juniordevmind.bookapi.book_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.juniordevmind.bookapi.book_api.dtos.AuthorDto;
import com.juniordevmind.bookapi.book_api.dtos.BookDto;
import com.juniordevmind.bookapi.book_api.dtos.CreateBookDto;
import com.juniordevmind.bookapi.book_api.dtos.UpdateBookDto;
import com.juniordevmind.bookapi.book_api.mappers.AuthorMapper;
import com.juniordevmind.bookapi.book_api.mappers.BookMapper;
import com.juniordevmind.bookapi.book_api.models.Author;
import com.juniordevmind.bookapi.book_api.models.Book;
import com.juniordevmind.bookapi.book_api.repositories.AuthorRepository;
import com.juniordevmind.bookapi.book_api.repositories.BookRepository;
import com.juniordevmind.shared.constants.RabbitMQKeys;
import com.juniordevmind.shared.domain.BookEventDto;
import com.juniordevmind.shared.errors.NotFoundException;
import com.juniordevmind.shared.models.CustomMessage;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
  // private final BookRepository _bookRepository;
  // private final BookMapper _bookMapper;

  private final BookRepository _bookRepository;
  private final AuthorRepository _authorRepository;
  private final BookMapper _bookMapper;
  private final AuthorMapper _authorMapper;
  private final RabbitTemplate _template;

  @Override
  public List<BookDto> getBooks() {
    return _bookRepository.findAll().stream().map(bookItem -> _bookMapper.toDto(bookItem)).toList();

    // List<Book> books = _bookRepository.findAll();

    // List<BookDto> bookDtos = books.stream().map(

    // bookItem -> (BookDto)
    // BookDto.builder().id(bookItem.getId()).title(bookItem.getTitle())
    // .description(bookItem.getDescription())
    // .createdAt(bookItem.getCreatedAt())
    // .updatedAt(bookItem.getUpdatedAt())
    // .build()

    // ).toList();

    // List<BookDto> bookDtos = books.stream().map(
    // bookItem -> new BookDto(bookItem.getId(), bookItem.getTitle(),
    // bookItem.getDescription())

    // ).toList();

    // return bookDtos;
  }

  @Override
  public BookDto getBook(String id) {

    Book book = _findBookById(id);
    List<Author> authors = _authorRepository.findAllById(book.getAuthors());
    List<AuthorDto> authorDtos = authors.stream().map(author -> _authorMapper.toDto(author)).toList();
    BookDto bookDto = _bookMapper.toDto(book);
    bookDto.setAuthors(authorDtos);
    return bookDto;

    // return _bookMapper.toDto(_findBookById(id));

    // Book book = _findBookById(id);

    // return new BookDto(
    // book.getId(),
    // book.getTitle(),
    // book.getDescription());

    // return BookDto.builder()
    // .id(book.getId())
    // .title(book.getTitle())
    // .description(book.getDescription())
    // .createdAt(book.getCreatedAt())
    // .updatedAt(book.getUpdatedAt())
    // .build();
  }

  @Override
  public BookDto createBook(CreateBookDto dto) {
    // return _bookMapper.toDto(_bookRepository.save(new Book(dto.getTitle(),
    // dto.getDescription())));
    // return _bookMapper.toDto(_bookRepository.save(new Book(dto.getTitle(),
    // dto.getDescription(), dto.getAuthors())));

    Book savedBook = _bookRepository.save(new Book(dto.getTitle(), dto.getDescription(), dto.getAuthors()));
    CustomMessage<BookEventDto> message = new CustomMessage<>();
    message.setMessageId(UUID.randomUUID());
    message.setMessageDate(LocalDateTime.now());
    message.setPayload(_bookMapper.toEventDto(savedBook));
    _template.convertAndSend(RabbitMQKeys.BOOK_CREATED_EXCHANGE, null, message);
    return _bookMapper.toDto(savedBook);

    // Book newBook = new Book();
    // newBook.setTitle(dto.getTitle());
    // newBook.setDescription(dto.getDescription());
    // Book savedBook = _bookRepository.save(newBook);
    // return BookDto.builder()
    // .id(savedBook.getId())
    // .title(savedBook.getTitle())
    // .description(savedBook.getDescription())
    // .createdAt(savedBook.getCreatedAt())
    // .updatedAt(savedBook.getUpdatedAt())
    // .build();

    // return new BookDto(
    // savedBook.getId(),
    // savedBook.getTitle(),
    // savedBook.getDescription());

  }

  @Override
  public void deleteBook(String id) {
    Book book = _findBookById(id);
    _bookRepository.delete(book);
    CustomMessage<BookEventDto> message = new CustomMessage<>();
    message.setMessageId(UUID.randomUUID());
    message.setMessageDate(LocalDateTime.now());
    message.setPayload(_bookMapper.toEventDto(book));
    _template.convertAndSend(RabbitMQKeys.BOOK_DELETED_EXCHANGE, null, message);
    // Book book = _findBookById(id);
    // _bookRepository.delete(book);
  }

  @Override
  public void updateBook(UpdateBookDto dto, String id) {
    Book found = _findBookById(id);

    if (Objects.nonNull(dto.getTitle())) {
      found.setTitle(dto.getTitle());
    }

    if (Objects.nonNull(dto.getDescription())) {
      found.setDescription(dto.getDescription());
    }

    if (Objects.nonNull(dto.getAuthors())) {
      found.setAuthors(dto.getAuthors());
    }

    _bookRepository.save(found);

    CustomMessage<BookEventDto> message = new CustomMessage<>();
    message.setMessageId(UUID.randomUUID());
    message.setMessageDate(LocalDateTime.now());
    message.setPayload(_bookMapper.toEventDto(found));
    _template.convertAndSend(RabbitMQKeys.BOOK_UPDATED_EXCHANGE, null, message);

    // Book found = _findBookById(id);

    // if (Objects.nonNull(dto.getTitle())) {
    // found.setTitle(dto.getTitle());
    // }

    // if (Objects.nonNull(dto.getDescription())) {
    // found.setDescription(dto.getDescription());
    // }

    // _bookRepository.save(found);
    // Book found = _findBookById(id);

    // if (Objects.nonNull(dto.getTitle())) {
    // found.setTitle(dto.getTitle());
    // }

    // if (Objects.nonNull(dto.getDescription())) {
    // found.setDescription(dto.getDescription());
    // }

    // _bookRepository.save(found);
  }

  private Book _findBookById(String id) {
    Optional<Book> result = _bookRepository.findById(id);

    if (result.isEmpty()) {
      throw new NotFoundException("Not found with this ID: " + id);
    }

    return result.get();
  }
}
