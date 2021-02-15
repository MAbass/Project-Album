package sn.esp.interf;

import sn.esp.entities.Album;
import sn.esp.entities.Image;

import java.util.List;

public interface ImageInterface {
    boolean ajouter(Image img);

    Image getByTitre(String titre);
    List<Image> getAllImagesByAlbum(Album album);

    Image findById(long parseLong);

    boolean modifier(Image image);

    boolean modifierComplet(Image img);

    boolean supprimer(long parseLong);

    Image getLastInsert();
}
