import javax.swing.*;
import java.awt.*;

public class PanelConImagen extends JPanel {
   
        private Image imagenFondo;

        public PanelConImagen(String rutaImagen) {
            ImageIcon icon = new ImageIcon(rutaImagen);
            imagenFondo = icon.getImage();
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
