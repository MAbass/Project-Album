package sn.esp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "photo")
@NamedQueries({
        @NamedQuery(name = "Image.findByTitre", query = "SELECT im FROM Image im WHERE im.titre= :titre"),
        @NamedQuery(name = "Image.findById", query = "SELECT im FROM Image im WHERE im.id= :id"),
        @NamedQuery(name = "Image.getLastInsert", query = "SELECT im FROM Image im ORDER BY im.id DESC"),
        @NamedQuery(name = "Image.deleteById", query = "DELETE FROM Image im WHERE im.id= :id"),
        @NamedQuery(name = "Image.findByAlbum", query = "SELECT im FROM Image im WHERE im.album_appartenant= :album"),
        @NamedQuery(name = "Image.updateSimple", query = "UPDATE Image im SET im.titre=:titre, im.photoType=:type,im.description=:desc, im.motscles=:motscles, im.dateModification=:dateModification WHERE im.id=:id"),
        @NamedQuery(name = "Image.updateComplet", query = "UPDATE Image im SET im.titre=:titre, im.photoType=:type,im.description=:desc, im.motscles=:motscles, im.hauteur=:hauteur, im.longueur=:longueur, im.dateModification=:dateModification WHERE im.id=:id"),

})
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "photoType")
    private String photoType;

    @Column(name = "descript")
    private String description;

    @Column(name = "motscles")
    private String motscles;

    @Column(name = "hauteur")
    private int hauteur;

    @Column(name = "longueur")
    private int longueur;

    @Column(name = "dateCreation")
    private String dateCreation;

    @Column(name = "dateModification")
    private String dateModification;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album_appartenant;

    public Image() {
    }

    public Image(String titre, String photoType, String description, String motscles, Album album_appartenant, int hauteur, int longueur, String dateCreation, String dateModification) {
        this.hauteur = hauteur;
        this.longueur = longueur;
        this.titre = titre;
        this.photoType = photoType;
        this.description = description;
        this.motscles = motscles;
        this.album_appartenant = album_appartenant;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    public Image(String titre, String type, String desc, String motscles, int height, int width, String dateCreation, String dateModification) {
        this.hauteur = height;
        this.longueur = width;
        this.titre = titre;
        this.photoType = type;
        this.description = desc;
        this.motscles = motscles;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;


    }

    public Image(int id, String titre, String type, String desc, String motscles, int height, int width) {
        this.id = id;
        this.hauteur = height;
        this.longueur = width;
        this.titre = titre;
        this.photoType = type;
        this.description = desc;
        this.motscles = motscles;

    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public String getMotscles() {
        return motscles;
    }

    public void setMotscles(String motscles) {
        this.motscles = motscles;
    }

    public Album getAlbum_appartenant() {
        return album_appartenant;
    }

    public void setAlbum_appartenant(Album album_appartenant) {
        this.album_appartenant = album_appartenant;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateModification() {
        return dateModification;
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", photoType='" + photoType + '\'' +
                ", description='" + description + '\'' +
                ", motscles='" + motscles + '\'' +
                ", hauteur=" + hauteur +
                ", longueur=" + longueur +
                ", album_appartenant=" + album_appartenant +
                '}';
    }
}
