package com.juniordevmind.authorapi.author_api.models;


import java.util.List;

import com.juniordevmind.shared.models.EntityBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.juniordevmind.shared.models.EntityBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book extends EntityBase {
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false)
  private String description;
}

// import jakarta.persistence.CollectionTable;
// import jakarta.persistence.Column;
// import jakarta.persistence.ElementCollection;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.JoinColumn;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @EqualsAndHashCode(callSuper = true)
// // @EqualsAndHashCode(callSuper = true)
// public class Book extends EntityBase {
//   // @Id
//   // @Column(name = "id", updatable = false, nullable = false, length = 36)
//   // private String id; // UUIDを文字列として扱う 修正

//   @Column(name = "name", nullable = false)
//   private String title;

//   @Column(name = "description", nullable = false)
//   private String description;

//   @ElementCollection(fetch = FetchType.LAZY)
//   @CollectionTable(name = "book_authors", joinColumns = @JoinColumn(name = "id"))
//   @Column(name = "author_id")
//   private List<String> authors;

//   // @PrePersist
//   // protected void prePersist() {
//   // if (this.id == null) {
//   // this.id = UUID.randomUUID().toString();
//   // System.err.println(this.id);
//   // }
//   // }
// }