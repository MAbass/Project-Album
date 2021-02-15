package sn.esp.form;

import sn.esp.entities.Album;
import sn.esp.entities.Utilisateur;
import sn.esp.interf.AlbumInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddAlbumForm {

    private static final String CHAMP_NOM_ALBUM = "nom";
    private static final String CHAMP_ID = "id";
    private static final String CHAMP_STATUS_ALBUM = "status";
    private final AlbumInterface albumInterface;

    public AddAlbumForm(AlbumInterface albumInterface) {
        this.albumInterface = albumInterface;
    }

    public void addAlbum(HttpServletRequest request) {
        String nom = request.getParameter(CHAMP_NOM_ALBUM);
        String status = request.getParameter(CHAMP_STATUS_ALBUM);

        HttpSession session = request.getSession();

        Utilisateur user = ((Utilisateur) session.getAttribute("sessionUser"));

        Album album = new Album(nom, status, user);

        boolean result = this.albumInterface.ajouter(album);
        if (result){
            request.setAttribute("message", "Ajout effectuée");
        }else {
            request.setAttribute("message", "Erreur lors de l'ajout");
        }


    }

    public void modifyAlbum(HttpServletRequest request) {
        String nom = request.getParameter(CHAMP_NOM_ALBUM);
        String status = request.getParameter(CHAMP_STATUS_ALBUM);
        String id = request.getParameter(CHAMP_ID);

        Album album = this.albumInterface.findById(Long.parseLong(id));

        album.setNom(nom);
        album.setStatus(status);


        boolean result = this.albumInterface.modifier(album);
        if (result){
            request.setAttribute("message", "Modification effectuée");
        }else {
            request.setAttribute("message", "Erreur lors de la modification");
        }

    }
}
