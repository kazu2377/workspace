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

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookDeletedEventListener {
    private final BookRepository _bookRepository;
    private final AuthorRepository _authorRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_BOOK_DELETED)
    public void handleMessage(CustomMessage<BookEventDto> message) {
        log.info("{} got triggered. Message: {}", BookDeletedEventListener.class, message.toString());
        BookEventDto bookEventDto = message.getPayload();
        Optional<Book> result = _bookRepository.findById(bookEventDto.getId());
        if (result.isEmpty()) {
            return;
        }

        Book book = result.get();
        _bookRepository.delete(book);

        List<Author> authors = _authorRepository.findAllByBooks(bookEventDto.getId());

        for (Author authorItem : authors) {
            authorItem.getBooks().remove(bookEventDto.getId());
        }
    }
}