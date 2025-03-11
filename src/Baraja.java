import java.util.Random;

public class Baraja {
    private int[] cartasDisponibles;
    private int indiceActual;
    private Random random;

    public Baraja() {
        cartasDisponibles = new int[52]; // 52 cartas en la baraja
        for (int i = 0; i < 52; i++) {
            cartasDisponibles[i] = i + 1; // Llenamos la baraja con los índices de las cartas
        }
        indiceActual = 0;
        random = new Random();
        mezclar(); // Mezclamos la baraja al inicio
    }

    private void mezclar() {
        for (int i = 51; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = cartasDisponibles[i];
            cartasDisponibles[i] = cartasDisponibles[j];
            cartasDisponibles[j] = temp;
        }
    }

    public int repartirCarta() {
        if (indiceActual < 52) {
            return cartasDisponibles[indiceActual++];
        } else {
            return -1; // No hay más cartas disponibles
        }
    }
}
