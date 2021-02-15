package sn.esp.form;

import sn.esp.entities.Album;
import sn.esp.entities.Utilisateur;
import sn.esp.entities.UserAlbum;
import sn.esp.interf.UserAlbumInterface;


public class PrivilegeForm {

    private final UserAlbumInterface userAlbumInterface;

    public PrivilegeForm(UserAlbumInterface userAlbumInterface) {
        this.userAlbumInterface = userAlbumInterface;
    }

    public boolean donnerPrivileges(Album album, Utilisateur user) {

        UserAlbum userAlbum = new UserAlbum(user, album);

        boolean status = this.userAlbumInterface.ajouter(userAlbum);

        return status;
    }

    public boolean supprimerPrivilege(Album album, Utilisateur user) {

        UserAlbum userAlbum = new UserAlbum(user, album);
        boolean status = this.userAlbumInterface.supprimer(userAlbum);

        return status;

    }

    public boolean verifierPrivilege(Album album, Utilisateur user) {

        UserAlbum userAlbum = new UserAlbum(user, album);
        boolean status = this.userAlbumInterface.verifier(userAlbum);

        return status;


    }
}
