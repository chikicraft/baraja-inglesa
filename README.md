# Juego de Cartas en Java

Este proyecto es un juego de cartas en Java desarrollado con Swing para la interfaz gráfica. Permite a dos jugadores competir en la obtención de combinaciones y escaleras con una baraja.

## Estructura del Código

### 1. `App.java`
Este es el punto de entrada del programa. Se encarga de iniciar la aplicación mostrando la ventana de configuración de jugadores (`FrmInicio`).

```java
public class App {
    public static void main(String[] args) {
        new FrmInicio().setVisible(true);
    }
}
```

### 2. `FrmInicio.java`
Clase que representa la ventana inicial donde los jugadores ingresan sus nombres.

- Contiene dos campos de texto para los nombres de los jugadores.
- Un botón que, al presionarlo, crea una instancia de `FrmJuego` y cierra la ventana actual.

### 3. `FrmJuego.java`
Esta es la ventana principal del juego.

- Administra a los jugadores y las cartas.
- Incluye botones para repartir cartas y verificar resultados.
- Usa `JTabbedPane` para organizar las cartas de cada jugador en pestañas.
- Permite identificar combinaciones y escaleras.

### 4. `Jugador.java`
Clase que representa a un jugador del juego.

- Cada jugador tiene una lista de `Carta`.
- Métodos principales:
  - `repartir()`: Asigna cartas aleatorias al jugador.
  - `mostrar()`: Muestra las cartas en la interfaz gráfica.
  - `calcularPuntaje()`: Determina el puntaje del jugador basado en sus cartas.
  - `obtenerFiguras()`: Identifica combinaciones como pares, ternas, etc.
  - `obtenerEscaleras()`: Verifica si el jugador tiene una escalera de cartas del mismo palo.

### 5. `Carta.java`
Representa una carta del juego.

- Atributos:
  - `Pinta`: TREBOL, PICA, CORAZON, DIAMANTE.
  - `Nombre`: A, 2-10, J, Q, K.
- Métodos:
  - `obtenerPinta()`: Determina el palo de la carta.
  - `obtenerNombre()`: Retorna el nombre de la carta.
  - `obtenerValor()`: Devuelve el valor en puntos de la carta.
  - `mostrarCarta()`: Dibuja la carta en el panel gráfico y permite visualizar detalles al hacer clic.

## Instalación y Ejecución
1. Clonar el repositorio:
   ```sh
   git clone https://github.com/usuario/juego-cartas.git
   ```
2. Abrir el proyecto en un IDE compatible con Java (Eclipse, IntelliJ, NetBeans).
3. Compilar y ejecutar `App.java`.

## Referencias
- Documentación de Java Swing: [https://docs.oracle.com/javase/tutorial/uiswing/](https://docs.oracle.com/javase/tutorial/uiswing/)
- Generación de números aleatorios en Java: [https://docs.oracle.com/javase/8/docs/api/java/util/Random.html](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)

