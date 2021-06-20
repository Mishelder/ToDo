package com.miaskor.util;

import lombok.experimental.UtilityClass;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class DateUtil {
    private static final Integer COUNT_OF_DAYS = 5;

    public static List<String> createTimeCutOffRange(ZonedDateTime from){
        ArrayList<String> days = new ArrayList<>(COUNT_OF_DAYS);
        days.add(from.toLocalDate().toString());
        for(int i=1;i<COUNT_OF_DAYS;i++) {
            ZonedDateTime nextDay = from.plusDays(i);
            days.add(nextDay.toLocalDate().toString());
        }
        return days;
    }

    public static List<String> createTimeFullMonth(ZonedDateTime from){
        ArrayList<String> days = new ArrayList<>(COUNT_OF_DAYS);
        days.add("%s, %s".formatted(from.getDayOfMonth(),from.getMonth().name()));
        for(int i=1;i<COUNT_OF_DAYS;i++) {
            ZonedDateTime nextDay = from.plusDays(i);
            days.add("%s, %s".formatted(nextDay.getDayOfMonth(),nextDay.getMonth().name()));
        }
        return days;
    }
}
