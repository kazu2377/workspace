package com.juniordevmind.shared.models;

import java.time.LocalDateTime;
// import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DtoBase {
    // uuidからstringへ変更しました
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
