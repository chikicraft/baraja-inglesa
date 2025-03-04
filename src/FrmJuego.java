import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FrmJuego extends JFrame {
    private JButton btnRepartir, btnVerificar;
    private JPanel pnlJugador1, pnlJugador2;
    private JTabbedPane tpJugadores;
    private Jugador[] jugadores;

    public FrmJuego() {
        // Inicializar componentes
        btnRepartir = new JButton("Repartir");
        btnVerificar = new JButton("Verificar");
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();
        jugadores = new Jugador[2];

        // Inicializar jugadores
        for (int i = 0; i < 2; i++) {
            jugadores[i] = new Jugador();
        }

        // Configuración de la ventana
        setSize(575, 250);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        // Configuración de los paneles de jugadores
        pnlJugador1.setBackground(new Color(153, 255, 51));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        // Agregar pestañas con los jugadores
        tpJugadores.setBounds(10, 60, 535, 135);
        tpJugadores.addTab("Juan David", pnlJugador1);
        tpJugadores.addTab("Sara Delgado", pnlJugador2);

        // Configuración del botón "Repartir"
        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        // Configuración del botón "Verificar"
        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        // Agregar componentes a la ventana
        add(tpJugadores);
        add(btnRepartir);
        add(btnVerificar);
    }

    private void btnRepartirClick(ActionEvent evt) {
        // Reparte las cartas y las muestra en los paneles
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].repartir();
        }
        jugadores[0].mostrar(pnlJugador1, false);
        jugadores[1].mostrar(pnlJugador2, false);
    }

    private void btnVerificarClick(ActionEvent evt) {
        int pestaña = tpJugadores.getSelectedIndex();
        Jugador jugador = jugadores[pestaña];
    
        if (jugador == null || jugador.obtenerFiguras().equals("No se han repartido cartas.")) {
            JOptionPane.showMessageDialog(this, "Primero debes repartir las cartas.");
            return;
        }
    
        String mensaje = jugador.obtenerFiguras() + "\n" +
                         jugador.obtenerEscaleras() + "\n" +
                         "Puntaje total: " + jugador.calcularPuntaje();
    
        JOptionPane.showMessageDialog(this, mensaje);
    }
       

    public static void main(String[] args) {
        new FrmJuego().setVisible(true);
    }
}
