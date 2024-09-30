package com.juniordevmind.bookapi.book_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juniordevmind.bookapi.book_api.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
  // List<Book> findAllByAuthors(String authorId);
  List<Book> findAllByAuthors(String authorId);

  // List<Book> findAllByAuthorsContaining(String authorId);

  // 方法1: Contains を使用
}