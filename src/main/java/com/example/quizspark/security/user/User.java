package com.example.quizspark.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Represents a user entity within the QuizSpark application, detailing user information and their role.
 * This class implements the {@link UserDetails} interface provided by Spring Security to integrate with its authentication mechanisms.
 *
 * <p>It uses Lombok annotations for boilerplate code like getters, setters, constructors, and builders.</p>
 *
 * @see UserDetails
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    /**
     * The role of the user, represented as an Enum. This field is crucial for assigning permissions and authorities within the application.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Returns the authorities granted to the user. In this case, it converts the user's role to a {@link GrantedAuthority}.
     *
     * @return a collection of {@link GrantedAuthority} based on the user's role.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Uses the user's email as the username in the context of Spring Security.
     *
     * @return the email of the user, serving as the username.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be authenticated.
     *
     * @return true, indicating the account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
     *
     * @return true, indicating the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired. Expired credentials prevent authentication.
     *
     * @return true, indicating the credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
     *
     * @return true, indicating the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
