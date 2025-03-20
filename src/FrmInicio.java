import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrmInicio extends JFrame {
    private JTextField txtNombre1, txtNombre2;
    private JButton btnIniciarJuego;
    
    public FrmInicio() {
        setTitle("Configuración de Jugadores");
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel lblJugador1 = new JLabel("Nombre Jugador 1:");
        lblJugador1.setBounds(20, 20, 150, 25);
        add(lblJugador1);
        
        txtNombre1 = new JTextField();
        txtNombre1.setBounds(180, 20, 180, 25);
        add(txtNombre1);
        
        JLabel lblJugador2 = new JLabel("Nombre Jugador 2:");
        lblJugador2.setBounds(20, 60, 150, 25);
        add(lblJugador2);
        
        txtNombre2 = new JTextField();
        txtNombre2.setBounds(180, 60, 180, 25);
        add(txtNombre2);
        
        btnIniciarJuego = new JButton("Iniciar Juego");
        btnIniciarJuego.setBounds(120, 110, 150, 30);
        add(btnIniciarJuego);
        
        btnIniciarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
    }
    
    private void iniciarJuego() {
        String nombre1 = txtNombre1.getText().trim();
        String nombre2 = txtNombre2.getText().trim();
        
        if (nombre1.isEmpty() || nombre2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ambos jugadores deben tener un nombre.");
            return;
        }
        
        FrmJuego juego = new FrmJuego(2, new String[]{nombre1, nombre2});
        juego.setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {
        new FrmInicio().setVisible(true);
    }
}
