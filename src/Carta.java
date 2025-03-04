import java.util.Random;
import javax.swing.*;

public class Carta {
    private int indice;  // Identificador de la carta (1 a 52)

    public Carta(Random r) {
        // Se genera aleatoriamente el número de la carta (1 a 52)
        indice = r.nextInt(52) + 1;
    }

    public String obtenerPinta() {
        // Determina la pinta según el índice
        if (indice <= 13) return "Trébol";
        else if (indice <= 26) return "Pica";
        else if (indice <= 39) return "Corazón";
        else return "Diamante";
    }

    public String obtenerNombre() {
        // Determina el nombre de la carta según el índice
        String[] nombres = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int numero = (indice - 1) % 13;  // Obtener la posición en el array
        return nombres[numero];
    }

    public int obtenerValor() {
        // Asigna valores a las cartas (A, J, Q y K valen 10, el resto su número)
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
        pnl.add(lblCarta);

            // Mover la carta al frente
        pnl.setComponentZOrder(lblCarta, 0);

        pnl.repaint();
    }
    
}
