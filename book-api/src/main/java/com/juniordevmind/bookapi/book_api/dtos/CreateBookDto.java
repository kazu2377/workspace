package com.juniordevmind.bookapi.book_api.dtos;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateBookDto {
  @NotNull
  @Length(max = 50)
  private String title;
  @NotNull
  @Length(max = 255)
  private String description;

  private List<String> authors;
}
