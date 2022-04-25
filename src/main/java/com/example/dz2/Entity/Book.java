package com.example.dz2.Entity;

import com.example.dz2.Dto.BookDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;

    public static Book of(BookDto bookDto){
        if(bookDto.getIsbn()==null||bookDto.getTitle()==null||bookDto.getIsbn().isEmpty()||bookDto.getTitle().isEmpty())
            throw new IllegalArgumentException("neither isbn bot title could be null");
        return new Book(bookDto.getIsbn(),bookDto.getTitle(),bookDto.getAuthor());
    }
}
