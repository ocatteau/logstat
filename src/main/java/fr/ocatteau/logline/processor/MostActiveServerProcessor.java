package fr.ocatteau.logline.processor;

import fr.ocatteau.LogLine;
import fr.ocatteau.logline.LogLineProcessor;

import java.util.HashMap;
import java.util.Map;

public class MostActiveServerProcessor extends LogLineProcessor {

    private Map<String, Integer> serverToCountMap = new HashMap<>();

    @Override
    public void processLogLine(LogLine logLine) {
        if (!serverToCountMap.containsKey(logLine.fromHost)) {
            serverToCountMap.put(logLine.fromHost, 1);
        } else {
            serverToCountMap.put(logLine.fromHost, serverToCountMap.get(logLine.fromHost) + 1);
        }
    }

    @Override
    public String generateReport() {
        Map.Entry<String, Integer> maxServerCountEntry = null;

        for (Map.Entry<String, Integer> serverCountEntry : serverToCountMap.entrySet()) {
            if (maxServerCountEntry == null || isServerMoreActiveThanMax(serverCountEntry, maxServerCountEntry)) {
                maxServerCountEntry = serverCountEntry;
            }
        }

        return "Most active server : " + maxServerCountEntry.getKey() +
                " with " + maxServerCountEntry.getValue() + " connections.";
    }

    private boolean isServerMoreActiveThanMax(Map.Entry<String, Integer> serverCountEntry,
                                              Map.Entry<String, Integer> maxEntry) {
        return serverCountEntry.getValue().compareTo(maxEntry.getValue()) > 0;
    }

    @Override
    protected void resetProcessor() {
        serverToCountMap = new HashMap<>();
    }
}
