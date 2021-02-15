package sn.esp.servlet;

import sn.esp.entities.Utilisateur;
import sn.esp.form.RegistrationForm;
import sn.esp.interf.UserInterface;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin", value = {"/lists-utilisateurs","/modifier-user","/supprimer-user"})
public class AdminServlet extends HttpServlet {

    public static final String VUE_LISTER_USERS = "/jsp/listUsers.jsp";
    public static final String VUE_MODIFIER_USER = "/jsp/modifyUser.jsp";

    @EJB
    private UserInterface userInterface;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        if (path.equals("/lists-utilisateurs")){

            List<Utilisateur> users = userInterface.findAll();

            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher(VUE_LISTER_USERS).forward(request, response);

        }else if (path.equals("/modifier-user")){
            String id = request.getParameter("id");

            Utilisateur user = userInterface.findById(Long.parseLong(id));

            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher(VUE_MODIFIER_USER).forward(request, response);

        }else {
            String id = request.getParameter("id");

            boolean status = userInterface.deleteById(Long.parseLong(id));
            if (status){
                request.setAttribute("message", "Suppression reussie");

            }else {
                request.setAttribute("message", "Il a erreur lors de la suppression");

            }
            List<Utilisateur> users = userInterface.findAll();
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher(VUE_LISTER_USERS).forward(request, response);


        }



    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        if (path.equals("/modifier-user")){
            RegistrationForm registrationForm = new RegistrationForm(userInterface);
            registrationForm.modifier(request);
            String id = request.getParameter("id");
            Utilisateur user = userInterface.findById(Long.parseLong(id));
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher(VUE_MODIFIER_USER).forward(request, response);

        }
    }
}
