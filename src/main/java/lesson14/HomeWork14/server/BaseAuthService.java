package lesson14.HomeWork14.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {

    private List<Entry> entries;
    private static final Logger logger = LogManager.getLogger(BaseAuthService.class);

    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("login1", "pass1", "nick1"));
        entries.add(new Entry("login2", "pass2", "nick2"));
        entries.add(new Entry("login3", "pass3", "nick3"));
    }

    @Override
    public void start() {
        System.out.println("Auth service is running");
        logger.info("Auth service is running");
    }

    @Override
    public void stop() {
        System.out.println("Auth service is shutting down");
        logger.info("Auth service is shutting down");
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {
        for (Entry entry : entries) {
            if (entry.login.equals(login) && entry.password.equals(pass)) {
                return entry.nick;
            }
        }
        return null;
    }
/*    class Entry {
        String login;
        String password;
        String nick;

        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }*/
}
