package sn.esp.dao;

import sn.esp.entities.Album;
import sn.esp.entities.Utilisateur;
import sn.esp.interf.AlbumInterface;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AlbumDao implements AlbumInterface {

    @PersistenceContext
    EntityManager entityManager;

    String findAllByStatus = "Album.findAllByStatus";
    String findAll = "Album.findAll";
    String findById = "Album.findById";
    String deleteById = "Album.deleteById";
    String update = "Album.update";
    String privateAccess = "Album.privateAccess";
    String findByUserAndStatus = "Album.findByUserAndStatus";
    String findAlbumByUser = "Album.findAlbumByUser";


    @Override
    public boolean ajouter(Album album) {
        try {
            entityManager.persist(album);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public List<Album> getAllOfAlbums(String status) {
        Query query = entityManager.createNamedQuery(findAllByStatus);
        query.setParameter("status", status);


        return (List<Album>) query.getResultList();
    }

    @Override
    public List<Album> findAll() {
        Query query = entityManager.createNamedQuery(findAll);
        return (List<Album>) query.getResultList();
    }

    @Override
    public Album findById(long id) {
        Query query = entityManager.createNamedQuery(findById);
        query.setParameter("id", id);

        return (Album) query.getSingleResult();
    }

    @Override
    public boolean modifier(Album album) {
        Query query = entityManager.createNamedQuery(update);
        query.setParameter("status", album.getStatus());
        query.setParameter("nom", album.getNom());
        query.setParameter("id", album.getId());

        int status = query.executeUpdate();
        return status == 1;

    }

    @Override
    public boolean modifierPrivileges(Album album) {
        try {
            entityManager.merge(album);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public List<Album> getAllOfAlbumsAccess(Long id) {
        Query query = entityManager.createNamedQuery(privateAccess);
        query.setParameter("id", id);

        return (List<Album>) query.getResultList();
    }

    @Override
    public List<Album> getMyAlbum(Utilisateur utilisateur, String privee) {
        Query query = entityManager.createNamedQuery(findByUserAndStatus);
        query.setParameter("user", utilisateur);
        query.setParameter("status", privee);

        return (List<Album>) query.getResultList();

    }

    @Override
    public boolean deleteById(long id) {
        Query query = entityManager.createNamedQuery(deleteById);
        query.setParameter("id", id);

        int status = query.executeUpdate();

        return status == 1;
    }

    @Override
    public boolean supprimer(Album album) {
        try {
            Album album1 = entityManager.find(Album.class, album.getId());
            entityManager.remove(album1);
            return true;
        } catch (Exception e) {
            return false;

        }

    }

    @Override
    public List<Album> findAlbumByUser(Utilisateur user) {
        Query query = entityManager.createNamedQuery(findAlbumByUser);
        query.setParameter("id", user.getId());

        return (List<Album>) query.getResultList();
    }
}
