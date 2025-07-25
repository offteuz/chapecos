package br.com.fiap.chapecos.logic;

import java.time.LocalTime;

public record ExpedientTime(

        LocalTime opening,

        LocalTime closing
) {
}
