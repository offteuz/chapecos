package br.com.fiap.chapecos.logic;

import java.time.DayOfWeek;

public record TimeEntry(

        DayOfWeek dayOfWeek,

        ExpedientTime expedientTime
) {
}
