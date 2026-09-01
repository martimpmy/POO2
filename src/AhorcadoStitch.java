import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;

public class AhorcadoStitch extends JFrame {

    private final java.util.List<Palabra> palabras = java.util.Arrays.asList(
        new Palabra("ohana", "Significa familia"),
        new Palabra("lilo", "Nombre de la nena hawaiana"),
        new Palabra("stitch", "Experimento alienígena azul"),
        new Palabra("nani", "Hermana mayor de Lilo"),
        new Palabra("aloha", "Hola y Chau en hawaiano"),
        new Palabra("hawai", "Hogar de Lilo"),
        new Palabra("surf", "Deporte que practica Nani"),
        new Palabra("tabla", "Lo que utilizan para surfear"),
        new Palabra("experimento", "Stitch fue un"),
        new Palabra("familia", "Nunca te abandona"),
        new Palabra("alien", "Seres de otro planeta"),
        new Palabra("extraterrestre", "Muchos personajes lo son"),
        new Palabra("adopcion", "Stitch encuentra su lugar en la Tierra gracias a este acto de amor."),
        new Palabra("hermanas", "Lilo y Nani"),
        new Palabra("perro", "Lo que Lilo cree haber adoptado"),
        new Palabra("nave", "Stitch viaja a la Tierra en una de estas"),
        new Palabra("mision", "Cada alien en la historia tiene una"),
        new Palabra("amor", "Lo que transforma a Stitch de destructor a ser parte de una familia."),
        new Palabra("playa", "Donde todos surfean"),
        new Palabra("oceano", "Gran azul que rodea Hawaii"),
        new Palabra("casa", "El lugar que intentan salvar Lilo y Nani"),
        new Palabra("hula", "Baile típico que Lilo ama practicar"),
        new Palabra("elvis", "El ídolo musical favorito de Lilo"),
        new Palabra("ukelele", "Instrumento que Lilo toca"),
        new Palabra("jumba", "El científico loco que creó a Stitch"),
        new Palabra("pleakley", "Alien con un solo ojo")
    );

    private Partida partida;
    private String nombreUsuario = "Jugador";

    private JLabel tituloLabel;
    private JLabel usuarioLabel;
    private JLabel palabraLabel;
    private JLabel imagenLabel;
    private JLabel contadorLabel;
    private JLabel pistaLabel;
    private JPanel letrasPanel;

    public AhorcadoStitch(String usuario) {
        nombreUsuario = usuario;

        configurarVentana();
        crearInterfaz();

        nuevaPalabra();
        actualizarUsuarioYPuntaje();

        setVisible(true);
    }

    private void configurarVentana() {
        setTitle("Ahorcado de Stitch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 720);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void crearInterfaz() {

        PanelConImagen fondo =
            new PanelConImagen("imagenes/fondoJUEGO.png");

        fondo.setLayout(new BorderLayout(20, 20));
        setContentPane(fondo);

        JPanel panelCentral =
            new JPanel(new BorderLayout(20, 15)) {

                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(new Color(255, 255, 255, 230));
                    g2.fillRoundRect(
                        0, 0,
                        getWidth(), getHeight(),
                        35, 35
                    );
                }
            };

        panelCentral.setOpaque(false);
        panelCentral.setBorder(
            new EmptyBorder(25, 40, 25, 40)
        );

        fondo.add(panelCentral, BorderLayout.CENTER);

        crearPanelSuperior(panelCentral);
        crearPanelCentral(panelCentral);
        crearPanelLetras(fondo);
    }

    private void crearPanelSuperior(JPanel panelCentral) {

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        panelCentral.add(topPanel, BorderLayout.NORTH);

        tituloLabel =
            new JLabel("¡No dejes que Stitch se enoje!");

        tituloLabel.setFont(
            new Font("Papyrus", Font.BOLD, 30)
        );

        tituloLabel.setForeground(
            new Color(25, 25, 112)
        );

        tituloLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        topPanel.add(
            tituloLabel,
            BorderLayout.CENTER
        );

        usuarioLabel = new JLabel();

        usuarioLabel.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

        usuarioLabel.setForeground(
            new Color(50, 50, 50)
        );

        usuarioLabel.setHorizontalAlignment(
            SwingConstants.RIGHT
        );

        usuarioLabel.setBorder(
            new EmptyBorder(0, 0, 10, 0)
        );

        topPanel.add(
            usuarioLabel,
            BorderLayout.EAST
        );
    }

