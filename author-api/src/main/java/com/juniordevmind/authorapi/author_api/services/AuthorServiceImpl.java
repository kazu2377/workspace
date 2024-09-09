package com.juniordevmind.authorapi.author_api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.juniordevmind.authorapi.author_api.dtos.AuthorDto;
import com.juniordevmind.authorapi.author_api.dtos.CreateAuthorDto;
import com.juniordevmind.authorapi.author_api.dtos.UpdateAuthorDto;
import com.juniordevmind.authorapi.author_api.models.Author;
import com.juniordevmind.authorapi.author_api.repositories.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
  private final AuthorRepository _authorRepository;

  @Override
  public List<AuthorDto> getAuthors() {
    List<Author> authors = _authorRepository.findAll();
    // List<AuthorDto> authorDtos = authors.stream().map(authorItem -> new
    // AuthorDto(
    // authorItem.getId(),
    // authorItem.getName(),
    // authorItem.getDescription())).toList();

    List<AuthorDto> authorDtos = authors.stream().map(

        authorItem -> (AuthorDto) AuthorDto.builder().id(authorItem.getId()).name(authorItem.getName())
            .description(authorItem.getDescription())
            .createdAt(authorItem.getCreatedAt())
            .updatedAt(authorItem.getUpdatedAt())
            .build())
        .toList();

    return authorDtos;
  }

  @Override
  public AuthorDto getAuthor(String id) {
    Author author = _findAuthorById(id);

    return AuthorDto.builder().id(author.getId())
        .name(author.getName())
        .description(author.getDescription())
        .createdAt(author.getCreatedAt())
        .updatedAt(author.getUpdatedAt())
        .build();
    // return new AuthorDto(
    // author.getId(),
    // author.getName(),
    // author.getDescription());
  }

  @Override
  public AuthorDto createAuthor(CreateAuthorDto dto) {
    Author newAuthor = new Author();
    newAuthor.setName(dto.getName());
    newAuthor.setDescription(dto.getDescription());
    Author savedAuthor = _authorRepository.save(newAuthor);
    return AuthorDto.builder().id(savedAuthor.getId())
        .name(savedAuthor.getName())
        .description(savedAuthor.getDescription())
        .createdAt(savedAuthor.getCreatedAt())
        .updatedAt(savedAuthor.getUpdatedAt())
        .build();

  }

  @Override
  public void deleteAuthor(String id) {
    Author author = _findAuthorById(id);
    _authorRepository.delete(author);
  }

  @Override
  public void updateAuthor(UpdateAuthorDto dto, String id) {
    Author found = _findAuthorById(id);

    if (Objects.nonNull(dto.getName())) {
      found.setName(dto.getName());
    }

    if (Objects.nonNull(dto.getDescription())) {
      found.setDescription(dto.getDescription());
    }

    _authorRepository.save(found);
  }

  private Author _findAuthorById(String id) {
    Optional<Author> result = _authorRepository.findById(id);

    if (result.isEmpty()) {
      throw new RuntimeException("Not found with this ID: " + id);
    }

    return result.get();
  }

}