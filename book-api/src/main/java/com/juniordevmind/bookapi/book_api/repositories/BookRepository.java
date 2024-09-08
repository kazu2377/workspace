package com.juniordevmind.bookapi.book_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juniordevmind.bookapi.book_api.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}