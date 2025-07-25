package br.com.fiap.chapecos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "T_USER",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_user_user_name", columnNames = "user_name")
        }
)
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "user_name",unique = true)
    private String userName;

    private String password;

    @Embedded
    @Valid
    private Address address;

    private Role role;

    @Embedded
    @Valid
    private Audit audit;

    @PrePersist
    public void onPrePersist() {
        if (this.role == null) {
            this.setRole(Role.USER);
        }
    }

    public User(String email, String userName, String password, Address address) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role.equals(Role.ADMIN)) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
