package br.com.fiap.chapecos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Audit {

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "created_date")
    @CreatedDate
    private Instant createdDate;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Instant lastModifiedDate;
}
