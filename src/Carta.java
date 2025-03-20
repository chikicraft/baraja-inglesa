import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Carta {
    private String[] fondos = {"fondo3.jpg"};

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

    public void mostrarCarta(int x, int y, JPanel pnl, boolean tapada, String fondoJugador) {
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
                mostrarInformacionCarta(fondoJugador);
            }
        });
    
        pnl.add(lblCarta);
        pnl.setComponentZOrder(lblCarta, 0);
        pnl.repaint();
    }
    
    public void mostrarInformacionCarta(String fondoJugador) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Información de la Carta");
        dialogo.setSize(400, 350); // Se amplía el tamaño del diálogo
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(null);
    
        JPanel panelContenido = new JPanel() {
            private Image fondo = new ImageIcon(getClass().getResource("/img/" + fondoJugador)).getImage();
    
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                int imgWidth = fondo.getWidth(this);
                int imgHeight = fondo.getHeight(this);
                
                double escala = Math.max((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);
                int newWidth = (int) (imgWidth * escala);
                int newHeight = (int) (imgHeight * escala);
                
                int x = (panelWidth - newWidth) / 2;
                int y = (panelHeight - newHeight) / 2;
                
                g.drawImage(fondo, x, y, newWidth, newHeight, this);
            }
        };
        
        panelContenido.setLayout(new BorderLayout());
    
        String nombreImagen = "/img/Carta" + indice + ".png";
        java.net.URL imgURL = getClass().getResource(nombreImagen);
        
        if (imgURL != null) {
            ImageIcon imagen = new ImageIcon(imgURL);
            Image img = imagen.getImage();
            Image imgEscalada = img.getScaledInstance(150, 210, Image.SCALE_SMOOTH); // Se amplía el tamaño de la carta
            JLabel lblImagen = new JLabel(new ImageIcon(imgEscalada));
            panelContenido.add(lblImagen, BorderLayout.CENTER);
        }
    
        JLabel lblInfo = new JLabel("<html><div style='text-align: center;'>Nombre: " + obtenerNombre() + "<br>Pinta: " + obtenerPinta() + "<br>Valor: " + obtenerValor()+ "<br><br>" + "</div></html>", SwingConstants.CENTER);
        lblInfo.setForeground(Color.WHITE);
        
        panelContenido.add(lblInfo, BorderLayout.SOUTH);
        dialogo.add(panelContenido);
    
        dialogo.setVisible(true);
    }
}