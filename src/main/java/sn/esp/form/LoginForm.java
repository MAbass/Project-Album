package sn.esp.form;

import sn.esp.entities.Utilisateur;
import sn.esp.interf.UserInterface;
import sn.esp.utility.CryptPassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public class LoginForm {
    private final UserInterface userInterface;
    public static final String CHAMP_USERNAME = "username";
    public static final String CHAMP_PASSWORD = "password";

    public LoginForm(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void seConnecter(HttpServletRequest request) throws NoSuchAlgorithmException {

        String login = request.getParameter(CHAMP_USERNAME);
        String password = CryptPassword.toHexString(CryptPassword.getSHA(request.getParameter(CHAMP_PASSWORD)));

        Utilisateur user = this.userInterface.getUserByLogin(login);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", user);
                request.setAttribute("connexion", "Connexion Reussie");

            } else {
                request.setAttribute("connexion", "Cet utilisateur n'existe pas");

            }
        } else {
            request.setAttribute("connexion", "Cet utilisateur n'existe pas");
        }
    }
}
