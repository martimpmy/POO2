import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class PanelConImagen extends JPanel {
   
    private Image imagenFondo;

    public PanelConImagen(String rutaImagen) {
        // Intentar diferentes rutas
        Image imagen = null;
        
        // Opción 1: Ruta relativa desde user.dir
        String rutaBase = System.getProperty("user.dir");
        File archivo = new File(rutaBase + File.separator + rutaImagen);
        
        if (archivo.exists()) {
            System.out.println("✓ Imagen encontrada en: " + archivo.getAbsolutePath());
            ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
            imagen = icon.getImage();
        } else {
            // Opción 2: Ruta desde recursos (si están en src)
            URL recurso = getClass().getResource("/" + rutaImagen);
            if (recurso != null) {
                System.out.println("✓ Imagen encontrada en recursos: " + recurso);
                ImageIcon icon = new ImageIcon(recurso);
                imagen = icon.getImage();
            } else {
                System.err.println("✗ Imagen no encontrada en ninguna ubicación");
                System.err.println("  Buscada en: " + archivo.getAbsolutePath());
            }
        }
        
        imagenFondo = imagen;
        setOpaque(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
