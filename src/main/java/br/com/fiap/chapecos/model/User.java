package br.com.fiap.chapecos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(name = "user_name",unique = true)
    private String userName;

    private String password;

    @Embedded
    @Valid
    private Address address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private Role role;

    @Embedded
    @Valid
    private Audit audit;
}
