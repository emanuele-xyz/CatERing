package businesslogic.user;

import javafx.collections.FXCollections;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class User {

    private static final Map<Integer, User> loadedUsers = FXCollections.observableHashMap();

    public enum Role {SERVIZIO, CUOCO, CHEF, ORGANIZZATORE}

    private int id;
    private String username;
    private final Set<Role> roles;

    public User() {
        id = 0;
        username = "";
        roles = new HashSet<>();
    }

    public boolean isChef() {
        return roles.contains(Role.CHEF);
    }

    public String getUserName() {
        return username;
    }

    public int getID() {
        return this.id;
    }

    public String debugString(int indentation, int spacesPerIndent) {
        final String outerIndent = " ".repeat(indentation * spacesPerIndent);
        final String indent = " ".repeat((indentation + 1) * spacesPerIndent);

        StringBuilder sb = new StringBuilder();
        sb.append(outerIndent).append("Utente '").append(username).append("'");
        roles.forEach(role -> sb.append(' ').append(role.toString()));

        return sb.toString();
    }

    @Override
    public String toString() {
        String result = username;
        if (roles.size() > 0) {
            result += ": ";

            for (User.Role r : roles) {
                result += r.toString() + " ";
            }
        }
        return result;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static User loadUserById(int uid) {
        if (loadedUsers.containsKey(uid)) return loadedUsers.get(uid);

        User load = new User();
        String userQuery = "SELECT * FROM Users WHERE id='"+uid+"'";
        PersistenceManager.executeQuery(userQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                load.id = rs.getInt("id");
                load.username = rs.getString("username");
            }
        });
        if (load.id > 0) {
            loadedUsers.put(load.id, load);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + load.id;
            PersistenceManager.executeQuery(roleQuery, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    String role = rs.getString("role_id");
                    switch (role.charAt(0)) {
                        case 'c':
                            load.roles.add(User.Role.CUOCO);
                            break;
                        case 'h':
                            load.roles.add(User.Role.CHEF);
                            break;
                        case 'o':
                            load.roles.add(User.Role.ORGANIZZATORE);
                            break;
                        case 's':
                            load.roles.add(User.Role.SERVIZIO);
                    }
                }
            });
        }
        return load;
    }

    public static User loadUser(String username) {
        User u = new User();
        String userQuery = "SELECT * FROM Users WHERE username='"+username+"'";
        PersistenceManager.executeQuery(userQuery, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                u.id = rs.getInt("id");
                u.username = rs.getString("username");
            }
        });
        if (u.id > 0) {
            loadedUsers.put(u.id, u);
            String roleQuery = "SELECT * FROM UserRoles WHERE user_id=" + u.id;
            PersistenceManager.executeQuery(roleQuery, new ResultHandler() {
                @Override
                public void handle(ResultSet rs) throws SQLException {
                    String role = rs.getString("role_id");
                    switch (role.charAt(0)) {
                        case 'c':
                            u.roles.add(User.Role.CUOCO);
                            break;
                        case 'h':
                            u.roles.add(User.Role.CHEF);
                            break;
                        case 'o':
                            u.roles.add(User.Role.ORGANIZZATORE);
                            break;
                        case 's':
                            u.roles.add(User.Role.SERVIZIO);
                    }
                }
            });
        }
        return u;
    }
}
