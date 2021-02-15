package sn.esp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "album")
@NamedQueries({
        @NamedQuery(name = "Album.findAllByStatus", query = "SELECT a FROM Album a WHERE a.status= :status"),
        @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
        @NamedQuery(name = "Album.findAlbumByUser", query = "SELECT a FROM Album a WHERE a.user.id=:id"),
        @NamedQuery(name = "Album.findById", query = "SELECT a FROM Album a where a.id =:id"),
        @NamedQuery(name = "Album.deleteById", query = "DELETE FROM Album a where a.id =:id"),
        @NamedQuery(name = "Album.findByUser", query = "SELECT a FROM Album a where a.user=:user"),
        @NamedQuery(name = "Album.findByUserAndStatus", query = "SELECT a FROM Album a where a.user=:user AND a.status=:status"),
        @NamedQuery(name = "Album.update", query = "UPDATE Album a set a.status=:status, a.nom=:nom where a.id=:id"),
        @NamedQuery(name = "Album.privateAccess", query = "SELECT ua.albumUser FROM  UserAlbum ua  INNER JOIN Utilisateur u ON u.id = ua.userAlbum.id Where u.id=:id"),
})
public class Album implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utilisateur user;

    @OneToMany(mappedBy = "albumUser", cascade = CascadeType.REMOVE)
    private List<UserAlbum> userList_accees_to_the_Album;

    @OneToMany(mappedBy = "album_appartenant", cascade = CascadeType.REMOVE)
    private List<Image> images_album;


    public Album() {
    }

    public Album(String nom, String status, Utilisateur user) {
        this.nom = nom;
        this.status = status;
        this.user = user;
    }

    public Album(Long id, String nom, String status, Utilisateur user) {
        this.id = id;
        this.nom = nom;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public List<UserAlbum> getUserList_accees_to_the_Album() {
        return userList_accees_to_the_Album;
    }

    public void setUserList_accees_to_the_Album(List<UserAlbum> userList_accees_to_the_Album) {
        this.userList_accees_to_the_Album = userList_accees_to_the_Album;
    }

    public List<Image> getImages_album() {
        return images_album;
    }

    public void setImages_album(List<Image> images_album) {
        this.images_album = images_album;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
