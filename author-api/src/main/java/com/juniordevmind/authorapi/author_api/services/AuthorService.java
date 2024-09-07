package com.juniordevmind.authorapi.author_api.services;

import java.util.List;

import com.juniordevmind.authorapi.author_api.dtos.AuthorDto;
import com.juniordevmind.authorapi.author_api.dtos.CreateAuthorDto;
import com.juniordevmind.authorapi.author_api.dtos.UpdateAuthorDto;

public interface AuthorService {
  public List<AuthorDto> getAuthors();

  // public AuthorDto getAuthor(UUID id);

  // public AuthorDto createAuthor(CreateAuthorDto dto);

  // public void deleteAuthor(UUID id);

  // public void updateAuthor(UpdateAuthorDto dto, UUID id);

  public AuthorDto getAuthor(String id);

  public AuthorDto createAuthor(CreateAuthorDto dto);

  public void deleteAuthor(String id);

  public void updateAuthor(UpdateAuthorDto dto, String id);

}