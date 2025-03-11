import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;

enum Figura {
    PAR(2), TERNA(3), CUARTA(4), QUINTA(5),
    SEXTA(6), SEPTIMA(7), OCTAVA(8), NOVENA(9), DECIMA(10);

    private final int cantidad;

    Figura(int cantidad) {
        this.cantidad = cantidad;
    }

    public static String obtenerNombre(int cantidad) {
        for (Figura f : values()) {
            if (f.cantidad == cantidad) {
                return f.name();
            }
        }
        return null;
    }
}

// Enum para Pinta
enum Pinta {
    TREBOL, PICA, CORAZON, DIAMANTE;
}

// Enum para Nombre de Carta
enum NombreCarta {
    A, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, J, Q, K;
}

public class Jugador {
    private Random r;
    private Carta[] cartas;

    public Jugador() {
        r = new Random();
        cartas = new Carta[10];
    }

    public void repartir(Baraja baraja) {
        for (int i = 0; i < 10; i++) {
            int indiceCarta = baraja.repartirCarta();
            if (indiceCarta != -1) {
                cartas[i] = new Carta(indiceCarta);
            }
        }
    }
    

    public void mostrar(JPanel pnl, boolean tapada) {
        pnl.removeAll();
        for (int i = 0; i < 10; i++) {
            Carta carta = cartas[i];
            JLabel lblCarta = carta.mostrarCartaComoLabel(10 + i * 50, 10, tapada);
            final int index = i;
            lblCarta.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    mostrarVentanaCarta(index);
                }
            });
            pnl.add(lblCarta);
        }
        pnl.repaint();
    }

    private void mostrarVentanaCarta(int index) {
        Carta carta = cartas[index];
        JFrame ventanaCarta = new JFrame("Detalle de la Carta");
        ventanaCarta.setSize(250, 300);
        ventanaCarta.setLayout(new BorderLayout());
        ventanaCarta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblTexto = new JLabel("Carta: " + carta.obtenerNombre() + " de " + carta.obtenerPinta(), SwingConstants.CENTER);
        JLabel lblImagen = new JLabel(new ImageIcon(getClass().getResource("/img/Carta" + carta.getIndice() + ".png")));

        ventanaCarta.add(lblTexto, BorderLayout.NORTH);
        ventanaCarta.add(lblImagen, BorderLayout.CENTER);

        ventanaCarta.setLocationRelativeTo(null); // Centrar ventana
        ventanaCarta.setVisible(true);
    }

    public String obtenerFiguras() {
        if (cartas == null || cartas[0] == null) {
            return "No se han repartido cartas.";
        }

        int[] contadores = new int[13];
        for (Carta carta : cartas) {
            contadores[carta.obtenerNombre().ordinal()]++;
        }

        StringBuilder resultado = new StringBuilder("Figuras encontradas:\n");
        boolean hayFiguras = false;

        for (int i = 0; i < 13; i++) {
            if (contadores[i] >= 2) {
                String tipoFigura = Figura.obtenerNombre(contadores[i]);
                if (tipoFigura != null) {
                    resultado.append(tipoFigura).append(" de ").append(NombreCarta.values()[i]).append("\n");
                    hayFiguras = true;
                }
            }
        }

        return hayFiguras ? resultado.toString() : "No hay figuras.";
    }


    public String obtenerEscaleras() {
        if (cartas == null || cartas[0] == null) {
            return "No se han repartido cartas.";
        }
        
        Map<Pinta, List<Integer>> cartasPorPinta = new HashMap<>();
        for (Pinta p : Pinta.values()) {
            cartasPorPinta.put(p, new ArrayList<>());
        }

        for (Carta carta : cartas) {
            cartasPorPinta.get(carta.obtenerPinta()).add(carta.obtenerValor());
        }

        StringBuilder resultado = new StringBuilder("Escaleras encontradas:\n");
        boolean hayEscaleras = false;

        for (Map.Entry<Pinta, List<Integer>> entry : cartasPorPinta.entrySet()) {
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
