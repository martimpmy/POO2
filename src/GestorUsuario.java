import java.util.Objects;

public class GestorUsuario {
    private final RepositorioUsuarios repositorio;

    public GestorUsuario(RepositorioUsuarios repositorio) {
        this.repositorio = Objects.requireNonNull(repositorio);
    }

    public boolean registrarUsuario(String nombre, String contraseña) {
        validarDatos(nombre, contraseña);
        if (usuarioExiste(nombre)) return false;
        repositorio.guardar(new Usuario(nombre, contraseña));
        return true;
    }

    public Usuario login(String nombre, String contraseña) {
        validarDatos(nombre, contraseña);
        Usuario usuario = repositorio.buscarPorNombre(nombre);
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            return usuario;
        }
        return null;
    }

    public boolean usuarioExiste(String nombre) {
        return repositorio.buscarPorNombre(nombre) != null;
    }

    private void validarDatos(String nombre, String contraseña) {
        if (nombre == null || contraseña == null || nombre.isBlank() || contraseña.isBlank()) {
            throw new IllegalArgumentException("Completa todos los campos.");
        }
        if (contieneSeparadores(nombre) || contieneSeparadores(contraseña)) {
            throw new IllegalArgumentException("No uses comas, punto y coma ni saltos de línea.");
        }
    }

    private boolean contieneSeparadores(String texto) {
        return texto.contains(",") || texto.contains(";")
                || texto.contains("\n") || texto.contains("\r");
    }
}
