package br.com.fiap.chapecos.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record RegistrationTimeRequestDTO(

        DayOfWeek dayOfWeek,

        LocalTime opening,

        LocalTime closing,

        Long idEstablishment
) {
}
