package fr.ocatteau.logline.processor;

import fr.ocatteau.LogLine;
import fr.ocatteau.logline.LogLineProcessor;

import java.util.HashSet;
import java.util.Set;

public class ConnectedToServerProcessor extends LogLineProcessor {

    private final String fromHost;
    private Set<String> toHostList = new HashSet<>();

    public ConnectedToServerProcessor(String fromHost) {
        this.fromHost = fromHost;
    }

    @Override
    public void processLogLine(final LogLine logLine) {
        if (logLine.fromHost.equals(fromHost)) {
            toHostList.add(logLine.toHost);
        }
    }

    @Override
    public String generateReport() {
        return "Server '" + fromHost + "' was connected to " + toHostList;
    }

    @Override
    protected void resetProcessor() {
        toHostList = new HashSet<>();
    }
}
