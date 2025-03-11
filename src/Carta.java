import java.util.Random;
import javax.swing.*;

public class Carta {
    private int indice;  // Identificador de la carta (1 a 52)

    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }

    public Carta(int indice) {
        this.indice = indice;
    }    

    public Pinta obtenerPinta() {
        return Pinta.values()[(indice - 1) / 13];
    }

    public NombreCarta obtenerNombre() {
        return NombreCarta.values()[(indice - 1) % 13];
    }

    public int obtenerValor() {
        int numero = (indice - 1) % 13 + 1;
        return (numero > 10) ? 10 : numero;
    }

    public int getIndice() {
        return indice;
    }

    public JLabel mostrarCartaComoLabel(int x, int y, boolean tapada) {
        String nombreImagen = tapada ? "/img/Tapada.jpg" : "/img/Carta" + indice + ".png";
        java.net.URL imgURL = getClass().getResource(nombreImagen);
    
        if (imgURL == null) {
            System.err.println("No se encontró la imagen: " + nombreImagen);
            return new JLabel("[Imagen no encontrada]");
        }
    
        ImageIcon imagen = new ImageIcon(imgURL);
        JLabel lblCarta = new JLabel(imagen);
        lblCarta.setBounds(x, y, 60, 84);
        return lblCarta;
    }
}
