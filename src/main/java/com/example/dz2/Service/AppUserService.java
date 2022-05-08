package com.example.dz2.Service;

import com.example.dz2.Dto.BookToChange;
import com.example.dz2.Entity.AppUser;
import com.example.dz2.Entity.Book;
import com.example.dz2.Repo.AppUserRepository;
import com.example.dz2.Repo.BookRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final CustomPasswordEncoder customPasswordEncoder;

    private final BookRepository bookRepository;
    private final AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(""));
    }

    public AppUser registerNewAppUser(AppUser appUser){
        String password=customPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(password);
        AppUser appUser1 = appUserRepository.save(appUser);
        System.out.println(appUser1);
        return appUser1;
    }

    public Set<Book> getLikedBooks(String username){
        if(!appUserRepository.findByUsername(username).isPresent())
            throw new UsernameNotFoundException("user not found");

        return appUserRepository.findByUsername(username).get().getBooks();
    }

    public void removeLikedBook(BookToChange bookToChange, String username) {
        Optional<AppUser> thisUser = appUserRepository.findByUsername(username);
        if(!thisUser.isPresent())
            throw new UsernameNotFoundException("user not found");
        AppUser user = thisUser.get();
        user.removeLikedBook(bookToChange.getIsbn());
        appUserRepository.save(user);
    }

    public void addLikedBook(BookToChange bookToChange, String username) {
        Optional<AppUser> thisUser = appUserRepository.findByUsername(username);
        if(!thisUser.isPresent())
            throw new UsernameNotFoundException("user not found");
        if(bookRepository.existsById(bookToChange.getIsbn())) {
            AppUser user = thisUser.get();
            user.addLikedBook(bookRepository.getByIsbn(bookToChange.getIsbn()));
            appUserRepository.save(user);
        }
    }
}
