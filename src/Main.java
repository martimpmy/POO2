import javax.swing.SwingUtilities;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        RepositorioUsuarios repositorio = new RepositorioUsuariosArchivo("usuarios.txt");
        GestorUsuario gestor = new GestorUsuario(repositorio);
        FuentePalabras fuente = new BancoPalabras();
        PoliticaPuntaje politica = new PuntajeClasico();
        Consumer<Usuario> abrirJuego = usuario -> {
            ControladorJuego controlador = new ControladorJuego(usuario, fuente, politica, 6);
            new AhorcadoStitch(controlador);
        };
        SwingUtilities.invokeLater(() -> new PantallaInicio(gestor, abrirJuego));
    }
}
