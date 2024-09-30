package com.juniordevmind.shared.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public class EntityBase {

  @Id
  @Column(name = "id", updatable = false, nullable = false, length = 36)
  private String id; // UUIDを文字列として扱う 修正

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  // @PrePersist
  // protected void prePersist() {
  // if (Objects.isNull(this.id))
  // this.id = UUID.randomUUID();
  // if (Objects.isNull(this.createdAt))
  // this.createdAt = LocalDateTime.now();
  // if (Objects.isNull(this.updatedAt))
  // this.updatedAt = LocalDateTime.now();
  // }

  @PrePersist
  protected void prePersist() {
    if (this.id == null) {
      this.id = UUID.randomUUID().toString();
      System.err.println(this.id);
    }
    if (Objects.isNull(this.createdAt))
      this.createdAt = LocalDateTime.now();

    if (Objects.isNull(this.updatedAt))
      this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

 
}