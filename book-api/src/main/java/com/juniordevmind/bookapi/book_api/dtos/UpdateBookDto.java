package com.juniordevmind.bookapi.book_api.dtos;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookDto {
  @Length(max = 50)
  private String title;
  @Length(max = 255)
  private String description;

  private List<String> authors;

}
