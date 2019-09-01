package fr.ocatteau;

public class LogLine {
    public final int timestamp;
    public final String fromHost;
    public final String toHost;

    public LogLine(int timestamp, String fromHost, String toHost) {
        this.timestamp = timestamp;
        this.fromHost = fromHost;
        this.toHost = toHost;
    }

    @Override
    public String toString() {
        return "LogLine{" +
                "timestamp=" + timestamp +
                ", fromHost='" + fromHost + '\'' +
                ", toHost='" + toHost + '\'' +
                '}';
    }
}
