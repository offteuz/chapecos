package br.com.fiap.chapecos.repository;

import br.com.fiap.chapecos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByEmail(String email);

    boolean existsUserByUserName(String userName);
}
