package br.com.fiap.chapecos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class Audit {

    @CreationTimestamp
    @Column(name = "created_as", updatable = false)
    private LocalDateTime createAs;

    @UpdateTimestamp
    @Column(name = "updated_as")
    private LocalDateTime updateAs;
}
