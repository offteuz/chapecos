package br.com.fiap.chapecos.logic;

import java.time.*;
import java.util.List;
import java.util.Map;

public class OperationTime {
    private final Map<DayOfWeek, List<ExpedientTime>> expedientTimes;
    private final ZoneId time;

    public OperationTime(Map<DayOfWeek, List<ExpedientTime>> expedientTimes, ZoneId time) {
        this.expedientTimes = expedientTimes;
        this.time = time;
    }

    public boolean isOpening(Instant instant) {
        ZonedDateTime localDateTime = instant.atZone(this.time);
        DayOfWeek dayCurrentWeek = localDateTime.getDayOfWeek();
        LocalDate currentDay = localDateTime.toLocalDate();
        LocalTime currentTime = localDateTime.toLocalTime();

        List<ExpedientTime> timeDay = expedientTimes.get(dayCurrentWeek);

        if (timeDay == null || timeDay.isEmpty()) {
            return false;
        }

        return timeDay.stream()
                .anyMatch(interval ->
                        !currentTime.isBefore(interval.opening()) && currentTime.isBefore(interval.closing())
                );
    }
}
