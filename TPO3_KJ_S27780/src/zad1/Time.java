/**
 * @author Karwowski Jakub S27780
 */

package zad1;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class Time {

    //    Daty są podawane jako napisy w formacie ISO-8601:
//- data bez czasu: YYYY-MM-DD
//- data z czasem: YYYY-MM-DDTGG:MM
    public static String passed(String from, String to) {
        try{
            if (from.contains("T")) {
                return handleDateWithTime(from, to);
            } else {
                return handleDateWithoutTime(from, to);
            }
        } catch (DateTimeParseException e){
            return "*** "+e.toString();
        }

    }

    private static String handleDateWithTime(String from, String to) {
        LocalDateTime parsedFrom = LocalDateTime.parse(from);
        LocalDateTime parsedTo = LocalDateTime.parse(to);

        return formatTheResultCorrectlyWithTime(parsedFrom, parsedTo);
    }

    private static String handleDateWithoutTime(String from, String to) {


        LocalDate parsedFrom = LocalDate.parse(from);
        LocalDate parsedTo = LocalDate.parse(to);

        return formatTheResultCorrectlyWithoutTime(parsedFrom, parsedTo);

    }
   
    private static String formatTheResultCorrectlyWithoutTime(LocalDate parsedFrom, LocalDate parsedTo) {
        StringBuilder resultBuilder = new StringBuilder();
        Locale resultFormatter = new Locale("pl", "PL");
        long daysBetween = ChronoUnit.DAYS.between(parsedFrom, parsedTo);
        String weeksBetween = formWeeksString(daysBetween * 1.0 / 7);
        Period between = Period.between(parsedFrom, parsedTo);

        resultBuilder.append("Od ").append(parsedFrom.getDayOfMonth()).append(" ")
                .append(parsedFrom.getMonth().getDisplayName(TextStyle.FULL, resultFormatter)).append(" ")
                .append(parsedFrom.getYear()).append(" ").append("(")
                .append(parsedFrom.getDayOfWeek().getDisplayName(TextStyle.FULL, resultFormatter)).append(")")
                .append(" do ").append(parsedTo.getDayOfMonth()).append(" ").append(parsedTo.getMonth().getDisplayName(TextStyle.FULL, resultFormatter))
                .append(" ").append(parsedTo.getYear()).append(" (").append(parsedTo.getDayOfWeek().getDisplayName(TextStyle.FULL, resultFormatter))
                .append(")\n").append(" - mija: ").append(daysBetween).append(" dni, tygodni ").append(weeksBetween)
                .append(giveProperCalendarDifference(between));
        return resultBuilder.toString();
    }


    private static String formatTheResultCorrectlyWithTime(LocalDateTime parsedFrom, LocalDateTime parsedTo) {
        StringBuilder resultBuilder = new StringBuilder();
        Locale resultFormatter = new Locale("pl", "PL");

        long daysBetween = ChronoUnit.DAYS.between(parsedFrom, parsedTo);
        String weeksBetween = formWeeksString(daysBetween * 1.0 / 7);
        Period between = Period.between(parsedFrom.toLocalDate(), parsedTo.toLocalDate());


        resultBuilder.append("Od ").append(parsedFrom.getDayOfMonth()).append(" ")
                .append(parsedFrom.getMonth().getDisplayName(TextStyle.FULL, resultFormatter)).append(" ")
                .append(parsedFrom.getYear()).append(" ").append("(")
                .append(parsedFrom.getDayOfWeek().getDisplayName(TextStyle.FULL, resultFormatter)).append(")")
                .append(" godz. ").append(formatHourAndMinute(parsedFrom.getHour(), parsedFrom.getMinute()))
                .append(" do ").append(parsedTo.getDayOfMonth()).append(" ").append(parsedTo.getMonth().getDisplayName(TextStyle.FULL, resultFormatter))
                .append(" ").append(parsedTo.getYear()).append(" (").append(parsedTo.getDayOfWeek().getDisplayName(TextStyle.FULL, resultFormatter))
                .append(") ").append("godz. ").append(formatHourAndMinute(parsedTo.getHour(), parsedTo.getMinute()))
                .append("\n").append(" - mija: ").append(daysBetween).append(" dni, tygodni ").append(weeksBetween)
                .append(giveProperHourAndMinuteDiffernce(parsedFrom, parsedTo))
                .append(giveProperCalendarDifference(between));
        return resultBuilder.toString();
    }

    private static String giveProperHourAndMinuteDiffernce(LocalDateTime from, LocalDateTime to) {
        ZoneId zoneId = ZoneId.of("Europe/Warsaw");
        ZonedDateTime zdtFrom = ZonedDateTime.of(from, zoneId);
        ZonedDateTime zdtTo = ZonedDateTime.of(to, zoneId);

        StringBuilder sbd = new StringBuilder();
        sbd.append("\n - godzin: ");
        long hoursBetween = ChronoUnit.HOURS.between(zdtFrom, zdtTo);
        long minutesBetween = ChronoUnit.MINUTES.between(zdtFrom, zdtTo);
        sbd.append(hoursBetween).append(", minut: ").append(minutesBetween);
        return sbd.toString();
    }

    private static String formatHourAndMinute(int hour, int minute) {
        StringBuilder sbd = new StringBuilder();
        if (hour < 10) {
            sbd.append(0).append(hour);
        } else {
            sbd.append(hour);
        }
        sbd.append(":");
        if (minute < 10) {
            sbd.append(0).append(minute);
        } else {
            sbd.append(minute);
        }
        return sbd.toString();
    }

    private static String formWeeksString(double weeks) {
        String s = String.valueOf(weeks);
        String[] twoParts = s.split("\\.");

        String result = twoParts[0] + ".";
        if (twoParts[1].length() == 1) return result += twoParts[1];
        else return result + twoParts[1].substring(0, 2);
    }

    private static String giveProperCalendarDifference(Period between) {
        StringBuilder result = new StringBuilder();
        int years = between.getYears();
        int months = between.getMonths();
        int days = between.getDays();
        if (years == 0 && months == 0 && days == 0) return "";
        result.append("\n - kalendarzowo: ");
        if (years == 1) {
            result.append("1 rok, ");
        } else if (years > 1 && years < 5) {
            result.append(years).append(" lata, ");
        } else if (years != 0) {
            result.append(years).append(" lat, ");
        }

        if (months == 1) {
            result.append("1 miesiąc, ");
        } else if (months > 1 && months < 5) {
            result.append(months).append(" miesiące, ");
        } else if (months != 0) {
            result.append(months).append(" miesięcy, ");
        }
        if (days == 1) {
            result.append("1 dzień");
        } else if (days != 0) {
            result.append(days).append(" dni");
        }
        String resultString = result.toString();
        if (resultString.charAt(resultString.length() - 1) == ' ')
            return resultString.substring(0, resultString.length() - 2);
        return resultString;
    }
}

