import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class RepositorioUsuariosArchivo implements RepositorioUsuarios {
    private final Path archivo;

    // La ruta se recibe desde Main; el gestor no la conoce.
    public RepositorioUsuariosArchivo(String ruta) {
        archivo = Path.of(Objects.requireNonNull(ruta));
    }

    @Override
    public Usuario buscarPorNombre(String nombre) {
        try (BufferedReader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String linea;
            int numeroLinea = 0;
            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                if (linea.isBlank()) continue;
                // Lee ambos formatos del proyecto original. Las nuevas altas usan coma.
                String[] datos = linea.split("[,;]", -1);
                if (datos.length != 3 || datos[0].isBlank() || datos[1].isBlank()) {
                    throw new IOException("Registro inválido en la línea " + numeroLinea);
                }
                int puntaje;
                try {
                    puntaje = Integer.parseInt(datos[2]);
                } catch (NumberFormatException e) {
                    throw new IOException("Puntaje inválido en la línea " + numeroLinea, e);
                }
                if (puntaje < 0) {
                    throw new IOException("Puntaje negativo en la línea " + numeroLinea);
                }
                if (datos[0].equals(nombre)) {
                    Usuario usuario = new Usuario(datos[0], datos[1]);
                    usuario.sumarPuntos(puntaje);
                    return usuario;
                }
            }
            return null;
        } catch (NoSuchFileException e) {
            return null; // Todavía no se registró ningún usuario.
        } catch (IOException e) {
            throw new AccesoUsuariosException("No se pudieron leer los usuarios.", e);
        }
    }

    public void guardar(Usuario usuario) {
        Objects.requireNonNull(usuario);
        // Protege el formato incluso si otro cliente usa el repositorio directamente.
        validarCampo(usuario.getNombre());
        validarCampo(usuario.getContraseña());
        try {
            // Evita unir registros si el archivo previo no termina con salto de línea.
            boolean agregarSalto = false;
            if (Files.exists(archivo) && Files.size(archivo) > 0) {
                try (var canal = Files.newByteChannel(archivo, StandardOpenOption.READ)) {
                    canal.position(canal.size() - 1);
                    var ultimo = java.nio.ByteBuffer.allocate(1);
                    canal.read(ultimo);
                    byte caracter = ultimo.array()[0];
                    agregarSalto = caracter != '\n' && caracter != '\r';
                }
            }
            try (BufferedWriter writer = Files.newBufferedWriter(archivo,
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                if (agregarSalto) writer.newLine();
                writer.write(usuario.getNombre() + "," + usuario.getContraseña()
                        + "," + usuario.getPuntaje());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AccesoUsuariosException("No se pudo guardar el usuario.", e);
        }
    }

    private void validarCampo(String campo) {
        if (campo == null || campo.isBlank() || campo.contains(",")
                || campo.contains(";") || campo.contains("\n") || campo.contains("\r")) {
            throw new IllegalArgumentException("Nombre o contraseña con formato inválido.");
        }
    }
}
