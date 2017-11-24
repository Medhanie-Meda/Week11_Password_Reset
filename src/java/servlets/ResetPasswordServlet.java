package servlets;


import businesslogic.AccountService;
import businesslogic.UserService;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uuid = request.getParameter("uuid");
        String url = "/WEB-INF/reset.jsp";
        if(uuid != null)
        {
            request.setAttribute("uuid", uuid);
            url = "/WEB-INF/resetNewPassword.jsp";
        }        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        User user = null;
        UserService us = new UserService();
        AccountService as = new AccountService();
        
        if(action != null && action.equals("resetpass"))
        {
           String email = request.getParameter("toResetEmail");            
           String url = request.getRequestURL().toString();
           String path = getServletContext().getRealPath("/WEB-INF");
            
            try {
                user = us.getUserByEmail(email);
            } catch (Exception ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            boolean res;
           user = as.restPassword(email, path, url);
            if(user != null)
            {   
                request.setAttribute("message", "Please check your email.");                                
            }
            else 
            {
                request.setAttribute("message", "Email address does not exist."); 
            }
        }    
        else if(action != null && action.equals("getnewpassword"))
        {
            String newPassowrd = request.getParameter("newPassord");
            String uuid = request.getParameter("uuid");
            boolean changePassword = as.changePassword(uuid, newPassowrd);
            
            if(changePassword == true)
            {
                request.setAttribute("message", "You changed your password successfully.");
            }
            else
            {
                request.setAttribute("message", "Sorry. Your password is not changed.");
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }    
}
