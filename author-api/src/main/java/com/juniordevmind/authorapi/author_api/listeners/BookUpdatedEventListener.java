package com.juniordevmind.authorapi.author_api.listeners;
import java.util.Optional;
import java.util.List;

import com.juniordevmind.authorapi.author_api.config.RabbitMQConfig;
import com.juniordevmind.authorapi.author_api.models.Author;
import com.juniordevmind.authorapi.author_api.models.Book;
import com.juniordevmind.authorapi.author_api.repositories.AuthorRepository;
import com.juniordevmind.authorapi.author_api.repositories.BookRepository;
import com.juniordevmind.shared.domain.BookEventDto;
import com.juniordevmind.shared.models.CustomMessage;

import org.hibernate.validator.constraints.UUID;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookUpdatedEventListener {
    private final BookRepository _bookRepository;
    private final AuthorRepository _authorRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_BOOK_UPDATED)
    public void handleMessage(CustomMessage<BookEventDto> message) {
        log.info("{} got triggered. Message: {}", BookUpdatedEventListener.class, message.toString());
        BookEventDto bookEventDto = message.getPayload();
        Optional<Book> maybeExistingBook = _bookRepository.findById(bookEventDto.getId());
        if (maybeExistingBook.isEmpty()) {
            return;
        }
        Book existingBook = maybeExistingBook.get();
        existingBook.setTitle(bookEventDto.getTitle());
        existingBook.setDescription(bookEventDto.getDescription());

        List<Author> oldAuthors = _authorRepository.findAllByBooks(bookEventDto.getId());
        List<String> newAuthorIds = bookEventDto.getAuthors();
        List<Author> newAuthors = _authorRepository.findAllById(newAuthorIds);

        for (Author newAuthorItem : newAuthors) {
            if (!newAuthorItem.getBooks().contains(bookEventDto.getId())) {
                newAuthorItem.getBooks().add(bookEventDto.getId());
            }
        }

        for (Author oldAuthorItem : oldAuthors) {
            if (!newAuthorIds.contains(oldAuthorItem.getId())) {
                oldAuthorItem.getBooks().remove(bookEventDto.getId());
            }
        }
    }
}