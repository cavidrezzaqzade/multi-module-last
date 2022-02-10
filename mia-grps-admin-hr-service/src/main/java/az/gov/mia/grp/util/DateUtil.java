package az.gov.mia.grp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DateUtil {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseWith(String dt){
        return dt==null ? null : LocalDate.parse(dt, formatter);
    }

    public static String formatWith(LocalDate dt){
        return dt==null ? null : dt.format(formatter);
    }

    /**
     * <a href="https://howtodoinjava.com/java/date-time/calculate-business-days/">Reference<a/>
     */
    public static List<LocalDate> countBusinessDaysBetween(final LocalDate startDate,
                                                                  final LocalDate endDate,
                                                                  final Optional<List<LocalDate>> holidays,
                                                                  final boolean excludeWeekends) {
        // Validate method arguments
        if (startDate == null || endDate == null || (boolean) startDate.isAfter(endDate)) {
            log.error("Invalid method argument(s) to countBusinessDaysBetween (" + startDate
                    + "," + endDate + "," + holidays + ")");

            return new ArrayList<>(Collections.emptyList());
        }

        // Predicate 1: Is a given date is a holiday
        Predicate<LocalDate> isHoliday = date -> holidays.isPresent() && holidays.get().contains(date);

        // Predicate 2: Is a given date is a weekday
        Predicate<LocalDate> isWeekend = date ->
                !excludeWeekends && (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);

        // Iterate over stream of all dates and check each day against any weekday or
        // holiday
        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(getDatesBetween(startDate, endDate).size())
                .filter(isHoliday.or(isWeekend).negate())
                .collect(Collectors.toList());
    }

    public static List<LocalDate> countBusinessDaysBetween(final LocalDate startDate,
                                                           final LocalDate endDate,
                                                           final Optional<List<LocalDate>> holidays) {
        return countBusinessDaysBetween(startDate, endDate, holidays, false);
    }

    /**
     * <a href="https://www.baeldung.com/java-between-dates">Reference<a/>
     */
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        //Bitmə tarixi daxil olmalıdır, bu səbəbdən-də +1
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(startDate::plusDays)
                .collect(Collectors.toList());
    }

    public static List<LocalDate> getDatesBetween(Date startDate, Date endDate) {
        LocalDate startLocalDate = convertToLocalDate(startDate);
        LocalDate endLocalDate = convertToLocalDate(endDate);

        return getDatesBetween(startLocalDate, endLocalDate);
    }

    /**
     * <a href="https://stackoverflow.com/a/48951547">Reference<a/>
     */
    public static List<LocalDate> getMonthsBetween(LocalDate startDate, LocalDate endDate) {
        long numOfMonthsBetween = ChronoUnit.MONTHS.between(startDate, endDate) + 1;

        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfMonthsBetween)
                .mapToObj(startDate::plusMonths)
                .collect(Collectors.toList());
    }

    public static List<LocalDate> getMonthsBetween(Date startDate, Date endDate) {
        return getMonthsBetween(convertToLocalDate(startDate), convertToLocalDate(endDate));
    }

    public static List<LocalDate> getYearsBetween(LocalDate startDate, LocalDate endDate) {
        long numOfYearsBetween = ChronoUnit.YEARS.between(startDate, endDate) + 1;

        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfYearsBetween)
                .mapToObj(startDate::plusYears)
                .collect(Collectors.toList());
    }

    public static List<LocalDate> getYearsBetween(Date startDate, Date endDate) {
        return getYearsBetween(convertToLocalDate(startDate), convertToLocalDate(endDate));
    }

    /**
     * <a href="https://www.baeldung.com/java-date-to-localdate-and-localdatetime">Reference<a/>
     */
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate convertToLocalDate(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
    }

    //TODO:: need refactor https://stackoverflow.com/a/28454044
    public static Integer getWeekNumber(LocalDate date){
        switch (date.getDayOfWeek()){
            case MONDAY:
                return 1;
            case TUESDAY:
                return 2;
            case WEDNESDAY:
                return 3;
            case THURSDAY:
                return 4;
            case FRIDAY:
                return 5;
            case SATURDAY:
                return 6;
            default:
                return 7;
        }
    }

    /**
     * <p>
     * The values are numbered following the ISO-8601 standard,
     * from 1 (January) to 12 (December).
     */
    public static String monthName(int month){
        String[] monthNames = {"Yanvar",
                "Fevral",
                "Mart",
                "Aprel",
                "May",
                "İyun",
                "İyul",
                "Avqust",
                "Sentyabr",
                "Oktyabr",
                "Noyabr",
                "Dekabr"};
        return monthNames[month - 1];
    }
}
