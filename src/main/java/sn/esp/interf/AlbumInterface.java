package sn.esp.interf;

import sn.esp.entities.Album;
import sn.esp.entities.Utilisateur;

import java.util.List;

public interface AlbumInterface {
    boolean ajouter(Album album);

    List<Album> getAllOfAlbums(String status);

    List<Album> findAll();

    Album findById(long id);

    boolean modifier(Album album);

    boolean modifierPrivileges(Album album);

    List<Album> getAllOfAlbumsAccess(Long id);

    List<Album> getMyAlbum(Utilisateur utilisateur, String privee);

    boolean deleteById(long parseLong);

    boolean supprimer(Album album);

    List<Album> findAlbumByUser(Utilisateur user);
}
