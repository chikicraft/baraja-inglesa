import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Carta {
    public enum Pinta { TREBOL, PICA, CORAZON, DIAMANTE }
    public enum Nombre { A, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, J, Q, K }

    private int indice;

    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }

    public Pinta obtenerPinta() {
        if (indice <= 13) return Pinta.TREBOL;
        else if (indice <= 26) return Pinta.PICA;
        else if (indice <= 39) return Pinta.CORAZON;
        else return Pinta.DIAMANTE;
    }

    public Nombre obtenerNombre() {
        return Nombre.values()[(indice - 1) % 13];
    }

    public int obtenerValor() {
        int numero = (indice - 1) % 13 + 1;
        return (numero > 10) ? 10 : numero;
    }

    public void mostrarCarta(int x, int y, JPanel pnl, boolean tapada) {
        String nombreImagen = tapada ? "/img/Tapada.jpg" : "/img/Carta" + indice + ".png";
        java.net.URL imgURL = getClass().getResource(nombreImagen);
        
        if (imgURL == null) {
            System.err.println("No se encontró la imagen: " + nombreImagen);
            return;
        }
        
        ImageIcon imagen = new ImageIcon(imgURL);
        JLabel lblCarta = new JLabel(imagen);
        lblCarta.setBounds(x, y, 60, 84);
        
        lblCarta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarInformacionCarta();
            }
        });
        
        pnl.add(lblCarta);
        pnl.setComponentZOrder(lblCarta, 0);
        pnl.repaint();
    }

    private void mostrarInformacionCarta() {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Información de la Carta");
        dialogo.setSize(220, 180);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(null);
        
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        panelContenido.setBackground(new Color(37, 122, 81));
        
        String nombreImagen = "/img/Carta" + indice + ".png";
        java.net.URL imgURL = getClass().getResource(nombreImagen);
        ImageIcon imagen = (imgURL != null) ? new ImageIcon(imgURL) : new ImageIcon();
        JLabel lblImagen = new JLabel(imagen);
        
        JLabel lblInfo = new JLabel("<html><div style='text-align: center;'>Nombre: " + obtenerNombre() + "<br>Pinta: " + obtenerPinta() + "<br>Valor: " + obtenerValor() + "</div></html>", SwingConstants.CENTER);
        lblInfo.setForeground(Color.WHITE);
        
        panelContenido.add(lblImagen, BorderLayout.CENTER);
        panelContenido.add(lblInfo, BorderLayout.SOUTH);
        dialogo.add(panelContenido);
        
        dialogo.setVisible(true);
    }
}