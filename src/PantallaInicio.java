import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {

    public PantallaInicio() {
        setTitle("Ahorcado de Stitch - Bienvenido");
        setSize(520, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Panel superior con imagen y título
        JPanel panelSuperior = new JPanel();
        panelSuperior.setOpaque(false);
        PanelConImagen fondo = new PanelConImagen("imagenes/fondoAhorcado.png");
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // Título en la parte inferior del panel superior
        JLabel titulo = new JLabel();

        ImageIcon iconoLogo = new ImageIcon("imagenes/AhorcadoLOGO.png");
        Image logoEscalado = iconoLogo.getImage().getScaledInstance( 250, 200, Image.SCALE_SMOOTH);
        titulo.setIcon(new ImageIcon(logoEscalado));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(
        BorderFactory.createEmptyBorder(10, 0, 15, 0));
        panelSuperior.add(
                titulo,
                BorderLayout.CENTER
            );

        add(panelSuperior, BorderLayout.NORTH);

        // Panel central con botones
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);
        centro.setBorder(BorderFactory.createEmptyBorder(30, 50, 40, 50));

        Font btnFont = new Font("Arial", Font.BOLD, 20);

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setFont(btnFont);
        btnRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistro.setBackground(new Color(30, 60, 90));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setFocusPainted(false);
        btnRegistro.setBorder(BorderFactory.createLineBorder(new Color(255, 230, 150), 2));
        btnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(btnFont);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setBackground(new Color(30, 60, 90));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createLineBorder(new Color(255, 230, 150), 2));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Añadir efectos hover simples (opcional)
        btnRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistro.setBackground(new Color(255, 230, 150));
                btnRegistro.setForeground(new Color(30, 60, 90));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistro.setBackground(new Color(30, 60, 90));
                btnRegistro.setForeground(Color.WHITE);
            }
        });
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(255, 230, 150));
                btnLogin.setForeground(new Color(30, 60, 90));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(30, 60, 90));
                btnLogin.setForeground(Color.WHITE);
            }
        });

        btnRegistro.addActionListener(e -> {
            new RegistroUsuario(this);
            setVisible(false);
        });

        btnLogin.addActionListener(e -> {
            new LoginUsuario(this);
            setVisible(false);
        });

        centro.add(btnRegistro);
        centro.add(Box.createVerticalStrut(30));
        centro.add(btnLogin);

        add(centro, BorderLayout.CENTER);

        // Fondo general claro con un borde elegante
        ((JComponent) getContentPane()).setBorder(BorderFactory.createLineBorder(new Color(30, 60, 90), 3));

        setVisible(true);
    }
}
