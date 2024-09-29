package com.juniordevmind.bookapi.book_api.listeners;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.juniordevmind.bookapi.book_api.config.RabbitMQConfig;
import com.juniordevmind.bookapi.book_api.mappers.AuthorMapper;
import com.juniordevmind.bookapi.book_api.models.Author;
import com.juniordevmind.bookapi.book_api.repositories.AuthorRepository;
import com.juniordevmind.shared.domain.AuthorEventDto;
import com.juniordevmind.shared.models.CustomMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthorCreatedEventListener {

    private final AuthorRepository _authorRepository;
    private final AuthorMapper _authorMapper;

    @RabbitListener(queues = { RabbitMQConfig.QUEUE_AUTHOR_CREATED })
    public void handleMessage(CustomMessage<AuthorEventDto> message) {
        log.info("{} got triggered. got a message: {}", AuthorCreatedEventListener.class, message.toString());

        AuthorEventDto authorEventDto = message.getPayload();

        Optional<Author> existingAuthor = _authorRepository.findById(authorEventDto.getId());
        if (existingAuthor.isPresent()) {
            return;
        }
        Author newAuthor = _authorMapper.toEntity(authorEventDto);
        _authorRepository.save(newAuthor);
    }

}