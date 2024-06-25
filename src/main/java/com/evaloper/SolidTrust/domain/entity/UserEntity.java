package com.evaloper.SolidTrust.domain.entity;

import com.evaloper.SolidTrust.domain.enums.Role;
import com.evaloper.SolidTrust.validations.ValidEmail;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_tbl")
public class UserEntity extends BaseClass implements UserDetails{
    private String firstName;
    private String lastName;
    private String otherName;

    private String gender;
    private String address;
    private String stateOfOrigin;
    private String accountNumber;

    // we are using big decimal because of accuracy when it comes to currency
    private BigDecimal accountBalance;
    private String phoneNumber;

    private String alternativeNumber;

    private String profilePicture;

    @ValidEmail
    @Email(message = "Invalid Email")
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}

