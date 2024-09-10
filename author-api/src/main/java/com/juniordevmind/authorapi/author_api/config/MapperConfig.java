package com.juniordevmind.authorapi.author_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
  @Bean
  ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // DTOとテーブルの定義が同じ名前なら自動で入ってくれる名前が違う場合にはここでいれる。
    // 今回は同じなので省略する。

    return modelMapper;
  }

}