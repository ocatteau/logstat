package fr.ocatteau.logline.processor;

import fr.ocatteau.LogLine;
import fr.ocatteau.logline.LogLineProcessor;

import java.util.Date;

public class DebugProcessor extends LogLineProcessor {

    @Override
    public void processLogLine(LogLine logLine) {
        System.out.println("logLine = " + logLine + " " + new Date(logLine.timestamp * 1000));
    }

    @Override
    public String generateReport() {
        return "";
    }

    @Override
    protected void resetProcessor() {
        //
    }
}
