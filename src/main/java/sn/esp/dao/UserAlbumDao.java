package sn.esp.dao;

import sn.esp.entities.UserAlbum;
import sn.esp.interf.UserAlbumInterface;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserAlbumDao implements UserAlbumInterface {

    @PersistenceContext
    EntityManager entityManager;

    String findAll = "UserAlbum.findAll";
    String delete = "UserAlbum.delete";
    String findUserAlbum = "UserAlbum.findUserAlbum";

    @Override
    public boolean ajouter(UserAlbum userAlbum) {


        try {
            entityManager.persist(userAlbum);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public List<UserAlbum> findAll() {
        Query query = entityManager.createNamedQuery(findAll);

        return (List<UserAlbum>) query.getResultList();
    }

    @Override
    public boolean supprimer(UserAlbum userAlbum) {
        Query query = entityManager.createNamedQuery(delete);
        query.setParameter("album", userAlbum.getAlbumUser());
        query.setParameter("user", userAlbum.getUserAlbum());

        int status = query.executeUpdate();

        return status == 1;
    }

    @Override
    public boolean verifier(UserAlbum userAlbum) {
        Query query = entityManager.createNamedQuery(findUserAlbum);
        query.setParameter("album", userAlbum.getAlbumUser());
        query.setParameter("user", userAlbum.getUserAlbum());
        UserAlbum userAlbum1 = null;
        try {
        userAlbum1 = (UserAlbum) query.getSingleResult();

        }catch (NoResultException e){
            System.out.println("Non result for UserAlbum");
        }
        return userAlbum1 == null;

    }
}
