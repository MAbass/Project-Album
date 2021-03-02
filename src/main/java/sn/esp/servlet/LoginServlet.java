package sn.esp.servlet;

import sn.esp.form.LoginForm;
import sn.esp.form.RegistrationForm;
import sn.esp.interf.UserInterface;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "LoginServlet", value = {"/login","/logout" })
public class LoginServlet extends HttpServlet {
    private static final String VUE_LOGIN = "/jsp/login.jsp";

    @EJB
    private UserInterface userInterface;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        if (path.equals("/login")){

            getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
        }else if (path.equals("/logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/login");
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        if (path.equals("/login")){
            LoginForm loginForm = new LoginForm(userInterface);

            try {
                loginForm.seConnecter(request);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
        }

    }
}
