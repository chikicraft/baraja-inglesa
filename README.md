# Juego de Cartas en Java

Este proyecto es un juego de cartas en Java desarrollado con Swing para la interfaz gráfica. Permite a dos jugadores competir en la obtención de combinaciones y escaleras con una baraja.

## Estructura del Código

El juego está compuesto por las siguientes clases:

### 1. `App.java` - Punto de entrada del programa
- Se encarga de iniciar la aplicación mostrando la ventana de configuración de jugadores (`FrmInicio`).

```java
public class App {
    public static void main(String[] args) {
        new FrmInicio().setVisible(true);
    }
}
```

### 2. `FrmInicio.java` - Pantalla de configuración de jugadores
- Permite a los jugadores ingresar sus nombres.
- Contiene:
  - Dos campos de texto (`txtNombre1`, `txtNombre2`) para ingresar los nombres.
  - Un botón "Iniciar Juego" que crea una instancia de `FrmJuego` y cierra esta ventana.
  
### 3. `FrmJuego.java` - Ventana principal del juego
- Gestiona a los jugadores y las cartas.
- Contiene:
  - Botones para repartir cartas y verificar resultados.
  - Un `JTabbedPane` con una pestaña para cada jugador.
  - Métodos principales:
    - `btnRepartirClick()`: Asigna cartas aleatorias a los jugadores.
    - `btnVerificarClick()`: Calcula el puntaje de cada jugador y determina al ganador.

### 4. `Jugador.java` - Representación de un jugador
- Cada jugador tiene una lista de 10 cartas (`Carta`).
- Métodos principales:
  - `repartir()`: Asigna cartas aleatorias al jugador.
  - `mostrar()`: Muestra las cartas en la interfaz gráfica.
  - `calcularPuntaje()`: Suma el valor de las cartas para determinar el puntaje.
  - `obtenerFiguras()`: Identifica combinaciones como pares, ternas, etc.
  - `obtenerEscaleras()`: Verifica si el jugador tiene una escalera.

### 5. `Carta.java` - Representa una carta del juego
- Atributos:
  - `Pinta`: TREBOL, PICA, CORAZON, DIAMANTE.
  - `Nombre`: A, 2-10, J, Q, K.
- Métodos principales:
  - `obtenerPinta()`: Determina el palo de la carta.
  - `obtenerNombre()`: Retorna el nombre de la carta.
  - `obtenerValor()`: Devuelve el valor en puntos de la carta.
  - `mostrarCarta()`: Dibuja la carta en la interfaz.

## Reglas del Juego
1. Cada jugador recibe 10 cartas al inicio.
2. Los jugadores pueden formar combinaciones:
   - **Figuras:** Cartas repetidas en diferentes cantidades (par, terna, cuarta, etc.).
   - **Escaleras:** Secuencia de cartas consecutivas del mismo palo.
3. Se calcula el puntaje de cada jugador:
   - Cartas numeradas: Su valor numérico (excepto 10, J, Q y K, que valen 10 puntos).
   - Se suman los valores de las cartas que no estén en combinaciones.
4. Gana el jugador con más puntos o, en caso de empate, ambos pierden.

## Ejemplo de Flujo de Juego
1. Se inicia `App.java`, mostrando `FrmInicio.java`.
2. Los jugadores ingresan sus nombres y presionan "Iniciar Juego".
3. Se abre `FrmJuego.java`, donde los jugadores pueden:
   - Hacer clic en "Repartir" para recibir cartas.
   - Verificar su puntaje con "Verificar".
4. Se muestra el ganador o si hay un empate.

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

