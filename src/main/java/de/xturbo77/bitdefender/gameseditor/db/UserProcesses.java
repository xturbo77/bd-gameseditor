package de.xturbo77.bitdefender.gameseditor.db;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author schmidt
 */
public class UserProcesses {

    private static final Logger LOG = LoggerFactory.getLogger(UserProcesses.class);

    private final static String ID_COLUMN = "id";
    private final static String TYPE_COLUMN = "Type";
    private final static String NAME_COLUMN = "Name";
    private final static String PRODUCT_COLUMN = "Product";
    private final static String FRIENDLYNAME_COLUMN = "FriendlyName";
    private final static String FULLPATH_COLUMN = "FullPath";

    private BoneCP connectionPool;

    public UserProcesses(final String path) {
        try {
            Class.forName("org.sqlite.JDBC");
            BoneCPConfig config = new BoneCPConfig();
            config.setMaxConnectionsPerPartition(1);
            config.setJdbcUrl("jdbc:sqlite:" + path);
            config.setUsername("");
            config.setPassword("");
            connectionPool = new BoneCP(config);
        } catch (ClassNotFoundException | SQLException ex) {
            LOG.error(ex.getMessage(), ex);
            System.exit(1);
        }
    }

    public void addProcessFile(final File file) {
        boolean exists = false;
        try (Connection con = connectionPool.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Processes WHERE Name = ?");
            pstmt.setString(1, file.getName());
            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        if (!exists) {
            Process process = new Process();
            process.setType(2);
            process.setName(file.getName());
            process.setProduct(file.getParentFile().getName());
            process.setFriendlyName(file.getParentFile().getName());
            process.setFullPath(file.getAbsolutePath());
            insertProcess(process);
        } else {
            LOG.info("executable {} has already been added", file.getName());
        }
    }

    public void insertProcess(final Process process) {
        try (Connection con = connectionPool.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Processes (Type, Name, Product, FriendlyName, FullPath) VALUES(?, ?, ?, ?, ?)");
            pstmt.setInt(1, process.getType());
            pstmt.setString(2, process.getName());
            pstmt.setString(3, process.getProduct());
            pstmt.setString(4, process.getFriendlyName());
            pstmt.setString(5, process.getFullPath());
            pstmt.execute();
            LOG.info("added executable {} ", process);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public List<Process> findAll() {
        List<Process> result = new ArrayList<>();
        try (Connection con = connectionPool.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Processes");
            while (rs.next()) {
                Process p = new Process();
                p.setId(rs.getInt(ID_COLUMN));
                p.setType(rs.getInt(TYPE_COLUMN));
                p.setName(rs.getString(NAME_COLUMN));
                p.setProduct(rs.getString(PRODUCT_COLUMN));
                p.setFriendlyName(rs.getString(FRIENDLYNAME_COLUMN));
                p.setFullPath(rs.getString(FULLPATH_COLUMN));
                result.add(p);
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return result;
    }

}
