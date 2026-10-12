public interface RepositorioUsuarios {
    Usuario buscarPorNombre(String nombre);
    void guardar(Usuario usuario);
}
