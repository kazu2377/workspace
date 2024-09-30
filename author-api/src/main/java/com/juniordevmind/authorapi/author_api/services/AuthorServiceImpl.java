package com.juniordevmind.authorapi.author_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.juniordevmind.authorapi.author_api.dtos.AuthorDto;
import com.juniordevmind.authorapi.author_api.dtos.CreateAuthorDto;
import com.juniordevmind.authorapi.author_api.dtos.UpdateAuthorDto;
import com.juniordevmind.authorapi.author_api.mappers.AuthorMapper;
import com.juniordevmind.authorapi.author_api.models.Author;
import com.juniordevmind.authorapi.author_api.repositories.AuthorRepository;
import com.juniordevmind.shared.constants.RabbitMQKeys;
import com.juniordevmind.shared.domain.AuthorEventDto;
import com.juniordevmind.shared.errors.NotFoundException;
import com.juniordevmind.shared.models.CustomMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository _authorRepository;
  private final AuthorMapper _authorMapper;
  private final RabbitTemplate _template;

  @Override
  public List<AuthorDto> getAuthors() {
    // List<Author> authors = _authorRepository.findAll();

    // 修正１
    // List<AuthorDto> authorDtos = authors.stream().map(authorItem -> new
    // AuthorDto(
    // authorItem.getId(),
    // authorItem.getName(),
    // authorItem.getDescription())).toList();

    // 修正２
    // List<AuthorDto> authorDtos = authors.stream().map(

    // authorItem -> (AuthorDto)
    // AuthorDto.builder().id(authorItem.getId()).name(authorItem.getName())
    // .description(authorItem.getDescription())
    // .createdAt(authorItem.getCreatedAt())
    // .updatedAt(authorItem.getUpdatedAt())
    // .build())
    // .toList();

    // 修正３
    List<AuthorDto> authors = _authorRepository.findAll().stream().map(author -> _authorMapper.toDto(author)).toList();
    System.out.println("Authors List: " + authors);

    return _authorRepository.findAll().stream().map(author -> _authorMapper.toDto(author)).toList();

    // return authorDtos;
  }

  @Override
  public AuthorDto getAuthor(String id) {

    return _authorMapper.toDto(_findAuthorById(id));

    // 修正２
    // Author author = _findAuthorById(id);

    // return AuthorDto.builder().id(author.getId())
    // .name(author.getName())
    // .description(author.getDescription())
    // .createdAt(author.getCreatedAt())
    // .updatedAt(author.getUpdatedAt())
    // .build();

    // 修正１
    // return new AuthorDto(
    // author.getId(),
    // author.getName(),
    // author.getDescription());
  }

  @Override
  public AuthorDto createAuthor(CreateAuthorDto dto) {

    // Author savedAuthor = _authorRepository.save(new Author(dto.getName(),
    // dto.getDescription()));
    // _template.convertAndSend(RabbitMQKeys.AUTHOR_CREATED_EXCHANGE, null,
    // savedAuthor);
    // return _authorMapper.toDto(savedAuthor);

    // return _authorMapper.toDto(_authorRepository.save(new Author(dto.getName(),
    // dto.getDescription())));

    // Author savedAuthor = _authorRepository.save(new Author(dto.getName(),
    // dto.getDescription()));

    // CustomMessage<AuthorEventDto> message = new CustomMessage<>();
    // message.setMessageId(UUID.randomUUID());
    // message.setMessageDate(LocalDateTime.now());
    // message.setPayload(_authorMapper.toEventDto(savedAuthor));

    // _template.convertAndSend(RabbitMQKeys.AUTHOR_CREATED_EXCHANGE, null,
    // savedAuthor);
    // return _authorMapper.toDto(savedAuthor);

    Author savedAuthor = _authorRepository.save(new Author(dto.getName(),
        dto.getDescription()));
    CustomMessage<AuthorEventDto> message = new CustomMessage<>();
    message.setMessageId(UUID.randomUUID());
    message.setMessageDate(LocalDateTime.now());
    message.setPayload(_authorMapper.toEventDto(savedAuthor));
    _template.convertAndSend(RabbitMQKeys.AUTHOR_CREATED_EXCHANGE, null,
        message);
    return _authorMapper.toDto(savedAuthor);

    // 修正前
    // Author newAuthor = new Author();
    // newAuthor.setName(dto.getName());
    // newAuthor.setDescription(dto.getDescription());
    // Author savedAuthor = _authorRepository.save(newAuthor);
    // return AuthorDto.builder().id(savedAuthor.getId())
    // .name(savedAuthor.getName())
    // .description(savedAuthor.getDescription())
    // .createdAt(savedAuthor.getCreatedAt())
    // .updatedAt(savedAuthor.getUpdatedAt())
    // .build();

  }

  @Override
  public void deleteAuthor(String id) {
    Author author = _findAuthorById(id);
    _authorRepository.delete(author);

    CustomMessage<AuthorEventDto> message = new CustomMessage<>();
    message.setMessageId(UUID.randomUUID());
    message.setMessageDate(LocalDateTime.now());
    message.setPayload(_authorMapper.toEventDto(author));

    _template.convertAndSend(RabbitMQKeys.AUTHOR_DELETED_EXCHANGE, null, message);

  }

  @Override
  public void updateAuthor(UpdateAuthorDto dto, String id) {
    Author existingAuthor = _findAuthorById(id);

    if (Objects.nonNull(dto.getName())) {
      existingAuthor.setName(dto.getName());
    }

    if (Objects.nonNull(dto.getDescription())) {
      existingAuthor.setDescription(dto.getDescription());
    }

    _authorRepository.save(existingAuthor);
    CustomMessage<AuthorEventDto> message = new CustomMessage<>();
    message.setMessageId(UUID.randomUUID());
    message.setMessageDate(LocalDateTime.now());
    message.setPayload(_authorMapper.toEventDto(existingAuthor));
    _template.convertAndSend(RabbitMQKeys.AUTHOR_UPDATED_EXCHANGE, null, message);

  }

  private Author _findAuthorById(String id) {
    Optional<Author> result = _authorRepository.findById(id);

    if (result.isEmpty()) {
      throw new NotFoundException("Not found with this ID: " + id);
    }

    return result.get();
  }

}