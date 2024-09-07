package com.juniordevmind.authorapi.author_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private String id;// UUIDを文字列として扱う事で、データに書き込む場合の型も変更テーブルへの前
    private String name;
    private String description;
}