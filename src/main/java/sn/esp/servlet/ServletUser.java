package sn.esp.servlet;

import sn.esp.entities.Album;
import sn.esp.entities.Utilisateur;
import sn.esp.form.RegistrationForm;
import sn.esp.interf.AlbumInterface;
import sn.esp.interf.UserInterface;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", value = {"/acceuil", "/createAccount","/profile", "/erreur"})
public class ServletUser extends HttpServlet {

    public static final String VUE_INDEX = "/jsp/index.jsp";
    public static final String VUE_INSCRIPTION = "/jsp/inscription.jsp";
    public static final String VUE_PROFILE = "/jsp/profile.jsp";
    public static final String ERREUR_PAGE = "/404error/index.jsp";

    @EJB
    private UserInterface userInterface;
    @EJB
    private AlbumInterface albumInterface;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        switch (path) {
            case "/acceuil":

                List<Album> albumList = albumInterface.getAllOfAlbums("PUBLIC");
                request.setAttribute("albums", albumList);

                getServletContext().getRequestDispatcher(VUE_INDEX).forward(request, response);
                break;
            case "/profile":
                String id = request.getParameter("id");

                Utilisateur user = userInterface.findById(Long.parseLong(id));

                request.setAttribute("user", user);
                getServletContext().getRequestDispatcher(VUE_PROFILE).forward(request, response);

                break;
            case "/erreur":

                getServletContext().getRequestDispatcher(ERREUR_PAGE).forward(request, response);

                break;
            default:
                getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);

                break;
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        if (path.equals("/createAccount")){
            RegistrationForm registrationForm = new RegistrationForm(userInterface);
            registrationForm.ajouter(request);
            getServletContext().getRequestDispatcher(VUE_INSCRIPTION).forward(request, response);

        }else {
            RegistrationForm registrationForm = new RegistrationForm(userInterface);
            registrationForm.profile(request);
            getServletContext().getRequestDispatcher(VUE_PROFILE).forward(request, response);

        }

    }
}
