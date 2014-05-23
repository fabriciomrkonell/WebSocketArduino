package servlet;

import conexao.User;
import conexao.UserDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;


public class AutenticarService extends HttpServlet {
    
   public User authenticate(String login, String passwd) {
        User user = null;
        try {
            user = this.findAuthenticate(login, passwd);
        } catch (Exception e) {            
        }
        return user;
    }

    private User findAuthenticate(String theLogin, String thePasswd) throws Exception {        
        User user = this.findAuthenticateFullScan(theLogin, thePasswd);
        return user;
    }

    private User findAuthenticateFullScan(String theLogin, String thePasswd) throws Exception {
        User user = null;
        UserDAO dao = new UserDAO();
        ArrayList<User> users = (ArrayList) dao.findAll();
        for (User u : users) {
            if ((u.getEmail().equals(theLogin)) && (u.getSenha().equals(thePasswd))) {
                user = u;
                break;
            }
        }
        return user;
    }        
}