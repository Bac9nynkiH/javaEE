package com.example.dz2.Controller;

import com.example.dz2.Dto.BookToChange;
import com.example.dz2.Dto.RegistrationDto;
import com.example.dz2.Entity.AppUser;
import com.example.dz2.Enum.AppUserRole;
import com.example.dz2.Service.AppUserService;
import com.example.dz2.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller("/")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final BookService bookService;
    @GetMapping("/registration")
    public String getRegisterPage(Model model){
        model.addAttribute("registrationDto",new RegistrationDto(AppUserRole.USER));
        return "registration";
    }
    @PostMapping("/registration")
    @ResponseBody
    public AppUser getRegisterPage(RegistrationDto registrationDto){
        return appUserService.registerNewAppUser(registrationDto.buildUser());
    }
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/books/liked")
    public String getLikedBooks(Principal principal, Model model){
        model.addAttribute("likedBooks",appUserService.getLikedBooks(principal.getName()));
        model.addAttribute("allBooks",bookService.findAllBooks());
        model.addAttribute("bookToChange",new BookToChange());
        return "likedbooks";
    }

    @PostMapping("/books/liked")
    public String changedLikedBooks(Principal principal,BookToChange bookToChange){
        if(bookToChange.isDelete())
            appUserService.removeLikedBook(bookToChange,principal.getName());
        else
            appUserService.addLikedBook(bookToChange,principal.getName());
        return "redirect:/books/liked";
    }
}