    private void crearPanelCentral(JPanel panelCentral) {

        JPanel centerPanel =
            new JPanel(new BorderLayout(20, 15));

        centerPanel.setOpaque(false);

        panelCentral.add(
            centerPanel,
            BorderLayout.CENTER
        );

        JPanel leftPanel = new JPanel();

        leftPanel.setLayout(
            new BoxLayout(
                leftPanel,
                BoxLayout.Y_AXIS
            )
        );

        leftPanel.setOpaque(false);

        leftPanel.setBorder(
            new EmptyBorder(10, 10, 10, 10)
        );

        palabraLabel = new JLabel();

        palabraLabel.setFont(
            new Font("Monospaced", Font.BOLD, 54)
        );

        palabraLabel.setForeground(
            new Color(25, 25, 112)
        );

        palabraLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        palabraLabel.setBorder(
            new EmptyBorder(0, 0, 40, 0)
        );

        leftPanel.add(palabraLabel);

        pistaLabel = new JLabel("Pista: ");

        pistaLabel.setFont(
            new Font("Arial", Font.ITALIC, 26)
        );

        pistaLabel.setForeground(
            new Color(0, 100, 0)
        );

        pistaLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        leftPanel.add(pistaLabel);

        leftPanel.add(
            Box.createVerticalStrut(20)
        );

        contadorLabel =
            new JLabel("Errores: 0 / 6");

        contadorLabel.setFont(
            new Font("Arial", Font.BOLD, 28)
        );

        contadorLabel.setForeground(
            new Color(178, 34, 34)
        );

        contadorLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        leftPanel.add(contadorLabel);

        centerPanel.add(
            leftPanel,
            BorderLayout.WEST
        );

        imagenLabel = new JLabel();

        imagenLabel.setPreferredSize(
            new Dimension(320, 320)
        );

        imagenLabel.setHorizontalAlignment(
            SwingConstants.CENTER
        );

        imagenLabel.setBorder(
            BorderFactory.createCompoundBorder(
                new LineBorder(
                    new Color(30, 144, 255),
                    6,
                    true
                ),
                BorderFactory.createEmptyBorder(
                    20, 20, 20, 20
                )
            )
        );

        imagenLabel.setOpaque(true);
        imagenLabel.setBackground(Color.WHITE);

        centerPanel.add(
            imagenLabel,
            BorderLayout.EAST
        );
    }

    private void crearPanelLetras(PanelConImagen fondo) {

        letrasPanel =
            new JPanel(
                new GridLayout(3, 9, 14, 14)
            );

        letrasPanel.setBorder(
            new EmptyBorder(20, 40, 20, 40)
        );

        letrasPanel.setBackground(
            new Color(240, 248, 255)
        );

        fondo.add(
            letrasPanel,
            BorderLayout.SOUTH
        );

        for (char letra = 'A'; letra <= 'Z'; letra++) {

            final char letraFinal = letra;

            JButton boton =
                new JButton(String.valueOf(letra));

            configurarBoton(boton);

            boton.addActionListener(e -> {
                boton.setEnabled(false);
                boton.setBackground(Color.GRAY);

                procesarLetra(letraFinal);
            });

            letrasPanel.add(boton);
        }
    }

