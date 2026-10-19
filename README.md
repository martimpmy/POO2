# Ahorcado de Stitch – Iteración Strategy

Trabajo práctico de Programación Orientada a Objetos (Java / Swing).

## Iteración implementada: Strategy

Se extendió el sistema de puntuación para que el jugador elija entre **dos estrategias** desde la pantalla del juego:

| Estrategia | Victoria | Derrota |
|---|---|---|
| **Clásico** (`PuntajeClasico`) | +10 puntos | -5 puntos |
| **Por precisión** (`PuntajePorPrecision`) | `10 + 2 × (máximo de errores − errores cometidos)` puntos | -5 puntos |

Por ejemplo, en una partida con un máximo de 6 errores, ganar sin equivocaciones otorga **22 puntos** en el modo de precisión; ganar con 1 error otorga **20 puntos**.

### Roles del patrón de diseño

- **Strategy:** `PoliticaPuntaje` define el contrato `calcularPuntos(Partida)`.
- **ConcreteStrategy:** `PuntajeClasico` y `PuntajePorPrecision` implementan algoritmos de puntuación diferentes.
- **Context:** `ControladorJuego` utiliza la estrategia sin depender de su implementación y ofrece `seleccionarPoliticaPuntaje()`.
- **Cliente:** `AhorcadoStitch` presenta un selector desplegable que modifica la estrategia para la próxima ronda.

El modo de puntuación **queda fijado al iniciar cada ronda**. Si el jugador cambia la opción durante la partida, la selección se aplica desde la siguiente ronda, evitando que el puntaje de una ronda ya iniciada cambie retroactivamente.

### Archivos modificados / nuevos

- Modificados: `src/PoliticaPuntaje.java`, `src/PuntajeClasico.java`, `src/ControladorJuego.java`, `src/AhorcadoStitch.java`.
- Nuevos: `src/PuntajePorPrecision.java`, `tests/PruebaStrategy.java`, `README.md`.

## Ejecutar

Necesitás tener instalado un JDK (recomendado: Java 17 o superior). Ejecutar los comandos **desde la carpeta principal del proyecto** para que las imágenes se encuentren correctamente.

En la terminal de Windows (PowerShell) o Linux/macOS:

```sh
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

También se puede abrir directamente la carpeta en IntelliJ IDEA y ejecutar `Main`.

## Probar la iteración

Compilar el proyecto junto a las pruebas:

```sh
javac -encoding UTF-8 -d out src/*.java tests/*.java
java -cp out PruebaStrategy
```

La prueba verifica las dos políticas de puntuación, el cambio entre estrategias y que los puntos se otorguen una sola vez por ronda.

## Subir como iteración a GitHub

Dentro de un clon del repositorio original, incorporar los archivos de esta entrega y ejecutar:

```sh
git checkout -b feature/strategy-puntaje
git add src/PoliticaPuntaje.java src/PuntajeClasico.java src/PuntajePorPrecision.java src/ControladorJuego.java src/AhorcadoStitch.java tests/PruebaStrategy.java README.md
git commit -m "feat: integrar Strategy para modos de puntuacion"
git push -u origin feature/strategy-puntaje
```

Luego se puede abrir un pull request de esa rama hacia la rama principal del repositorio.

**Nota:** `usuarios.txt` contiene datos de usuario del proyecto original y no es necesario incluirlo en el commit de esta iteración.
