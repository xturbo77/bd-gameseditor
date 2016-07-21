package de.xturbo77.bitdefender.gameseditor;

import de.xturbo77.bitdefender.gameseditor.db.UserProcesses;
import de.xturbo77.bitdefender.gameseditor.file.GameExecutableCollector;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author schmidt
 */
public class GamesEditior {

    private static final Logger LOG = LoggerFactory.getLogger(GamesEditior.class);

    public static void main(String[] args) {
        final String gamespath = "C:\\Program Files\\7-Zip";
        final String path = "C:\\Users\\schmidt.IOMEDICO\\Documents\\UserProcesses.db";

        GameExecutableCollector collector = new GameExecutableCollector(gamespath);
        List<File> files = collector.findGameExecutables();
        UserProcesses db = new UserProcesses(path);
        for (File f : files) {
            db.addProcessFile(f);
        }
    }
}
