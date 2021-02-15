package sn.esp.dao;


import sn.esp.entities.Utilisateur;
import sn.esp.interf.UserInterface;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateful
public class UserDao implements UserInterface {


    @PersistenceContext()
    EntityManager entityManager;

    String findByLogin = "User.findByLogin";
    String findByEmail = "User.findByEmail";
    String findById = "User.findById";
    String findAll = "User.findAll";
    String updateRole = "User.updateRole";
    String deleteById = "User.deleteById";
    String updateUser = "User.updateUser";


    @Override
    public boolean ajouter(Utilisateur user) {
        try {
            entityManager.persist(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Utilisateur getUserByLogin(String login) {
        Query query = entityManager.createNamedQuery(findByLogin);
        query.setParameter("login", login);
        Utilisateur user = null;
        try {
            user = (Utilisateur) query.getSingleResult();

        } catch (Exception ignored) {

        }

        return user;
    }

    @Override
    public List<Utilisateur> findAll() {
        Query query = entityManager.createNamedQuery(findAll);

        return (List<Utilisateur>) query.getResultList();
    }

    @Override
    public Utilisateur findById(long id) {
        Query query = entityManager.createNamedQuery(findById);
        query.setParameter("id", id);
        Utilisateur user = null;
        try {
            user = (Utilisateur) query.getSingleResult();

        } catch (Exception ignored) {

        }

        return user;
    }

    @Override
    public boolean modifier(Utilisateur user) {
        Query query = entityManager.createNamedQuery(updateRole);
        query.setParameter("role", user.getRole());
        query.setParameter("id", user.getId());

        int status = query.executeUpdate();

        return status == 1;
    }

    @Override
    public boolean deleteById(long id) {
        Query query = entityManager.createNamedQuery(deleteById);
        query.setParameter("id", id);

        int status = query.executeUpdate();

        return status == 1;
    }

    @Override
    public boolean modifierProfile(Utilisateur user) {
        Query query = entityManager.createNamedQuery(updateUser);
        query.setParameter("id", user.getId());
        query.setParameter("nom", user.getNom());
        query.setParameter("prenom", user.getPrenom());
        query.setParameter("mail", user.getMail());
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());

        int status = query.executeUpdate();

        return status == 1;
    }

    @Override
    public Utilisateur getUserByMail(String mail) {
        Query query = entityManager.createNamedQuery(findByEmail);
        query.setParameter("mail", mail);
        Utilisateur user = null;
        try {
            user = (Utilisateur) query.getSingleResult();

        } catch (Exception ignored) {

        }

        return user;
    }
}
