package com.juniordevmind.authorapi.author_api.dtos;

// import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
}