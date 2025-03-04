import java.util.*;
import javax.swing.*;

public class Jugador {
    private Random r;
    private Carta[] cartas;

    public Jugador() {
        r = new Random();
        cartas = new Carta[10]; // Inicializar el array de cartas
    }

    public void repartir() {
        // Asigna 10 cartas aleatorias al jugador
        for (int i = 0; i < 10; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl, boolean tapada) {
        pnl.removeAll(); // Limpiar el panel antes de mostrar nuevas cartas
        for (int i = 0; i < 10; i++) {
            cartas[i].mostrarCarta(10 + i * 50, 10, pnl, tapada);
        }
        pnl.repaint(); // Redibujar el panel con las nuevas cartas
    }

    public String obtenerFiguras() {
        if (cartas == null) {
            return "No se han repartido cartas.";
        }
    
        int[] contadores = new int[13]; // Contadores para cada tipo de carta
    
        for (Carta carta : cartas) {
            if (carta == null) continue; // Evita errores si alguna carta es null
            contadores[(carta.obtenerValor() - 1) % 13]++; 
        }
    
        StringBuilder resultado = new StringBuilder("Figuras encontradas:\n");
        boolean hayFiguras = false;
    
        for (int i = 0; i < 13; i++) {
            if (contadores[i] >= 2) {
                resultado.append(contadores[i]).append(" de ").append(nombreCarta(i + 1)).append("\n");
                hayFiguras = true;
            }
        }
    
        return hayFiguras ? resultado.toString() : "No hay figuras.";
    }
    

    private String nombreCarta(int valor) {
        String[] nombres = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return nombres[(valor - 1) % 13];
    }

    public String obtenerEscaleras() {
        // Map para clasificar las cartas por su pinta
        Map<String, List<Integer>> cartasPorPinta = new HashMap<>();
        cartasPorPinta.put("Trébol", new ArrayList<>());
        cartasPorPinta.put("Pica", new ArrayList<>());
        cartasPorPinta.put("Corazón", new ArrayList<>());
        cartasPorPinta.put("Diamante", new ArrayList<>());
    
        // Clasificar las cartas en el mapa
        for (Carta carta : cartas) {
            cartasPorPinta.get(carta.obtenerPinta()).add(carta.obtenerValor());
        }
    
        // Verificar escaleras
        StringBuilder resultado = new StringBuilder("Escaleras encontradas:\n");
        boolean hayEscaleras = false;
    
        for (Map.Entry<String, List<Integer>> entry : cartasPorPinta.entrySet()) {
            List<Integer> valores = entry.getValue();
            Collections.sort(valores); // Ordenar los valores de menor a mayor
    
            // Buscar secuencias de al menos 3 cartas consecutivas
            int contador = 1;
            for (int i = 1; i < valores.size(); i++) {
                if (valores.get(i) == valores.get(i - 1) + 1) {
                    contador++;
                } else {
                    if (contador >= 3) {
                        resultado.append("Escalera de ").append(contador).append(" en ").append(entry.getKey()).append("\n");
                        hayEscaleras = true;
                    }
                    contador = 1;
                }
            }
            // Evaluar la última escalera encontrada
            if (contador >= 3) {
                resultado.append("Escalera de ").append(contador).append(" en ").append(entry.getKey()).append("\n");
                hayEscaleras = true;
            }
        }
    
        return hayEscaleras ? resultado.toString() : "No hay escaleras.";
    }

    public int calcularPuntaje() {
        int puntaje = 0;
        Set<Integer> cartasUsadas = new HashSet<>(); // Para almacenar cartas en figuras o escaleras
    
        // Marcar cartas usadas en figuras
        int[] contadores = new int[13];
        for (Carta carta : cartas) {
            contadores[(carta.obtenerValor() - 1) % 13]++;
        }
        for (int i = 0; i < 13; i++) {
            if (contadores[i] >= 3) {
                for (Carta carta : cartas) {
                    if (carta.obtenerValor() == i + 1) {
                        cartasUsadas.add(carta.obtenerValor());
                    }
                }
            }
        }
    
        // Marcar cartas usadas en escaleras
        for (Carta carta : cartas) {
            if (!cartasUsadas.contains(carta.obtenerValor())) {
                puntaje += carta.obtenerValor();
            }
        }
    
        return puntaje;
    }
    
}
