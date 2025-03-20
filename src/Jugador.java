import java.util.*;
import javax.swing.*;

public class Jugador {
    public enum Combinacion { PAR, TERNA, CUARTA, QUINTA, SEXTA, SEPTIMA, OCTAVA, NOVENA, DECIMA }

    private Random r;
    private Carta[] cartas;

    public Jugador() {
        r = new Random();
        cartas = new Carta[10];
    }

    public void repartir() {
        for (int i = 0; i < 10; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public boolean cartasVacias() {
        return cartas[0] == null;
    }

    public void mostrar(JPanel pnl, boolean tapada, String fondoJugador) {
        pnl.removeAll();
        for (int i = 0; i < 10; i++) {
            cartas[i].mostrarCarta(10 + i * 52, 40, pnl, tapada, fondoJugador);
        }
        pnl.repaint();
    }
    

    public String obtenerFiguras() {
        if (cartas[0] == null) return "No se han repartido cartas.";
        
        Map<Carta.Nombre, Integer> contadores = new HashMap<>();
        for (Carta carta : cartas) {
            contadores.put(carta.obtenerNombre(), contadores.getOrDefault(carta.obtenerNombre(), 0) + 1);
        }
        
        StringBuilder resultado = new StringBuilder("Figuras encontradas:\n");
        boolean hayFiguras = false;
        
        for (Map.Entry<Carta.Nombre, Integer> entry : contadores.entrySet()) {
            if (entry.getValue() >= 2) {
                resultado.append(identificarCombinacion(entry.getValue())).append(" de ").append(entry.getKey()).append("\n");
                hayFiguras = true;
            }
        }
        
        return hayFiguras ? resultado.toString() : "No hay figuras.";
    }

    private String identificarCombinacion(int cantidad) {
        switch (cantidad) {
            case 2: return "Par";
            case 3: return "Terna";
            case 4: return "Cuarta";
            case 5: return "Quinta";
            case 6: return "Sexta";
            case 7: return "Séptima";
            case 8: return "Octava";
            case 9: return "Novena";
            case 10: return "Décima";
            default: return "";
        }
    }

    public int calcularPuntaje() {
        int puntaje = 0;
        Set<Integer> cartasUsadas = new HashSet<>();
    
        Map<Carta.Nombre, Integer> contadores = new HashMap<>();
        for (Carta carta : cartas) {
            contadores.put(carta.obtenerNombre(), contadores.getOrDefault(carta.obtenerNombre(), 0) + 1);
        }
        for (Map.Entry<Carta.Nombre, Integer> entry : contadores.entrySet()) {
            if (entry.getValue() >= 3) {
                for (Carta carta : cartas) {
                    if (carta.obtenerNombre().equals(entry.getKey())) {
                        cartasUsadas.add(carta.obtenerValor());
                    }
                }
            }
        }
    
        for (Carta carta : cartas) {
            if (!cartasUsadas.contains(carta.obtenerValor())) {
                puntaje += carta.obtenerValor();
            }
        }
    
        return puntaje;
    }

    public String obtenerEscaleras() {
        if (cartas[0] == null) return "No se han repartido cartas.";
    
        Map<Carta.Pinta, List<Integer>> cartasPorPinta = new HashMap<>();
        for (Carta.Pinta pinta : Carta.Pinta.values()) {
            cartasPorPinta.put(pinta, new ArrayList<>());
        }
    
        for (Carta carta : cartas) {
            cartasPorPinta.get(carta.obtenerPinta()).add(carta.obtenerValor());
        }
    
        StringBuilder resultado = new StringBuilder("Escaleras encontradas:\n");
        boolean hayEscaleras = false;
    
        for (Map.Entry<Carta.Pinta, List<Integer>> entry : cartasPorPinta.entrySet()) {
            List<Integer> valores = entry.getValue();
            Collections.sort(valores);
    
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
            if (contador >= 3) {
                resultado.append("Escalera de ").append(contador).append(" en ").append(entry.getKey()).append("\n");
                hayEscaleras = true;
            }
        }
    
        return hayEscaleras ? resultado.toString() : "No hay escaleras.";
    }
}
