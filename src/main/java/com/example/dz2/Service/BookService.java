package com.example.dz2.Service;

import com.example.dz2.Dto.BookDto;
import com.example.dz2.Entity.Book;
import com.example.dz2.Exception.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final EntityManager entityManager;

    @Transactional
    public Book createBook(BookDto bookDto) {
        if(findByIsbn(bookDto.getIsbn())==null)
            return entityManager.merge(Book.of(bookDto));
        else
            throw new AlreadyExistsException("already exists");
    }

    @Transactional
    public List<Book> findAllBooks(){
        return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Transactional
    public Book findByIsbn(String isbn){
        return entityManager.find(Book.class, isbn);
    }

    @Transactional
    public List<Book> findByAuthor(String author){
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Transactional
    public List<Book> findByTitle(String title){
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class)
                .setParameter("title", title)
                .getResultList();
    }
}
