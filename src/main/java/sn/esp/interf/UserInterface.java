package sn.esp.interf;

import sn.esp.entities.Utilisateur;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserInterface {


    boolean ajouter(Utilisateur user);

    Utilisateur getUserByLogin(String login);

    List<Utilisateur> findAll();

    Utilisateur findById(long parseLong);

    boolean modifier(Utilisateur user);

    boolean deleteById(long parseLong);

    boolean modifierProfile(Utilisateur user);

    Utilisateur getUserByMail(String mail);
}
