package helpers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImagenHelper {

    
    /** 
     * Metodo que recibe un Blob(un binary stream) y lo convierte a una imagen para poder ser mostrada en la interfaz
     * @param productoImagen blob de la imagen en la base de datos
     * @return BufferedImage imagen del blob
     */
    public static BufferedImage getProductoImagen(Blob productoImagen) {
        
        try {
            if (productoImagen == null) {
                File img = new File("assets" + File.separator + "logo.jpg");
                BufferedImage image = ImageIO.read(img);
                return image;
            }
            java.io.InputStream in = productoImagen.getBinaryStream();
            BufferedImage image;
            image = ImageIO.read(in);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    /** 
     * Metodo que recibe un icono y le hace un resize
     * @param imagen imagen que se quiere cambiar el tamaño
     * @return ImageIcon imagen con el resize aplicado
     */
    public static ImageIcon resizeImagen(ImageIcon imagen) {
        Image image = imagen.getImage();
        Image nuevaImagen = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImagen);
    }
}
