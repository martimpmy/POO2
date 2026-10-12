public class PuntajeClasico implements PoliticaPuntaje {
    @Override
    public int calcularPuntos(boolean victoria) {
        return victoria ? 10 : -5;
    }
}
