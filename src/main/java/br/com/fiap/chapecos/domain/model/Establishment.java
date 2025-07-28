package br.com.fiap.chapecos.model;

import br.com.fiap.chapecos.logic.ExpedientTime;
import br.com.fiap.chapecos.logic.OperationTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_ESTABLISHMENT")
@EntityListeners(AuditingEntityListener.class)
public class Establishment {

    @Id
    @SequenceGenerator(name = "seq_establishment", sequenceName = "seq_establishment", allocationSize = 1)
    @GeneratedValue(generator = "seq_establishment", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_establishment")
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    private KitchenType kitchenType;

    @Embedded
    private Address address;

    private String timeZone;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "fk_establishment_user"))
    private User user;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "establishment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RegistrationTime> registrationTimes = new HashSet<>();

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "establishment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Menu> menus = new HashSet<>();

    @Embedded
    private Audit audit = new Audit();

    @Transient
    public OperationTime buildLogic() {
        Map<DayOfWeek, List<ExpedientTime>> logicGrid = this.registrationTimes.stream()
                .collect(Collectors.groupingBy(
                        RegistrationTime::getDayOfWeek,
                        Collectors.mapping(
                            time -> new ExpedientTime(time.getOpening(), time.getClosing()),
                                Collectors.toList()
                        )
                ));

        ZoneId timeZone = ZoneId.of(this.timeZone);

        return new OperationTime(logicGrid, timeZone);
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kitchenType=" + kitchenType +
                ", address=" + address +
                ", user=" + user +
                ", audit=" + audit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Establishment that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
