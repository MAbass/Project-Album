package sn.esp.filter;

import sn.esp.entities.Album;
import sn.esp.entities.Image;
import sn.esp.entities.Utilisateur;
import sn.esp.interf.AlbumInterface;
import sn.esp.interf.ImageInterface;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "user-filter", value = {"/profile", "/logout", "/albums", "/add-album", "/imagesAlbum", "/modify-album", "/supprimer-album", "/privileges", "/donner-privileges", "/supp-privileges", "/ajouter-image", "/modifier-image", "/supprimer-image"})
public class UserFilter implements Filter {

    @EJB
    AlbumInterface albumInterface;
    @EJB
    ImageInterface imageInterface;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Utilisateur user = (Utilisateur) session.getAttribute("sessionUser");
        String path = request.getServletPath();

        if (user == null) {
            String id = request.getParameter("id");
            Album album = this.albumInterface.findById(Long.parseLong(id));
            if (album.getStatus().equals("PUBLIC")) {
                filterChain.doFilter(request, response);

            } else {
                response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/erreur");

            }

        } else {
            if (user.getRole().equals("ROLE_ADMIN")) {
                filterChain.doFilter(request, response);
                return;
            }
            if (!user.getRole().equals("ROLE_USER")) {
                response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/erreur");

            } else {
                if (path.equals("/modify-album") || path.equals("/supprimer-album") || path.equals("/privileges") || path.equals("/donner-privileges") || path.equals("/supp-privileges") || path.equals("/ajouter-image") || path.equals("/modifier-image") || path.equals("/supprimer-image")) {
                    String idAlbum = request.getParameter("id");
                    String idImage = request.getParameter("idImage");
                    String idAlbum2 = request.getParameter("idAlbum");

                    if (filterAlbum(response, user, idAlbum)) return;
                    if (filterAlbum(response, user, idAlbum2)) return;

                    if (idImage != null) {
                        Image image = imageInterface.findById(Long.parseLong(idImage));

                        if (image != null) {

                            if (!image.getAlbum_appartenant().getUser().getId().equals(user.getId())) {
                                response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/erreur");
                                return;
                            }
                        }

                    }
                }
                filterChain.doFilter(request, response);
            }
        }
    }

    private boolean filterAlbum(HttpServletResponse response, Utilisateur user, String idAlbum2) throws IOException {
        if (idAlbum2 != null) {
            Album album = albumInterface.findById(Long.parseLong(idAlbum2));
            if (album != null) {
                if (!album.getUser().getId().equals(user.getId())) {
                    response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/erreur");
                    return true;

                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}