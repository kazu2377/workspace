package com.juniordevmind.bookapi.book_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juniordevmind.bookapi.book_api.models.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

}
