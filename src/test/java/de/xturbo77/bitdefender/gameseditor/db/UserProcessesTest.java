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
public class UserProcessesTest {

    private UserProcesses userProcesses;

    @BeforeClass
    public void setup() throws URISyntaxException {
        URL url = UserProcessesTest.class.getResource("UserProcesses.db");
        Path path = Paths.get(url.toURI());
        userProcesses = new UserProcesses(path.toFile().getAbsolutePath());
    }

    public void testFindAll() {
        List<Process> processes = userProcesses.findAll();
        Assert.assertNotNull(processes, "Processes should not be null");
        Assert.assertEquals(processes.size(), 10);
    }

}
