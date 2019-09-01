package fr.ocatteau;

import fr.ocatteau.logline.LogLineProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LogFileProcessor {

    private List<? extends LogLineProcessor> lineProcessors;

    public LogFileProcessor(List<? extends LogLineProcessor> lineProcessors) {
        this.lineProcessors = lineProcessors;
    }

    public void process(File logFile) {
        try (FileInputStream inputStream = new FileInputStream(logFile);
             Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line);

                if (scanner.ioException() != null) {
                    throw scanner.ioException();
                }
            }

            for (LogLineProcessor lineProcessor : lineProcessors) {
                lineProcessor.printReport();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        String[] lineValues = line.split(" ");
        int timestamp = Integer.valueOf(lineValues[0]);
        String fromHost = lineValues[1];
        String toHost = lineValues[2];
        LogLine logLine = new LogLine(timestamp, fromHost, toHost);

        for (LogLineProcessor processor : lineProcessors) {
            processor.process(logLine);
        }
    }

    private void print(String report) {
        System.out.println(report);
    }
}
