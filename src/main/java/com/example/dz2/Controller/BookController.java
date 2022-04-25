package com.example.dz2.Controller;

import com.example.dz2.Dto.BookDto;
import com.example.dz2.Entity.Book;
import com.example.dz2.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book-list")
    public String bookList(){
        return "addbook";
    }

    @GetMapping("/book-list/{isbn}")
    @ResponseBody
    public Book getBookByIsbn(@PathVariable String isbn){
        return bookService.findByIsbn(isbn);
    }

    @GetMapping("/book-list/{author}")
    @ResponseBody
    public List<Book> getBooksByAuthor(@PathVariable String author){
        return bookService.findByAuthor(author);
    }

    @GetMapping("/book-list/{title}")
    @ResponseBody
    public List<Book> getBooksByTitle(@PathVariable String title){
        return bookService.findByTitle(title);
    }

    @PostMapping("/book-list")
    @ResponseBody
    public Book addNewBook(@RequestBody BookDto book){
            return bookService.createBook(book);
    }

    @GetMapping("/book-list/get")
    @ResponseBody
    public List<Book> bookListGet(){
        return bookService.findAllBooks();
    }

}
