package lesson14.HomeWork14.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DBAuthService implements AuthService {

    private static Connection connection;
    private static Statement statement;
    private static final Logger logger = LogManager.getLogger(DBAuthService.class);


    @Override
    public void start() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:authservice.db");
        statement = connection.createStatement();
        System.out.println("Auth service is running");
        logger.info("Auth service is running");
    }


    @Override
    public void stop() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Auth service is shutting down");
        logger.info("Auth service is shutting down");
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {
        try (ResultSet rs = statement.executeQuery("select * from users")) {
            Entry entry = new Entry(null, null, null);
            while (rs.next()) {
                entry.login = rs.getString(2);
                entry.password = rs.getString(3);
                entry.nick = rs.getString(4);
                if (entry.login.equals(login) && entry.password.equals(pass)) {
                    return entry.nick;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void PAuthService() throws SQLException {
        statement.executeUpdate("create table if not exists users (\n" +
                "    id integer primary key autoincrement not null,\n" +
                "    login text not null unique,\n" +
                "    password text,\n" +
                "    nick text not null unique\n" +
                ");");
    }

    public void insertUser(String login, String password, String nick) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into users (login, password, nick) " +
                        "values (?, ?, ?)")) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, nick);
            ps.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void renameToNick(String newNick, String name) throws SQLException {
        try (PreparedStatement ps =
                     connection.prepareStatement("UPDATE users set nick ="+'"' +newNick +'"'+ " where nick =" +'"'+ name +'"'+ ";")) {
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

   // UPDATE users set nick = "valentin" where nick = "nick1";
