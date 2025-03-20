import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrmJuego extends JFrame {
    private JButton btnRepartir, btnVerificar;
    private JPanel[] panelesJugadores;
    private JTabbedPane tpJugadores;
    private Jugador[] jugadores;
    private String[] nombres;
    private String[] fondos = {"fondo1.jpg", "fondo2.jpg", "fondo3.jpg"};

    public FrmJuego(int cantidadJugadores, String[] nombres) {
        this.nombres = nombres;
        jugadores = new Jugador[cantidadJugadores];
        panelesJugadores = new JPanel[cantidadJugadores];

        // Inicializar jugadores
        for (int i = 0; i < cantidadJugadores; i++) {
            jugadores[i] = new Jugador();
        }

        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        // Configuración de los botones
        btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });
        add(btnRepartir);

        btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });
        add(btnVerificar);

        // Configuración de los paneles de jugadores
        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 50, 560, 200);
        for (int i = 0; i < cantidadJugadores; i++) {
            final int index = i % fondos.length; // Asegurar que el índice no se salga del array
            panelesJugadores[i] = new JPanel() {
                private Image fondo = new ImageIcon(getClass().getResource("/img/" + fondos[index])).getImage();
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            panelesJugadores[i].setLayout(null);
            tpJugadores.addTab(nombres[i], panelesJugadores[i]);
        }
        add(tpJugadores);
    }

    private void btnRepartirClick(ActionEvent evt) {
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].repartir();
            final int index = i % fondos.length; // Seleccionar el fondo correcto
            jugadores[i].mostrar(panelesJugadores[i], false, fondos[index]);
        }
    }
    

    private void btnVerificarClick(ActionEvent evt) {
        StringBuilder mensaje = new StringBuilder();
        int maxPuntaje = -1;
        String ganador = "";

        for (int i = 0; i < jugadores.length; i++) {
            int puntaje = jugadores[i].calcularPuntaje();
            mensaje.append(nombres[i]).append(":\n")
                   .append(jugadores[i].obtenerFiguras()).append("\n")
                   .append(jugadores[i].obtenerEscaleras()).append("\n")
                   .append("Puntaje total: ").append(puntaje).append("\n\n")
                   .append("------------------------------------------------\n\n");
            
            if (puntaje > maxPuntaje) {
                maxPuntaje = puntaje;
                ganador = nombres[i];
            } else if (puntaje == maxPuntaje) {
                ganador = "Empate";
            }
        }
        
        if (!ganador.equals("Empate")) {
            mensaje.append("¡Ganador: ").append(ganador).append("!");
        } else {
            mensaje.append("¡Es un empate!");
        }
        
        JOptionPane.showMessageDialog(this, mensaje.toString());
    }
}