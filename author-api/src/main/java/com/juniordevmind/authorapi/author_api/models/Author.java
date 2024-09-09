package com.juniordevmind.authorapi.author_api.models;

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
public class Author extends EntityBase {
  // @Id
  // @Column(name = "id", updatable = false, nullable = false, length = 36)
  // private String id; // UUIDを文字列として扱う 修正

  @Column(name = "name", nullable = false)
  private String name;

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

// @Entity
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class Author {
// @Id
// // 変更前
// // @Column(name = "id")
// // @Type(type = "uuid-char")
// // private UUID id;

// // 変更後
// @Column(name = "id", columnDefinition = "VARCHAR(36)") // UUIDを文字列として保存する場合
// private UUID id;

// @Column(name = "name", nullable = false)
// private String name;

// @Column(name = "description", nullable = false)
// private String description;

// @PrePersist
// protected void prePersist() {

// if (Objects.isNull(this.id)) {
// this.id = UUID.randomUUID();
// System.err.println(this.id);

// }
// }
// }