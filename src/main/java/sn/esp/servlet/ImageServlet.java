package sn.esp.servlet;

import sn.esp.entities.Image;
import sn.esp.form.ImageForm;
import sn.esp.interf.AlbumInterface;
import sn.esp.interf.ImageInterface;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "Image", value = {"/ajouter-image", "/modifier-image", "/supprimer-image"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ImageServlet extends HttpServlet {

    private static final String VUE_ADD_IMAGE = "/jsp/addImage.jsp";
    private static final String VUE_MODIFY_IMAGE = "/jsp/modifyImage.jsp";


    @EJB
    private ImageInterface imageInterface;
    @EJB
    private AlbumInterface albumInterface;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        if (path.equals("/ajouter-image")) {

            getServletContext().getRequestDispatcher(VUE_ADD_IMAGE).forward(request, response);

        } else if (path.equals("/supprimer-image")) {
            String imageId = request.getParameter("idImage");
            String albumId = request.getParameter("idAlbum");

            boolean status = imageInterface.supprimer(Long.parseLong(imageId));

            if (status){
                String name = "image-" + imageId + "-album" + albumId + ".png";
                Files.delete(Paths.get("/Users/macbook/Desktop/Project/J2EE/Album Project/src/main/webapp/images/" + name));
            }
            response.sendRedirect("/AlbumProject-1.0-SNAPSHOT/imagesAlbum?id="+albumId);


        } else {
            String imageId = request.getParameter("idImage");

            Image image = imageInterface.findById(Long.parseLong(imageId));
            request.setAttribute("image", image);
            getServletContext().getRequestDispatcher(VUE_MODIFY_IMAGE).forward(request, response);

        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        ImageForm imageForm = new ImageForm(imageInterface, albumInterface);
        if (path.equals("/ajouter-image")) {

            imageForm.ajouter(request);

        } else {

            imageForm.modifier(request);

        }
        getServletContext().getRequestDispatcher(VUE_ADD_IMAGE).forward(request, response);
    }
}
