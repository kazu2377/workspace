package com.juniordevmind.shared.models;

import java.time.LocalDateTime;
import java.util.UUID;


import lombok.Data;

@Data
public class CustomMessage<T> {
  private UUID messageId;

  // @Id
  // @Column(name = "id", updatable = false, nullable = false, length = 36)
  // private String id; // UUIDを文字列として扱う 修正

  private LocalDateTime messageDate;
  private T payload;
}
