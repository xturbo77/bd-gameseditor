package de.xturbo77.bitdefender.gameseditor.db;

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
public class CloudProcessesTest {

    private CloudProcesses cloudProcesses;

    @BeforeClass
    public void setup() throws URISyntaxException {
        URL url = CloudProcessesTest.class.getResource("cloudprocesses.db");
        Path path = Paths.get(url.toURI());
        cloudProcesses = new CloudProcesses(path.toFile().getAbsolutePath());
    }

    public void testFindAll() {
        List<Process> processes = cloudProcesses.findAll();
        Assert.assertNotNull(processes, "Processes should not be null");
        Assert.assertEquals(processes.size(), 189);
    }

}
