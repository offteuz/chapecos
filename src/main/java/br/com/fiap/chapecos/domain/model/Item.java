package br.com.fiap.chapecos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_ITEM")
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @SequenceGenerator(name = "seq_item", sequenceName = "seq_item", allocationSize = 1)
    @GeneratedValue(generator = "seq_item", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_item")
    private Long id;

    private String name;

    private String description;

    private Double price;

    @Column(name = "local_consumption")
    private LocalConsumption localConsumption;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "id_menu", foreignKey = @ForeignKey(name = "fk_item_menu"))
    private Menu menu;

    private String picture;

    @Embedded
    private Audit audit = new Audit();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", localConsumption=" + localConsumption +
                ", menu=" + menu +
                ", picture='" + picture + '\'' +
                ", audit=" + audit +
                '}';
    }
}
