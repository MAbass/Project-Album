package sn.esp.interf;

import sn.esp.entities.UserAlbum;

import java.util.List;

public interface UserAlbumInterface {
    boolean ajouter(UserAlbum userAlbum);

    List<UserAlbum> findAll();

    boolean supprimer(UserAlbum userAlbum);

    boolean verifier(UserAlbum userAlbum);
}
