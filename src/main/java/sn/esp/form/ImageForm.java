package sn.esp.form;


import sn.esp.entities.Album;
import sn.esp.entities.Image;
import sn.esp.interf.AlbumInterface;
import sn.esp.interf.ImageInterface;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImageForm {

    private static final String CHAMP_ID = "id";
    private static final String CHAMP_DESC = "desc";
    private static final String CHAMP_TITRE = "titre";
    private static final String CHAMP_CLES = "motscles";
    private static final String CHAMP_TYPE = "type";
    private static final String CHAMP_ID_ALBUM = "idAlbum";
    private static final String CHAMP_ID_IMAGE = "idImage";
    private final ImageInterface imageInterface;
    private final AlbumInterface albumInterface;

    public ImageForm(ImageInterface imageInterface, AlbumInterface albumInterface) {
        this.imageInterface = imageInterface;
        this.albumInterface = albumInterface;
    }

    public boolean ajouter(HttpServletRequest request) throws IOException, ServletException {
        String id = request.getParameter(CHAMP_ID);
        String titre = request.getParameter(CHAMP_TITRE);
        String desc = request.getParameter(CHAMP_DESC);
        String type = request.getParameter(CHAMP_TYPE);
        String motscles = request.getParameter(CHAMP_CLES);
        Part image = request.getPart("image");

        Album album = albumInterface.findById(Long.parseLong(id));

        InputStream initialStream = image.getInputStream();

        byte[] bytes = readBytes(initialStream);

        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentTime = formatter.format(date);

        Image img = new Image(titre, type, desc, motscles, album, bufferedImage.getHeight(), bufferedImage.getWidth(), currentTime, currentTime);

        boolean status = this.imageInterface.ajouter(img);
        if (status) {
            request.setAttribute("message", "Ajout effectuée");
        } else {
            request.setAttribute("message", "Erreur lors de l'ajout");
            return false;
        }
        Image lastImage = this.imageInterface.getLastInsert();

        try {
            String name = "image-" + lastImage.getId() + "-album" + id + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("/Users/macbook/Desktop/Project/J2EE/Album Project/src/main/webapp/images/" + name));

            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    public static byte[] readBytes(InputStream stream) throws IOException {
        if (stream == null) return new byte[]{};
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        boolean error = false;
        try {
            int numRead;
            while ((numRead = stream.read(buffer)) > -1) {
                output.write(buffer, 0, numRead);
            }
        } catch (IOException | RuntimeException e) {
            error = true; // this error should be thrown, even if there is an error closing stream
            throw e;
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                if (!error) throw e;
            }
        }
        output.flush();
        return output.toByteArray();
    }

    public boolean modifier(HttpServletRequest request) throws IOException, ServletException {
        String idImage = request.getParameter(CHAMP_ID_IMAGE);
        String idAlbum = request.getParameter(CHAMP_ID_ALBUM);
        String titre = request.getParameter(CHAMP_TITRE);
        String desc = request.getParameter(CHAMP_DESC);
        String type = request.getParameter(CHAMP_TYPE);
        String motscles = request.getParameter(CHAMP_CLES);
        Part photo = request.getPart("image");

        Image image = this.imageInterface.findById(Long.parseLong(idImage));

        image.setDescription(desc);
        image.setPhotoType(type);
        image.setTitre(titre);
        image.setMotscles(motscles);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentTime = formatter.format(date);

        image.setDateModification(currentTime);

        long size = photo.getSize();

        if (size == 0){

            boolean status = this.imageInterface.modifier(image);
            if (status) {
                request.setAttribute("message", "Modification effectuée");
            } else {
                request.setAttribute("message", "Erreur lors de la modification");
                return false;
            }

        }else {
            InputStream initialStream = photo.getInputStream();

            byte[] bytes = readBytes(initialStream);

            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
            image.setHauteur(bufferedImage.getHeight());
            image.setLongueur(bufferedImage.getWidth());

            boolean status = this.imageInterface.modifierComplet(image);
            if (status) {
                request.setAttribute("message", "Modification effectuée");
            } else {
                request.setAttribute("message", "Erreur lors de la modification");
                return false;
            }

            try {
                String name = "image-" + idImage + "-album" + idAlbum + ".png";
                Files.delete(Paths.get("/Users/macbook/Desktop/Project/J2EE/Album Project/src/main/webapp/images/" + name));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("/Users/macbook/Desktop/Project/J2EE/Album Project/src/main/webapp/images/" + name));

                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return true;
    }
}
