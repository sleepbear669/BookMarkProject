package gom.cave.sleep.bookmark.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
@Repository
public class ConnectionMaker {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/bookmark_sharing", "bookmark", "gom0119!1");
    }
}
