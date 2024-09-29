package com.juniordevmind.bookapi.book_api.dtos;

import java.util.List;

import com.juniordevmind.shared.models.DtoBase;

// import com.juniordevmind.shared.models.DtoBase;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;
// import lombok.experimental.SuperBuilder;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @SuperBuilder
// @EqualsAndHashCode(callSuper = false)
// public class BookDto extends DtoBase {
//   private String title;
//   private String description;
// }

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class BookDto extends DtoBase {
    // private String id;// UUIDを文字列として扱う事で、データに書き込む場合の型も変更テーブルへの前
    private String title;
    private String description;
    private List<AuthorDto> authors;
    private List<String> authorIds;
}