package sn.esp.dao;

import sn.esp.entities.Album;
import sn.esp.entities.Image;
import sn.esp.interf.ImageInterface;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ImageDao implements ImageInterface {

    @PersistenceContext
    EntityManager entityManager;

    String findByTitre = "Image.findByTitre";
    String findByAlbum = "Image.findByAlbum";
    String findById = "Image.findById";
    String updateSimple = "Image.updateSimple";
    String updateComplet = "Image.updateComplet";
    String deleteById = "Image.deleteById";
    String getLastInsert = "Image.getLastInsert";

    @Override
    public boolean ajouter(Image img) {
        try {
            entityManager.persist(img);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public Image getByTitre(String titre) {
        Query query = entityManager.createNamedQuery(findByTitre);
        query.setParameter("titre", titre);

        return (Image) query.getSingleResult();
    }

    @Override
    public List<Image> getAllImagesByAlbum(Album album) {
        Query query = entityManager.createNamedQuery(findByAlbum);
        query.setParameter("album", album);

        return (List<Image>) query.getResultList();
    }

    @Override
    public Image findById(long id) {
        Query query = entityManager.createNamedQuery(findById);
        query.setParameter("id", id);

        return (Image) query.getSingleResult();

    }

    @Override
    public boolean modifier(Image image) {
        Query query = entityManager.createNamedQuery(updateSimple);
        query.setParameter("titre", image.getTitre());
        query.setParameter("desc", image.getDescription());
        query.setParameter("type", image.getPhotoType());
        query.setParameter("motscles", image.getMotscles());
        query.setParameter("id", image.getId());
        query.setParameter("dateModification", image.getDateModification());

        int status = query.executeUpdate();

        return status == 1;
    }

    @Override
    public boolean modifierComplet(Image image) {
        Query query = entityManager.createNamedQuery(updateComplet);
        query.setParameter("titre", image.getTitre());
        query.setParameter("desc", image.getDescription());
        query.setParameter("type", image.getPhotoType());
        query.setParameter("motscles", image.getMotscles());
        query.setParameter("hauteur", image.getHauteur());
        query.setParameter("longueur", image.getLongueur());
        query.setParameter("id", image.getId());
        query.setParameter("dateModification", image.getDateModification());


        int status = query.executeUpdate();

        return status ==1;
    }

    @Override
    public boolean supprimer(long id) {
        Query query = entityManager.createNamedQuery(deleteById);
        query.setParameter("id", id);

        int status = query.executeUpdate();
        return status ==1;
    }

    @Override
    public Image getLastInsert() {
        Query query = entityManager.createNamedQuery(getLastInsert);
        query.setMaxResults(1);

        return (Image) query.getSingleResult();
    }
}
