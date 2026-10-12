import javax.swing.*;
import java.awt.*;

public class LoginUsuario extends JFrame {

    public LoginUsuario(PantallaInicio pantallaInicio, GestorUsuario gestor, java.util.function.Consumer<Usuario> abrirJuego) {
        setTitle("Iniciar Sesión");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

       PanelConImagen fondo = new PanelConImagen("imagenes/fondoLOGIN.png");
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Logo en lugar de título
        ImageIcon logoIcon = new ImageIcon("imagenes/LOGOLOGIN.png"); // Asegurate de tener bien la ruta
        Image logoEscalado = logoIcon.getImage().getScaledInstance(190, 70, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoEscalado));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(logoLabel);
        panel.add(Box.createVerticalStrut(10));

        // Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(Box.createVerticalStrut(25));

        // Botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(new Color(200, 230, 255));
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        Font btnFont = new Font("Arial", Font.BOLD, 16);
        btnIngresar.setFont(btnFont);
        btnCancelar.setFont(btnFont);

        btnIngresar.setBackground(new Color(30, 60, 90));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);

        btnCancelar.setBackground(new Color(180, 30, 30));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        botonesPanel.add(btnIngresar);
        botonesPanel.add(btnCancelar);

        panel.add(botonesPanel);
        add(panel, BorderLayout.CENTER);

        btnIngresar.addActionListener(e -> {

            String nombre = txtUsuario.getText().trim();
            String contraseña = new String(txtPassword.getPassword()).trim();

            if (nombre.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Completa todos los campos."
                );
                return;
            }

            try {
                Usuario usuario = gestor.login(nombre, contraseña);
                if (usuario != null) {
                    JOptionPane.showMessageDialog(this, "¡Bienvenido " + usuario.getNombre() + "!");
                    abrirJuego.accept(usuario);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }
            } catch (IllegalArgumentException | AccesoUsuariosException error) {
                JOptionPane.showMessageDialog(this, error.getMessage(),
                        "No se pudo iniciar sesión", JOptionPane.ERROR_MESSAGE);
            }
});

        btnCancelar.addActionListener(e -> {
            this.dispose();
            pantallaInicio.setVisible(true);
        });

        setVisible(true);
    }
}
