# Documentación del código

## Índice
1. [Descripción General](#descripcion-general)
2. [Archivos y Explicaciones](#archivos-y-explicaciones)
   - [App.java](#appjava)
   - [Baraja.java](#barajava)
   - [Carta.java](#cartajava)
   - [FrmJuego.java](#frmjuegojava)
   - [Jugador.java](#jugadorjava)
3. [información](#web-de-donde-saqué-la-información-y-ayudas)

---

## Descripción General
Este proyecto es un juego de cartas implementado en Java utilizando Swing para la interfaz gráfica. Permite repartir cartas, verificar combinaciones (pares, ternas, escaleras, etc.) y calcular puntajes para los jugadores.

---

## Archivos y Explicaciones

### App.java
```java
public class App {
    public static void main(String[] args) throws Exception {
        new FrmJuego().setVisible(true);
    }
}
```

- **Clase `App`**: Es la clase principal que inicia la aplicación.
- **Método `main`**: Crea una nueva instancia de `FrmJuego` y la hace visible.

---

### Baraja.java
```java
import java.util.Random;

public class Baraja {
    private int[] cartasDisponibles;
    private int indiceActual;
    private Random random;

    public Baraja() {
        cartasDisponibles = new int[52];
        for (int i = 0; i < 52; i++) {
            cartasDisponibles[i] = i + 1;
        }
        indiceActual = 0;
        random = new Random();
        mezclar();
    }
```
- **Clase `Baraja`**: Representa un mazo de 52 cartas.
- **Atributos**:
  - `cartasDisponibles`: Arreglo de enteros que almacena las 52 cartas.
  - `indiceActual`: Indica la posición de la siguiente carta a repartir.
  - `random`: Generador de números aleatorios.
- **Método `Baraja()`**: Inicializa el mazo con las 52 cartas y lo mezcla.

```java
    private void mezclar() {
        for (int i = 51; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = cartasDisponibles[i];
            cartasDisponibles[i] = cartasDisponibles[j];
            cartasDisponibles[j] = temp;
        }
    }
```
- **Método `mezclar()`**: Realiza un shuffle aleatorio del mazo.

```java
    public int repartirCarta() {
        if (indiceActual < 52) {
            return cartasDisponibles[indiceActual++];
        } else {
            return -1; // No hay más cartas disponibles
        }
    }
}
```
- **Método `repartirCarta()`**: Retorna la siguiente carta disponible o -1 si no hay más.

---

### Carta.java
```java
import java.util.Random;
import javax.swing.*;

public class Carta {
    private int indice;
```
- **Clase `Carta`**: Representa una carta individual.
- **Atributo `indice`**: Identifica la carta de 1 a 52.

```java
    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }
```
- **Constructor `Carta(Random r)`**: Asigna un índice aleatorio entre 1 y 52.

```java
    public Carta(int indice) {
        this.indice = indice;
    }
```
- **Constructor `Carta(int indice)`**: Permite crear una carta con un índice específico.

---

### FrmJuego.java
```java
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmJuego extends JFrame {
    private JButton btnRepartir, btnVerificar;
    private JPanel pnlJugador1, pnlJugador2;
    private JTabbedPane tpJugadores;
    private Jugador[] jugadores;
    private Baraja baraja;
```
- **Clase `FrmJuego`**: Define la ventana del juego.
- **Atributos**:
  - `btnRepartir`, `btnVerificar`: Botones de interacción.
  - `pnlJugador1`, `pnlJugador2`: Paneles para mostrar las cartas de los jugadores.
  - `tpJugadores`: Contenedor con pestañas para los jugadores.
  - `jugadores`: Array de objetos `Jugador`.
  - `baraja`: Instancia de la baraja de cartas.

```java
    public FrmJuego() {
        btnRepartir = new JButton("Repartir");
        btnVerificar = new JButton("Verificar");
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();
        baraja = new Baraja();
        jugadores = new Jugador[2];
```
- **Constructor `FrmJuego()`**: Inicializa los componentes de la interfaz gráfica.

```java
    private void btnRepartirClick(ActionEvent evt) {
        baraja = new Baraja();
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].repartir(baraja);
        }
        jugadores[0].mostrar(pnlJugador1, false);
        jugadores[1].mostrar(pnlJugador2, false);
    }
```
- **Método `btnRepartirClick()`**: Reinicia la baraja y reparte cartas a los jugadores.

---

### Jugador.java
```java
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Jugador {
    private Random r;
    private Carta[] cartas;
```
- **Clase `Jugador`**: Representa a un jugador en la partida.
- **Atributos**:
  - `r`: Generador aleatorio.
  - `cartas`: Arreglo con las cartas del jugador.

```java
    public void repartir(Baraja baraja) {
        for (int i = 0; i < 10; i++) {
            int indiceCarta = baraja.repartirCarta();
            if (indiceCarta != -1) {
                cartas[i] = new Carta(indiceCarta);
            }
        }
    }
```
- **Método `repartir()`**: Asigna cartas del mazo al jugador.

```java
    public int calcularPuntaje() {
        if (cartas == null || cartas[0] == null) {
            return 0;
        }
        int puntaje = 0;
        for (Carta carta : cartas) {
            puntaje += carta.obtenerValor();
        }
        return puntaje;
    }
}
```
- **Método `calcularPuntaje()`**: Suma los valores de las cartas para determinar el puntaje del jugador.
---

## Web de donde saqué la información y ayudas
Para desarrollar este proyecto, se utilizaron las siguientes referencias y recursos:
- [Documentación oficial de Java](https://docs.oracle.com/en/java/)
- [Tutoriales de Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Foros de Stack Overflow](https://stackoverflow.com/)
- [Guías y ejemplos de programación en GitHub](https://github.com/)

Estos recursos fueron clave para la implementación y depuración del código.

