package sn.esp.form;

import sn.esp.entities.Utilisateur;
import sn.esp.interf.UserInterface;
import sn.esp.utility.CryptPassword;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

public class RegistrationForm {

    public static final String CHAMP_NOM = "nom";
    public static final String CHAMP_PRENOM = "prenom";
    public static final String CHAMP_MAIL = "mail";
    public static final String CHAMP_LOGIN = "login";
    public static final String CHAMP_PASSWORD = "password";
    private static final String CHAMP_ROLE = "role";
    private static final String CHAMP_ID = "id";
    UserInterface userInterface;

    public RegistrationForm(UserInterface userInterface) {
        this.userInterface = userInterface;
    }


    public void ajouter(HttpServletRequest request) throws NoSuchAlgorithmException {
        String nom = request.getParameter(CHAMP_NOM);
        String prenom = request.getParameter(CHAMP_PRENOM);
        String mail = request.getParameter(CHAMP_MAIL);
        String login = request.getParameter(CHAMP_LOGIN);
        String password = CryptPassword.toHexString(CryptPassword.getSHA(request.getParameter(CHAMP_PASSWORD)));
        String role = request.getParameter(CHAMP_ROLE);
        Utilisateur user = new Utilisateur(nom, prenom, login, mail, password, role);

        if (emailAndLoginVerification(request, mail, login)) return;

        boolean status = this.userInterface.ajouter(user);

        if (status) {
            request.setAttribute("message", "Ajout effectuée");
        } else {
            request.setAttribute("message", "Erreur lors de l'ajout");
        }

    }

    public void modifier(HttpServletRequest request) {
        String role = request.getParameter(CHAMP_ROLE);
        String id = request.getParameter(CHAMP_ID);

        Utilisateur user = this.userInterface.findById(Long.parseLong(id));

        user.setRole(role);

        boolean status = this.userInterface.modifier(user);

        if (status) {
            request.setAttribute("message", "Modification effectuée");
        } else {
            request.setAttribute("message", "Erreur lors de la modification");
        }

    }

    public void profile(HttpServletRequest request) throws NoSuchAlgorithmException {
        String nom = request.getParameter(CHAMP_NOM);
        String prenom = request.getParameter(CHAMP_PRENOM);
        String mail = request.getParameter(CHAMP_MAIL);
        String login = request.getParameter(CHAMP_LOGIN);
        String password = CryptPassword.toHexString(CryptPassword.getSHA(request.getParameter(CHAMP_PASSWORD)));
        String id = request.getParameter(CHAMP_ID);

        Utilisateur user = this.userInterface.findById(Long.parseLong(id));

        if (emailAndLoginVerification(request, mail, login)) return;


        user.setNom(nom);
        user.setMail(mail);
        user.setPrenom(prenom);
        user.setLogin(login);
        user.setPassword(password);

        boolean status = this.userInterface.modifierProfile(user);

        if (status) {
            request.setAttribute("message", "Modification effectuée");
        } else {
            request.setAttribute("message", "Erreur lors de la modification");
        }

    }

    private boolean emailAndLoginVerification(HttpServletRequest request, String mail, String login) {
        Utilisateur loginExiste;
        Utilisateur email;
        loginExiste = this.userInterface.getUserByLogin(login);
        email = this.userInterface.getUserByMail(mail);


        if (loginExiste != null) {
            request.setAttribute("message", "Cet Login existe déja");
            return true;
        }
        if (email != null) {
            request.setAttribute("message", "Cet email existe déja");
            return true;
        }
        return false;
    }
}
