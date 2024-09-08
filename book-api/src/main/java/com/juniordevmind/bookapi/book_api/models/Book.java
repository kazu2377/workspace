package com.juniordevmind.bookapi.book_api.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id
  @Column(name = "id", updatable = false, nullable = false, length = 36)
  private String id; // UUIDを文字列として扱う 修正

  @Column(name = "name", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @PrePersist
  protected void prePersist() {
    if (this.id == null) {
      this.id = UUID.randomUUID().toString();
      System.err.println(this.id);
    }
  }
}