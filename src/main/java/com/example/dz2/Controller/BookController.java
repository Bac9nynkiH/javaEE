package com.example.dz2.Controller;

import com.example.dz2.Dto.BookDto;
import com.example.dz2.Entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private static List<Book> books= new ArrayList();

    @GetMapping("/book-list")
    public String bookList(){
        return "addbook";
    }

    @PostMapping("/book-list")
    @ResponseBody
    public Book addNewBook(@RequestBody BookDto book){
        books.add(Book.of(book));
        return Book.of(book);
    }

    @GetMapping("/book-list/get")
    @ResponseBody
    public List<Book> bookListGet(){
        return books;
    }

}
