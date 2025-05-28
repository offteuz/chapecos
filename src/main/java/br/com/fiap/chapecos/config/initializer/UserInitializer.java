package br.com.fiap.chapecos.config.initializer;

import br.com.fiap.chapecos.model.Address;
import br.com.fiap.chapecos.model.Audit;
import br.com.fiap.chapecos.model.Role;
import br.com.fiap.chapecos.model.User;
import br.com.fiap.chapecos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserInitializer implements CommandLineRunner {

    public final UserRepository userRepository;

    public PasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User user = new User();

            user.setAudit(new Audit());
            user.setEmail("admin@chapecos.com");
            user.setUserName("Admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setRole(Role.ADMIN);

            Address address = new Address();
            address.setPostalCode("36500-067");
            address.setStreet("Avenida Raul Soares");
            address.setNumber("96");
            address.setNeighborhood("Centro");
            address.setCity("Ubá");
            address.setState("MG");
            address.setCountry("Brasil");

            user.setAddress(address);
            userRepository.save(user);
        }
    }
}
