package helpers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagenHelper {

    public static BufferedImage getProductoImagen(Blob productoImagen) {
        try {
            java.io.InputStream in = productoImagen.getBinaryStream();
            BufferedImage image;
            image = ImageIO.read(in);
            return image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon resizeImagen(ImageIcon imagen) {
        Image image = imagen.getImage();
        Image nuevaImagen = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImagen);
    }
}
