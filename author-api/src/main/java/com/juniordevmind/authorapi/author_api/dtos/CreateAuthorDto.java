package com.juniordevmind.authorapi.author_api.dtos;

import java.util.List;

import org.hibernate.validator.constraints.Length;

// import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorDto {
  @NotNull
  @Length(max = 50)
  private String name;
  @NotNull
  @Length(max = 255)
  private String description;

  private List<String> books;

}