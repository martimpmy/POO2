import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BancoPalabras implements FuentePalabras {

    private final List<Palabra> palabras = Arrays.asList(
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
        new Palabra(
            "adopcion",
            "Stitch encuentra su lugar en la Tierra gracias a este acto de amor."
        ),
        new Palabra("hermanas", "Lilo y Nani"),
        new Palabra("perro", "Lo que Lilo cree haber adoptado"),
        new Palabra("nave", "Stitch viaja a la Tierra en una de estas"),
        new Palabra("mision", "Cada alien en la historia tiene una"),
        new Palabra(
            "amor",
            "Lo que transforma a Stitch de destructor a ser parte de una familia."
        ),
        new Palabra("playa", "Donde todos surfean"),
        new Palabra("oceano", "Gran azul que rodea Hawaii"),
        new Palabra("casa", "El lugar que intentan salvar Lilo y Nani"),
        new Palabra("hula", "Baile típico que Lilo ama practicar"),
        new Palabra("elvis", "El ídolo musical favorito de Lilo"),
        new Palabra("ukelele", "Instrumento que Lilo toca"),
        new Palabra("jumba", "El científico loco que creó a Stitch"),
        new Palabra("pleakley", "Alien con un solo ojo")
    );

    private final Random random = new Random();

    @Override
    public Palabra obtenerPalabraAleatoria() {
        int indice = random.nextInt(palabras.size());
        return palabras.get(indice);
    }
}