package com.example.dz2.Service;

import com.example.dz2.Dto.BookDto;
import com.example.dz2.Entity.Book;
import com.example.dz2.Exception.AlreadyExistsException;
import com.example.dz2.Repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book createBook(BookDto bookDto) {
            return bookRepository.save(Book.of(bookDto));
    }

    @Transactional
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book findByIsbn(String isbn){
        return bookRepository.getByIsbn(isbn);
    }

    @Transactional
    public List<Book> findByAuthor(String author){
        return bookRepository.getByAuthor(author);
    }

    @Transactional
    public List<Book> findByTitle(String title){
        return bookRepository.getByTitle(title);
    }
}
