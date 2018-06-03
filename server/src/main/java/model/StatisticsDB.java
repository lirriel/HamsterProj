package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticsDB {
    private Connection connection;
    private DatabaseMetaData metaData;
    private Statement statement;

    public StatisticsDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        if (connection == null)
            throw new SQLException("Can't connect to DB");

        metaData = connection.getMetaData();
        statement = connection.createStatement();
        if (!metaData.getTables(null, null, "stats", null).next()) {
            statement.execute("create table stats("
                    + "id integer primary key auto_increment, "
                    + "name varchar(100), "
                    + "time bigint, "
                    + "rate byte"
                    + ");");
        }

        statement.close();
        connection.close();
    }

    public String[] insert(String[] params) throws Exception {
        synchronized (this) {
            if (params.length == 3) {
                return insert(params[1] + params[2]);
            } else {
                insert(params[1] + params[2], Long.parseLong(params[3]), Byte.parseByte(params[4]));
                return null;
            }
        }
    }

    private String[] insert(String userId) throws SQLException {
        String[] windowsConfiguration;
        synchronized (this) {
            connection = DriverManager.getConnection("jdbc:h2:~/test");
            statement = connection.createStatement();
            statement.executeUpdate("insert into stats "
                    + String.format("VALUES(%s, 0, 0)", userId));

            ResultSet rs = statement.executeQuery("select count(*) from stats");
            rs.next();
            int rowsCount = rs.getInt(1);

            switch (rowsCount % 4) {
                case 1:
                    windowsConfiguration = new String[]{"old", "new"};
                    break;
                case 2:
                    windowsConfiguration = new String[]{"new", "old"};
                    break;
                case 3:
                    windowsConfiguration = new String[]{"new", "new"};
                    break;
                default:
                    windowsConfiguration = new String[]{"old", "old"};
                    break;
            }
            rs.close();
            statement.close();
            connection.close();
        }

        return windowsConfiguration;
    }

    private void insert(String userId, Long time, byte rate) throws SQLException {
        String[] windowsConfiguration;
        synchronized (this) {
            connection = DriverManager.getConnection("jdbc:h2:~/test");
            statement = connection.createStatement();
            statement.executeUpdate("update stats "
                    + String.format("set time = %d, rate = %d where name = %s", time, rate, userId));
            statement.close();
            connection.close();
        }
    }
}
