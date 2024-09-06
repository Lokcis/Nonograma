package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mundo.LoadNono;

/**
 *
 * @author asus
 */
public class PanelPistasHorizontal extends JPanel {

    //Atributos
    private JPanel pnlPistasHorizontal;
    private JLabel lblPistasHorizontal;
    private String pistasHorizontales[][];

    public PanelPistasHorizontal(LoadNono loadNono) {
        setLayout(new GridLayout(1, 10, 2, 2));

        //Instancia atributos de la clase
        pistasHorizontales = loadNono.getRows();

        //Crear y agregar un arreglo de paneles al panel principal
        for (int i = 0; i < 10; i++) {
            pnlPistasHorizontal = new JPanel();
            pnlPistasHorizontal.setLayout(new GridLayout(5, 1));
            pnlPistasHorizontal.setBackground(new Color(234, 238, 249));

            // Aplicar el borde redondeado con un radio de 8 píxeles y color negro
            pnlPistasHorizontal.setBorder(new RoundedBorder(8, Color.BLACK));

            // Crear y agregar 5 JLabel a cada panel interno
            for (int j = 0; j < 5; j++) {
                lblPistasHorizontal = new JLabel(pistasHorizontales[j][i]);
                lblPistasHorizontal.setHorizontalAlignment(JLabel.CENTER);
                lblPistasHorizontal.setVerticalAlignment(JLabel.CENTER);
                pnlPistasHorizontal.add(lblPistasHorizontal);
            }

            add(pnlPistasHorizontal);
        }
    }

    public void actualizarPistas(LoadNono loadNono) {
        pistasHorizontales = loadNono.getRows();
        for (int i = 0; i < 10; i++) {
            JPanel pnlPista = (JPanel) getComponent(i);
            for (int j = 0; j < 5; j++) {
                JLabel lblPista = (JLabel) pnlPista.getComponent(j);
                lblPista.setText(pistasHorizontales[j][i]);
            }
        }
    }

}
