package com.juniordevmind.bookapi.book_api.models;

import com.juniordevmind.shared.models.EntityBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Author extends EntityBase {
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "description", nullable = false)
  private String description;
}