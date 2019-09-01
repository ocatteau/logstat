package fr.ocatteau.logline.processor;

import fr.ocatteau.LogLine;
import fr.ocatteau.logline.LogLineProcessor;

import java.util.HashSet;
import java.util.Set;

public class ConnectedFromServerProcessor extends LogLineProcessor {

    private final String toHost;
    private Set<String> fromHostList = new HashSet<>();

    public ConnectedFromServerProcessor(String toHost) {
        this.toHost = toHost;
    }

    @Override
    public void processLogLine(final LogLine logLine) {
        if (logLine.toHost.equals(toHost)) {
            fromHostList.add(logLine.fromHost);
        }
    }

    @Override
    public String generateReport() {
        return "Servers '" + fromHostList + "' were connected to " + toHost;
    }

    @Override
    protected void resetProcessor() {
        fromHostList = new HashSet<>();
    }
}
