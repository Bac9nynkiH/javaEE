package com.example.dz2.Dto;

import com.example.dz2.Entity.AppUser;
import com.example.dz2.Enum.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private  String username;
    private  String password;

    public RegistrationDto(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

    private  AppUserRole appUserRole;

    public AppUser buildUser(){
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(password);
        appUser.setRole(appUserRole);
        appUser.setBooks(new HashSet<>());
        return appUser;
    }

}