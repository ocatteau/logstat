package fr.ocatteau.logline;

import fr.ocatteau.LogLine;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public abstract class LogLineProcessor {

    private LocalDateTime currentHour;
    private List<LogLine> nexttHourLogLines = new ArrayList<>();

    public void process(LogLine logLine) {
        if (currentHour == null) {
            currentHour = truncateOnHour(logLine);
        }

        if (isLoggedOnNextHour(logLine)) {
            nexttHourLogLines.add(logLine);
        }


        if (isHourPeriodFinished(logLine)) {
            printReport();

            for (LogLine nexttHourLogLine : nexttHourLogLines) {
                processLogLine(nexttHourLogLine);
            }

            nexttHourLogLines = new ArrayList<>();
            currentHour = truncateOnHour(logLine);
            resetProcessor();
        } else {
            processLogLine(logLine);
        }
    }

    public void printReport() {
        System.out.println("On " + currentHour + " - "  + generateReport());
    }

    protected abstract String generateReport();

    protected abstract void processLogLine(LogLine logLine);

    protected abstract void resetProcessor();

    private boolean isLoggedOnNextHour(LogLine logLine) {
        return  truncateOnHour(logLine).isAfter(currentHour);
    }

    private boolean isHourPeriodFinished(LogLine logLine) {
        return timestampToLocalDateTime(logLine).isAfter(currentHour.plusHours(1).plusMinutes(5));
    }

    private LocalDateTime truncateOnHour(LogLine logLine) {
        LocalDateTime localDateTime = timestampToLocalDateTime(logLine);
        return localDateTime.truncatedTo(ChronoUnit.HOURS);
    }

    private LocalDateTime timestampToLocalDateTime(LogLine logLine) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(logLine.timestamp), TimeZone.getDefault().toZoneId());
    }
}