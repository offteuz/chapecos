package br.com.fiap.chapecos.adapter.inbound.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record RegistrationTimeRequestDTO(

        @NotNull
        DayOfWeek dayOfWeek,

        @NotNull
        LocalTime opening,

        @NotNull
        LocalTime closing,

        @NotNull
        Long idEstablishment
) {
}
