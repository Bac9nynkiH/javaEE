package com.example.dz2.Enum;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserRole {
    USER(new HashSet<AppUserPermission>());

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    private final Set<AppUserPermission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> permissionsSet=permissions.stream().map(permissions->new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
        permissionsSet.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissionsSet;
    }
}
