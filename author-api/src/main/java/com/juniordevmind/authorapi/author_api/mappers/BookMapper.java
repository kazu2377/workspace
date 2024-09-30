package com.juniordevmind.authorapi.author_api.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.juniordevmind.authorapi.author_api.dtos.BookDto;
import com.juniordevmind.authorapi.author_api.models.Book;
import com.juniordevmind.shared.domain.AuthorEventDto;
import com.juniordevmind.shared.domain.BookEventDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final ModelMapper _modelMapper;

    public BookDto toDto(Book book) {
        return _modelMapper.map(book, BookDto.class);
    }

    public Book toEntity(BookEventDto eventDto) {
        return _modelMapper.map(eventDto, Book.class);
    }
}
