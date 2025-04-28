package br.com.fiap.chapecos.repository;

import br.com.fiap.chapecos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUserName(String userName);

    boolean existsUserByEmail(String email);

    boolean existsUserByUserName(String userName);
}
