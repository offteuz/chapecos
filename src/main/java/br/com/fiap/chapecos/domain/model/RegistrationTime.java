package br.com.fiap.chapecos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_REGISTRATION_TIME")
@EntityListeners(AuditingEntityListener.class)
public class RegistrationTime {

    @Id
    @SequenceGenerator(name = "seq_registration_time", sequenceName = "seq_registration_time", allocationSize = 1)
    @GeneratedValue(generator = "seq_registration_time", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime opening;

    private LocalTime closing;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "establishment_id", referencedColumnName = "id_establishment", foreignKey = @ForeignKey(name = "fk_registration_time_establishment"))
    private Establishment establishment;

    @Embedded
    private Audit audit = new Audit();

    public RegistrationTime(LocalTime opening, LocalTime closing) {
        this.opening = opening;
        this.closing = closing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationTime that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "RegistrationTime{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", opening=" + opening +
                ", closing=" + closing +
                ", establishment=" + establishment +
                ", audit=" + audit +
                '}';
    }
}
