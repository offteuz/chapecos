package br.com.fiap.chapecos.logic;

import br.com.fiap.chapecos.infrastructure.exception.InvalidScheduleException;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidatorSchedules {

    public void validate(Set<TimeEntry> schedules) {
        if (schedules == null || schedules.isEmpty()) {
            return;
        }

        Map<DayOfWeek, List<TimeEntry>> schedulesPerDay = schedules.stream()
                .collect(Collectors.groupingBy(TimeEntry::dayOfWeek));

        for (Map.Entry<DayOfWeek, List<TimeEntry>> entry : schedulesPerDay.entrySet()) {

            DayOfWeek dayOfWeek = entry.getKey();
            List<TimeEntry> timeEntries = entry.getValue();

            if (timeEntries.size() > 2) {
                throw new InvalidScheduleException("Não é permitido mais de 2 períodos para: " + dayOfWeek + ". Verfique!");
            }

            if (timeEntries.size() == 2) {

                timeEntries.sort(Comparator.comparing(h -> h.expedientTime().opening()));

                ExpedientTime firstBreak = timeEntries.get(0).expedientTime();
                ExpedientTime secondBreak = timeEntries.get(1).expedientTime();

                if (firstBreak.closing().isAfter(secondBreak.opening())) {
                    throw new InvalidScheduleException("Sobreposição de horários para: " + dayOfWeek + ". Verfique!");
                }
            }
        }
    }
}
