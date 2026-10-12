// Permite distinguir un problema de almacenamiento de un login incorrecto.
public class AccesoUsuariosException extends RuntimeException {
    public AccesoUsuariosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
