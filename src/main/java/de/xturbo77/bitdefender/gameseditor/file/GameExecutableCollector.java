package de.xturbo77.bitdefender.gameseditor.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 *
 * @author schmidt
 */
public class GameExecutableCollector {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GameExecutableCollector.class);
    private final String path;

    public GameExecutableCollector(final String path) {
        this.path = path;
    }

    public List<File> findGameExecutables() {
        final List<File> files = new ArrayList<>();
        Path p = FileSystems.getDefault().getPath(path);
        try {
            Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().endsWith("exe")) {
                        LOG.info("found executable: " + file);
                        files.add(file.toFile());
                    }
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    LOG.warn("could not visit path: {} exception: {}", file, exc.getMessage());
                    return FileVisitResult.SKIP_SUBTREE;
                }

            });
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return files;
    }
}
