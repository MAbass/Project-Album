package sn.esp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userAlbum")
@NamedQueries({
        @NamedQuery(name = "UserAlbum.findAll", query = "SELECT ua FROM UserAlbum ua"),
        @NamedQuery(name = "UserAlbum.delete", query = "DELETE FROM UserAlbum ua where ua.albumUser=:album and ua.userAlbum=:user"),
        @NamedQuery(name = "UserAlbum.findUserAlbum", query = "SELECT ua FROM UserAlbum ua WHERE ua.albumUser=:album and ua.userAlbum=:user"),
})
public class UserAlbum implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userAlbumId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utilisateur userAlbum;

    @ManyToOne
    @JoinColumn(name = "albumUserId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album albumUser;

    public UserAlbum() {
    }

    public UserAlbum(Utilisateur userAlbum, Album albumUser) {
        this.userAlbum = userAlbum;
        this.albumUser = albumUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUserAlbum() {
        return userAlbum;
    }

    public void setUserAlbum(Utilisateur userAlbum) {
        this.userAlbum = userAlbum;
    }

    public Album getAlbumUser() {
        return albumUser;
    }

    public void setAlbumUser(Album albumUser) {
        this.albumUser = albumUser;
    }
}
