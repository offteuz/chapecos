package br.com.fiap.chapecos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_MENU")
@EntityListeners(AuditingEntityListener.class)
public class Menu {

    @Id
    @SequenceGenerator(name = "seq_menu", sequenceName = "seq_menu", allocationSize = 1)
    @GeneratedValue(generator = "seq_menu")
    @Column(name = "id_menu")
    private Long id;

    private MenuType menuType;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Item> items = new HashSet<>();

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id", referencedColumnName = "id_establishment", foreignKey = @ForeignKey(name = "fk_menu_establishment"))
    private Establishment establishment;

    @Embedded
    private Audit audit = new Audit();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu menu)) return false;
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuType=" + menuType +
                ", items=" + items +
                ", establishment=" + establishment +
                ", audit=" + audit +
                '}';
    }
}
