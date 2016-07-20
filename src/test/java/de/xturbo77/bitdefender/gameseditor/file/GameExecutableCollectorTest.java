package de.xturbo77.bitdefender.gameseditor.file;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author schmidt
 */
@Test
public class GameExecutableCollectorTest {

    private GameExecutableCollector gameExecutableCollector;

    @BeforeClass
    public void setup() throws URISyntaxException {
        URL url = GameExecutableCollectorTest.class.getResource("exe");
        Path path = Paths.get(url.toURI());
        gameExecutableCollector = new GameExecutableCollector(path.toFile().getAbsolutePath());
    }

    public void testFindExecutables() {
        List<File> executables = gameExecutableCollector.findGameExecutables();
        Assert.assertNotNull(executables);
        Assert.assertEquals(executables.size(), 2);
    }
}
