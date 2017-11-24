package businesslogic;

import dataaccess.UserDB;
import domainmodel.Role;
import domainmodel.User;
import java.util.List;

public class UserService {

    private UserDB userDB;

    public UserService() {
        userDB = new UserDB();
    }

    public User get(String username) throws Exception {
        return userDB.getUser(username);
    }
    
    public User getByUUID(String uuid) throws Exception {
        return userDB.getByUUID(uuid);
    }
    
    public User getUserByEmail(String email) throws Exception {
        return userDB.getUserByEmail(email);
    }
    
    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    public int update(String username, String password, String email, boolean active, String firstname, String lastname) throws Exception {
        User user = userDB.getUser(username);
        user.setPassword(password);
        user.setActive(active);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return userDB.update(user);
    }
    
    public int update(String username, String password, String email, String firstname, String lastname) throws Exception {
        User user = userDB.getUser(username);
        user.setPassword(password);
        user.setActive(true);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        return userDB.update(user);
    }
    
    public int update(User user) throws Exception 
    {
        return userDB.update(user);
    }

    public int delete(String username) throws Exception {
        User deletedUser = userDB.getUser(username);
        return userDB.delete(deletedUser);
    }

    public int insert(String username, String password, String email, boolean active, String firstname, String lastname) throws Exception {
        User user = new User(username, password, email, active, firstname, lastname);
        Role role = new Role(2);  // default regular user role
        user.setRole(role);
        return userDB.insert(user);
    }
    
//    public int insert(String uuid) throws Exception {
//        User user = new User(uuid);
//        return userDB.insert(user);
//    }
}
