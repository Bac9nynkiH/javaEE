package com.example.dz2;

import com.example.dz2.Service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = Dz2Application.class)
public class BdTest {
    @SpyBean
    private BookService bookService;
    @Test
    @Sql(value = "/com/example/dz2/init-bd-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void test(){
        System.out.println(bookService.findByAuthor("author1"));
    }
}
