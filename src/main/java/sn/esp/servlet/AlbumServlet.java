package sn.esp.servlet;

import sn.esp.entities.Album;
import sn.esp.entities.Image;
import sn.esp.entities.UserAlbum;
import sn.esp.entities.Utilisateur;
import sn.esp.form.AddAlbumForm;
import sn.esp.form.PrivilegeForm;
import sn.esp.interf.AlbumInterface;
import sn.esp.interf.ImageInterface;
import sn.esp.interf.UserAlbumInterface;
import sn.esp.interf.UserInterface;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Album", value = {"/albums", "/add-album", "/imagesAlbum", "/modify-album", "/supprimer-album", "/privileges", "/donner-privileges", "/supp-privileges"})
public class AlbumServlet extends HttpServlet {

    private static final String VUE_ALBUM = "/jsp/albums.jsp";
    private static final String VUE_ADD_ALBUM = "/jsp/addAlbum.jsp";
    public static final String VUE_IMAGEALBUM = "/jsp/imagesAlbum.jsp";
    public static final String VUE_MODIFY_ALBUM = "/jsp/modifyAlbum.jsp";
    public static final String VUE_PRIVILEGES = "/jsp/privileges.jsp";


    @EJB
    private AlbumInterface albumInterface;
    @EJB
    private ImageInterface imageInterface;
    @EJB
    private UserInterface userInterface;
    @EJB
    UserAlbumInterface userAlbumInterface;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        switch (path) {
            case "/albums":

                HttpSession session = request.getSession();
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");
                List<Album> albumList = new ArrayList<>();


                if (utilisateur.getRole().equals("ROLE_ADMIN")) {
                    albumList = albumInterface.findAll();
                } else {
                    List<Album> albumPrivateAccess = albumInterface.getAllOfAlbumsAccess(utilisateur.getId());
                    List<Album> albumPublic = albumInterface.getAllOfAlbums("PUBLIC");
                    List<Album> myAlbum = albumInterface.getMyAlbum(utilisateur, "PRIVEE");


                    albumList.addAll(albumPublic);
                    albumList.addAll(myAlbum);
                    albumList.addAll(albumPrivateAccess);

                }
                request.setAttribute("albums", albumList);
                getServletContext().getRequestDispatcher(VUE_ALBUM).forward(request, response);

                break;
            case "/imagesAlbum": {
                String parametre = request.getParameter("id");


                Album album = albumInterface.findById(Long.parseLong(parametre));

                request.setAttribute("album", album);

                List<Image> imageList = imageInterface.getAllImagesByAlbum(album);

                request.setAttribute("imageList", imageList);

                getServletContext().getRequestDispatcher(VUE_IMAGEALBUM).forward(request, response);

                break;
            }
            case "/modify-album": {
                String parametre = request.getParameter("id");

                Album album = albumInterface.findById(Long.parseLong(parametre));

                request.setAttribute("album", album);

                getServletContext().getRequestDispatcher(VUE_MODIFY_ALBUM).forward(request, response);

                break;
            }
            case "/supprimer-album": {
                String parametre = request.getParameter("id");

                Album album = albumInterface.findById(Long.parseLong(parametre));

                List<Image> imageList = imageInterface.getAllImagesByAlbum(album);

                for (Image image : imageList) {
                    String name = "image-" + image.getId() + "-album" + parametre + ".png";
                    Files.delete(Paths.get("/Users/macbook/Desktop/Project/J2EE/Album Project/src/main/webapp/images/" + name));
                }

                albumInterface.deleteById(album.getId());


                response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/albums");


                break;
            }
            case "/privileges": {
                List<Utilisateur> users = userInterface.findAll();

                List<UserAlbum> userAlbums = userAlbumInterface.findAll();

                request.setAttribute("userAlbums", userAlbums);
                request.setAttribute("users", users);
                getServletContext().getRequestDispatcher(VUE_PRIVILEGES).forward(request, response);

                break;
            }
            case "/donner-privileges": {
                String idUser = request.getParameter("idUser");
                String idAlbum = request.getParameter("idAlbum");
                Album album = albumInterface.findById(Long.parseLong(idAlbum));
                Utilisateur user = userInterface.findById(Long.parseLong(idUser));
                PrivilegeForm privilegeForm = new PrivilegeForm(userAlbumInterface);
                boolean existe = privilegeForm.verifierPrivilege(album, user);
                if (existe) {
                    boolean status = privilegeForm.donnerPrivileges(album, user);
                    privilegeServlet(request, response, idAlbum, status);

                } else {
                    response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/privileges?id=" + idAlbum);

                }


                break;
            }
            case "/supp-privileges": {
                String idUser = request.getParameter("idUser");
                String idAlbum = request.getParameter("idAlbum");
                Album album = albumInterface.findById(Long.parseLong(idAlbum));
                Utilisateur user = userInterface.findById(Long.parseLong(idUser));
                PrivilegeForm privilegeForm = new PrivilegeForm(userAlbumInterface);
                boolean status = privilegeForm.supprimerPrivilege(album, user);
                privilegeServlet(request, response, idAlbum, status);


                break;
            }
            default:
                getServletContext().getRequestDispatcher(VUE_ADD_ALBUM).forward(request, response);

                break;
        }

    }

    private void privilegeServlet(HttpServletRequest request, HttpServletResponse response, String idAlbum, boolean status) throws ServletException, IOException {
        if (!status) {
            request.setAttribute("message", "Action refusée");
            getServletContext().getRequestDispatcher(VUE_PRIVILEGES).forward(request, response);

        } else {
            response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/privileges?id=" + idAlbum);
        }
        List<Utilisateur> users = userInterface.findAll();
        request.setAttribute("users", users);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        AddAlbumForm addAlbumForm = new AddAlbumForm(albumInterface);
        if (path.equals("/add-album")) {

            addAlbumForm.addAlbum(request);
        } else {

            addAlbumForm.modifyAlbum(request);
        }
        getServletContext().getRequestDispatcher(VUE_ADD_ALBUM).forward(request, response);
    }
}
