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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @EqualsAndHashCode(callSuper = true)
public class Book extends EntityBase {
  // @Id
  // @Column(name = "id", updatable = false, nullable = false, length = 36)
  // private String id; // UUIDを文字列として扱う 修正

  @Column(name = "name", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  // @PrePersist
  // protected void prePersist() {
  // if (this.id == null) {
  // this.id = UUID.randomUUID().toString();
  // System.err.println(this.id);
  // }
  // }
}