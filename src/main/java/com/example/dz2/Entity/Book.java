package com.example.dz2.Entity;

import com.example.dz2.Dto.BookDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private String title;
    private String isbn;

    public static Book of(BookDto bookDto){
        if(bookDto.getIsbn()==null||bookDto.getTitle()==null||bookDto.getIsbn().isEmpty()||bookDto.getTitle().isEmpty())
            throw new IllegalArgumentException("neither isbn bot title could be null");
        return new Book(bookDto.getTitle(),bookDto.getIsbn());
    }
}
