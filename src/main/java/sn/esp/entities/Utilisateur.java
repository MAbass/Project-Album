package sn.esp.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "utilisateur")
@NamedQueries({
        @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.login=:login"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM Utilisateur u WHERE u.mail=:mail"),
        @NamedQuery(name = "User.findById", query = "SELECT u FROM Utilisateur u WHERE u.id=:id"),
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM Utilisateur u"),
        @NamedQuery(name = "User.updateRole", query = "UPDATE Utilisateur u set u.role=:role where u.id=:id"),
        @NamedQuery(name = "User.updateUser", query = "UPDATE Utilisateur u set u.nom=:nom , u.password=:password, u.prenom=:prenom, u.mail=:mail, u.login=:login where u.id=:id"),
        @NamedQuery(name = "User.deleteById", query = "DELETE FROM Utilisateur u where u.id=:id"),
})
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "login")
    private String login;
    @Column(name = "mail")
    private String mail;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Album> user_albums;

    @OneToMany(mappedBy = "userAlbum")
    List<UserAlbum> albumList_user_access;


    public Utilisateur() {
    }

    public Utilisateur(Long id, String nom, String prenom, String login, String mail, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String login, String mail, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.role = role;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Album> getUser_albums() {
        return user_albums;
    }

    public void setUser_albums(List<Album> user_albums) {
        this.user_albums = user_albums;
    }

    public List<UserAlbum> getAlbumList_user_access() {
        return albumList_user_access;
    }

    public void setAlbumList_user_access(List<UserAlbum> albumList_user_access) {
        this.albumList_user_access = albumList_user_access;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
