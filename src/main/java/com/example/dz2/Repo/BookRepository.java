package com.example.dz2.Repo;

import com.example.dz2.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Book getByIsbn(String isbn);
    List<Book> getByTitle(String title);
    List<Book> getByAuthor(String author);

}
