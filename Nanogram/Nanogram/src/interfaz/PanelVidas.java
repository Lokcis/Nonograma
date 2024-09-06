package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author asus
 */
public class PanelVidas extends JPanel {

    //Atributos
    private JLabel[] lblVida;
    private ImageIcon icon;

    //Constructor
    public PanelVidas() {
        setLayout(new GridLayout());
        lblVida = new JLabel[3];
        setBackground(Color.WHITE);

        //Instancia de clase
        icon = new ImageIcon(getClass().getResource("/resources/image/vida64.png"));

        for (int i = 0; i < 3; i++) {
            lblVida[i] = new JLabel(icon);
            lblVida[i].setIcon(icon);
            lblVida[i].setBounds(1, 1, 64, 64);
            add(lblVida[i]);
        }

    }

    public void actualizarVidas(int vidasRestantes) {
        for (int i = 0; i < lblVida.length; i++) {
            if (i < vidasRestantes) {
                lblVida[i].setVisible(true); // Show the life icon
            } else {
                lblVida[i].setIcon(new ImageIcon(getClass().getResource("/resources/image/sinvida64.png")));
            }
        }

    }

    public void reiniciarVidas() {
        actualizarVidas(3); // Restablece las vidas a 3
        for (int i = 0; i < lblVida.length; i++) {
            lblVida[i].setIcon(icon); // Restaurar íconos de vida completa
        }
    }

}
