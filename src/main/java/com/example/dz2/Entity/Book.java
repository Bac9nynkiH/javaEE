package com.example.dz2.Entity;

import com.example.dz2.Dto.BookDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;

    @ManyToMany
    private Set<AppUser> likedBy;

    public static Book of(BookDto bookDto){
        if(bookDto.getIsbn()==null||bookDto.getTitle()==null||bookDto.getIsbn().isEmpty()||bookDto.getTitle().isEmpty())
            throw new IllegalArgumentException("neither isbn bot title could be null");
        return new Book(bookDto.getIsbn(),bookDto.getTitle(),bookDto.getAuthor(), new HashSet<>());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
