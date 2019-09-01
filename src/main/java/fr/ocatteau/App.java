package fr.ocatteau;


import fr.ocatteau.logline.LogLineProcessor;
import fr.ocatteau.logline.processor.ConnectedFromServerProcessor;
import fr.ocatteau.logline.processor.ConnectedToServerProcessor;
import fr.ocatteau.logline.processor.DebugProcessor;
import fr.ocatteau.logline.processor.MostActiveServerProcessor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        File logFile = new File("src/main/resources/fr/ocatteau/log-15.txt");

        List<? extends LogLineProcessor> processors = Arrays.asList(
                new DebugProcessor(),
                new ConnectedToServerProcessor("Opalina"),
                new ConnectedFromServerProcessor("Margarita"),
                new MostActiveServerProcessor());

        LogFileProcessor logFileReader = new LogFileProcessor(processors);
        logFileReader.process(logFile);
    }
}
