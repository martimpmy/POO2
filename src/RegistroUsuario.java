import javax.swing.*;
import java.awt.*;

public class RegistroUsuario extends JFrame {

    public RegistroUsuario(PantallaInicio pantallaInicio, GestorUsuario gestor) {
        setTitle("Registro de Usuario");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Fondo con imagen
        PanelConImagen fondo = new PanelConImagen("imagenes/fondoLOGIN.png");
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // Panel principal sobre el fondo
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Hacemos el panel transparente
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Logo en lugar del texto de título
        ImageIcon logoIcon = new ImageIcon("imagenes/LOGOREGISTRO.png");
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

        // Botones en un panel horizontal
        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(new Color(200, 230, 255));
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        // Estilos botones
        Font btnFont = new Font("Arial", Font.BOLD, 16);
        btnRegistrar.setFont(btnFont);
        btnCancelar.setFont(btnFont);

        btnRegistrar.setBackground(new Color(30, 60, 90));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);

        btnCancelar.setBackground(new Color(180, 30, 30));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        botonesPanel.add(btnRegistrar);
        botonesPanel.add(btnCancelar);

        panel.add(botonesPanel);
        add(panel, BorderLayout.CENTER);

        // Acciones
        btnRegistrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String contraseña = new String(txtPassword.getPassword()).trim();

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos.");
                return;
            }

            try {
                if (gestor.registrarUsuario(usuario, contraseña)) {
                    JOptionPane.showMessageDialog(this, "¡Registro exitoso!");
                    this.dispose();
                    pantallaInicio.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Ese usuario ya existe.");
                }
            } catch (IllegalArgumentException | AccesoUsuariosException error) {
                JOptionPane.showMessageDialog(this, error.getMessage(),
                        "No se pudo registrar", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> {
            this.dispose();
            pantallaInicio.setVisible(true);
        });

        setVisible(true);
    }

}
