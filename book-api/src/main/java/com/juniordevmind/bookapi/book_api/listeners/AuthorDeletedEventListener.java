package com.juniordevmind.bookapi.book_api.listeners;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.juniordevmind.bookapi.book_api.config.RabbitMQConfig;
import com.juniordevmind.bookapi.book_api.models.Author;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthorDeletedEventListener  {

    @RabbitListener(queues = { RabbitMQConfig.QUEUE_AUTHOR_DELETED  })
    public void handleMessage(Author message) {
        log.info("{} got triggered. got a message: {}", AuthorDeletedEventListener.class, message.toString());
    }
}