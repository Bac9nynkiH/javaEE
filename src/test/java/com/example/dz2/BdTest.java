package com.example.dz2;

import com.example.dz2.Dto.BookDto;
import com.example.dz2.Entity.Book;
import com.example.dz2.Exception.AlreadyExistsException;
import com.example.dz2.Service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Dz2Application.class)
public class BdTest {
    @SpyBean
    private BookService bookService;
    @Test
    @Sql(value = "/sql/init-bd-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void test(){
        assert bookService.findByTitle("title1").size()==2;
        assert bookService.findByAuthor("author2").size()==2;
        assert bookService.findByIsbn("1").equals(new Book("1","title1","author1"));
        BookDto bookDto = new BookDto("title4","5","author4");
        bookService.createBook(bookDto);
        assertEquals(bookService.findAllBooks().size(),5);
        assert bookService.findByIsbn("5").equals(Book.of(bookDto));
        assertThrows(AlreadyExistsException.class,()->bookService.createBook(new BookDto("title4","5","author4")));
        assert  bookService.findByIsbn("1000000")==null;
    }
}
