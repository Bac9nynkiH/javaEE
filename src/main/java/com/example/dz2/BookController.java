package com.example.dz2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {
    private static List<Book> books= new ArrayList();

    @GetMapping("/book-list")
    public String bookList(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("books",books);
        return "addbook";
    }

    @PostMapping("/book-list")
    public String addNewBook(Book book){
        books.add(book);
        return "redirect:book-list";
    }
}
