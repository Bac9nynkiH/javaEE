package com.example.dz2.Entity;

import com.example.dz2.Enum.AppUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString
@Table(name="app_user")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public AppUser(AppUserRole role, String username, String password) {
        this.role = role;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @ManyToMany
    @JoinTable(
            name = "book_liked",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public void removeLikedBook(String isbn) {
        Book bookToRemove = new Book();
        bookToRemove.setIsbn(isbn);
        books.remove(bookToRemove);
    }

    public void addLikedBook(Book book) {
        books.add(book);
    }
}