    private void configurarBoton(JButton boton) {

        boton.setFont(
            new Font("Arial", Font.BOLD, 28)
        );

        boton.setFocusPainted(false);

        boton.setBackground(
            new Color(30, 144, 255)
        );

        boton.setForeground(Color.WHITE);

        boton.setBorder(
            BorderFactory.createLineBorder(
                new Color(0, 104, 179),
                3,
                true
            )
        );

        boton.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        boton.addMouseListener(
            new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(
                    java.awt.event.MouseEvent evt) {

                    if (boton.isEnabled()) {
                        boton.setBackground(
                            new Color(0, 104, 179)
                        );
                    }
                }

                @Override
                public void mouseExited(
                    java.awt.event.MouseEvent evt) {

                    if (boton.isEnabled()) {
                        boton.setBackground(
                            new Color(30, 144, 255)
                        );
                    }
                }
            }
        );
    }

    private void nuevaPalabra() {

        int indice =
            new Random().nextInt(palabras.size());

        partida =
            new Partida(palabras.get(indice));

        actualizarPalabra();
        actualizarImagen();

        contadorLabel.setText(
            "Errores: 0 / " +
            partida.getMaxErrores()
        );

        pistaLabel.setText(
            "Pista: " +
            partida.getPalabra().getPista()
        );

        habilitarBotones();
    }

    private void procesarLetra(char letra) {

        partida.procesarLetra(letra);

        actualizarImagen();

        contadorLabel.setText(
            "Errores: " +
            partida.getErrores() +
            " / " +
            partida.getMaxErrores()
        );

        actualizarPalabra();

        chequearFinJuego();
    }

    private void actualizarPalabra() {

        palabraLabel.setText(
            partida.getPalabra().mostrar()
        );
    }

    private void actualizarImagen() {

        String ruta;

        if (partida.perdio()) {

            ruta = "imagenes/stitchroj.png";

        } else if (partida.gano()) {

            ruta = "imagenes/stitchverde.png";

        } else {

            int imgNum =
                partida.getErrores() == 0
                    ? 1
                    : partida.getErrores();

            ruta =
                "imagenes/stitch" +
                imgNum +
                ".jpg";
        }

        ImageIcon icon =
            new ImageIcon(ruta);

        if (icon.getIconWidth() <= 0) {

            imagenLabel.setText(
                "(Imagen no encontrada)"
            );

            imagenLabel.setIcon(null);

            return;
        }

        Image imagen =
            icon.getImage().getScaledInstance(
                320,
                320,
                Image.SCALE_SMOOTH
            );

        imagenLabel.setIcon(
            new ImageIcon(imagen)
        );

        imagenLabel.setText(null);
    }

    private void chequearFinJuego() {

        if (partida.gano()) {

            partida.actualizarPuntaje();
            actualizarUsuarioYPuntaje();

            ImageIcon icono =
                new ImageIcon(
                    "imagenes/stitchcontento.png"
                );

            Image imagenEscalada =
                icono.getImage().getScaledInstance(
                    120,
                    120,
                    Image.SCALE_SMOOTH
                );

            JOptionPane.showMessageDialog(
                this,
                "¡Ganaste! La palabra era: " +
                    partida.getPalabra().getPalabra(),
                "¡Bien hecho!",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(imagenEscalada)
            );

            nuevaPalabra();

        } else if (partida.perdio()) {

            partida.actualizarPuntaje();
            actualizarUsuarioYPuntaje();

            ImageIcon icono =
                new ImageIcon(
                    "imagenes/stitchenojao.png"
                );

            Image imagenEscalada =
                icono.getImage().getScaledInstance(
                    150,
                    150,
                    Image.SCALE_SMOOTH
                );

            JOptionPane.showMessageDialog(
                this,
                "¡Perdiste! La palabra era: " +
                    partida.getPalabra().getPalabra(),
                "Stitch está enojado :(",
                JOptionPane.ERROR_MESSAGE,
                new ImageIcon(imagenEscalada)
            );

            nuevaPalabra();
        }
    }

    private void habilitarBotones() {

        for (Component componente :
                letrasPanel.getComponents()) {

            if (componente instanceof JButton) {

                JButton boton =
                    (JButton) componente;

                boton.setEnabled(true);

                boton.setBackground(
                    new Color(30, 144, 255)
                );

                boton.setForeground(Color.WHITE);
            }
        }
    }

    private void actualizarUsuarioYPuntaje() {

        usuarioLabel.setText(
            "<html><b>Usuario:</b> " +
            nombreUsuario +
            " &nbsp;&nbsp;&nbsp; " +
            "<b>Puntaje:</b> " +
            partida.getPuntaje() +
            "</html>"
        );
    }
   
}
