package com.juniordevmind.authorapi.author_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juniordevmind.authorapi.author_api.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
  // List<Book> findAllByAuthors(String authorId);
  // List<Book> findAllByAuthors(String authorId);

  // List<Book> findAllByAuthorsContaining(String authorId);

  // 方法1: Contains を使用
}