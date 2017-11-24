package businesslogic;

import dataaccess.NotesDBException;
import dataaccess.UserDB;
import domainmodel.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 * @author awarsyle
 */
public class AccountService {
    
    public User checkLogin(String username, String password, String path) {
        User user;
        
        UserDB userDB = new UserDB();
        try {
            user = userDB.getUser(username);
            
            if (user.getPassword().equals(password)) {
                // successful login
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO,
                                "A user logged in: {0}", username);
                String email = user.getEmail();
                try {
                    
                    // WebMailService.sendMail(email, "NotesKeepr Login", "Big brother is watching you!  Hi " + user.getFirstname(), false);
                    
                    HashMap<String, String> contents = new HashMap<>();
                    contents.put("firstname", user.getFirstname());
                    contents.put("date", ((new java.util.Date()).toString()));
                    
                    try {
                        WebMailService.sendMail(email, "NotesKeepr Login", path + "/emailtemplates/login.html", contents);
                    } catch (IOException ex) {
                        Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (MessagingException ex) {
                    Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return user;
            }
            
        } catch (NotesDBException ex) {
        }        
        return null;
    }
    
    public User restPassword(String email, String path, String url)
    {      
        User user = null;
        //UserDB userDB = new UserDB();
        UserService us = new UserService();
        String uuid = UUID.randomUUID().toString();
        String link = url + "?uuid=" + uuid;
        
        try 
        {
            user = us.getUserByEmail(email);
            
            String firstname = user.getFirstname();
            String lastname = user.getLastname(); 
            user.setResetPasswordUUID(uuid);
            
            try {
                us.update(user);
            } catch (Exception ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            HashMap<String, String> contents;
            
            contents = new HashMap<>();
            contents.put("link", link);
            contents.put("firstname", firstname);   
            contents.put("lastname", lastname);
            
            try {
                WebMailService.sendMail(email, "NotesKeepr Login", path + "/emailtemplates/resetpassword.html", contents);
            } catch (IOException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return user;
        } 
        catch (NotesDBException ex) 
        {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {    
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean changePassword(String uuid, String password) 
    {
        UserService us = new UserService();
        try {
            User user = us.getByUUID(uuid);
            if(user==null) return false;
            user.setPassword(password);
            user.setResetPasswordUUID(null);
            //UserService us = new UserService();
            us.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
