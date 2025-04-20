package br.com.fiap.chapecos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "T_ROLE")
public class Role {

    @Id
    @SequenceGenerator(sequenceName = "seq_role", name = "seq_role", allocationSize = 1)
    @GeneratedValue(generator = "seq_role", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
